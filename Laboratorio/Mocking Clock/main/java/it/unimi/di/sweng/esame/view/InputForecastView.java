package it.unimi.di.sweng.esame.view;

import it.unimi.di.sweng.esame.presenter.InputPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class InputForecastView extends Region implements InputView {

  final TextField text = new TextField();
  final TextField text1 = new TextField();
  final TextField time = new TextField();
  final Button addButton = new Button("Add");
  final Label error = new Label("");


  public InputForecastView() {
    setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
    setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

    time.setPrefColumnCount(6);
    GridPane grid = new GridPane();

    text.setPromptText("Location");
    text1.setPromptText("Weather phenomena");
    time.setPromptText("Time");
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.add(text, 0, 0);
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.add(text1, 1, 0);
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.add(time, 0, 1);
    grid.add(addButton, 1, 1);
    grid.add(error, 0, 2);
    GridPane.setColumnSpan(error, GridPane.REMAINING);

    this.getChildren().add(grid);
  }

  public void addHandlers(@NotNull InputPresenter presenter) {
    addButton.setOnAction(eh -> presenter.action(text.getText(), text1.getText(), time.getText()));
  }

  @Override
  public void showError(@NotNull String s) {
    error.setText(s);
    setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
  }

  @Override
  public void showSuccess() {
    error.setText("");
    text.clear();
    text1.clear();
    time.clear();
    setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
  }
}
