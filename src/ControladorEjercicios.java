import java.util.Random;
import java.util.StringJoiner;

public class ControladorEjercicios {

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



}
