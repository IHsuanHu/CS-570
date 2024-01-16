// I-Hsuan Hu
public class Complexity {
    //time complexity O(n)
    public static void method0(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("operation " + i);
            count++;
        }
        System.out.println("Number of operation: " + count + " times.");
    }
    // time complexity O(n^2)
    public static void method1(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Operation " + i + j);
                count++;
            }
        }
        System.out.println("Number of operation: " + count + " times.");
    }
    // time complexity O(n^3)
    public static void method2(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("Operation " + i + j + k);
                    count++;
                }
            }
        }
        System.out.println("Number of operation; " + count + " times.");
    }
    // time complexity O(logn)
    public static void method3(int n) {
        int count = 0;
        for (int i = n; i > 0; i /= 2) {
            System.out.println("Operation " + i);
            count += 1;
        }
        System.out.println("Number of operation: " + count + " times.");
    }
    // time complexity O(nlogn)
    public static void method4(int n) {
        int count = 0;
        for (int i = n; i > 0; i /= 2) {
            for (int j = 0; j < n; j++){
                System.out.println("Operation "+ j + " " + i);
                count++;
            }
        }
        System.out.println("Number of operation: " + count + " times.");
    }
    // time complexity O(loglogn)
    public static void method5(int n) {
        int count = 0;
        for (long i = 1; i < n; i = i*i ) {
            if (i < 2) {
                System.out.println("Operation " + i);
                count += 1;
                i++;
            }
            System.out.println("Operation " + i);
            count += 1;
            }
        
        System.out.println("Number of operation " + count + " times.");
        }
    // optional time complexity O(2^n)
    public static void method6(int n) {
        recursion(n);
        System.out.println("Number of operation " + count + " times.");
    }
    static int count = 0;
    public static void recursion(int j) {
        if (j == 0) {
            System.out.println("Operation " + j);
            count += 1;
            return;
           }
        count += 1;
        System.out.println("Operation " + j);
        recursion(j-1);
        recursion(j-1);
    }
    public static void main(String[] args) {
        method0(64);
        method1(64);
        method2(16);
        method3(256);
        method4(256);
        method5(100000);
        method6(5);
    }
}