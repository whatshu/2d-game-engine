package ge.util;

public class compare {

    public final static float FLOAT_THRESHOLD = (float) 1e-10;

    public static boolean floatEqual(float num1, float num2) {
        return Math.abs(num1 - num2) < FLOAT_THRESHOLD;
    }

}
