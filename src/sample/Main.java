package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.Agenda.Agenda;
//import sample.Weather.Weather;
//import sample.Plante.Plante;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public final static int largeur = 1400;
    public final static int hauteur = 800;


    @Override
    public void start(Stage stage) throws Exception{
        Agenda a = new Agenda(createLeftMenu(), stage);

        Scene scene = new Scene(a, largeur, hauteur);
        stage.setTitle("Agenda Etudiant Jardinage");
        stage.setScene(scene);
        stage.show();


    }


    private VBox createLeftMenu() {

        Button b1 = new Button("Agenda");
        b1.setPrefSize(225, 50);
        b1.setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), CornerRadii.EMPTY, Insets.EMPTY)));
        b1.setTextFill(Color.WHITE);
        b1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Button b2 = new Button("Plante");
        b2.setPrefSize(225, 50);
        b2.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        b2.setTextFill(Color.BLACK);
        b2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        return new VBox(b1, b2);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
