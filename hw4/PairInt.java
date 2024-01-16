/**
 * Name: I-Hsuan Hu
 * CWID: 20010728
 */
public class PairInt{
    private int x;
    private int y;

    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals(Object p) {
        if (p == null || getClass() != p.getClass()){
            return false;
        }
        PairInt pairInt = (PairInt) p;
        return x == pairInt.x && y == pairInt.y;
    }
    public String toString() {
        return "PairInt{ x=" + this.x + ", y=" + this.y +"}";
    }
    public PairInt copy() {
        PairInt newPairInt = new PairInt(this.x, this.y);
        return newPairInt;
    }
}