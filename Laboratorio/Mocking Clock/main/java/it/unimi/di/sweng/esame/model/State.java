package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private Map<LocalitaData, Fenomeno> previsioni = new HashMap<>();

    public void addPrevisione(@NotNull Previsione p) {
        previsioni.put(new LocalitaData(p.loc(), p.data()), p.fenomeno());
    }

    public @NotNull List<Previsione> getPrevisioni() {
        List<Previsione> result = new ArrayList<>();
        for (Map.Entry<LocalitaData, Fenomeno> entry : previsioni.entrySet()) {
            result.add(new Previsione(entry.getKey().loc(), entry.getValue(), entry.getKey().data()));
        }
        return result;
    }


}
