import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pregunta {
    String pregunta;
    String respuestaCorrecta;

    List<String> opciones = new ArrayList<>();

    public void addOpcion(String opcion) {
        opciones.add(opcion);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setOpcionCorrecta(int index) {
        this.opciones.get(index);
    }

    public String getrespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setrespuestaCorrecta(String correcta) {
        this.respuestaCorrecta = correcta;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}
