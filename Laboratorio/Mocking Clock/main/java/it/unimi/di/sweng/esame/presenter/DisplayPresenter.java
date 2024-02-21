package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.Observer;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Previsione;
import it.unimi.di.sweng.esame.strategies.Strategy;
import it.unimi.di.sweng.esame.view.OutputView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisplayPresenter implements Observer<List<Previsione>> {
    @NotNull private final Strategy strategy;
    @NotNull private final OutputView view;

    public DisplayPresenter(@NotNull OutputView view, @NotNull Model model, @NotNull Strategy sortingStrategy) {
        this.view = view;
        this.strategy = sortingStrategy;
        model.addObserver(this);
    }

    @Override
    public void update(List<Previsione> state) {
        for (int i = 0; i < Main.SIZEVIEW; i++) {
            view.set(i, "");
        }
        int i = 0;
        for (Previsione p : strategy.getInOrderAndLimited(state)) {
            view.set(i, p.toString());
            i++;
        }
    }
}
