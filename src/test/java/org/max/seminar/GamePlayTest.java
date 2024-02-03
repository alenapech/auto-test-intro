package org.max.seminar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.seminar.type.Ranks;
import org.max.seminar.type.Suits;

/**
 * Тест кейс для проверки условий определения победителя
 */
public class GamePlayTest extends AbstractTest {

    @Test
    void checkGamerWin21 () {
        //given
        game.getGamer().setRisk(2);
        game.getCasino().setRisk(0);
        //when
        game.getGamer().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getGamer().addCard(new Card(Ranks.TEN, Suits.CLUBS));
        //then
        Assertions.assertEquals(game.getGamer(), game.getWinner(game.getGamer(), game.getCasino()));
    }

    @Test
    void checkCasinoWin21 () {
        //given
        game.getGamer().setRisk(2);
        game.getCasino().setRisk(0);
        //when
        game.getCasino().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getCasino().addCard(new Card(Ranks.TEN, Suits.CLUBS));
        //then
        Assertions.assertEquals(game.getCasino(), game.getWinner(game.getGamer(), game.getCasino()));
    }

    @Test
    void checkGamerWin () {
        //given
        game.getGamer().setRisk(3);
        game.getCasino().setRisk(3);
        //when
        game.getGamer().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getGamer().addCard(new Card(Ranks.NINE, Suits.CLUBS));
        game.getCasino().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getCasino().addCard(new Card(Ranks.EIGHT, Suits.CLUBS));
        //then
        Assertions.assertEquals(game.getGamer(), game.getWinner(game.getGamer(), game.getCasino()));
    }

    @Test
    void checkCasinoWin () {
        //given
        game.getGamer().setRisk(3);
        game.getCasino().setRisk(3);
        //when
        game.getGamer().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getGamer().addCard(new Card(Ranks.EIGHT, Suits.CLUBS));
        game.getCasino().addCard(new Card(Ranks.ACE, Suits.CLUBS));
        game.getCasino().addCard(new Card(Ranks.NINE, Suits.CLUBS));
        //then
        Assertions.assertEquals(game.getCasino(), game.getWinner(game.getGamer(), game.getCasino()));
    }

    @AfterEach
    void newGame() {
        game.getGamer().newGame();
        game.getCasino().newGame();
    }
}
