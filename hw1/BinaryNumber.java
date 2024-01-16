//I-Hsuan Hu, CWID: 20010728
// This script uses little-endian format
public class BinaryNumber{
    private int data[]; 
    private boolean overflow = false;
    public BinaryNumber(int length){
	  data = new int[length];
    }
    public BinaryNumber(String str){
       data = new int[str.length()]; 
       for (int i = 0; i < str.length(); i++) {
           data[i] = str.charAt(i) - '0'; 
           // char to int by subtracting with '0' based ASCII code
       }
    }
    public int getLength(){
        return data.length;
    }
 
    public int getDigit(int index){
         
        try {
            int d = data[index];
            return d;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is out of boundary!");
            return -1;
        }
        
         
        /** 
        if ((index < 0) || (index >= data.length)) {
            System.out.println("Index is out of bounds.");
		    return -1; 
        }
	    else {
		return data[index];
        }
        */
    }
    public int toDecimal(){
        int sum = 0;
        for(int i = data.length-1; i >= 0; i--){
            if (getDigit(i) == 1) {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    public void shiftR(int amount){
	  int[] temp = new int[getLength() + amount];  
        for (int i = amount; i < temp.length; i++) {
            temp[i] = data[i - amount];
        }
        data = temp;
    }
    private int[] reallocate(int size) {
        int[] temp = new int[data.length + size];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
        return data;
    }

    public void add(BinaryNumber aBinaryNumber) {
       clearOverflow();

       int diff = this.getLength() - aBinaryNumber.getLength();
       if (diff != 0) {
           System.out.println("Length of two binary number are not equal.");
       }
       // modofing length
       if (diff > 0) {
           aBinaryNumber.reallocate(aBinaryNumber.getLength() + diff);
       } else if (diff < 0) {
           reallocate(getLength() + (diff * -1));
       }
       int[] temp = new int[getLength()];
       for (int i = 0; i < getLength(); i++) {
           int n1 = data[i];
           int n2 = aBinaryNumber.data[i];
           if (n1 == 0 && n2 == 0 && !overflow) {
               temp[i] = 0;
               overflow = false;
           } else if ((n1 == 1 && n2 == 0 && !overflow) || (n1 == 0 && n2 == 1 && !overflow) || (n1 == 0 && n2 == 0 && overflow)) {
               temp[i] = 1;
               overflow = false;
           } else if ((n1 == 1 && n2 == 1 && !overflow) || (n1 == 0 && n2 == 1 && overflow) || (n1 == 1 && n2 == 0 && overflow)) {
               temp[i] = 0;
               overflow = true;
           } else {
               temp[i] = 1;
               overflow = true;
           }
       }
       data = temp;
       if (overflow) {
           reallocate(1);
           data[getLength() - 1] = 1;
       }
   }
    
    public String toString() {
	String output = new String();
      for (int i = 0; i < getLength(); i++) {
           output += data[i];
       }
	 if (overflow) {
           return "Overflow: " + output;
       } else {
           return output;
       } 
    }
	
    public void clearOverflow(){
	overflow = false;
    }
}