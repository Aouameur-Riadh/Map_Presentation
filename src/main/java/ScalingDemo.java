import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

import javafx.stage.Stage;
public class ScalingDemo extends Application{
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane(new Label("DDDDDd"));
        Slider slider = new Slider(0.5,2,1);
        ZoomingPane zoomingPane = new ZoomingPane(anchorPane);
        zoomingPane.zoomFactorProperty().bind(slider.valueProperty());
        primaryStage.setScene(new Scene(new BorderPane(zoomingPane, null, null, slider, null)));

        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private class ZoomingPane extends Pane {
        Node content;
        private DoubleProperty zoomFactor = new SimpleDoubleProperty(1);
        Scale scale;
        private ZoomingPane(Node content) {
            this.content = content;
            getChildren().add(content);
           scale = new Scale(1, 1);
            content.getTransforms().add(scale);
        }
        
      public   void Zoom(){
            zoomFactor.addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    scale.setX(newValue.doubleValue());
                    scale.setY(newValue.doubleValue());
                    requestLayout();
                }
            });
        }
        


        public final DoubleProperty zoomFactorProperty() {
            return zoomFactor;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}