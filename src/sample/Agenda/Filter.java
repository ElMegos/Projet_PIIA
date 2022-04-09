package sample.Agenda;

import javafx.scene.paint.Color;


public class Filter {
    private final String name;
    private final Color color;
    private boolean isTicked;

    public Filter(String name, Color color) {
        this.name = name;
        this.color = color;
        this.isTicked = true;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void unTick() {
        isTicked = false;
    }

    public void tick() {
        isTicked = true;
    }

    public boolean isTicked() {
        return isTicked;
    }

}
