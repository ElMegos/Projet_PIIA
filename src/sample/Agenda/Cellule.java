package sample.Agenda;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cellule extends Region{

    /**
     * Var : Date de la cellule
     */
    private LocalDate date;

    /**
     * Var : Début de l'evenement
     */
    private int debutEvent;

    /**
     * Var : Si la cellule a un event ou non
     */
    private boolean hasEvent;

    private final Stage stage;
    private final Agenda agenda;

    /**
     * Const : Un filtre pour chaque cellule
     */
    private final ArrayList<Filter> filters;


    //Constructeur pour le reste des cellules
    public Cellule(LocalDate date, int debutEvent, Stage stage, Agenda agenda, ArrayList<Filter> filters, boolean currentDay) {
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.hasEvent = false;
        this.date = date;
        this.debutEvent = debutEvent;
        this.stage = stage;
        this.agenda = agenda;
        this.filters = filters;
        if (currentDay)
            setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
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

    /**
     * Ajoute un evenement sur la case selectionne
     * @param e l'evenement
     * @param hasLabel
     */
    public void ajoutEvent(Event e, boolean hasLabel) {
        setBackground(new Background(new BackgroundFill(e.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        if (hasLabel) ajoutText(e.getNom(), e.getNomPlante());
    }


    /**
     * Crée une fenetre pour ajouter un evenement
     */
    private void updateOnClick() {
        if (!this.hasEvent)
            System.out.println("test updateOnClick");
            //setOnMouseClicked(mouseEvent -> new PopUp(stage, new EventPopUp(agenda, date, startingTime, filters), "Createur d'evenement"));
        else setOnMouseClicked(null);
    }
}
