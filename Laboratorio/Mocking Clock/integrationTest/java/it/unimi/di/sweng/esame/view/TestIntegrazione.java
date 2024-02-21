package it.unimi.di.sweng.esame.view;


import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

import it.unimi.di.sweng.esame.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {


  private static final boolean HEADLESS = false;

  private InputForecastView input;
  private DisplayView displatByDate;
  private DisplayView displayByCriticality;
  private DisplayView displayLastOfEachLocation;


  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage primaryStage) {

    Main m = new Main();
    m.start(primaryStage);

    GridPane gp = (GridPane) primaryStage.getScene().getRoot();

    ObservableList<Node> view = gp.getChildren();

    input = (InputForecastView) view.get(0);
    displayLastOfEachLocation = (DisplayView) view.get(1);
    displatByDate = (DisplayView) view.get(2);
    displayByCriticality = (DisplayView) view.get(3);

  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      '',HURRICANE, 01/02/2024, empty location name
      Milano, THUNDERSTORM, 01/02/2023, forecast date cannot be in the past
      Milano, RAINY, 1/2/2024, incorrect data format (correct format: dd/mm/yyyy)
      Milano, BOH, 07/02/2024, incorrect phenomena name    
      """)
  void testInputError(String name, String phenomena, String date, String error, FxRobot robot) {
    writeOnGui(robot, input.text, name);
    writeOnGui(robot, input.text1, phenomena);
    writeOnGui(robot, input.time, date);
    robot.clickOn(input.addButton);

    verifyThat(input.error, hasText(error));
  }

  @Test
  public void testDisplayStart(FxRobot robot) {
    assertThat(displayLastOfEachLocation.get(0)).isEqualTo("");
    assertThat(displatByDate.get(0)).isEqualTo("");
    assertThat(displayByCriticality.get(0)).isEqualTo("");
  }


  @Test
  void testCorrectSingleInput(FxRobot robot) {
    writeOnGui(robot, input.text, "Milano");
    writeOnGui(robot, input.text1, "HURRICANE");
    writeOnGui(robot, input.time, "06/08/2024");
    robot.clickOn(input.addButton);

    verifyThat(input.error, hasText(""));

    //in questo modo vengono testate tutte e tre le asserzioni anche se ne fallisce qualcuna
    SoftAssertions.assertSoftly(sa -> {
      sa.assertThat(displayLastOfEachLocation.get(0)).startsWith("Milano").endsWith("06/08/2024");
      sa.assertThat(displatByDate.get(0)).startsWith("Milano").endsWith("06/08/2024");
      sa.assertThat(displayByCriticality.get(0)).startsWith("Milano").endsWith("06/08/2024");
    });

  }

  @Test
  void testCorrectMultInputWithSameLocation(FxRobot robot) {
    writeOnGui(robot, input.text, "Milano");
    writeOnGui(robot, input.text1, "HURRICANE");
    writeOnGui(robot, input.time, "06/08/2024");

    robot.clickOn(input.addButton);

    writeOnGui(robot, input.text, "Milano");
    writeOnGui(robot, input.text1, "TYPHOON");
    writeOnGui(robot, input.time, "06/08/2024");

    robot.clickOn(input.addButton);

    writeOnGui(robot, input.text, "Milano");
    writeOnGui(robot, input.text1, "RAINY");
    writeOnGui(robot, input.time, "07/08/2024");

    robot.clickOn(input.addButton);

    verifyThat(input.error, hasText(""));
    assertThat(displatByDate.get(0)).contains("TYPHOON");
    assertThat(displatByDate.get(1)).contains("RAINY");
  }

  private void writeOnGui(FxRobot robot, TextField input, String Milano) {
    robot.doubleClickOn(input);
    robot.write(Milano);
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
  }
}
