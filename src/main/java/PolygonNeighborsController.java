import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PolygonNeighborsController implements Initializable {
    public TextArea text;
    public TextArea text_1;
    private final DrawShape drawShape;

    public PolygonNeighborsController(DrawShape drawShape) {
        this.drawShape = drawShape;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
           if (drawShape.getAllNeighbor()!=null){
               ObservableList <Neighbor> neighbors = drawShape.getAllNeighbor();
               String st ="" ;
               for (Neighbor neighbor : neighbors){
                   st =st +neighbor.getZ1().getZoneName()+" ( "+neighbor.getPoint().getX()
                           +","+neighbor.getPoint().getY()+" ) "+neighbor.getZ2().getZoneName()+"\n";

               }
               text.setText(st);

           }


           if (drawShape.getZones()!=null){
               ObservableList <Zone> zones   = drawShape.getZones().getObservableList();
               String st ="" ;
               for (Zone zone : zones){
                   st =st +zone.getZoneName()+"\n"+zone.getMyPointsToString();

               }
               text_1.setText(st);

           }
       }catch (Exception exception){

       }


    }
}
