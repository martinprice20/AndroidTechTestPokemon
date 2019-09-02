package io.deepmatter.pokemon.core;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.deepmatter.pokemon.model.Card;
import io.deepmatter.pokemon.model.Rarity;
import io.deepmatter.pokemon.util.random.Randomiser;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoundFactoryImplTestJava {

    @Mock
    Randomiser mRandomiser;

    @Mock
    List<Card> mCardList = new ArrayList<>();

    private RoundFactory mFactory;
    private Card mCardOne;
    private Card mCardTwo;
    private Card mCardThree;

    @Before
    public void setUp() {
        mRandomiser = Mockito.mock(Randomiser.class);
        mFactory = new RoundFactoryImpl(mRandomiser);
        mCardOne = new Card("testImageURLOne", Rarity.Common.INSTANCE);
        mCardTwo = new Card("testImageURLTwo", Rarity.RareSecret.INSTANCE);
        mCardThree = new Card("testImageURLThree", Rarity.RareHolo.INSTANCE);

    }

    //Test to check that the RoundFactory getCard() method returns a valid Card object
    @Test
    public void get_card_test() {
        when(mFactory.getCard(mCardList)).thenReturn(mCardOne);
        Card testCard = mFactory.getCard(mCardList);

        Assert.assertEquals(testCard, mCardOne);
    }

    //Test to check that the RoundFactory getWinner() method returns the card with the highest rarity
    @Test
    public void check_rarest_card_wins() {
        Card winningCard = mFactory.getWinner(mCardOne, mCardTwo);

        Assert.assertEquals(mCardTwo, winningCard);
    }

    //Test to check that the RoundFactory checkCardRarity() method evaluates the Card object rarity correctly
    @Test
    public void check_card_rarity_int_vaue() {
        int rareHoloIntValue = 3;
        int testIntValue = mFactory.checkCardRarity(mCardThree);

        Assert.assertEquals(rareHoloIntValue, testIntValue);
    }
}
