// I-Hsuan Hu
public class IDLListTest {
    public static void main(String[] args) {
        IDLList<String> idlList = new IDLList<>();
        System.out.println(idlList.toString()); //return empty
        System.out.println(idlList.getHead()); //return null
        System.out.println(idlList.getLast());//return null
        System.out.println();
        System.out.println(idlList.add("eighteen")); //add the first element;
        System.out.println("The list is " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println(idlList.add(0,"tewnty"));// Add at the head
        System.out.println("The list is: " + idlList.toString() + "; The size is: " + idlList.size());
        System.out.println();
        System.out.println(idlList.add(1,"ten"));//add by index
        System.out.println(idlList.add(6,"nine")); // add out of bound
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println(idlList.append("sixty"));//Append at the last;
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println();
        System.out.println("At index 3 is: " + idlList.get(3));//Get element
        System.out.println("The head is: " + idlList.getHead()); //Get head
        System.out.println("The tail is: " + idlList.getLast());// Get tail
        System.out.println();
        System.out.println("Removing element at index is 2: " + idlList.removeAt(2));//remove by index
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size()); 
        System.out.println("Remove head: " + idlList.remove());//remove head
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println("Remove tail: " + idlList.removeLast());//remove tail
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println();
        System.out.println(idlList.add("five")); 
        System.out.println(idlList.add("five"));
        System.out.println(idlList.add("four"));
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println();
        System.out.println("Remove element two : " + idlList.remove("two"));//remvoe not exist element
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
        System.out.println("Remove first appear element five : " + idlList.remove("five"));//remove exist element
        System.out.println("The list is: " + idlList.toString()+ "; The size is: " + idlList.size());
    }
}
