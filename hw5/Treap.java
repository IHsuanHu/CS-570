import java.util.Random;

//Name: I-Hsuan Hu
//cwid: 20010728

public class Treap {
    private  Random priorityGenerator;
    private Node<E> root;
    
    private static class Node<E>{
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node (E data, int priority) {
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }
        public Node (E data, int priority, Node<E> left, Node<E> right) {
            this.data = data;
            this.priority = priority;
            this.left = left;
            this.right = right;
        }
        public Node<E> rotationRight(Node<E> root){
            if(root.left != null){
                if(root.left.right != null){
                    Node<E> temp = root.left.right;
                    root.left.right = null;
                    Node<E> newRoot = root.left;
                    root.left = temp;
                    newRoot.right = root;
                    return newRoot;
                }
            }
            System.out.println("Node not found.");
            return null;
        }
        public Node<E> rotationleft(Node<E> root) {
            if(root.right != null) {
                if(root.right.left != null) {
                    Node<E> temp = root.right.left;
                    root.right.left = null;
                    Node<E> newRoot = root.right;
                    root.right = temp;
                    newRoot.left = root;
                    return newRoot;
                }
            }
            System.out.println("Node not found.");
            return null;

        }
    }
    
}
