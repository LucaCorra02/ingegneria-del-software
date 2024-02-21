package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.Observable;
import it.unimi.di.sweng.esame.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Model extends State implements Observable<List<Previsione>> {
    private List<Observer<List<Previsione>>> observers;

    public Model() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addPrevisione(@NotNull Previsione p) {
        super.addPrevisione(p);
        notifyObservers();
    }

    @Override
    public void addObserver(@NotNull Observer<List<Previsione>> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Previsione>> observer : observers) {
            observer.update(getState());
        }
    }

    @Override
    public @NotNull List<Previsione> getState() {
        return getPrevisioni();
    }
}
