package ge.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class COLLISION_BORDER {

    private List<POINT> points;

    public static COLLISION_BORDER genDefaultCollisionBorder() {
        return new COLLISION_BORDER(new ArrayList<>(Arrays.asList(new POINT(0, 0), new POINT(1, 0), new POINT(1, 1), new POINT(0, 1))));
    }

    public COLLISION_BORDER(List<POINT> points) {
        this.points = points;
    }

    public List<POINT> getPoints(){
        return points;
    }

}
