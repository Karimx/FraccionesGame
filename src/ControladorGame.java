import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class ControladorGame {
    double score;
    int indexActual;

    public void setRoot(Group root) {
        this.root = root;
    }

    Group root;

    List<Ejercicios> ejerciciosList =new ArrayList<>();

    public ControladorGame(Group root) {
        indexActual=0;
        this.root = root;
        Ejercicios ejercicios=new Ejercicios(3,10);
        ejerciciosList.add(ejercicios);

        root.getChildren().add(ejerciciosList.get(0).crearEjecricio(1,5));

    }

    public void siguienteEjercicio(){

        root.getChildren().remove(0,root.getChildren().size());
        indexActual=indexActual+1;

        ejerciciosList.get(indexActual).play();
    }





}
