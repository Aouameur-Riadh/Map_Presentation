import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Zones {
   private ObservableList<Zone> observableList;

    public Zones() {
        observableList = FXCollections.observableArrayList();
    }

    public ObservableList<Zone> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<Zone> observableList) {
        this.observableList = observableList;
    }

    public   ObservableList<MyPoint> getAllPoints() {
         ObservableList<MyPoint> allPoints= FXCollections.observableArrayList();

         for (Zone zone : this.getObservableList()){
             allPoints.addAll(zone.getObservableList());
             allPoints.remove(zone.getObservableList().get(0));


         }
         return allPoints;


    }

    public   ObservableList<Segment> getAllSeg() {
        ObservableList<Segment> allPoints= FXCollections.observableArrayList();

        for (Zone zone : this.getObservableList()){
            allPoints.addAll(zone.getSegments());



        }
        return allPoints;


    }




}
