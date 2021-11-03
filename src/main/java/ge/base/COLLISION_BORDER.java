package ge.base;

import ge.geException.geException;

import java.util.List;

public class COLLISION_BORDER {

    private List<POINT> points;
    private POINT center;

    public COLLISION_BORDER(List<POINT> points, POINT center) {
        this.points = points;
        this.center = center;
    }

    public boolean contact(COLLISION_BORDER other) {
        for (int i = 1; i < points.size(); i++) {
            if (other.contact(new LINE(points.get(i - 1), points.get(i)))) {
                return true;
            }
        }
        return false;
    }

    public boolean contact(LINE line) {
        for (int i = 1; i < points.size(); i++) {
            try {
                if (line.crossTest(new LINE(points.get(i - 1), points.get(i)))) {
                    return true;
                }
            } catch (geException ignore) { // ignored parallel exception
            }
        }
        return false;
    }

}
