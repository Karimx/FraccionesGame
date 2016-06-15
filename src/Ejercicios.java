import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Ejercicios extends EjercicioAbstract {
    int opciones;
    int selecciones;
    final HBox hbox;
    private double espaciado;

    public Ejercicios(int opciones,double espaciado) {
        pregunta =new Pregunta();
        nombre="¿Cual faccion es menor?";
        this.opciones = opciones;
        hbox=new HBox(espaciado+10);
    }

    @Override
    public Node crearEjecricio(int tipo, int nivel) {
        hbox.getChildren().add(new Label(nombre));

        for (int i = 0; i < opciones; i++) {
           pregunta.addOpcion(ControladorEjercicios.crearFracciones(1));
           }
        correctaIndex=(ControladorEjercicios.randInt(1,opciones));

        for (int i = 0; i < selecciones; i++) {
            Fraccion fraccion=new Fraccion("3/4");
            fraccion.setCentro(300,300);
            fraccion.setColor(Color.BLUE);
            fraccion.setRadio(10,10);
            fraccion.setTamaño(20);
            hbox.getChildren().add(fraccion.build());
        }

        for (String p :pregunta.opciones) {
            Button button=new Button(p);
            hbox.getChildren().add(button);

        }
        return hbox;
    }

    @Override
    public void play() {

    }

    public boolean esCorrecta(int indexRespuesta) {
        if(pregunta.opciones.get(correctaIndex).equals(pregunta.opciones.get(indexRespuesta)))
            return true;
        return false;
    }


    private static HBox createHBox(final double spacing,
                                   final Node... children) {
        final HBox hbox2 = new HBox(spacing);

        return hbox2;
    }



}
