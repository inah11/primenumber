package com.sachin.service;

import com.sachin.primenumber.PrimeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PrimeNumberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeNumberService.class);
    private final PrimeCalculator trialDivisionPrimeCalculator;
    private final PrimeCalculator millerRabinPrimeCalculator;


    @Autowired
    public PrimeNumberService(PrimeCalculator trialDivisionPrimeCalculator, PrimeCalculator millerRabinPrimeCalculator) {
        this.trialDivisionPrimeCalculator = trialDivisionPrimeCalculator;
        this.millerRabinPrimeCalculator = millerRabinPrimeCalculator;
    }

    public Integer[] getPrimesUntil(int max) throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> collect;
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        if (max <= 1000000) {
            collect = forkJoinPool.submit(() ->
                    IntStream.rangeClosed(1, max).parallel()
                            .filter(i -> trialDivisionPrimeCalculator.isPrime(i) == true)
                            .boxed()
                            .collect(Collectors.toList()))
                    .get();
        } else {
            collect = forkJoinPool.submit(() ->
                    IntStream.rangeClosed(1, max).parallel()
                            .filter(i -> millerRabinPrimeCalculator.isPrime(i) == true)
                            .boxed()
                            .collect(Collectors.toList()))
                    .get();
        }

        stopWatch.stop();
        LOGGER.info("Time taken to find all the prime numbers until {}: {} ms", max, stopWatch.getLastTaskTimeMillis());
        LOGGER.info("Total prime numbers found = {}", collect.size());
        return collect.toArray(new Integer[collect.size()]);
    }
}
