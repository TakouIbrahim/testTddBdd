package com.example.learningtddbdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.assertj.core.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorTests {

    private Calculator calculatorUnderTest;

    private static Instant startedAt;

    @BeforeAll
    static void initStatingTime(){
        System.out.println("avant tout les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    static void showTestDuration(){
        System.out.println("après tous les tests");
        Instant endAt = Instant.now();
        long duration = Duration.between(startedAt, endAt).toMillis();
        System.out.println(MessageFormat.format("durée des tests : {0} ms ", duration));
    }

    @BeforeEach
    void initCalculator(){
        calculatorUnderTest = new Calculator();
        System.out.println("avant chaque test");
    }

    @AfterEach
    void undefCalculator(){
        System.out.println("après chaque test");
        calculatorUnderTest = null;
    }

    @Test
    void testAddTwoPositiveNumbers(){
        //ARANGE
        int a = 5; int b = 6;

        // ACC
        int somme = calculatorUnderTest.add(a, b);

        // ASSERT
        Assertions.assertThat(somme).isEqualTo(11);

    }

    @Test
    void shouldTwoPositiveNumber(){
        //ARRANGE
        int a =5;
        int b=2;

        //ACC
        int produit = calculatorUnderTest.multiply(a, b);

        //Assert
        Assertions.assertThat(produit).isEqualTo(10);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0 ")
    @ValueSource(ints = {1, 2, 400, 1002, 4587})
    void multiply_shouldReturnZero_ofZeoWithMultipleIntegers(int arg) {
        // ARRANGE

        //ACC
        int actualResult = calculatorUnderTest.multiply(arg, 0);

        // ASSERT
        Assertions.assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} + {1} doit être égal à {2} ")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    void add_shouldReturnZero_ofZeroWithMultipleIntegers(int num1, int num2, int expectResult) {
        // ARRANGE

        //ACC
        int actualResult = calculatorUnderTest.add(num1, num2);

        // ASSERT
        Assertions.assertThat(expectResult).isEqualTo(actualResult);
    }

    @Test
    @Timeout(3)
    void longCalcul_shouldComputerInLessThanSecond(){
        // ARRANGE

        //ACC
        calculatorUnderTest.longCalculation();

        //ASSERT

    }

    @Test
    void listDigits_shouldReturnsTheListsOfDigits_ofPositiveInteger(){
        // GIVEN
        int number = 95897;

        // WHEN
        Set<Integer> actualDigits =  calculatorUnderTest.digitsSet(number) ;

        //THEN
        //Set<Integer> expectedDigits = Stream.of(5, 9, 8, 7).collect(Collectors.toSet());
        //org.junit.jupiter.api.Assertions.assertEquals(expectedDigits, actualDigits);
        Assertions.assertThat(actualDigits).containsExactlyInAnyOrder(5, 9, 8, 7);

    }

    @Test
    void listDigits_shouldReturnsTheListsOfDigits_ofNegativeInteger(){
        // GIVEN
        int number = -12343;

        // WHEN
        Set<Integer> actualDigits =  calculatorUnderTest.digitsSet(number) ;

        //THEN
        Assertions.assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);

    }
    @Test
    void listDigits_shouldReturnsTheListsOfDigits_ofZero(){
        // GIVEN
        int number = 0;

        // WHEN
        Set<Integer> actualDigits =  calculatorUnderTest.digitsSet(number) ;

        //THEN
        //Set<Integer> expectedDigits = Stream.of(5, 9, 8, 7).collect(Collectors.toSet());
        //org.junit.jupiter.api.Assertions.assertEquals(expectedDigits, actualDigits);
        Assertions.assertThat(actualDigits).containsExactlyInAnyOrder(0);

    }

}
