import javafx.beans.property.SimpleDoubleProperty;

public class MyPoint {

    SimpleDoubleProperty x;
    SimpleDoubleProperty y;

    public MyPoint(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    public MyPoint() {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
    }

    public double getY() {
        return y.get();
    }

    public SimpleDoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getX() {
        return x.get();
    }

    public SimpleDoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x.get() +
                ", y=" + y.get() +
                '}';
    }


    public boolean isEQ(MyPoint point){
        if (this.getX() == point.getX()&&this.getY() == point.getY()){
            return true;
        }


        return false;
    }

}
