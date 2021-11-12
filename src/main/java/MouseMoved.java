import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public class MouseMoved {

    public Label lab;

    public MouseMoved(Label lab) {
        this.lab = lab;
    }

    public EventHandler<MouseEvent> getOnMovedEventHandler() {
        return onMouseMovedEventHandler;
    }

    private EventHandler<MouseEvent> onMouseMovedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            lab.setText("X : "+(event.getSceneX()-120.0)+" Y : "+event.getSceneY()+"");


            event.consume();

        }

    };
}
