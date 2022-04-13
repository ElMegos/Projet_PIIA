package sample.Agenda;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cell extends Region{

    private LocalDate date;
    private int startingTime;
    private boolean hasEvent;
    private final Stage stage;
    private final Agenda agenda;
    private final ArrayList<Filter> filters;

    //Constructeur des cellules pour les dates
    public Cell(Stage stage, Agenda agenda, ArrayList<Filter> filters, boolean currentDay){
        this.hasEvent = false;
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.stage = stage;
        this.agenda = agenda;
        this.filters = filters;
        if (currentDay)
            setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        updateOnClick();
    }

    //Constructeur pour le reste des cellules
    public Cell(LocalDate date, int startingTime, Stage stage, Agenda agenda, ArrayList<Filter> filters, boolean currentDay) {
        this.hasEvent = false;
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.date = date;
        this.startingTime = startingTime;
        this.stage = stage;
        this.agenda = agenda;
        this.filters = filters;
        if (currentDay)
            setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        updateOnClick();
    }

    /**
     * Ajoute le texte ou une plante mis en paramètre
     * @param text texte voulu
     * @param plant plante voulu
     */
    public void ajoutText(String text, String plant) {
        this.hasEvent = true;
        updateOnClick();
        Text txt = new Text(text);
        txt.setFill(Color.BLACK);
        VBox box = new VBox(txt);
        if (!plant.equals("")) box.getChildren().add(new Text(plant));
        getChildren().setAll(box);
    }

    public void ajoutEvent(Event e, boolean hasLabel) {
        setBackground(new Background(new BackgroundFill(e.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        if (hasLabel) ajoutText(e.getLabel(), e.getPlantName());
    }


    private void updateOnClick() {
        if (!this.hasEvent)
            System.out.println("test updateOnClick");
            //setOnMouseClicked(mouseEvent -> new PopUp(stage, new EventPopUp(agenda, date, startingTime, filters), "Createur d'evenement"));
        else setOnMouseClicked(null);
    }
}
