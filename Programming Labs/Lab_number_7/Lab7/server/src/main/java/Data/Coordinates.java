package Data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x; //Максимальное значение поля: 404
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }
}
