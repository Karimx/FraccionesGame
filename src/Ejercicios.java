import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Ejercicios extends EjercicioAbstract {
    int opciones;
    int selecciones=3;
    final HBox hbox;
    final VBox vbox;
    private double espaciado;
    List<Fraccion> fraccionList=new ArrayList<>();

    public Ejercicios(int opciones,double espaciado) {
        pregunta =new Pregunta();

        nombre="¿Cual faccion es menor?";
        this.opciones = opciones;
        hbox=new HBox(espaciado+10);
        vbox=new VBox(espaciado+10);
    }

    @Override
    public void crearEjecricio(int tipo, int nivel) {
        for (int i = 0; i < opciones; i++) {
           pregunta.addOpcion(ControladorEjercicios.crearFracciones(1));
           }
        correctaIndex=(ControladorEjercicios.randInt(1,opciones));

        vbox.getChildren().add( hbox );


       // return vbox;
    }

    public Node getTitulo(){
        HBox box=new HBox(espaciado+10);
        box.getChildren().add(new Label(nombre));
        return box;

    }
    public Node getOpciones(){
        HBox hbox=new HBox(espaciado+10);
        for (int i = 0; i < selecciones; i++) {
            Fraccion fraccion=new Fraccion("3/4");

            fraccion.setCentro(100,100);
            fraccion.setColor(Color.BLUE);
            fraccion.setRadio(60,60);
            fraccion.setTamaño(20);
            //System.out.println(fraccion);
            hbox.getChildren().add(fraccion.build());
        }
        return hbox;
    }

    public Node getRespuestas(){
        HBox hbox=new HBox(espaciado+10);
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
