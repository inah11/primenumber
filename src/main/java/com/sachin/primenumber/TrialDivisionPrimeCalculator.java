package com.sachin.primenumber;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 */
@Component
public class TrialDivisionPrimeCalculator implements PrimeCalculator {

    public static final int INDICATE_ITS_PRIME = -1;

    private final Set<Integer> cacheOfPrimes;

    public TrialDivisionPrimeCalculator() {
        this.cacheOfPrimes = Collections.synchronizedSet(new HashSet<>());
    }


    @Override
    public boolean isPrime(int number) {

        if (number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        if (cacheOfPrimes.contains(number)) {
            return true;
        }

        int smallerFactor = (int) Math.sqrt(number);

        int result = IntStream.rangeClosed(2, smallerFactor)
                .filter(i -> isPrime(i) == true)
                .filter(i -> number % i == 0).findFirst().orElse(INDICATE_ITS_PRIME);
        if (result == INDICATE_ITS_PRIME) {
            cacheOfPrimes.add(number);
        }
        return result == INDICATE_ITS_PRIME;
    }
}
