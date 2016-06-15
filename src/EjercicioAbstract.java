import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class EjercicioAbstract {
    String nombre;
    int correctaIndex;
    Pregunta pregunta;
    public abstract void crearEjecricio(int tipo, int nivel);

    public abstract void play();

}
