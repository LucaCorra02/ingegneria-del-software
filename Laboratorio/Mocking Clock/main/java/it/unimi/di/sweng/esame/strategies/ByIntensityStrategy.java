package it.unimi.di.sweng.esame.strategies;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Previsione;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ByIntensityStrategy implements Strategy {
    @Override
    public @NotNull List<@NotNull Previsione> getInOrderAndLimited(@NotNull List<@NotNull Previsione> previsioni) {
        return previsioni.stream().sorted(Comparator.comparing(Previsione::fenomeno)).limit(Main.SIZEVIEW).toList();
    }
}
