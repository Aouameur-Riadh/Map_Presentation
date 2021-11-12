public class Segment {
   private MyPoint m2;
     private MyPoint m1;

    public Segment(MyPoint m2, MyPoint m1) {
        this.m2 = m2;
        this.m1 = m1;
    }

    public MyPoint getM2() {
        return m2;
    }

    public void setM2(MyPoint m2) {
        this.m2 = m2;
    }

    public MyPoint getM1() {
        return m1;
    }

    public void setM1(MyPoint m1) {
        this.m1 = m1;
    }

    public boolean isEQ(Segment segment){
        if (
                (this.getM1().getX() == segment.getM1().getX()&&
                this.getM1().getY() == segment.getM1().getY()&&
                this.getM2().getX() == segment.getM2().getX()&&
                this.getM2().getY() == segment.getM2().getY())||
                        this.getM1().getX() == segment.getM2().getX()&&
                        this.getM1().getY() == segment.getM2().getY()&&
                        this.getM2().getX() == segment.getM1().getX()&&
                        this.getM2().getX() == segment.getM1().getX()

        ){
            return true;
        }


        return false;
    }


}
