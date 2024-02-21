package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record Previsione(@NotNull String loc, @NotNull Fenomeno fenomeno, @NotNull LocalDate data) {
    @Override
    public String toString() {
        return String.format("%s %s %s", loc, fenomeno, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
