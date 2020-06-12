package fr.univ_amu.iut.ihm;

import fr.univ_amu.iut.utilitaires.Analyseur;
import fr.univ_amu.iut.utilitaires.ErreurDeSyntaxe;
import fr.univ_amu.iut.utilitaires.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TraceurDeFonction extends Application {

  Stage primaryStage;
  Button btnTracer;
  Button btnAnalyser;
  Button btnEffacer;
  TextField textToAnalyze;
  TextField xMin;
  TextField xMax;
  Text resultat;

  void calculCoeffTransformationsAffines() {
  }
  	
  int transformationAffineY(double y) {
    /*Double Ax = primaryStage.getWidth() / */

    return 0;
  }

  int transformationAffineX(double x) {
    return 0;
  }

  private void setIds() {
    textToAnalyze.setId("texteAAnalyser");
    btnAnalyser.setId("demandeAnalyser");
    btnTracer.setId("demandeTracer");
    btnEffacer.setId("demandeEffacer");
    resultat.setId("resultatAnalyse");
       /*

      votreIdentificateur.setId("zoneTraceCourbe");
      votreIdentificateur.setId("choixXMin");
      votreIdentificateur.setId("choixXMax");
      votreIdentificateur.setId("choixEspacementX_v1");
      votreIdentificateur.setId("choixEspacementX_v2");
      votreIdentificateur.setId("choixEspacementY_v1");
      votreIdentificateur.setId("choixEspacementY_v2");
      votreIdentificateur.setId("choixCouleur");
      votreIdentificateur.setId("choixEpaisseur");
      votreIdentificateur.setId("segmentsATracer");
      votreIdentificateur.setId("quadrillage");
      votreIdentificateur.setId("axeX");
      votreIdentificateur.setId("axeY"); */
  }
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {

    //FENETRE
    primaryStage = stage;
    primaryStage.setTitle("Traceur de fonction");

    //EXPRESSION_FIELD
    textToAnalyze = new TextField();
    textToAnalyze.setText("exp(-x * 0.2) * sin(x)");

    //XMIN
    xMin = new TextField();
    xMin.setText("-0.5");

    //XMAX
    xMax = new TextField();
    xMax.setText("20");

    //BOUTON_ANALYSER
    btnAnalyser = new Button();
    btnAnalyser.setText("Analyser");
    btnAnalyser.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        try {
          System.out.println(textToAnalyze.getText());
          Analyseur analyseur = new Analyseur("exp(-x * 0.2) * sin(x)");
          Expression expression = analyseur.analyser();
          resultat.setText(expression.toString());
          CalculateurPointsFonction calculs = new CalculateurPointsFonction(expression, Double.parseDouble(xMin.getText()), Double.parseDouble(xMax.getText()));
        } catch (ErreurDeSyntaxe | IOException erreurDeSyntaxe) {
          erreurDeSyntaxe.printStackTrace();
        }
      }
    });

    btnAnalyser.setTranslateY(210);

    xMin.setTranslateX(-70);
    xMin.setTranslateY(210);
    xMin.setMaxWidth(60);

    xMax.setTranslateX(70);
    xMax.setTranslateY(210);
    xMax.setMaxWidth(60);

    resultat = new Text();
    resultat.setFont(new Font(20));
    resultat.setText("");

    textToAnalyze.setTranslateY(180);

    //ELEMENTS
    StackPane root = new StackPane();
        ///BOUTON_TRACER
        root.getChildren().add(btnAnalyser);
        //EXPRESSION_FIELD
        root.getChildren().add(textToAnalyze);

        //XMIN
        root.getChildren().add(xMin);

        //XMAX
        root.getChildren().add(xMax);

        //RESULT

        root.getChildren().add(resultat);

    primaryStage.setResizable(false);
    Scene scene = new Scene(root, 600, 650);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}