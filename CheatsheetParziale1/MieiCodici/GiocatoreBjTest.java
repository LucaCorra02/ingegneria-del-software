package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static it.unimi.di.sweng.blackjack.MockUtils.whenIterated;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GiocatoreBjTest {

    @Mock
    Mazziere mazziere = Mockito.mock(Mazziere.class);

    @Test
    void giocatoreBjTest(){
        var mano = List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.EIGHT,Suit.DIAMONDS)
        );
        when(mazziere.getPunti()).thenCallRealMethod();
        when(mazziere.getCards()).thenReturn(mano.iterator());
        assertThat(mazziere.getPunti()).isEqualTo(19);
    }

    @Test
    void giocatoreBjAssoTest(){
        var mano = List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.ACE,Suit.CLUBS),
                Card.get(Rank.EIGHT,Suit.DIAMONDS)
        );
        when(mazziere.getPunti()).thenCallRealMethod();
        when(mazziere.getCards()).thenReturn(mano.iterator());
        assertThat(mazziere.getPunti()).isEqualTo(20);
    }


    @ParameterizedTest
    @CsvSource({
        "AD AC 8D,20",
        "AD 8D,19",
        "AD AC KS,22"
    })
    void testGetPuntiParam(String str,Integer points){
        var mano = CardUtility.fromStringList(str);

        Sfidante SUT = mock(Sfidante.class);
        when(SUT.getPunti()).thenCallRealMethod();
        when(SUT.getCards()).thenAnswer(invocation -> mano.iterator());

        assertThat(SUT.getPunti()).isEqualTo(points);
    }

    @Test
    void testGetPunti (){
        Sfidante SUT = mock(Sfidante.class);
        when(SUT.getPunti()).thenCallRealMethod();
        List<Card> mano = List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.ACE,Suit.CLUBS),
                Card.get(Rank.EIGHT,Suit.DIAMONDS)
        );
        //when(SUT.getCards()).thenAnswer((Answer<Iterator<Card>>) invocation -> mano.iterator());
        when(SUT.getCards()).thenAnswer(invocation -> mano.iterator());
        assertThat(SUT.getPunti()).isEqualTo(20);
        assertThat(SUT.getPunti()).isEqualTo(20);

    }




}
