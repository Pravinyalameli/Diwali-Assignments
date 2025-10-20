package Day2;

import java.util.Scanner;

class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class circularlinklist {
    private Node head;
    private Node tail;
    
    public circularlinklist() {
        head = null;
        tail = null;
    }
    
    // a) Insert at nth position
    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head;
            System.out.println("Inserted " + data + " as first node.");
            return;
        }
        
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            tail.next = head;
            System.out.println("Inserted " + data + " at position 1.");
            return;
        }
        
        Node current = head;
        int count = 1;
        
        while (count < position - 1 && current.next != head) {
            current = current.next;
            count++;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        
        if (newNode.next == head) {
            tail = newNode;
        }
        
        System.out.println("Inserted " + data + " at position " + position + ".");
    }
    
    // b) Delete by data
    public void deleteByData(int data) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        
        if (head.data == data) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            System.out.println("Deleted " + data);
            return;
        }
        
        Node current = head;
        Node prev = tail;
        
        do {
            prev = current;
            current = current.next;
            
            if (current.data == data) {
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                System.out.println("Deleted " + data);
                return;
            }
        } while (current != head);
        
        System.out.println(data + " not found.");
    }
    
    // c) Modify node
    public void modifyNode(int oldData, int newData) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        
        Node current = head;
        
        do {
            if (current.data == oldData) {
                current.data = newData;
                System.out.println("Modified " + oldData + " to " + newData);
                return;
            }
            current = current.next;
        } while (current != head);
        
        System.out.println(oldData + " not found.");
    }
    
    // d) Display list
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        
        Node current = head;
        System.out.print("List: ");
        
        do {
            System.out.print(current.data);
            if (current.next != head) {
                System.out.print(" -> ");
            }
            current = current.next;
        } while (current != head);
        
        System.out.println(" -> (head: " + head.data + ")");
    }
    
    // Get length
    public int getLength() {
        if (head == null) return 0;
        
        int count = 0;
        Node current = head;
        
        do {
            count++;
            current = current.next;
        } while (current != head);
        
        return count;
    }
    
    // Main method
    public static void main(String[] args) {
        circularlinklist list = new circularlinklist();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Insert  2. Delete  3. Modify  4. Display  5. Length  6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Data: ");
                    int data = sc.nextInt();
                    System.out.print("Position: ");
                    int pos = sc.nextInt();
                    list.insertAtPosition(data, pos);
                    break;
                    
                case 2:
                    System.out.print("Data to delete: ");
                    int delData = sc.nextInt();
                    list.deleteByData(delData);
                    break;
                    
                case 3:
                    System.out.print("Old data: ");
                    int old = sc.nextInt();
                    System.out.print("New data: ");
                    int newD = sc.nextInt();
                    list.modifyNode(old, newD);
                    break;
                    
                case 4:
                    list.display();
                    break;
                    
                case 5:
                    System.out.println("Length: " + list.getLength());
                    break;
                    
                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}