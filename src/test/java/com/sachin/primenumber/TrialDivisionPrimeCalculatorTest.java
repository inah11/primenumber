package com.sachin.primenumber;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sachinsinha on 14/04/2016.
 */
public class TrialDivisionPrimeCalculatorTest {
    private TrialDivisionPrimeCalculator target;

    @Before
    public void setup() {
        target = new TrialDivisionPrimeCalculator();
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
    public void shouldReturnFalseFor9839() {
        //GIVEN
        int number = 9839;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(true));
    }

    @Test
    public void shouldReturnFalseFor9838() {
        //GIVEN
        int number = 9838;
        //WHEN
        boolean isPrime = target.isPrime(number);
        //THEN
        assertThat(isPrime, is(false));
    }


}