package sample.Agenda;


import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;


public class CelluleDate extends Region{

    private LocalDate date;
    private int startingTime;
    private boolean hasEvent;
    private final Stage stage;
    private final Agenda agenda;
    private final ArrayList<Filter> filters;

    //Constructeur des cellules pour les dates
    public CelluleDate(Stage stage, Agenda agenda, ArrayList<Filter> filters, boolean currentDay){
        this.hasEvent = false;
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.stage = stage;
        this.agenda = agenda;
        this.filters = filters;
        if (currentDay)
            setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        //updateOnClick();
    }


    /**
     * Ajoute le texte ou une plante mis en paramètre
     * @param text texte voulu
     * @param plant plante voulu
     */
    public void ajoutText(String text, String plant) {
        this.hasEvent = true;
        //updateOnClick();
        Text txt = new Text(text);
        txt.setFill(Color.BLACK);
        VBox box = new VBox(txt);
        getChildren().setAll(box);
    }
}
