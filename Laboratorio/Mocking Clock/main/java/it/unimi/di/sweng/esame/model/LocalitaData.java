package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public record LocalitaData(@NotNull String loc, @NotNull LocalDate data) {
}
