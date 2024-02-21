package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Fenomeno;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Previsione;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TestModel {
    private Model SUT = new Model();

    @Test
    void testNuovaPrevisione() {
        SUT.addPrevisione(new Previsione("Milano", Fenomeno.HURRICANE, LocalDate.of(2024, 1, 24)));
        assertThat(SUT.getPrevisioni()).containsExactly(new Previsione("Milano", Fenomeno.HURRICANE, LocalDate.of(2024, 1, 24)));
    }

}
