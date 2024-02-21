package it.unimi.di.sweng.esame.strategies;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Previsione;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupByCityStrategy implements Strategy {
    @Override
    public List<Previsione> getInOrderAndLimited(List<Previsione> previsioni) {
        previsioni.sort(Comparator.comparing(Previsione::data));
        List<Previsione> result = new ArrayList<>();
        for (Previsione p : previsioni) {
            if (result.size() >= Main.SIZEVIEW) return result;
            if (result.stream().noneMatch(x -> {
                return x.loc().equals(p.loc());
            }))
                result.add(p);
        }
        return result;
    }
}
