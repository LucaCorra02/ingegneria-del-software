package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Fenomeno;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Previsione;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputForecastPresenter implements InputPresenter {
    @NotNull private InputView view;
    @NotNull private Clock clock;
    @NotNull private Model model;

    public InputForecastPresenter (@NotNull InputView view, @NotNull Model model) {
        this.view = view;
        view.addHandlers(this);
        clock = Clock.systemDefaultZone();
        this.model = model;
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1, @NotNull String text2) {
        try {
            if (text.isBlank())
                throw new IllegalArgumentException("empty location name");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(text2, df);
            if (date.isBefore(LocalDate.now(clock)))
                throw new IllegalArgumentException("forecast date cannot be in the past");
            Fenomeno fenomeno = Fenomeno.valueOf(text1);
            model.addPrevisione(new Previsione(text, fenomeno, date));
            view.showSuccess();
        } catch (DateTimeParseException e) {
            view.showError("incorrect data format (correct format: dd/mm/yyyy)");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().startsWith("No enum constant it.unimi.di.sweng.esame.model.Fenomeno."))
                view.showError("incorrect phenomena name");
            else
                view.showError(e.getMessage());
        }
    }
}
