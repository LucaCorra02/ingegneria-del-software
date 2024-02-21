package it.unimi.di.sweng.esame;


import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.DisplayPresenter;
import it.unimi.di.sweng.esame.presenter.InputForecastPresenter;
import it.unimi.di.sweng.esame.strategies.ByDateStrategy;
import it.unimi.di.sweng.esame.strategies.ByIntensityStrategy;
import it.unimi.di.sweng.esame.strategies.GroupByCityStrategy;
import it.unimi.di.sweng.esame.view.DisplayView;
import it.unimi.di.sweng.esame.view.InputForecastView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  public static final int SIZEVIEW = 4;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Meteo Forecast");

    InputForecastView input = new InputForecastView();

    DisplayView nextOfEachLocation = new DisplayView(SIZEVIEW, "NEXT FORECAST FOR EACH LOCATION");

    DisplayView byDate = new DisplayView(SIZEVIEW, "BY DATE");
    DisplayView byCriticality = new DisplayView(SIZEVIEW, "BY  CRITICALITY");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(input, 0, 0);
    GridPane.setColumnSpan(input, GridPane.REMAINING);

    gridPane.add(nextOfEachLocation, 0, 1);
    GridPane.setColumnSpan(nextOfEachLocation, GridPane.REMAINING);

    gridPane.add(byDate, 0, 2);
    gridPane.add(byCriticality, 1, 2);


    Model model = new Model();
    new InputForecastPresenter(input, model);
    new DisplayPresenter(nextOfEachLocation, model, new GroupByCityStrategy());
    new DisplayPresenter(byDate, model, new ByDateStrategy());
    new DisplayPresenter(byCriticality, model, new ByIntensityStrategy());
    model.notifyObservers();

    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
