package sample.Agenda;


import javafx.scene.paint.Color;

import java.time.LocalDate;

public class Event {
    private final LocalDate date;
    private final int startingTime;
    private final int endingTime;
    private final String label;
    private final Filter filter;
    private final String plantName;

    public Event(Filter filter, LocalDate date, int startingTime, int endingTime, String label, String plantName) {
        this.filter = filter;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.label = label;
        this.plantName = plantName;
    }

    public Event(Filter filter, LocalDate date, int startingTime, String label, String plantName) {
        this.filter = filter;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = startingTime + 1;
        this.label = label;
        this.plantName = plantName;
    }

    public Color getColor() {
        return filter.getColor();
    }

    public LocalDate getDay() {
        return date;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public String getLabel() {
        return label;
    }

    public Filter getFilter() {
        return filter;
    }

    public String getPlantName() {
        return plantName;
    }
}