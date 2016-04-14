package com.sachin.service;

import com.sachin.primenumber.PrimeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final int maxForTrialDivisionAlgo;

    private final int threadCount;


    @Autowired
    public PrimeNumberService(PrimeCalculator trialDivisionPrimeCalculator, PrimeCalculator millerRabinPrimeCalculator,
                              @Value("${max.val.for.trial.division.algo}") int maxForTrialDivisionAlgo,
                              @Value("${thread.count}") int threadCount) {
        this.trialDivisionPrimeCalculator = trialDivisionPrimeCalculator;
        this.millerRabinPrimeCalculator = millerRabinPrimeCalculator;
        this.maxForTrialDivisionAlgo = maxForTrialDivisionAlgo;
        this.threadCount = threadCount;
    }

    public Integer[] getPrimesUntil(int max) throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> collect;
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        if (max <= maxForTrialDivisionAlgo) {
            collect = forkJoinPool.submit(() -> IntStream.rangeClosed(1, max).parallel()
                    .filter(i -> trialDivisionPrimeCalculator.isPrime(i) == true)
                    .boxed()
                    .collect(Collectors.toList()))
                    .get();
        } else {
            collect = forkJoinPool.submit( () -> IntStream.rangeClosed(1, max).parallel()
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
