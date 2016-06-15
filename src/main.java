import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);


        //System.out.println(controladorEjercicios.crearFracciones(5));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ControladorEjercicios controladorEjercicios=new ControladorEjercicios();

        Group root = new Group();
        primaryStage.setTitle("Hello World");
        final Scene scene = new Scene(root, 800, 500);
        try {
            scene.getStylesheets().add("estilos.css");
        } catch (Exception ex) {
            System.err.println("Cannot acquire stylesheet: " + ex.toString());
        }
        primaryStage.setScene(scene);
        ControladorGame controladorGame=new ControladorGame(root);
        System.out.println(root.getChildren().size());
        primaryStage.show();

    }
}

