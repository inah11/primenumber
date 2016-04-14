package com.sachin.service;

import com.sachin.primenumber.MillerRabinPrimeCalculator;
import com.sachin.primenumber.PrimeCalculator;
import com.sachin.primenumber.TrialDivisionPrimeCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by sachinsinha on 14/04/2016.
 */
public class PrimeNumberServiceTest {

    private PrimeNumberService target;

    @Before
    public void setup() {
        PrimeCalculator trialDivisionPrimeCalculator = new TrialDivisionPrimeCalculator();
        PrimeCalculator millerRabinPrimeCalculator = new MillerRabinPrimeCalculator();
        target = new PrimeNumberService(trialDivisionPrimeCalculator, millerRabinPrimeCalculator);
    }

    @Test
    public void shouldGetFirstTenPrimeNumbers() throws ExecutionException, InterruptedException {
        //GIVEN
        int number = 10;
        //WHEN
        Integer[] primesUntilTen = target.getPrimesUntil(number);
        //THEN
        assertThat(primesUntilTen.length, is(4));
        assertThat(primesUntilTen[0], is(2));
        assertThat(primesUntilTen[1], is(3));
        assertThat(primesUntilTen[2], is(5));
        assertThat(primesUntilTen[3], is(7));
    }

    @Test
    public void shouldGetFirst1000001PrimeNumbers() throws ExecutionException, InterruptedException {
        //GIVEN
        int number = 1000001;
        //WHEN
        Integer[] primesUntilTen = target.getPrimesUntil(number);
        //THEN
        assertThat(primesUntilTen.length, is(78498));
        assertThat(primesUntilTen[0], is(2));
        assertThat(primesUntilTen[1], is(3));
        assertThat(primesUntilTen[2], is(5));
        assertThat(primesUntilTen[3], is(7));
    }

}