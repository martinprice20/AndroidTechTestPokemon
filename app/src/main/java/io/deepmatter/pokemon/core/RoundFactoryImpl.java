package io.deepmatter.pokemon.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.deepmatter.pokemon.model.Card;
import io.deepmatter.pokemon.model.Rarity;
import io.deepmatter.pokemon.util.random.Randomiser;
import io.deepmatter.pokemon.viewmodel.Round;

import static io.deepmatter.pokemon.model.Rarity.Common;
import static io.deepmatter.pokemon.model.Rarity.Rare;
import static io.deepmatter.pokemon.model.Rarity.RareHolo;
import static io.deepmatter.pokemon.model.Rarity.RareSecret;
import static io.deepmatter.pokemon.model.Rarity.RareUltra;
import static io.deepmatter.pokemon.model.Rarity.Uncommon;

public class RoundFactoryImpl implements RoundFactory{

    private Randomiser randomiser;

    @Inject
    Rarity mRarity;

    public RoundFactoryImpl(Randomiser randomiser) {
        this.randomiser = randomiser;
    }

    @NotNull
    @Override
    public Round buildRound(@NotNull List<Card> cards) {
        List<Card> roundCardList = new ArrayList<>();
        Card cardOne = getCard(cards);
        Card cardTwo = cardOne;
        while (cardOne.getRarity() == cardTwo.getRarity()) {
            cardTwo = getCard(cards);
        }
        roundCardList.add(cardOne);
        roundCardList.add(cardTwo);
        return new Round(roundCardList, getWinner(cardOne,cardTwo));

    }

    @NotNull
    @Override
    public Card getCard(List<Card> cards) {
        int size = cards.size() -1;
        return cards.get(randomiser.getIntInRange(0, size));
    }

    @NotNull
    @Override
    public Card getWinner(@NotNull Card cardOne, @NotNull Card cardTwo) {
        Card winningCard = cardTwo;
        int cardOneRarity = checkCardRarity(cardOne);
        int cardTwoRarity = checkCardRarity(cardTwo);
        if ( cardOneRarity > cardTwoRarity) {
            winningCard = cardOne;
        }
        return winningCard;
    }

    @Override
    public int checkCardRarity(Card card) {
        int rarity;
        Rarity cardRarity = card.getRarity();
        if (Common.INSTANCE.equals(cardRarity)) {
            rarity = 0;
        } else if (Uncommon.INSTANCE.equals(cardRarity)) {
            rarity = 1;
        } else if (Rare.INSTANCE.equals(cardRarity)) {
            rarity = 2;
        } else if (RareHolo.INSTANCE.equals(cardRarity)) {
            rarity = 3;
        } else if (RareUltra.INSTANCE.equals(cardRarity)) {
            rarity = 4;
        } else if (RareSecret.INSTANCE.equals(cardRarity)) {
            rarity = 5;
        } else {
            rarity = 0;
        }
        return rarity;
    }

}
