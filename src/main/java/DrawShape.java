import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;

public class DrawShape {
    Path path;
    Zones zones;

    public Zones getZones() {
        return zones;
    }

    public void setZones(Zones zones) {
        this.zones = zones;
    }

    public DrawShape(Zones zones) {
        this.zones = zones;

         path= new Path();
    }

   public Path onDraw(){
        for (int i=0;i<zones.getObservableList().size();i++){

            for (int j= 1;j<zones.getObservableList().get(i).getObservableList().size();j++ ){
                double x= zones.getObservableList().get(i).getObservableList().get(j).getX();
                double y= zones.getObservableList().get(i).getObservableList().get(j).getY();
                if (j==1){
                    MoveTo moveTo = new MoveTo(x,y);
                    path.getElements().add(moveTo);
                    LineTo lineTo =new LineTo(x,y);
                    path.getElements().add(lineTo);
                }
                else {
                    LineTo lineTo =new LineTo(x,y);
                    path.getElements().add(lineTo);
                }
            }
        }
       return path;
    }


    public void NbSegment(PannableCanvas pannableCanvas){
        ArrayList <MyPoint>filterArrayList = new ArrayList<>();
        ObservableList <MyPoint> allPoints = zones.getAllPoints();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L","M", "N", "O", "P", "K", "R", "S", "T", "V", "W", "X", "Y", "Z"};


        for (int i=0;i<allPoints.size();i++){

                boolean isFrequent = false;
                for (int j=0;j<filterArrayList.size();j++){
                    if(allPoints.get(i).getX()==filterArrayList.get(j).getX()&&
                            allPoints.get(i).getY()==filterArrayList.get(j).getY()){
                        isFrequent=true;
                    }
                }
                if (!isFrequent) {
                    filterArrayList.add(allPoints.get(i));
                     double x=allPoints.get(i).getX();
                     double y=allPoints.get(i).getY();
                    Text text = new Text(letters[filterArrayList.size()-1]+"( "+x+" , "+y+" )");
                    text.setX(x);
                    text.setY(y);
                    pannableCanvas.getChildren().add(text);
                }
            }
        }






    public int NbSegment(){
        ArrayList <Segment>filterArrayList = new ArrayList<>();
        ObservableList <Segment> allSeg = zones.getAllSeg();

        for (int i=0;i<allSeg.size();i++){
            boolean isFrequent = false;
            for (int j=0;j<filterArrayList.size();j++){
                if(allSeg.get(i).isEQ(filterArrayList.get(j))){
                        isFrequent=true;
                }
            }
            if (!isFrequent) {
                filterArrayList.add(allSeg.get(i));
            }
        }
        return filterArrayList.size();
    }

    public void onDetectErrors(MyPoint a,MyPoint b,MyPoint c ,MyPoint d ){
        double m1 = (b.getY()-a.getY())/(b.getX()-a.getX());
        double m2 = (d.getY()-c.getY())/(d.getX()-c.getX());
        if (m1 != m2) {
            double b1 = a.getY() - (m1 * a.getX());
            double b2 = c.getY() - (m2 * c.getX());
            double xo = (b1 - b2) / (m2 - m1);
            System.out.println("M1 = " + m1);
            System.out.println("M2 = " + m2);
            System.out.println("B1 = " + b1);
            System.out.println("B2 = " + b2);
            System.out.println("Xo = " + xo);
        }



    }
    public void onDetectErrors(MyPoint a,MyPoint b,MyPoint c ,MyPoint d,PannableCanvas pannableCanvas ){

            MyPoint o = new MyPoint();
        double m1 = (b.getY()-a.getY())/(b.getX()-a.getX());
        double m2 = (d.getY()-c.getY())/(d.getX()-c.getX());
        System.out.println("M1 = "+m1);
        System.out.println("M2 = "+m2);

        if (m1 != m2){
            if ((m1==Double.NEGATIVE_INFINITY ||m1==Double.POSITIVE_INFINITY)&&(m2==0)) {
                o.setX(a.getX());
                o.setY(c.getY());
                System.out.println(o.toString());

            }else if ((m2==Double.NEGATIVE_INFINITY || m2==Double.POSITIVE_INFINITY)&&(m1==0)) {
                o.setY(a.getY());
                o.setX(c.getX());
                System.out.println(o.toString());

               }
            else if (m1==Double.NEGATIVE_INFINITY ||m1==Double.POSITIVE_INFINITY){
                       o.setX(a.getX());
                       double b2 = c.getY()-(m2*c.getX());
                       double yo = m2*o.getX()+b2;
                       o.setY(yo);
                System.out.println(o.toString());

            }
             else  if (m2==Double.NEGATIVE_INFINITY ||m2==Double.POSITIVE_INFINITY){
                        o.setX(c.getX());
                        double b1 = a.getY()-(m1*a.getX());
                        double yo = m1*o.getX()+b1;
                        o.setY(yo);
                System.out.println(o.toString());


            }
             else  if (m1==0){
                 o.setY(a.getY());
                double b2 = c.getY()-(m2*c.getX());
                double xo = (o.getY()-b2)/m2;
                o.setX(xo);
                System.out.println(o.toString());

            }
             else  if (m2==0){
                        o.setY(c.getY());
                double b1 = a.getY()-(m1*a.getX());
                double xo = (o.getY()-b1)/m1;
                o.setX(xo);
                System.out.println(o.toString());

            }
             else {
                double b1 = a.getY()-(m1*a.getX());
                double b2 = c.getY()-(m2*c.getX());
                double xo = (b1-b2)/(m2-m1);
                o.setX(xo);
                double yo = m1*o.getX()+b1;
                o.setY(yo);
                System.out.println(o.toString());
             }

            double[] tbb =new double[2];
            double[] tss =new double[2];
             if (c.getX()<d.getX()){
                    tbb[0]=c.getX();
                    tbb[1]=d.getX();
                }else {
                    tbb[1]=c.getX();
                    tbb[0]=d.getX();
                }
            if (a.getX()<b.getX()){
                    tss[0]=a.getX();
                    tss[1]=b.getX();
                }else {
                    tss[1]=a.getX();
                    tss[0]=b.getX();
                }

             if((tbb[1]>o.getX()&&tbb[0]<o.getX())&&(tss[1]>o.getX()&&tss[0]<o.getX())){
                        Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
                        Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
                        Circle circle = new Circle(o.getX(),o.getY(),5.0);
                        l1.setStroke(Color.RED);;
                        l2.setStroke(Color.RED);;
                        l1.setStrokeWidth(5);
                        l2.setStrokeWidth(5);
                        pannableCanvas.getChildren().add(l1);
                        pannableCanvas.getChildren().add(l2);
                        pannableCanvas.getChildren().add(circle);
                 return;
             }
             if (a.getX()==b.getX()){
                 boolean tcc =false;
                 if (a.getY()< b.getY()){
                        tcc=true;
                 }
                 if (tcc){
                     if (a.getY()<o.getY()&& b.getY()>o.getY()){
                         Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
                         Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
                         Circle circle = new Circle(o.getX(),o.getY(),5.0);
                         l1.setStroke(Color.RED);;
                         l2.setStroke(Color.RED);;
                         l1.setStrokeWidth(5);
                         l2.setStrokeWidth(5);
                         pannableCanvas.getChildren().add(l1);
                         pannableCanvas.getChildren().add(l2);
                         pannableCanvas.getChildren().add(circle);
                         return;
                     }

                 }else {
                     if (a.getY()>o.getY()&& b.getY()<o.getY()){
                         Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
                         Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
                         Circle circle = new Circle(o.getX(),o.getY(),5.0);
                         l1.setStroke(Color.RED);;
                         l2.setStroke(Color.RED);;
                         l1.setStrokeWidth(5);
                         l2.setStrokeWidth(5);
                         pannableCanvas.getChildren().add(l1);
                         pannableCanvas.getChildren().add(l2);
                         pannableCanvas.getChildren().add(circle);
                         return;
                     }
                 }

             }
             if (c.getX()==d.getX()){
                 boolean tcc =false;
                 if (c.getY()< d.getY()){
                     tcc=true;
                 }
                 if (tcc){
                     if (c.getY()<o.getY()&& d.getY()>o.getY()){
                         Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
                         Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
                         Circle circle = new Circle(o.getX(),o.getY(),5.0);
                         l1.setStroke(Color.RED);;
                         l2.setStroke(Color.RED);;
                         l1.setStrokeWidth(5);
                         l2.setStrokeWidth(5);
                         pannableCanvas.getChildren().add(l1);
                         pannableCanvas.getChildren().add(l2);
                         pannableCanvas.getChildren().add(circle);
                         return;
                     }

                 }else {
                     if (c.getY()>o.getY()&& d.getY()<o.getY()){
                         Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
                         Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
                         Circle circle = new Circle(o.getX(),o.getY(),5.0);
                         l1.setStroke(Color.RED);;
                         l2.setStroke(Color.RED);;
                         l1.setStrokeWidth(5);
                         l2.setStrokeWidth(5);
                         pannableCanvas.getChildren().add(l1);
                         pannableCanvas.getChildren().add(l2);
                         pannableCanvas.getChildren().add(circle);
                         return;
                     }
                 }

             }


//            double b1 = a.getY()-(m1*a.getX());
//            double b2 = c.getY()-(m2*c.getX());
//            double xo = (b1-b2)/(m2-m1);
//            System.out.println("M1 = "+m1);
//            System.out.println("M2 = "+m2);
//            System.out.println("B1 = "+b1);
//            System.out.println("B2 = "+b2);
//            System.out.println("Xo = "+xo);
//            double[] tbb =new double[2];
//             double dAB = Math.sqrt(Math.abs(a.getX()- b.getX())+Math.sqrt(Math.abs(a.getY()-b.getY())));
//             double dCD = Math.sqrt(Math.abs(c.getX()- d.getX())+Math.sqrt(Math.abs(c.getY()-d.getY())));
//
//            if (dAB>dCD){
//                if (c.getX()<d.getX()){
//                    tbb[0]=c.getX();
//                    tbb[1]=d.getX();
//                }else {
//                    tbb[1]=c.getX();
//                    tbb[0]=d.getX();
//                }
//                if (c.getX()==d.getX()){
//                    if ((tbb[1]>o.getX()&&o.getX()>=tbb[0])
//                            && o.isEQ(a)
//                            && o.isEQ(b)
//                            && o.isEQ(c)
//                            && o.isEQ(d))
//                    {
//                        System.out.println("Work");
//                        Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
//                        Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
//                        Circle circle = new Circle(o.getX(),o.getY(),5.0);
//                        l1.setStroke(Color.RED);;
//                        l2.setStroke(Color.RED);;
//                        l1.setStrokeWidth(5);
//                        l2.setStrokeWidth(5);
//                        pannableCanvas.getChildren().add(l1);
//                        pannableCanvas.getChildren().add(l2);
//                        pannableCanvas.getChildren().add(circle);
//                    }
//                }else {
//                    if ((tbb[1]>o.getX()&&o.getX()>tbb[0]))
//                    {
//                        System.out.println("Work");
//                        Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
//                        Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
//                        Circle circle = new Circle(o.getX(),o.getY(),5.0);
//                        l1.setStroke(Color.RED);;
//                        l2.setStroke(Color.RED);;
//                        l1.setStrokeWidth(5);
//                        l2.setStrokeWidth(5);
//                        pannableCanvas.getChildren().add(l1);
//                        pannableCanvas.getChildren().add(l2);
//                        pannableCanvas.getChildren().add(circle);
//                    }
//                }
//
//            }else {
//                if (a.getX()<b.getX()){
//                    tbb[0]=a.getX();
//                    tbb[1]=b.getX();
//                }else {
//                    tbb[1]=a.getX();
//                    tbb[0]=b.getX();
//                }
//                if (a.getX()==b.getX()){
//                    if ((tbb[1]>o.getX()&&o.getX()>=tbb[0])
//                    && o.isEQ(a)
//                    && o.isEQ(b)
//                    && o.isEQ(c)
//                    && o.isEQ(d)
//                    )
//                    {
//                        System.out.println("Work");
//                        Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
//                        Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
//                        Circle circle = new Circle(o.getX(),o.getY(),5.0);
//                        l1.setStroke(Color.RED);;
//                        l2.setStroke(Color.RED);;
//                        l1.setStrokeWidth(5);
//                        l2.setStrokeWidth(5);
//                        pannableCanvas.getChildren().add(l1);
//                        pannableCanvas.getChildren().add(l2);
//                        pannableCanvas.getChildren().add(circle);
//                    }
//                }else {
//                    if ((tbb[1]>o.getX()&&o.getX()>tbb[0]))
//                    {
//                        System.out.println("Work");
//                        Line l1 = new Line(a.getX(),a.getY(),b.getX(),b.getY());
//                        Line l2 = new Line(c.getX(),c.getY(),d.getX(),d.getY());
//                        Circle circle = new Circle(o.getX(),o.getY(),5.0);
//                        l1.setStroke(Color.RED);;
//                        l2.setStroke(Color.RED);;
//                        l1.setStrokeWidth(5);
//                        l2.setStrokeWidth(5);
//                        pannableCanvas.getChildren().add(l1);
//                        pannableCanvas.getChildren().add(l2);
//                        pannableCanvas.getChildren().add(circle);
//                    }
//                }
//

         //   }







        }



    }


    public ObservableList<Neighbor> getAllNeighbor(){
        ObservableList<Neighbor> allNeighbor= FXCollections.observableArrayList();
        for (int i =0;i<zones.getObservableList().size();i++){
            for (int j=i+1;j<zones.getObservableList().size();j++){
               allNeighbor.addAll(zones.getObservableList().get(i).getNeighbor(zones.getObservableList().get(j)));
            }
        }
        return allNeighbor;
    }




}


