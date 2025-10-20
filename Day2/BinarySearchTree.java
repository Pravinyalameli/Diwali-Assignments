package Day2;

import java.util.*;

//Node class for BST
class TreeNode {
 int data;
 TreeNode left;
 TreeNode right;
 
 public TreeNode(int data) {
     this.data = data;
     this.left = null;
     this.right = null;
 }
}

//Binary Search Tree class
public class BinarySearchTree {
 private TreeNode root;
 
 public BinarySearchTree() {
     this.root = null;
 }
 
 // a) Add node
 public void add(int data) {
     root = addRecursive(root, data);
 }
 
 private TreeNode addRecursive(TreeNode current, int data) {
     if (current == null) {
         return new TreeNode(data);
     }
     
     if (data < current.data) {
         current.left = addRecursive(current.left, data);
     } else if (data > current.data) {
         current.right = addRecursive(current.right, data);
     }
     
     return current;
 }
 
 // b) Remove node
 public void remove(int data) {
     root = removeRecursive(root, data);
 }
 
 private TreeNode removeRecursive(TreeNode current, int data) {
     if (current == null) {
         return null;
     }
     
     if (data == current.data) {
         // Case 1: Node has no children
         if (current.left == null && current.right == null) {
             return null;
         }
         // Case 2: Node has one child
         else if (current.left == null) {
             return current.right;
         } else if (current.right == null) {
             return current.left;
         }
         // Case 3: Node has two children
         else {
             int smallestValue = findMinValue(current.right);
             current.data = smallestValue;
             current.right = removeRecursive(current.right, smallestValue);
             return current;
         }
     }
     
     if (data < current.data) {
         current.left = removeRecursive(current.left, data);
     } else {
         current.right = removeRecursive(current.right, data);
     }
     
     return current;
 }
 
 private int findMinValue(TreeNode root) {
     return root.left == null ? root.data : findMinValue(root.left);
 }
 
 // c) Display tree with traversals
 
 // In-order traversal (Left, Root, Right)
 public void inOrderTraversal() {
     System.out.print("In-order traversal: ");
     inOrderRecursive(root);
     System.out.println();
 }
 
 private void inOrderRecursive(TreeNode node) {
     if (node != null) {
         inOrderRecursive(node.left);
         System.out.print(node.data + " ");
         inOrderRecursive(node.right);
     }
 }
 
 // Pre-order traversal (Root, Left, Right)
 public void preOrderTraversal() {
     System.out.print("Pre-order traversal: ");
     preOrderRecursive(root);
     System.out.println();
 }
 
 private void preOrderRecursive(TreeNode node) {
     if (node != null) {
         System.out.print(node.data + " ");
         preOrderRecursive(node.left);
         preOrderRecursive(node.right);
     }
 }
 
 // Post-order traversal (Left, Right, Root)
 public void postOrderTraversal() {
     System.out.print("Post-order traversal: ");
     postOrderRecursive(root);
     System.out.println();
 }
 
 private void postOrderRecursive(TreeNode node) {
     if (node != null) {
         postOrderRecursive(node.left);
         postOrderRecursive(node.right);
         System.out.print(node.data + " ");
     }
 }
 
 // Display tree structure visually
 public void displayTree() {
     System.out.println("Binary Search Tree Structure:");
     if (root == null) {
         System.out.println("Tree is empty");
         return;
     }
     displayTreeRecursive(root, 0);
 }
 
 private void displayTreeRecursive(TreeNode node, int level) {
     if (node == null) {
         return;
     }
     
     displayTreeRecursive(node.right, level + 1);
     
     if (level != 0) {
         for (int i = 0; i < level - 1; i++) {
             System.out.print("│   ");
         }
         System.out.println("└── " + node.data);
     } else {
         System.out.println(node.data);
     }
     
     displayTreeRecursive(node.left, level + 1);
 }
 
 // Search for a node
 public boolean search(int data) {
     return searchRecursive(root, data);
 }
 
 private boolean searchRecursive(TreeNode current, int data) {
     if (current == null) {
         return false;
     }
     
     if (data == current.data) {
         return true;
     }
     
     return data < current.data 
         ? searchRecursive(current.left, data) 
         : searchRecursive(current.right, data);
 }
 
 // Get height of tree
 public int getHeight() {
     return calculateHeight(root);
 }
 
 private int calculateHeight(TreeNode node) {
     if (node == null) {
         return 0;
     }
     
     int leftHeight = calculateHeight(node.left);
     int rightHeight = calculateHeight(node.right);
     
     return Math.max(leftHeight, rightHeight) + 1;
 }
 
 // Get minimum value in tree
 public Integer getMin() {
     if (root == null) {
         return null;
     }
     return findMinNode(root).data;
 }
 
 private TreeNode findMinNode(TreeNode node) {
     while (node.left != null) {
         node = node.left;
     }
     return node;
 }
 
 // Get maximum value in tree
 public Integer getMax() {
     if (root == null) {
         return null;
     }
     return findMaxNode(root).data;
 }
 
 private TreeNode findMaxNode(TreeNode node) {
     while (node.right != null) {
         node = node.right;
     }
     return node;
 }
 
 // Check if tree is empty
 public boolean isEmpty() {
     return root == null;
 }
 
 // Main method for testing
 public static void main(String[] args) {
     BinarySearchTree bst = new BinarySearchTree();
     Scanner scanner = new Scanner(System.in);
     
     System.out.println("Binary Search Tree Implementation");
     System.out.println("=================================");
     
     while (true) {
         System.out.println("\nMenu:");
         System.out.println("1. Add node");
         System.out.println("2. Remove node");
         System.out.println("3. Display tree with traversals");
         System.out.println("4. Search node");
         System.out.println("5. Get tree height");
         System.out.println("6. Get min and max values");
         System.out.println("7. Exit");
         System.out.print("Enter your choice: ");
         
         int choice = scanner.nextInt();
         
         switch (choice) {
             case 1:
                 System.out.print("Enter value to add: ");
                 int addValue = scanner.nextInt();
                 bst.add(addValue);
                 System.out.println("Node " + addValue + " added successfully.");
                 break;
                 
             case 2:
                 System.out.print("Enter value to remove: ");
                 int removeValue = scanner.nextInt();
                 if (bst.search(removeValue)) {
                     bst.remove(removeValue);
                     System.out.println("Node " + removeValue + " removed successfully.");
                 } else {
                     System.out.println("Node " + removeValue + " not found in tree.");
                 }
                 break;
                 
             case 3:
                 if (bst.isEmpty()) {
                     System.out.println("Tree is empty.");
                 } else {
                     bst.displayTree();
                     System.out.println();
                     bst.inOrderTraversal();
                     bst.preOrderTraversal();
                     bst.postOrderTraversal();
                 }
                 break;
                 
             case 4:
                 System.out.print("Enter value to search: ");
                 int searchValue = scanner.nextInt();
                 boolean found = bst.search(searchValue);
                 System.out.println("Node " + searchValue + " is " + (found ? "found" : "not found") + " in tree.");
                 break;
                 
             case 5:
                 System.out.println("Tree height: " + bst.getHeight());
                 break;
                 
             case 6:
                 if (bst.isEmpty()) {
                     System.out.println("Tree is empty.");
                 } else {
                     System.out.println("Minimum value: " + bst.getMin());
                     System.out.println("Maximum value: " + bst.getMax());
                 }
                 break;
                 
             case 7:
                 System.out.println("Exiting...");
                 scanner.close();
                 return;
                 
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }
}