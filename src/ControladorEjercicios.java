import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class ControladorEjercicios {
    Group root=null;
    List<String> fracciones=new ArrayList<>();


    public static String crearFracciones(int nivel){
        int numerador;
        int denominador;
        denominador=randInt(1,maxi(nivel));
        numerador=randInt(1,maxi(nivel));
        while(denominador<=numerador)
            numerador=randInt(1,maxi(nivel));

        return numerador+"/"+denominador;
    }

    public static int randInt(int min, int max) {
        Random random =new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        //int randomNum = random.nextInt((max - min) + 1) + min;
        int randomNum=min + (int)(Math.random() * ((max - min) + 1));
        return randomNum;
    }

    private static int maxi(int nivel){
        switch (nivel){
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
        return 1;

    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public  void ejercicio1(int opciones){
        double startX=150;
        double espaciadoX=200;
        root.getChildren().add(new Label("¿Cual faccion es menor?"));
        int correctaIndex;

        while( fracciones.size()<3) {
            String ne=ControladorEjercicios.crearFracciones(3);
            if(!fracciones.equals(ne)){
                fracciones.add(ne);
            }
        }

        Pregunta pregunta =new Pregunta();
        for (int i = 0; i < opciones; i++) {
            pregunta.addOpcion(fracciones.get(i));
        }

        for (int i = 0; i < opciones; i++) {
            Fraccion fraccion=new Fraccion(pregunta.opciones.get(i));

            fraccion.setCentro(startX-50+(i*espaciadoX),100);
            fraccion.setColor(Color.BLUE);
            fraccion.setRadio(50,50);
            //fraccion.setTamaño(20);
            root.getChildren().add(fraccion.build());
        }

        for (int i = 0; i < opciones; i++) {
            Button button=new Button(pregunta.opciones.get(i) );
            //Button button=new Button(""+i);
            button.relocate(startX+(espaciadoX*i),350);
            root.getChildren().add(button);
        }

       correctaIndex=(ControladorEjercicios.randInt(1,opciones));


    }


    public void limpiarPantalla(){
        //
    }

    public int getMenorLista(List<String> lista){


        return 1;
    }

    public int[] fraccionEntero(String fraccion){
        String r[] = fraccion.split("/");
        int s[] = new int[0];

        s[0]=(Integer.parseInt(r[0]));
        s[1]=(Integer.parseInt(r[1]));
        System.out.println(s[0]);
        System.out.println(s[1]);
        return s ;

    }






}
