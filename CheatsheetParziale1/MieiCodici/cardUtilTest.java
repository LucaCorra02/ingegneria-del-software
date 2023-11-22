package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.awt.image.CropImageFilter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class cardUtilTest {
    @Test
    void testCard() {
        var mano = List.of(
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.TEN,Suit.SPADES),
                Card.get(Rank.KING,Suit.DIAMONDS)
        );
        var ris = CardUtility.fromStringList("4c 10S KD");
        assertThat(ris.toString()).isEqualTo(mano.toString());
    }

    @Test
    void testExceptionRank() {
        var mano = List.of(
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.TEN,Suit.SPADES),
                Card.get(Rank.KING,Suit.DIAMONDS)
        );
        var str =  "LC 10S KD";
        assertThatThrownBy(() -> CardUtility.fromStringList(str))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Rank non valido");
    }

}
