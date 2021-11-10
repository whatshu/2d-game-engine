package ge.base;

import ge.geException.geException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

//    public boolean contact(COLLISION_BORDER other) {
//        for (int i = 1; i < points.size(); i++) {
//            if (other.contact(new LINE(points.get(i - 1), points.get(i)))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean contact(LINE line) {
//        for (int i = 1; i < points.size(); i++) {
//            try {
//                if (line.crossTest(new LINE(points.get(i - 1), points.get(i)))) {
//                    return true;
//                }
//            } catch (geException ignore) { // todo ignored parallel exception
//            }
//        }
//        return false;
//    }

}
