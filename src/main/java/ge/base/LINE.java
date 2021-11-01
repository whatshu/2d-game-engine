package ge.base;

import ge.geException.geException;
import ge.geException.geParallelException;
import ge.geException.geVerticalLineException;
import ge.util.compare;

public class LINE {
    public POINT start;
    public POINT end;

    public LINE(POINT s, POINT e) {
        start = s;
        end = e;
    }

    public float slope() throws geException {
        if (compare.floatEqual(start.x, end.x)) throw new geVerticalLineException();
        return (start.y - end.y) / (start.x - end.x);
    }

    public boolean crossTest(LINE otherLine) throws geException {
        if (compare.floatEqual(this.slope(), otherLine.slope())) throw new geParallelException();

        float a1 = end.y - start.y;
        float b1 = start.x - end.x;
        float c1 = end.x * start.y - start.x * end.y;
        float a2 = otherLine.end.y - otherLine.start.y;
        float b2 = otherLine.start.x - otherLine.end.x;
        float c2 = otherLine.end.x * otherLine.start.y - otherLine.start.x * otherLine.end.y;
        float x0 = (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1);
        return Math.min(start.x, end.x) < x0 && x0 < Math.max(start.x, end.x) &&
                Math.min(otherLine.start.x, otherLine.end.x) < x0 && x0 < Math.max(otherLine.start.x, otherLine.end.x);
    }

    public static boolean crossTest(POINT line1Start, POINT line1End, POINT line2Start, POINT line2End) throws geException {
        if (compare.floatEqual(line1Start.x, line1End.x)) throw new geVerticalLineException();
        float slope1 = (line1Start.y - line1End.y) / (line1Start.x - line1End.x);

        if (compare.floatEqual(line2Start.x, line2End.x)) throw new geVerticalLineException();
        float slope2 = (line2Start.y - line2End.y) / (line2Start.x - line2End.x);

        if (compare.floatEqual(slope1, slope2)) throw new geParallelException();

        float x0 = ((line2End.x * line2Start.y - line2Start.x * line2End.y) * (line1Start.x - line1End.x) - (line1End.x * line1Start.y - line1Start.x * line1End.y) * (line2Start.x - line2End.x)) / ((line1End.y - line1Start.y) * (line2Start.x - line2End.x) - (line2End.y - line2Start.y) * (line1Start.x - line1End.x));
        return Math.min(line1Start.x, line1End.x) < x0 && x0 < Math.max(line1Start.x, line1End.x) &&
                Math.min(line2Start.x, line2End.x) < x0 && x0 < Math.max(line2Start.x, line2End.x);
    }
}
