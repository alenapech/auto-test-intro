package org.max.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComponentPatternTest {

    @Test
    void onePortfolioTest() {
        //given
        Portfolio portfolio = new Portfolio();
        portfolio.add(new Account(100L));
        portfolio.add(new Account(200L));
        portfolio.add(new Account(300L));
        //when
        Long result = portfolio.getSum();
        //then
        Assertions.assertEquals(600L, result);
    }

    @Test
    void complexPortfolioTest() {
        //given
        Portfolio portfolioFirstLevel = new Portfolio();
        Portfolio portfolioNextLevelOne = new Portfolio();
        Portfolio portfolioNextLevelTwo = new Portfolio();

        portfolioFirstLevel.add(portfolioNextLevelOne);
        portfolioFirstLevel.add(portfolioNextLevelTwo);

        portfolioNextLevelOne.add(new Account(100L));
        portfolioNextLevelOne.add(new Account(200L));
        portfolioNextLevelOne.add(new Account(300L));

        portfolioNextLevelTwo.add(new Account(100L));
        portfolioNextLevelTwo.add(new Account(200L));
        portfolioNextLevelTwo.add(new Account(300L));
        //when
        Long result = portfolioFirstLevel.getSum();
        //then
        Assertions.assertEquals(1200L, result);
    }
}
