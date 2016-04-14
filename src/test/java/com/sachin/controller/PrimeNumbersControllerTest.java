package com.sachin.controller;

import com.sachin.primenumber.MillerRabinPrimeCalculator;
import com.sachin.primenumber.PrimeCalculator;
import com.sachin.primenumber.TrialDivisionPrimeCalculator;
import com.sachin.service.PrimeNumberService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PrimeNumbersControllerTest {
    private PrimeNumbersController target;
    private HttpServletResponse response;

    @Before
    public void setup() {
        PrimeCalculator trialDivisionPrimeCalculator = new TrialDivisionPrimeCalculator();
        PrimeCalculator millerRabinPrimeCalculator = new MillerRabinPrimeCalculator();
        int maxForTrialDivisionAlgo = 1000000;
        int threadCount = 1;
        PrimeNumberService primeNumberService = new PrimeNumberService(trialDivisionPrimeCalculator, millerRabinPrimeCalculator, maxForTrialDivisionAlgo, threadCount);
        target = new PrimeNumbersController(primeNumberService);
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void shouldReturnAnArrayOfPrimes() throws Exception {
        //GIVEN
        int number = 10;
        //WHEN
        Integer[] primes = target.getPrimes(response, number);
        //THEN
        assertThat(primes.length, is(4));

    }

    @Test
    public void shouldSetBadRequestStatusForBadRequest() throws Exception {
        //GIVEN
        int number = 1;

        //WHEN
        Integer[] primes = target.getPrimes(response, number);
        //THEN
        verify(response, times(1)).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertThat(primes, nullValue());

    }

}