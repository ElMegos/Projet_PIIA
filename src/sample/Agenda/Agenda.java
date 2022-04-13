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
    public final static DayOfWeek[] week = new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
    //private Plante plante;
    //private Weather weather;
    private final VBox left;
    private HBox center = new HBox();
    private final DatePicker datePicker = new DatePicker(LocalDate.now());
    private final ArrayList<VBox> days = new ArrayList<>();
    private final ArrayList<Event> events = new ArrayList<>();
    private final ArrayList<Filter> filters = new ArrayList<>();
    private final Stage stage;

    public Agenda(VBox left, final Stage stage) {
        filters.add(new Filter("Pas de filtre", Color.GREY)); // Always here

        /* Examples of filters & events */
        filters.add(new Filter("Fac", Color.GREEN));
        filters.add(new Filter("Travail", Color.BLUEVIOLET));
        //events.add(new Event(filters.get(1), LocalDate.now().with(week[0]), 12, "Evenement 1", ""));
        //events.add(new Event(filters.get(2), LocalDate.now().with(week[5]), 12, 16, "Evenement 2", "plante"));
        //events.add(new Event(filters.get(2), LocalDate.of(2021, 5, 12), 12, 16, "Evenement 3", ""));

        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        this.left = left;
        this.stage = stage;
        this.setLeft(left);
        setButtonActions();
        littleAgenda();
        filterButton();
        checkBoxes();
        bigAgenda();
    }

    private void filterButton() {
        Button button = new Button("Ajouter un filtre");
        button.setPrefSize(225, 50);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button.setTextFill(Color.WHITE);
        //button.setOnMouseClicked(mouseEvent -> new PopUp(stage, new FilterPopUp(this), "Createur de filtre"));
        left.getChildren().add(button);
    }

    private void checkBoxes() {
        VBox box = new VBox();
        for (Filter f : filters) {
            CheckBox checkBox = new CheckBox(f.getName());
            checkBox.setTextFill(f.getColor());
            checkBox.setSelected(f.isTicked());
            checkBox.setPrefWidth(left.getPrefWidth());
            checkBox.setOnMouseClicked(mouseEvent -> {
                if (checkBox.isSelected()) f.tick();
                else f.unTick();
                center = new HBox();
                days.clear();
                bigAgenda();
            });
            box.getChildren().add(checkBox);
        }
        left.getChildren().add(box);
    }

    private boolean checkIfFilterIsTicked(Filter filter) {
        for (Filter f : filters) {
            if (f == filter && f.isTicked()) return true;
        }
        return false;
    }

    private void bigAgenda() {
        VBox names = new VBox();
        for (int i = -3; i < 18; i++) {
            Text horaire;
            if (i == -1 || i==-2 || i==-3) {
                horaire = new Text(" ");
                horaire.setFont(new Font(11));
            }
            else {
                horaire = new Text(i+6 + "h");
                horaire.setFont(new Font(27));
            }
            horaire.setFill(Color.LIGHTBLUE);
            names.getChildren().add(horaire);
        }

        days.add(names);

        for (int i = 0; i < 7; i++) {
            VBox box = new VBox();
            Cell dayCell = new Cell(stage, this, filters, DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now())
                    .equals(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(datePicker.getValue().with(week[i]))));

            /* Adding the name of the week */
            dayCell.setPrefSize((Main.largeur - left.getPrefWidth() - names.getPrefWidth()) / 7, Main.hauteur / 25f);
            dayCell.ajoutText(DateTimeFormatter.ofPattern("            EEEE dd MMMM", Locale.FRENCH).format(datePicker.getValue().with(week[i])), "");
            box.getChildren().add(dayCell);

            /* Adding other cells */
            for (int j = 0; j < 18; j++) {
                Cell cell = new Cell(LocalDate.now().with(week[i]), j, stage, this, filters, DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now())
                        .equals(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(datePicker.getValue().with(week[i]))));
                cell.setPrefSize((Main.largeur - left.getPrefWidth() - names.getPrefWidth()) / 7, Main.hauteur / 25f);

                /* Displaying the event if there is one */
                for (Event e : events) {
                    if (e.getDay().compareTo(datePicker.getValue().with(week[i])) == 0 && e.getStartingTime() <= j && e.getEndingTime() > j && checkIfFilterIsTicked(e.getFilter())) {
                        cell.ajoutEvent(e, e.getStartingTime() == j);
                    }
                }
                box.getChildren().add(cell);
            }
            days.add(box);
        }
        center.getChildren().addAll(days);
        setCenter(center);
    }

    public void createNewFilter(Filter f) {
        filters.add(f);
        left.getChildren().remove(5);
        checkBoxes();
    }

    public void createNewEvent(Event e) {
        this.events.add(e);
        center = new HBox();
        days.clear();
        bigAgenda();
    }

    /**
     * Affichage du calendrier
     */
    private void littleAgenda() {


        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        datePickerSkin.getPopupContent().setOnMouseClicked(mouseEvent -> {
            center = new HBox();
            center.setAlignment(Pos.CENTER_LEFT);
            days.clear();
            bigAgenda();
        });
        left.getChildren().add(datePickerSkin.getPopupContent());
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
