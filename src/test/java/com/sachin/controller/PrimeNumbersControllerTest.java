package com.sachin.controller;

import com.sachin.primenumber.MillerRabinPrimeCalculator;
import com.sachin.primenumber.PrimeCalculator;
import com.sachin.primenumber.TrialDivisionPrimeCalculator;
import com.sachin.service.PrimeNumberService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by sachinsinha on 14/04/2016.
 */
public class PrimeNumbersControllerTest {
    private PrimeNumbersController target;

    @Before
    public void setup() {
        PrimeCalculator trialDivisionPrimeCalculator = new TrialDivisionPrimeCalculator();
        PrimeCalculator millerRabinPrimeCalculator = new MillerRabinPrimeCalculator();
        PrimeNumberService primeNumberService = new PrimeNumberService(trialDivisionPrimeCalculator, millerRabinPrimeCalculator);
        target = new PrimeNumbersController(primeNumberService);
    }

    @Test
    public void shouldReturnAnArrayOfPrimes() throws Exception {
        //GIVEN
        int number = 10;
        //WHEN
        Integer[] primes = target.getPrimes(number);
        //THEN
        assertThat(primes.length, is(4));

    }

}