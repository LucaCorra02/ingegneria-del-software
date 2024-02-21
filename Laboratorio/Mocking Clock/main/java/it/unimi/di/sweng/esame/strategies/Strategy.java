package it.unimi.di.sweng.esame.strategies;

import it.unimi.di.sweng.esame.model.Previsione;

import java.util.List;

public interface Strategy {
    List<Previsione> getInOrderAndLimited(List<Previsione> previsioni);
}
