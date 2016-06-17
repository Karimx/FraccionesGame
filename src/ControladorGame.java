import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class ControladorGame {
    public static double  score;

int indexActual;

    public void setRoot(Group root) {
        this.root = root;
    }

    Group root;
    List<Ejercicios> ejerciciosList =new ArrayList<>();

    public ControladorGame(Group root) {

    }

    public void siguienteEjercicio(){
        root.getChildren().remove(0,root.getChildren().size());
        indexActual=indexActual+1;
        ejerciciosList.get(indexActual).play();
    }


    public static void addPoint(){
        score+=1;
    }








}
