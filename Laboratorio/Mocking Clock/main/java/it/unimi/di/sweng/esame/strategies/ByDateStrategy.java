package it.unimi.di.sweng.esame.strategies;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Previsione;

import java.util.Comparator;
import java.util.List;

public class ByDateStrategy implements Strategy {

    @Override
    public List<Previsione> getInOrderAndLimited(List<Previsione> previsioni) {
        return previsioni.stream().sorted(Comparator.comparing(Previsione::data)).limit(Main.SIZEVIEW).toList();
    }
}
