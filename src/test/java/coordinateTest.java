import ge.base.COORDINATE;
import ge.base.POINT;

public class coordinateTest {

    public static void main(String[] args) {
        COORDINATE c1 = new COORDINATE(COORDINATE.COORDINATE_TYPE.CENTER, new POINT(0.5f, 0.5f), 1f, 1f, 1, 1);
        POINT      p  = c1.getStandardPoint(0, 1);
        System.out.println(p.x + " " + p.y);
    }

}
