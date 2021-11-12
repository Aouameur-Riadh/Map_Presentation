

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Oij implements Initializable {
    public AnchorPane pane;
    public AnchorPane rootPane;
    public Button btn;
    public Label lab;
    Stage stage;
    PannableCanvas canvas;
    DrawShape drawShape;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new PannableCanvas();
        SceneGestures sceneGestures = new SceneGestures(canvas);
        MouseMoved mouseMoved = new MouseMoved(lab);
        pane.getChildren().add(canvas);
        pane.addEventFilter( MouseEvent.MOUSE_MOVED, mouseMoved.getOnMovedEventHandler());
        canvas.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        canvas.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        canvas.addEventFilter( ScrollEvent.SCROLL, sceneGestures.getOnScrollEventHandler());
        canvas.addGrid();
    }

    public void onOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choisir le fichier...");
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            ReadFile readFile = new ReadFile(file);
           drawShape = new DrawShape(readFile.GetDataFromFile());
            canvas.getChildren().add(drawShape.onDraw());
            drawShape.NbSegment(canvas);
           btn.setText("Number Of Segments : " + drawShape.NbSegment() + " Segment");


        }
    }




    public void onReset(ActionEvent actionEvent) {
        pane.getChildren().remove(canvas);
        canvas = new PannableCanvas();
        SceneGestures sceneGestures = new SceneGestures(canvas);
        MouseMoved mouseMoved = new MouseMoved(lab);
        pane.getChildren().add(canvas);
        pane.addEventFilter( MouseEvent.MOUSE_MOVED, mouseMoved.getOnMovedEventHandler());
        canvas.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        canvas.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        canvas.addEventFilter( ScrollEvent.SCROLL, sceneGestures.getOnScrollEventHandler());
        canvas.addGrid();
        btn.setText("");
    }

    public void onSaveImage(ActionEvent actionEvent) throws IOException {
        System.out.println(" button pressed");
        WritableImage nodeshot = pane.snapshot(new SnapshotParameters(),
                null);
        File file = new File("D:\\My Projects\\intellijIdeaProjects\\TpSIG_1\\src\\main\\resources\\snapshot.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);
        } catch (IOException e) {

        }
    }

    public void onGetPolygonNeighbors(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PolygonNeighbors.fxml"));
        loader.setControllerFactory(e -> {

            return new PolygonNeighborsController(drawShape);

        });

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void onDetectErrors(ActionEvent actionEvent) {
        for (Zone zone : drawShape.getZones().getObservableList()){
            ObservableList<Segment> segments = zone.getSegments();
            for (int i=0;i<segments.size();i++){
                for (int j=i+1;j<segments.size();j++){
                    Segment ab = segments.get(i);
                    Segment cd = segments.get(j);
                    drawShape.onDetectErrors(
                            ab.getM1(),
                            ab.getM2(),
                            cd.getM1(),
                            cd.getM2(),
                            canvas);

                }
            }
        }

//        drawShape.onDetectErrors(
//                new MyPoint(300,50),
//                new MyPoint(500,400),
//                new MyPoint(100,300),
//                new MyPoint(600,200),
//                canvas);


    }
}
