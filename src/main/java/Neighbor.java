public class Neighbor {
    Zone z1;
    Zone z2;
    MyPoint point;

    public Neighbor() {
        this.point = new MyPoint();
        this.z1= new Zone();
        this.z2= new Zone();
    }

    public Zone getZ1() {
        return z1;
    }

    public void setZ1(Zone z1) {
        this.z1 = z1;
    }

    public Zone getZ2() {
        return z2;
    }

    public void setZ2(Zone z2) {
        this.z2 = z2;
    }

    public MyPoint getPoint() {
        return point;
    }

    public void setPoint(MyPoint point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Neighbor{" +
                "z1=" + z1 +
                ", z2=" + z2 +
                ", point=" + point +
                '}';
    }
}
