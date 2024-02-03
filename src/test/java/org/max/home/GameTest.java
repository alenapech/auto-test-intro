package org.max.home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Тест кейс для тестирования методов порадокса
 */
public class GameTest {

    private static List<Door> doors;

    @BeforeEach
    void setUp() {
        doors = new ArrayList<>();
        doors.add(new Door(true));
        doors.add(new Door(false));
        doors.add(new Door(false));
    }

    private static Stream<Arguments> roundArgs() {
        return Stream.of(
                Arguments.of(new Player("1", true)
                        , 0
                        , false),
                Arguments.of(new Player("1", true)
                        , 1
                        , true),
                Arguments.of(new Player("1", true)
                        , 2
                        , true),
                Arguments.of(new Player("1", false)
                        , 0
                        , true),
                Arguments.of(new Player("1", false)
                        , 1
                        , false),
                Arguments.of(new Player("1", false)
                        , 2
                        , false)
        );
    }

    @ParameterizedTest
    @MethodSource("roundArgs")
    void round(Player player, int door, boolean expectedResult) {
        Game game = new Game(player, doors);
        assertEquals(expectedResult, game.round(door).isPrize());
    }
}
