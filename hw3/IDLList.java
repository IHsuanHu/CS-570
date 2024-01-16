import java.util.ArrayList;
// I-Hsuan Hu
public class IDLList<E> {
    //class node
    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        //constructor of nodes
        public Node(E elem) {
            try{
            this.data = elem;
            this.next = null;
            this.prev = null;
            } catch(Exception e) {
                System.out.println("Elem is invalid");
            }
        }
        //constructor of node
        public Node(E elem, Node<E> prev, Node<E> next){
            try{
            this.data = elem;
            this.next = next;
            this.prev = prev;
        } catch(Exception e) {
            System.out.println("Elem is invalid");
        }
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;
    //an array list of references to nodes
    private ArrayList<Node<E>> indices;

    public IDLList() {
        try{
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
    } catch(Exception e) {
        System.out.println("Elem is invalid");
    }
    }
    public boolean add (E elem) {
        Node<E> newNode = new Node<E> (elem);
        //edge case
        try { if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
            } else {
                newNode.next = this.head;
                this.head.prev = newNode;
                this.head = newNode;
            }
            //add at head
            this.indices.add(0, newNode);
            this.size += 1;
            return true;
        } catch (Exception e) {
            return false;
        }
        }
    public boolean add(int index, E elem) {
        Node<E> newNode = new Node<E> (elem);
        try { 
            // out of bound
            if (size < index) {
            return false;
        } 
        //edge case
        else if (index == 0){
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
            this.indices.add(0, newNode);
            this.size += 1;
            return true;
        } else { 
            this.indices.get(index-1).next = newNode;
            newNode.prev = this.indices.get(index-1);
            this.indices.get(index).prev = newNode;
            newNode.next = this.indices.get(index);
            this.indices.add(index, newNode);
            this.size += 1;
            return true;
        }} catch(Exception e) {
            return false;
        }
        }
    public boolean append (E elem) {
        Node<E> newNode = new Node<E> (elem);
        try { 
            //edge case
            if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
            this.size += 1;
            this.indices.add(0, newNode);
            return true;
        } 
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
            this.indices.add(this.size, newNode);
            this.size += 1;
            return true;
        } catch (Exception e) {
            return false;}
        }
    public E get(int index) {
        // out of bound
        if (index >= size || index < 0){
            return null;
        }
        return this.indices.get(index).data;
        }
    public E getHead() {
        //edge case
        if (this.size == 0) {
            return null;
        }
        return this.head.data;
        }
    public E getLast() {
        //edge case
        if (this.size == 0) {
            return null;
        }
        return this.tail.data;
        }
    public int size() {
        return this.size;
        }
    public E remove() {
        Node<E> delNode;
        Node<E> nextNode;
        //edge case
        if (size == 0) {
            return null;
        }
        //edge case
        if (size == 1) {
            delNode = this.indices.get(0);
            this.indices.remove(0);
            this.head = null;
            this.tail = null;
            size -= 1;            
            return delNode.data;
        }
        delNode = this.indices.get(0);
        nextNode = this.indices.get(0);
        this.indices.remove(0);
        size -= 1;
        nextNode.prev = null;
        this.head = nextNode;
        return delNode.data;
        }
    public E removeLast() {
            Node<E> delNode;
            Node<E> preNode;
            //edge case
            if (size == 0) {
                return null;
            }
            //edge case
            if (size == 1) {
                delNode = this.indices.get(0);
                this.indices.remove(0);
                this.size -= 1;
                this.head = null;
                this.tail = null;
                return delNode.data;
            }
            delNode = this.indices.get(size-1);
            preNode = this.indices.get(size-2);
            this.indices.remove(size-1);
            this.size -= 1;
            preNode.next = null;
            this.tail = preNode;
            return delNode.data;
        }
    public E removeAt(int index) {
        Node<E> delNode;
        Node<E> preNode;
        Node<E> nextNode;
        //out of bound
        if (index >= size) {
            return null;
        }
        // if head use remove()
        if (index == 0) {
            remove();
        }
        //if tail use removeLast()
        if (index == size-1) {
            removeLast();
        }
        delNode = this.indices.get(index);
        preNode = this.indices.get(index-1);
        nextNode = this.indices.get(index+1);
        this.indices.remove(index);
        size -= 1;
        preNode.next = nextNode;
        nextNode.prev = preNode;
        return delNode.data;
        }
    public boolean remove(E elem) {
        Node<E> preNode;
        Node<E> nextNode;
        Node<E> removeNode = new Node<E> (elem);
        //edge case
        if (size == 0) {
            return false;
        }
        //edge case
        if (size == 1) {
            if(this.head.data != removeNode.data) {
                return false;
            }
            this.indices.remove(0);
            size -= 1;
            this.head = null;
            this.tail = null;
            return true;
        }
        if (size == 2) {
            if (this.head.data == removeNode.data) {
                this.indices.remove(0);
                size -= 1;
                this.tail.prev = null;
                this.head = this.tail;
                return true;
            } if (this.tail.data == removeNode.data) {
                this.indices.remove(1);
                size -= 1;
                this.head.next = null;
                this.tail = this.head;
                return true;
            }
            return false;
        }
        for (int i = 0; i< size; i++) {
            if (this.indices.get(i).data == removeNode.data) {
                //edge case
                if(removeNode.data == this.tail.data) {
                    preNode = this.indices.get(i-1);
                    this.indices.remove(i);
                    size -= 1;
                    preNode.next = null;
                    this.tail = preNode;
                    return true;
                }
                //edge case
                if (removeNode.data == this.head.data) {
                    nextNode = this.indices.get(i+1);
                    this.indices.remove(i);
                    size -= 1;
                    nextNode.prev = null;
                    this.head = nextNode;
                    return true;
                }
                preNode = this.indices.get(i-1);
                nextNode = this.indices.get(i+1);
                this.indices.remove(i);
                size -= 1;
                preNode.next = nextNode;
                nextNode.prev = preNode;
                return true;
            }
        }
    return false;}
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += this.indices.get(i).data + " ";
        }
        // empty check
        if (result == "") {
            return "empty";
        }
        return result;
    }
}  