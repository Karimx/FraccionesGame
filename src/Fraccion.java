
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Fraccion extends Node {
    Font fontLarge = Font.font("Droid Sans", FontWeight.BOLD, 15);
    Font fontSmall = Font.font("Droid Sans", FontWeight.BOLD, 10);
    boolean essolovisibleCirculo = false;
    Pane hbox;

    public boolean isEsSolovisibleFrac() {
        return esSolovisibleFrac;
    }

    public void setEsSolovisibleFrac(boolean esSolovisibleFrac) {
        this.esSolovisibleFrac = esSolovisibleFrac;
    }

    enum colores {ROJO, AZUL}

    ;

    boolean esSolovisibleFrac = false;


    Group root;

    public void setRoot(Group root) {
        this.root = root;
    }

    ArcFactory arcFactory = new ArcFactory();

    public Fraccion(String fraccion) {
        String r[] = fraccion.split("/");
        setNumerador(Integer.parseInt(r[0]));
        setDenominador(Integer.parseInt(r[1]));
        color = Color.GREEN;
        init();
    }

    public int getDenominador() {
        return denominador;
    }

    public double getAnguloInicial() {
        return anguloInicial;
    }

    public void setAnguloInicial(double anguloInicial) {
        this.anguloInicial = anguloInicial;
    }

    public double getCentroX() {
        return centroX;
    }

    public void setCentroX(double centroX) {
        this.centroX = centroX;
    }

    public double getCentroY() {
        return centroY;
    }

    public void setCentroY(double centroY) {
        this.centroY = centroY;
    }

    public double getRadioX() {
        return radioX;
    }

    public void setRadioX(double radioX) {
        this.radioX = radioX;
    }

    public double getRadioY() {
        return radioY;
    }

    public void setRadioY(double radioY) {
        this.radioY = radioY;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    private void init() {
        anguloActual = anguloInicial;
        tamaño = (double) 360 / denominador;

    }

    int denominador;
    int numerador;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    double anguloInicial;
    private double anguloActual;
    double centroX;
    double centroY;
    double radioX;
    double radioY;

    Color color;

    double tamaño;

    List<Arc> rebanadas = new ArrayList<>();

    public String getFraccion() {
        return numerador + "/" + denominador;
    }

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public String toString() {
        return this.getFraccion();
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }

    private class ArcFactory {

        public Arc getNewArc() {

            Arc arc = new Arc(centroX, centroY, radioX, radioY, anguloActual, tamaño);
            arc.setType(ArcType.ROUND);
            arc.setStroke(Color.BLACK);
            arc.setFill(Color.TRANSPARENT);
            arc.setStrokeWidth(.5);
            anguloActual = (tamaño) + anguloActual;
            return arc;
        }

    }

    private void crearFigura() {
        for (int i = 0; i < denominador; i++) {
            rebanadas.add(arcFactory.getNewArc());
        }
        for (int j = 0; j < numerador; j++)
            rebanadas.get(j).setFill(color);

    }


    public Node build() {
        crearFigura();
        return createHBox(5);
    }

    public void setCentro(double x, double y) {
        this.setCentroX(x);
        this.setCentroY(y);

    }

    public void setRadio(double x, double y) {
        this.setRadioX(x);
        this.setRadioY(y);

    }

    //Clase Interna
    class Fraction extends VBox {
        private double offset;
        double figOffset = 25;

        public Fraction(int numerator, int denominator) {
            init(numerator + "", denominator + "");
        }

        public Fraction(String numerator, String denominator) {
            init(numerator, denominator);
        }

        private void init(String numerator, String denominator) {
            figOffset = radioY + 5;
            setAlignment(Pos.CENTER);
            Text numeratorText = new Text(numerator);
            Text denominatorText = new Text(denominator);
            offset = numeratorText.getBaselineOffset() * 1.5;
            double dividerWidth =
                    Math.max(
                            numeratorText.getLayoutBounds().getWidth(),
                            denominatorText.getLayoutBounds().getWidth()
                    ) + 6;
            Line divider = new Line(0, 1, dividerWidth, 1);
            divider.setStrokeWidth(2);
            getChildren().addAll(
                    numeratorText,
                    divider,
                    denominatorText
            );

            this.setTranslateX(centroX - 10);
            this.setTranslateY(centroY + figOffset);
        }
        public double getBaselineOffset() {
            return offset;
        }

    }

    private Pane createHBox(final double spacing
    ) {
        hbox = new Pane();

        for (Arc arc : rebanadas) {
            hbox.getChildren().add(arc);
        }

        hbox.getChildren().add(new Fraction(this.numerador, this.denominador));
        hbox.setLayoutX(100);
        hbox.setLayoutY(100);
        //hbox.getChildren().addAll(children);
        return hbox;
    }


}
