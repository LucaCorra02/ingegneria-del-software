package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CardUtility {

    public static List<Card> fromStringList(@NotNull String cards){
        List<Card> mano = new ArrayList<>();
        var input = cards.split(" ");

        for(String s: input){
           s = s.toUpperCase();
           mano.add(Card.get(getRank(s.substring(0,s.length()-1)), getSuit(s.charAt(s.length()-1))));
        }
        return mano;
    }
    public static Card toCardFromString(@NotNull String str){
        var s = getSuit(str.charAt(str.length()-1));
        var r = getRank(str.substring(0,str.length()-1));
        return Card.get(r,s);
    }

    private static Rank getRank(String rank){
        return switch (rank) {
            case "A" -> Rank.ACE;
            case "2" -> Rank.TWO;
            case "3" -> Rank.THREE;
            case "4" -> Rank.FOUR;
            case "5" -> Rank.FIVE;
            case "6" -> Rank.SIX;
            case "7" -> Rank.SEVEN;
            case "8" -> Rank.EIGHT;
            case "9" -> Rank.NINE;
            case "10" -> Rank.TEN;
            case "J" -> Rank.JACK;
            case "Q" -> Rank.QUEEN;
            case "K" -> Rank.KING;
            default -> throw new IllegalArgumentException("Rank non valido");
        };
    }
    private static Suit getSuit(char suit){
        return switch (suit) {
            case 'D' -> Suit.DIAMONDS;
            case 'C' -> Suit.CLUBS;
            case 'H' -> Suit.HEARTS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalArgumentException("Seme non valido");
        };
    }







}
