import java.util.Random;
import java.util.Stack;

// I-Hsuan Hu
//CWID: 20010728
public class Treap<E extends Comparable<E>> {
  private Random priorityGenerator;
  private Node<E> root;

  public Treap(){
    this.priorityGenerator = new Random();
    this.root = null;
  }
  public Treap(long seed){
    this.priorityGenerator = new Random(seed);
    this.root = null;
  }
  public boolean add(E key){
    if(key == null) {
      System.out.println("invalid key");
      return false;
    }else{
      int priority = priorityGenerator.nextInt();
      return add(key,priority);
    }
  }
  public boolean add(E key, int priority) {
    // Check if the key is valid
    if (key == null) {
        System.out.println("Invalid key");
        return false;
    }
    // Create a new node with the given key and priority
    Node<E> newNode = new Node<>(key, priority);

    // If the tree is empty, set the root to the new node
    if (this.root == null) {
        this.root = newNode;
        return true;
    }
    // Traverse the tree to find the correct position for the new node
    Node<E> cur = this.root;
    Node<E> parent = null;
    Stack<Node<E>> stack = new Stack<>();
    while (cur != null) {
        parent = cur;
        stack.push(parent);
        if (cur.data.compareTo(key) > 0) { // If the key is less than the current node, go left
            cur = cur.left;
        } else if (cur.data.compareTo(key) < 0) { // If the key is greater than the current node, go right
            cur = cur.right;
        } else { // If the key is equal to the current node, return false (don't add the node again)
            return false;
        }
    }
    // Add the new node to the tree at the correct position
    if (key.compareTo(parent.data) > 0) {
        parent.right = newNode;
    } else {
        parent.left = newNode;
    }
    // Reheap the tree from the new node to the root
    reheap(newNode, stack);
    return true;
}
  //re-order heap
  private void reheap(Node<E>node, Stack<Node<E>> stack){
    // if the stack is empty, set the root to the new node
    if(stack.isEmpty()){
      this.root = node;
    // otherwise, check if the new node's priority is greater than its parent node's priority
    }else if(stack.peek().priority > node.priority){
      return; // if so, the max heap property is already satisfied, so return
    }else{
      // if the new node's priority is less than its parent node's priority, perform rotations to restore the max heap property
      if(stack.peek().right == node){
        Node<E> tempNode = stack.pop();
        tempNode.rotateLeft();
        if(!stack.isEmpty()){
          if(stack.peek().right == tempNode){
            stack.peek().right = node;
          }else{
            stack.peek().left = node;
          }
        }
      }else{
        Node<E> tempNode1 = stack.pop();
        tempNode1.rotateRight();
        if(!stack.isEmpty()){
          if(stack.peek().right == tempNode1){
            stack.peek().right = node;
          }else{
            stack.peek().left = node;
          }
        }
      }
      // recursively call reheap() on the new node and its ancestors to continue restoring the max heap property
      reheap(node,stack);
    }
}
  
  public boolean delete(E key) {
    if (find(key) == false || root == null) { // check if key exists in the tree and if the tree is not empty
        return false; // if key doesn't exist, return false
    }

    Node<E> curr = root; // start from the root node
    Node<E> parent = null; // initialize parent node to null

    // traverse the tree until the key is found or the end is reached
    while (curr != null && !curr.data.equals(key)) {
        parent = curr; // store the parent of the current node
        curr = key.compareTo(curr.data) < 0 ? curr.left : curr.right; // move left or right depending on the key's value
    }

    if (curr == null) return false; // if key not found, return false

    // delete the node based on the number of children it has
    if (curr.left == null) { // if node has no left child
        if (curr == root) root = curr.right; // if node is root, set the right child as new root
        else if (curr == parent.left) parent.left = curr.right; // if node is left child of parent, set right child as new left child of parent
        else parent.right = curr.right; // if node is right child of parent, set right child as new right child of parent
    } else if (curr.right == null) { // if node has no right child
        if (curr == root) root = curr.left; // if node is root, set the left child as new root
        else if (curr == parent.left) parent.left = curr.left; // if node is left child of parent, set left child as new left child of parent
        else parent.right = curr.left; // if node is right child of parent, set left child as new right child of parent
    } else { // if node has two children
        Node<E> succParent = curr; // initialize the successor's parent to the current node
        Node<E> succ = curr.right; // initialize the successor to the right child of the current node

        // find the leftmost node in the right subtree of the current node
        while (succ.left != null) {
            succParent = succ; // store the parent of the successor
            succ = succ.left; // move to the left child
        }

        // replace the current node with the successor
        if (succParent != curr) {
            succParent.left = succ.right; // if successor has a right child, set it as new left child of successor's parent
            succ.right = curr.right; // set the current node's right child as the successor's right child
        }

        if (curr == root) root = succ; // if current node is root, set the successor as new root
        else if (curr == parent.left) parent.left = succ; // if current node is left child of parent, set the successor as new left child of parent
        else parent.right = succ; // if current node is right child of parent, set the successor as new right child of parent

        succ.left = curr.left; // set the current node's left child as the successor's left child
    }

    return true; // return true to indicate successful deletion
  }
  // use recursion to find the node
  private boolean find(Node<E>root, E key){
    if(root == null){
      return false;
    }  
    if(key.compareTo(root.data) == 0){
      return true;
    }else if(key.compareTo(root.data) < 0){
      return find(root.left, key);
    }else{
      return find(root.right, key);
    }
  }
  public boolean find(E key){
    if(key == null){
      throw new NullPointerException("invalid value");
    }
    return find(root,key);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    preOrderTraverse(root, 1, sb);
    return sb.toString();
}

private void preOrderTraverse(Node<E> root, int depth, StringBuilder sb) {
    for (int i = 1; i < depth; i++) {
        sb.append(" ");
    }
    if (root == null) {
        sb.append("null\n");
    } else {
        sb.append(root.toString() + "\n");
        preOrderTraverse(root.left, depth + 1, sb);
        preOrderTraverse(root.right, depth + 1, sb);
    }
}


  private class Node<E>{
    public E data;
    public int priority;
    public Node<E> left;
    public Node<E> right;

    public Node(E data, int priority){
      if(data == null){
        throw new NullPointerException("Invalid data");
      }
      this.left = null;
      this.right = null;
      this.data = data;
      this.priority =priority;
    }
    Node<E> rotateRight(){
      if(this.left == null){
        throw new NullPointerException("Unable to rotate to right");
      }
      Node<E> root = this.left;
      this.left = root.right;
      root.right = this;
      return root;
    }
    Node<E> rotateLeft(){
      if(this.right == null){
        throw new NullPointerException("Unable to rotate to left");
      }
      Node<E> root = this.right;
      this.right = root.left;
      root.left = this;
      return root;
    }

  public String toString() {
    return "Node{data " + data +", priority " + priority +'}';
  }
}
    public static void main(String[] args) {
      Treap testTree = new Treap<Integer>();
      testTree.add(4, 19);
      testTree.add(2, 31);
      testTree.add(6, 70);
      testTree.add(1, 84);
      testTree.add(3, 12);
      testTree.add(5, 83);
      testTree.add(7, 26);
      System.out.println(testTree.toString());

      //Test Find
      System.out.println("Now let's find node 5: " + testTree.find(5));
      System.out.println("Now let's find node 10: " + testTree.find(10));


      //Test delete
      System.out.println("Now let's delete node 10: " + testTree.delete(10));
      System.out.println("Now let's delete node 1: " + testTree.delete(1));
      System.out.println(testTree.toString());
      System.out.println();
      System.out.println("Now let's delete node 7: " + testTree.delete(7));
      System.out.println(testTree.toString());
    }
}