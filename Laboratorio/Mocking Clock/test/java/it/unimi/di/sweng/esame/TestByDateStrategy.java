package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Fenomeno;
import it.unimi.di.sweng.esame.model.Previsione;
import it.unimi.di.sweng.esame.strategies.ByDateStrategy;
import it.unimi.di.sweng.esame.strategies.Strategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestByDateStrategy {
    Strategy SUT = new ByDateStrategy();

    @Test
    void testSortAndLimit() {
        List<Previsione> previsioni = List.of(
                new Previsione("Milano", Fenomeno.HURRICANE, LocalDate.of(2024, 1, 1)),
                new Previsione("Milano", Fenomeno.RAINY, LocalDate.of(2024, 1, 2)),
                new Previsione("Roma", Fenomeno.THUNDERSTORM, LocalDate.of(2024, 1, 1)),
                new Previsione("Roma", Fenomeno.TYPHOON, LocalDate.of(2024, 1, 2)),
                new Previsione("Roma", Fenomeno.CYCLONE, LocalDate.of(2024, 1, 3))
        );
        assertThat(SUT.getInOrderAndLimited(previsioni)).containsExactly(
                new Previsione("Milano", Fenomeno.HURRICANE, LocalDate.of(2024, 1, 1)),
                new Previsione("Roma", Fenomeno.THUNDERSTORM, LocalDate.of(2024, 1, 1)),
                new Previsione("Milano", Fenomeno.RAINY, LocalDate.of(2024, 1, 2)),
                new Previsione("Roma", Fenomeno.TYPHOON, LocalDate.of(2024, 1, 2))
        );
    }
}
