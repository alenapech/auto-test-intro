package org.max.seminar;

import org.junit.jupiter.api.BeforeAll;

/**
 * абстрактный тест для игры BlackJack
 */
public abstract class AbstractTest {

    static Game game;

    @BeforeAll
    static void init() {
        game = new Game();
    }
}
