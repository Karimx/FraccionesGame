//import com.sun.org.apache.xpath.internal.operations.String;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.*;

public class ControladorEjercicios {
    Group root = null;
    List<String> fracciones = new ArrayList<>();
    double startX = 150;
    double espaciadoX = 200;
    boolean correcta = false;
    int dificultad;

    List<Pregunta> preguntasGlobal = new ArrayList<>();


    public static String crearFracciones(int nivel) {
        int numerador;
        int denominador;
        denominador = randInt(1, maxi(nivel));
        numerador = randInt(1, maxi(nivel));
        while (denominador <= numerador)
            numerador = randInt(1, maxi(nivel));
        return numerador + "/" + denominador;
    }

    public static String crearDivisibles(int nivel) {
        int numerador;
        int denominador;
        denominador = randInt(1, maxi(nivel));
        numerador = randInt(1, maxi(nivel));
        while (numerador % 2 == 0) {
            denominador = randInt(1, maxi(nivel));
            numerador = randInt(1, maxi(nivel));
            while (denominador <= numerador)
                numerador = randInt(1, maxi(nivel));

        }
        return numerador + "/" + denominador;
    }


    public static int randInt(int min, int max) {
        Random random = new Random();
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return randomNum;
    }

    private static int maxi(int nivel) {
        switch (nivel) {
            case 1:
                return 3;
            case 2:
                return 6;
            case 3:
                return 10;
            case 4:
                return 20;
            case 5:
                return 50;
        }
        return 3;

    }

    public void setRoot(Group root) {
        this.root = root;
    }

    //EJERCICIO 1


    public void ejercicio1(int opciones) {
        root.getChildren().add(new Label("¿Cual fraccion es menor?"));

        //Opciones
        while (fracciones.size() < opciones) {
            String ne = ControladorEjercicios.crearFracciones(3);
            if (!fracciones.equals(ne)) {
                fracciones.add(ne);
            }
        }

        //Secrea las preguntas
        Pregunta pregunta = new Pregunta();
        for (int i = 0; i < opciones; i++) {
            pregunta.addOpcion(fracciones.get(i));
        }

        //fracciones
        for (int i = 0; i < opciones; i++) {
            Fraccion fraccion = new Fraccion(pregunta.opciones.get(i));

            fraccion.setCentro(startX - 50 + (i * espaciadoX), 100);
            fraccion.setColor(Color.BLUE);
            fraccion.setRadio(50, 50);

            root.getChildren().add(fraccion.build());
        }

        Collections.shuffle(pregunta.opciones);
        String menor;
        menor = getMenorLista(pregunta.opciones);
        pregunta.setrespuestaCorrecta(menor);

        this.preguntasGlobal.add(pregunta);

        //botones
        for (int i = 0; i < opciones; i++) {
            Button button = new Button(pregunta.opciones.get(i));
            button.relocate(startX + (espaciadoX * i), 350);
            button.setOnAction(e -> {
                        if (button.getText().equals(menor)) {
                            System.out.println("correcta1");
                            ControladorGame.addPoint();
                        }
                        this.siguienteEjercicio();
                    }
            );
            root.getChildren().add(button);
        }

    }

    //EJERCICIO 2

    public void ejercicio2(int opciones) {
        //Collections.shuffle(fracciones);
        root.getChildren().add(new Label("¿Cuales fracciones son equivalentes a?"));


        String mayor = this.getMayorLista(fracciones);

        Fraccion fraccion = new Fraccion(mayor);
        fraccion.setCentro(startX - 50 + (espaciadoX), 100);
        fraccion.setColor(Color.RED);
        fraccion.setRadio(50, 50);
        root.getChildren().add(fraccion.build());


        List<String> mMayores = this.getEquivalentesMayores(mayor, 2);
        List<String> mMenores = this.getEquivalentesMenores(mayor, 2);
        Pregunta pregunta = new Pregunta();

        if (mMenores.size() > 0) {
            pregunta.addOpcion(mMenores.get(0));
        } else {
            pregunta.addOpcion(mMayores.get(1));
        }
        pregunta.addOpcion(mMayores.get(0));

        pregunta.addOpcion(ControladorEjercicios.crearFracciones(3));
        pregunta.setrespuestaCorrecta(mayor);

        System.out.println(pregunta.getrespuestaCorrecta());
        for (int i = 0; i < pregunta.getOpciones().size(); i++) {
            Button button2 = new Button(pregunta.opciones.get(i));
            button2.relocate(startX + (espaciadoX * i), 350);
            button2.setOnAction(e -> {
                        if (esEquivalente(button2.getText(), pregunta.getrespuestaCorrecta())) {
                            System.out.println("correcta2");
                            ControladorGame.addPoint();
                        }
                        this.siguienteEjercicio();
                    }
            );
            root.getChildren().add(button2);
        }

        //root.getChildren().get(1).relocate(startX,100);


    }

    //EJERCICIO 3
    public void ejercicio3(int opciones) {
        root.getChildren().add(new Label("¿Cual fraccion es mayor?"));
        //Opciones
        while (fracciones.size() < opciones) {
            String ne = ControladorEjercicios.crearFracciones(4);
            if (!fracciones.contains(ne)) {
                fracciones.add(ne);
            }
        }

        //Secrea las preguntas
        Pregunta pregunta = new Pregunta();
        for (int i = 0; i < opciones; i++) {
            pregunta.addOpcion(fracciones.get(i));
        }

        //fracciones
        for (int i = 0; i < opciones; i++) {
            Fraccion fraccion = new Fraccion(pregunta.opciones.get(i));

            fraccion.setCentro(startX - 50 + (i * espaciadoX), 100);
            fraccion.setColor(Color.ORANGE);
            fraccion.setRadio(50, 50);

            root.getChildren().add(fraccion.build());
        }

        Collections.shuffle(pregunta.opciones);
        String mayor;
        mayor = getMayorLista(pregunta.opciones);
        pregunta.setrespuestaCorrecta(mayor);
        System.out.println("--"+mayor);

        this.preguntasGlobal.add(pregunta);

        //botones
        for (int i = 0; i < opciones; i++) {
            Button button = new Button(pregunta.opciones.get(i));
            button.relocate(startX + (espaciadoX * i), 350);
            button.setOnAction(e -> {
                        if (button.getText().equals(mayor)) {

                            System.out.println("correcta3");
                            ControladorGame.addPoint();
                        }
                        this.siguienteEjercicio();
                    }
            );
            root.getChildren().add(button);
        }

    }

    //EJERCICIO 4

    public void ejercicio4(int opciones) {
        String menorRango;
        String mayorRango;

        //pregunta.setrespuestaCorrecta(crearFracciones(3));
        menorRango = crearFracciones(3);
        mayorRango = crearFracciones(4);

        if (fraccionDouble(menorRango) > fraccionDouble(mayorRango)) {
            String aux = mayorRango;
            mayorRango = menorRango;
            menorRango = aux;
        }

        root.getChildren().add(new Label("Selecciona la fraccion que esta " + menorRango + " y " + mayorRango));

        Pregunta pregunta = new Pregunta();

        pregunta.setrespuestaCorrecta(getFraccionEntre(menorRango,mayorRango));
        pregunta.opciones.add(pregunta.getrespuestaCorrecta());
        for (int i = 1; i < opciones; i++) {
            pregunta.opciones.add(crearFracciones(3));
        }

        System.out.println(pregunta.getrespuestaCorrecta());

        for (int i = 0; i < opciones; i++) {
            Fraccion fraccion = new Fraccion(pregunta.opciones.get(i));
            fraccion.setCentro(startX - 50 + (i * espaciadoX), 100);
            fraccion.setColor(Color.YELLOW);
            fraccion.setRadio(50, 50);
            root.getChildren().add(fraccion.build());
        }


        for (int i = 0; i < opciones; i++) {
            Button button = new Button(pregunta.opciones.get(i));
            button.relocate(startX + (espaciadoX * i), 350);

            final String finalMenorRango = menorRango;
            final String finalMayorRango = mayorRango;

            button.setOnAction(e -> {
                        // fraccionDouble(menor)>fraccionDouble(m) || fraccionDouble(mayor)<fraccionDouble(m)
                        if (fraccionDouble(button.getText())>fraccionDouble(finalMenorRango) || fraccionDouble(button.getText())<fraccionDouble(finalMayorRango) )  {

                            System.out.println("correcta4");
                            ControladorGame.addPoint();
                        }
                        this.siguienteEjercicio();
                    }
            );
            root.getChildren().add(button);
        }

    }


    //EJERCICIO 5

    public void ejercicio5(int opciones) {
        root.getChildren().add(new Label("¿Puntuación"));

        Label puntuacion=new Label("" + ControladorGame.score);
        puntuacion.relocate(200,200);
        root.getChildren().add(puntuacion);
        Button button = new Button("Otra vez");
        button.relocate(300,300);
        button.setOnAction(e -> {
                    this.indexEjercisio=0;
                    ControladorGame.score=0;
                    this.siguienteEjercicio();
        });
        root.getChildren().add(button);


    }


    public void limpiarPantalla() {
        root.getChildren().remove(0, root.getChildren().size());
    }

    public String getMenorLista(List<String> lista) {
        String menor = lista.get(0);
        for (String fraccion : lista) {
            if (fraccionDouble(menor) > fraccionDouble(fraccion)) {
                menor = fraccion;
            }
        }
        return menor;
    }

    public String getMayorLista(List<String> lista) {
        String mayor = lista.get(0);
        for (String fraccion : lista) {
            if (fraccionDouble(mayor) < fraccionDouble(fraccion)) {
                mayor = fraccion;
            }
        }
        return mayor;
    }

    public int[] fraccionEntero(String fraccion) {
        String r[] = fraccion.split("/");
        int s[] = new int[0];
        s[0] = (Integer.parseInt(r[0]));
        s[1] = (Integer.parseInt(r[1]));
        System.out.println(s[0]);
        System.out.println(s[1]);
        return s;
    }

    public double fraccionDouble(String fraccion) {
        String r[] = fraccion.split("/");
        return (double) Integer.parseInt(r[0]) / Integer.parseInt(r[1]);
    }

    int indexEjercisio = 0;

    public void siguienteEjercicio() {
        limpiarPantalla();
        indexEjercisio = indexEjercisio + 1;
        switch (indexEjercisio) {
            case 1:
                this.ejercicio1(3);
                break;
            case 2:
                this.ejercicio2(3);
                break;
            case 3:
                this.ejercicio3(3);
                break;
            case 4:
                ejercicio4(3);
                break;
            case 5:
                ejercicio5(1);
                break;
            case 6:
                break;
        }
    }

    public List<String> getEquivalentesMenores(String fraccion, int limite) {
        List<String> resultado = new ArrayList<>();
        String r[] = fraccion.split("/");
        String aux;
        int num, den;
        for (int i = 2; i < limite + 2; i++) {

            if (Integer.parseInt(r[0]) % i == 0 && Integer.parseInt(r[1]) % i == 0) {
                num = Integer.parseInt(r[0]) / i;
                den = Integer.parseInt(r[1]) / i;
                aux = num + "/" + den;
                resultado.add(aux);
            }
        }
        return resultado;
    }

    public List<String> getEquivalentesMayores(String fraccion, int limite) {
        List<String> resultado = new ArrayList<>();
        String aux;
        int num, den;
        String r[] = fraccion.split("/");
        for (int i = 2; i < limite + 2; i++) {
            num = Integer.parseInt(r[0]) * i;
            den = Integer.parseInt(r[1]) * i;
            aux = num + "/" + den;
            resultado.add(aux);
        }

        return resultado;
    }

    public boolean esEquivalente(String fraccion, String equiv) {
        List<String> mayores = getEquivalentesMayores(fraccion, 10);
        List<String> menores = getEquivalentesMenores(fraccion, 10);

        return mayores.contains(equiv) || menores.contains(equiv);
    }

    public String getFraccionEntre(String menor, String mayor) {
        String m = crearFracciones(5);
        while ( fraccionDouble(menor)>fraccionDouble(m) || fraccionDouble(mayor)<fraccionDouble(m) ) {
            m = crearFracciones(4);
        }
        return m;
    }


}
