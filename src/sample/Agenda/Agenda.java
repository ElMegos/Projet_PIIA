package sample.Agenda;

import javafx.geometry.Pos;
import sample.Main;
//import sample.Weather.Weather;
//import sample.Plante.Plante;
//import sample.PopUp.FilterPopUp;
//import sample.PopUp.PopUp;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Agenda extends BorderPane {

    /**
     * Const : Jour de la semaine
     */
    public final static DayOfWeek[] week = new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

    //private Plante plante;

    //private Weather weather;


    /**
     *  Var : HBox contenant tout les widgets
     */
    private final HBox WidgetMenu;

    /**
     * Var : HBox de
     */
    private HBox center = new HBox();


    private final DatePicker datePicker = new DatePicker(LocalDate.now());

    /**
     * Var : ArrayList contenant l'agenda de la semaine
     */
    private final ArrayList<VBox> agenda = new ArrayList<>();

    /**
     * Var : ArrayList contenant les events
     */
    private final ArrayList<Event> events = new ArrayList<>();

    /**
     * Var : ArrayList contenant les filtres
     */
    private final ArrayList<Filter> filters = new ArrayList<>();

    private final Stage stage;

    //Constructeur de la classe
    public Agenda(HBox WidgetMenu, final Stage stage) {

        //Ajout des filtres
        filters.add(new Filter("Fac", Color.BLUEVIOLET));
        filters.add(new Filter("Plante", Color.GREEN));
        filters.add(new Filter("Personnel",Color.BLUE));


        events.add(new Event(filters.get(2), LocalDate.now().with(week[0]), 6,8, "Cours de Bio-magie", ""));
        events.add(new Event(filters.get(1), LocalDate.now().with(week[5]), 12, 16, "Evenement 2", "plante"));
        events.add(new Event(filters.get(0), LocalDate.of(2022, 4, 12), 12, 16, "Evenement 3", ""));

        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        //Set le menu des widget en bas
        this.WidgetMenu = WidgetMenu;
        this.setBottom(WidgetMenu);
        WidgetMenu.setAlignment(Pos.TOP_CENTER);



        this.stage = stage;

        setButtonActions();
        Calendrier();
        filterButton();
        checkBoxes();
        AjoutHeure();
        bigAgenda();
    }

    private void filterButton() {
        Button button = new Button("Ajouter un filtre");
        button.setPrefSize(225, 50);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button.setTextFill(Color.WHITE);
        //button.setOnMouseClicked(mouseEvent -> new PopUp(stage, new FilterPopUp(this), "Createur de filtre"));
        WidgetMenu.getChildren().add(button);
    }

    private void checkBoxes() {
        VBox box = new VBox();
        for (Filter f : filters) {
            CheckBox checkBox = new CheckBox(f.getNom());
            checkBox.setTextFill(f.getCouleur());
            checkBox.setSelected(f.isEstCoche());
            checkBox.setPrefWidth(WidgetMenu.getPrefWidth());
            checkBox.setOnMouseClicked(mouseEvent -> {
                if (checkBox.isSelected()) f.tick();
                else f.unTick();
                center = new HBox();
                agenda.clear();
                bigAgenda();
            });
            box.getChildren().add(checkBox);
        }
        WidgetMenu.getChildren().add(box);
    }

    private boolean checkIfFilterIsTicked(Filter filter) {
        for (Filter f : filters) {
            if (f == filter && f.isEstCoche()) return true;
        }
        return false;
    }

    /**
     * Ajoute les heures pour l'agenda
     */
    private void AjoutHeure(){
        //On utilise une Vbox car on veux les heures sur une colonne vertical
        VBox names = new VBox();

        //Affichage des horaires sur le côté gauche de l'agenda
        for (int i = -3; i < 19; i++) {
            Text horaire;
            //Décalage nécessaire pour un bon affichage
            if (i == -1 || i==-2 || i==-3) {
                horaire = new Text(" ");
                horaire.setFont(new Font(11));
            }
            //Affichage des horaires une fois le décalage fait
            else {
                horaire = new Text(i+5 + "h");
                horaire.setFont(new Font(27));
            }
            horaire.setFill(Color.LIGHTBLUE);
            names.getChildren().add(horaire);
        }

        agenda.add(names);
    }


    /**
     * Création et affichage de toute les cellules de l'agenda
     */
    private void bigAgenda() {

        VBox names = new VBox();

        for (int i = 0; i < 7; i++) {

            VBox box = new VBox();


            CelluleDate dayCellule = new CelluleDate(stage, this, filters, DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now())
                    .equals(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(datePicker.getValue().with(week[i]))));

            // Adding the name of the week
            dayCellule.setPrefSize((Main.largeur - WidgetMenu.getPrefWidth() - names.getPrefWidth()) / 7, Main.hauteur / 25f);
            dayCellule.ajoutText(DateTimeFormatter.ofPattern("            EEEE dd MMMM", Locale.FRENCH).format(datePicker.getValue().with(week[i])), "");
            box.getChildren().add(dayCellule);



            // Adding other cells
            for (int j = 0; j < 17; j++) {
                Cellule cellule = new Cellule(LocalDate.now().with(week[i]), j, stage, this, filters, DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now())
                        .equals(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(datePicker.getValue().with(week[i]))));
                cellule.setPrefSize((Main.largeur - WidgetMenu.getPrefWidth() - names.getPrefWidth()) / 7, Main.hauteur / 25f);

                /* Displaying the event if there is one */
                for (Event e : events) {
                    if (e.getDay().compareTo(datePicker.getValue().with(week[i])) == 0 && e.getDebutEvent() <= j && e.getFinEvent() > j && checkIfFilterIsTicked(e.getFiltre())) {
                        cellule.ajoutEvent(e, e.getDebutEvent() == j);
                    }
                }
                box.getChildren().add(cellule);
            }
            agenda.add(box);
        }
        center.getChildren().addAll(agenda);
        setCenter(center);
    }

    public void createNewFilter(Filter f) {
        filters.add(f);
        WidgetMenu.getChildren().remove(5);
        checkBoxes();
    }

    public void createNewEvent(Event e) {
        this.events.add(e);
        center = new HBox();
        agenda.clear();
        bigAgenda();
    }

    /**
     * Affichage du calendrier
     */
    private void Calendrier() {

        HBox test = new HBox();

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        datePickerSkin.getPopupContent().setOnMouseClicked(mouseEvent -> {
            center = new HBox();
            //center.setAlignment(Pos.BOTTOM_LEFT);
            agenda.clear();
            bigAgenda();
        });
        test.getChildren().add(datePickerSkin.getPopupContent());
        test.setAlignment(Pos.BOTTOM_LEFT);
        setLeft(test);
        WidgetMenu.getChildren().add(test);

    }

    private void setButtonActions() {
        //left.getChildren().get(1).setOnMouseClicked(mouseEvent -> getScene().setRoot(plante));
        //left.getChildren().get(2).setOnMouseClicked(mouseEvent -> getScene().setRoot(weather));
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public Stage getStage() {
        return stage;
    }

    /*
    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    */

}
