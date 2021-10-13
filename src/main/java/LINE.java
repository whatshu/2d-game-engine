public class LINE {

    public POINT start;
    public POINT end;

    public LINE(POINT s, POINT e) {
        start = s;
        end = e;
    }

    public boolean crossTest(LINE otherLine) {
        return false;// todo finish method
    }

    public static boolean crossTest(POINT line1Start, POINT line1End, POINT line2Start, POINT line2End) {
        return false;// todo finish method
    }

}
