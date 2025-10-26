package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }

    @Transactional
    public Order createOrder(String customerId, List<OrderItemRequest> orderItems) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Customer customer = optionalCustomer.get();
        Order order = new Order();
        order.setCustomer(customer);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequest itemRequest : orderItems) {
            Optional<Product> optionalProduct = productRepository.findByProductId(itemRequest.getProductId());
            if (!optionalProduct.isPresent()) {
                throw new RuntimeException("Product not found: " + itemRequest.getProductId());
            }

            Product product = optionalProduct.get();
            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName() + 
                                         ". Available: " + product.getQuantity() + 
                                         ", Requested: " + itemRequest.getQuantity());
            }

            // Update product quantity
            product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            // Calculate item total
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            // Create order item
            OrderItem orderItem = new OrderItem(product, itemRequest.getQuantity(), product.getPrice());
            items.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        
        // Add order items
        for (OrderItem item : items) {
            order.addOrderItem(item);
        }

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(String orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found with ID: " + orderId);
    }

    @Transactional
    public boolean cancelOrder(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getStatus() == OrderStatus.PENDING) {
                // Restore product quantities
                for (OrderItem item : order.getOrderItems()) {
                    Product product = item.getProduct();
                    product.setQuantity(product.getQuantity() + item.getQuantity());
                    productRepository.save(product);
                }
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
                return true;
            } else {
                throw new RuntimeException("Cannot cancel order with status: " + order.getStatus());
            }
        }
        return false;
    }

    public static class OrderItemRequest {
        private String productId;
        private Integer quantity;

        // Getters and Setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}