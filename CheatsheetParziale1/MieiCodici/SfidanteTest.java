package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) //toglie unecessary stubbing
public class SfidanteTest {

    @Mock
    private Mazziere banco;
    @InjectMocks
    private Sfidante SUT;

    @BeforeEach
    void inizializzaMazzo(){
        when(banco.getCarta()).thenAnswer(AdditionalAnswers.returnsElementsOf(
                CardUtility.fromStringList("8D AC QH KC 3S")
        ));
    }

    @Test
    void carteIniziali(){
        when(banco.getCarta()).thenAnswer(AdditionalAnswers.returnsElementsOf(
                CardUtility.fromStringList("KC AC")
        ));
        SUT.carteIniziali();
        assertThat(SUT.getCards()).toIterable().containsExactlyInAnyOrder(
            CardUtility.toCardFromString("KC"),
            CardUtility.toCardFromString("AC")
        );
    }

    @Test
    void getNameTest(){
        var giocatore = new Sfidante("Prova",banco);
        assertThat(giocatore.getName()).isEqualTo("Prova");
    }

    @ParameterizedTest
    @CsvSource({
        "29,8D AC QH KC 3S",
        "21,AC AC 9S KS"
    })
    void giocaTest(int points, String cards){
        var strategia = Mockito.mock(Strategia.class);
        when(strategia.chiediCarta()).thenReturn(true);

        when(banco.getCarta()).thenAnswer(AdditionalAnswers.returnsElementsOf(
                CardUtility.fromStringList(cards)
            )
        );

        SUT.setStrategia(strategia); //injecto la strategia mockata
        SUT.carteIniziali();
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(points);
    }

}
