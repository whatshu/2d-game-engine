import ge.base.COORDINATE;
import ge.base.POINT;

public class coordinateTest {

    public static void main(String[] args) {
        COORDINATE c1 = new COORDINATE(COORDINATE.COORDINATE_TYPE.CENTER, new POINT(0.25f, 0.25f), 0.5f, 0.5f, 2, 2);
        for (int i = 0; i < 4; i++) {
            POINT p = c1.fromStandardPoint(i / 4.0f, i / 4.0f);
            System.out.println(p.x + " " + p.y);
        }
    }

}
