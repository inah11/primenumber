package com.sachin.primenumber;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by sachinsinha on 14/04/2016.
 */
public class MillerRabinPrimeCalculatorTest {

    private MillerRabinPrimeCalculator target;

    @Before
    public void setup() {
        target = new MillerRabinPrimeCalculator();
    }

    @Test
    public void shouldReturnFalseForOne() {
        //GIVEN
        int number = 1;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnTrueForTwo() {
        //GIVEN
        int number = 2;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }

    @Test
    public void shouldReturnTrueForThree() {
        //GIVEN
        int number = 3;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }

    @Test
    public void shouldReturnTrueForThirtySeven() {
        //GIVEN
        int number = 37;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }

    @Test
    public void shouldReturnFalseForThirtySix() {
        //GIVEN
        int number = 36;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnFalseFor8() {
        //GIVEN
        int number = 8;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnTrueFor9839() {
        //GIVEN
        int number = 9839;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }

    @Test
    public void shouldReturnFalseFor2147481234() {
        //GIVEN
        int number = 2147481235;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnFalseFor25326001() {
        //GIVEN
        int number = 25326001;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnFalseFor1373653() {
        //GIVEN
        int number = 1373653;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }

    @Test
    public void shouldReturnFalseFor10() {
        //GIVEN
        int number = 10;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }
    @Test
    public void shouldReturnTrueFor5() {
        //GIVEN
        int number = 5;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }




}