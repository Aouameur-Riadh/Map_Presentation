import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Zone {
   private SimpleStringProperty zoneName;

    private ObservableList<MyPoint> observableList;

    public Zone(SimpleStringProperty zoneName, SimpleDoubleProperty x, SimpleDoubleProperty y,ObservableList<MyPoint> observableList) {
        this.zoneName = zoneName;
        this.observableList= observableList;

    }
    public Zone() {
        this.zoneName= new SimpleStringProperty("");
        this.observableList=  FXCollections.observableArrayList();
    }

    public String getZoneName() {
        return zoneName.get();
    }

    public SimpleStringProperty zoneNameProperty() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName.set(zoneName);
    }



    public ObservableList<MyPoint> getObservableList() {
        return observableList;
    }
    public String getMyPointsToString() {
        String st =  "" ;
        for (int i =2;i<this.getObservableList().size();i++){
            st = st + "( "+this.observableList.get(i).getX()+","+this.observableList.get(i).getY()+" )"+"\n";

        }
        return st;
    }

    public void setObservableList(ObservableList<MyPoint> observableList) {
        this.observableList = observableList;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneName=" + zoneName +
                ", observableList=" + observableList +
                '}';
    }

    public   ObservableList<Segment> getSegments() {
        ObservableList<Segment> segments= FXCollections.observableArrayList();
        for (int j =1;j<this.getObservableList().size();j++){
                try {
                    MyPoint a =this.getObservableList().get(j);
                    MyPoint b =this.getObservableList().get(j+1);
                    Segment segment = new Segment(a,b);
                    segments.add(segment);
                }catch (Exception e){

                }

            }
        return segments;


    }
    public ObservableList<Neighbor> getNeighbor(Zone zone) {
        ObservableList<Neighbor> neighbors= FXCollections.observableArrayList();
        for (int i =2;i<this.getObservableList().size();i++){
            for (int j =2;j<zone.getObservableList().size();j++){
                if (this.getObservableList().get(i).getX()==zone.getObservableList().get(j).getX()&&
                        this.getObservableList().get(i).getY()==zone.getObservableList().get(j).getY()){
                    Neighbor neighbor= new Neighbor();
                    neighbor.setZ1(this);
                    neighbor.setZ2(zone);
                    neighbor.setPoint(new MyPoint(this.getObservableList().get(i).getX(),this.getObservableList().get(i).getY()));
                    System.out.println("N : "+neighbor.toString());
                    neighbors.add(neighbor);

                }


            }
        }
         return neighbors;
    }



}
