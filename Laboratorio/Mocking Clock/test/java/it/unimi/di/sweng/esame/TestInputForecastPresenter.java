package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.InputForecastPresenter;
import it.unimi.di.sweng.esame.view.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestInputForecastPresenter {
    InputView view = mock(InputView.class);
    Model model = mock(Model.class);
    @Spy
    Clock clock = Clock.fixed(LocalDate.of(2024, 1, 24).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    @InjectMocks
    InputForecastPresenter SUT = new InputForecastPresenter(view, model);

    @Test
    void testEmptyLocation() {
        SUT.action("","HURRICANE", "01/02/2024");
        verify(view).showError("empty location name");
    }

    @Test
    void TestFormatoDataErrato() {
        SUT.action("Milano", "RAINY", "1/2/2024");
        verify(view).showError("incorrect data format (correct format: dd/mm/yyyy)");
    }

    @Test
    void TestFormatoDataCorretto() {
        SUT.action("Milano", "RAINY", "01/02/2024");
        verify(view, never()).showError(anyString());
    }

    @Test
    void TestDataPassata() {
        SUT.action("Milano", "RAINY", "01/02/2023");
        verify(view).showError("forecast date cannot be in the past");
    }

    @Test
    void TestFenomenoNonValido() {
        SUT.action("Milano", "BOH", "01/02/2024");
        verify(view).showError("incorrect phenomena name");
    }
}
