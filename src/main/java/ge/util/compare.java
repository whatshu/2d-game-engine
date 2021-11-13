package ge.util;

import java.util.List;

public class compare {

    public final static float FLOAT_THRESHOLD = (float) 1e-10;

    public static boolean floatEqual(float num1, float num2) {
        return Math.abs(num1 - num2) < FLOAT_THRESHOLD;
    }

    public static boolean inOrder(float[] numbers) {
        float t = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (t > numbers[i]) {
                return false;
            }
            t = numbers[i];
        }
        return true;
    }

}
