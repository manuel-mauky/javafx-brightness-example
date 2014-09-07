import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    private IntegerProperty brightness = new SimpleIntegerProperty();

    public static void main(String... args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/View.fxml");

        Parent root = FXMLLoader.load(location);

        final DoubleBinding colorValue = brightness.multiply(2.55);

        StringExpression styleString = Bindings.format("-fx-base:rgb(%1$.0f , %1$.0f, %1$.0f)", colorValue);

        brightness.set(20);

        root.styleProperty().bind(styleString);

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();

        initBrightnessControl(primaryStage);
    }

    private void initBrightnessControl(Stage primaryStage) {

        Label label = new Label();
        label.textProperty().bind(Bindings.format("Brightness: %1$2d %%", brightness));

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.valueProperty().bindBidirectional(brightness);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(label, slider);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initOwner(primaryStage);

        stage.show();
    }
}
