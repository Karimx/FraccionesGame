import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pregunta {

    String pregunta;

    List<String> opciones=new ArrayList<>();


    public void addOpcion(String opcion){
        opciones.add(opcion);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setOpcionCorrecta(int index){
        this.opciones.get(index);
    }
}
