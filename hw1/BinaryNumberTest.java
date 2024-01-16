public class BinaryNumberTest {
    public static void main(String[] args) {
        BinaryNumber a = new BinaryNumber("11011");
        BinaryNumber b = new BinaryNumber("1011");
        System.out.println(a.toString());
        a.shiftR(2);
        System.out.println(a.toString());
        a.add(b);
        System.out.println(a.toString());
        System.out.println(a.getLength());
        System.out.println(a.getDigit(-1));
        System.out.println(a.getDigit(8));
        System.out.println(a.getDigit(3));
        System.out.println(a.toDecimal());
        System.out.println(a.getLength());

        
    }
}
