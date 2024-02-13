package org.max.demo;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

@Disabled
public class ReportAllureTest {

    @Test
    void simplePassTest() {
        //given
        //when
        //then
        Assertions.assertTrue(true);
    }

    @Test
    void simpleUnPassTest() {
        //given
        //when
        //then
        Assertions.fail();
    }

    @Test
    void simpleErrorTest() {
        //given
        int a = 10;
        int b = 0;
        //when
        int c = a/b;
        //then
    }

    @Test
    @DisplayName("Для данного теста указано имя")
    void nameTest() {
        //given
        //when
        //then
        Assertions.assertTrue(true);
    }

    @Test
    @Description("Добавим описание для нашего теста")
    void descriptionTest() {
        //given
        //when
        //then
        Assertions.assertTrue(true);
    }

 //Пример со Step работает только при компиляции под java 8
 /*   @Test
    void stepTest() {
        //given
        given();
        //when
        when();
        //then
        then();
    }

    @Step
    public static void given(){
        System.out.println("given");
    };
    @Step
    public static void when(){
        System.out.println("when");
    };
    @Step
    public static void then(){
        System.out.println("then");
    };*/

    @Test
    @Link("http://gb.ru")
    @TmsLink("http://tms/100")
    @Issue("http://tms/100")
    void linkTest() {
        //given
        //when
        //then
        Assertions.fail();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void severityTest() {
        //given
        //when
        //then
        Assertions.fail();
    }

    @Test
    @Owner("Кравченко Максим")
    void ownerTest() {
        //given
        //when
        //then
        Assertions.assertTrue(true);
    }

    @Test
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    void epicStoryFeatureSuccessTest() {
        //given
        //when
        //then
        Assertions.fail();
    }

    @Test
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Вычитание")
    void epicStoryFeatureNotSuccessTest() {
        //given
        //when
        //then
        Assertions.assertTrue(true);
    }


}
