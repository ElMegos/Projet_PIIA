package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.Agenda.Agenda;
//import sample.Weather.Weather;
//import sample.Plante.Plante;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Main extends Application {
    public final static int largeur = 1400;
    public final static int hauteur = 900;


    @Override
    public void start(Stage stage) throws Exception{
        Agenda a = new Agenda(createLeftMenu(), stage);

        Scene scene = new Scene(a, largeur, hauteur);
        stage.setTitle("Agenda Etudiant Jardinage");
        stage.setScene(scene);
        stage.show();


    }


    private HBox createLeftMenu() {

        HBox test = new HBox();

        Button b1 = new Button("Agenda");
        b1.setPrefSize(225, 50);
        b1.setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), CornerRadii.EMPTY, Insets.EMPTY)));
        b1.setTextFill(Color.WHITE);
        b1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        b1.setAlignment(Pos.CENTER);


        Button b2 = new Button("Plante");
        b2.setPrefSize(225, 50);
        b2.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        b2.setTextFill(Color.BLACK);
        b2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        b2.setAlignment(Pos.CENTER);

        test.getChildren().addAll(b1,b2);
        test.setAlignment(Pos.BOTTOM_RIGHT);

        return test;
        //return new HBox(b1, b2);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
