package com.sachin.primenumber;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Component
public class MillerRabinPrimeCalculator implements PrimeCalculator {

    private final Set<Integer> cacheOfPrimes;

    public MillerRabinPrimeCalculator() {
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

        int[] primesToTest = getPrimesToTest(number);

        int d = number - 1;
        int s = 0;
        while (d % 2 == 0) {
            d = d >> 1;
            s++;
        }

        final int dFinal = d;
        final int sFinal = s;

        int composite = Arrays.stream(primesToTest).filter(i -> tryComposite(i, dFinal, number, sFinal)).findFirst().orElse(-1);
        if (composite != -1) {
            return false;
        }
        cacheOfPrimes.add(number);
        return true;
    }


    private static int[] getPrimesToTest(int n) {
        if (n >= 2147481234) {
            return new int[]{2, 3, 5, 7, 11, 13, 17};
        }
        if (n >= 25326001) {
            return new int[]{2, 3, 5, 7, 11, 13};
        }
        if (n >= 1373653) {
            return new int[]{2, 3, 5, 11};
        }
        if (n >= 10) {
            return new int[]{2, 3};
        }
        if (n >= 5) {
            return new int[]{2, 3};
        }
        return new int[]{2};
    }


    private static boolean tryComposite(int a, int d, int n, int s) {
        BigInteger aB = BigInteger.valueOf(a);
        BigInteger dD = BigInteger.valueOf(d);
        BigInteger nN = BigInteger.valueOf(n);
        BigInteger sS = BigInteger.valueOf(s);
        if (aB.modPow(dD, nN).equals(BigInteger.ONE)) {
            return false;
        }
        int notAPrime = IntStream.range(0, sS.intValue())
                .filter(i -> aB.modPow(BigInteger.valueOf(2).pow(i).multiply(dD), nN).equals(nN.subtract(BigInteger.ONE))).findFirst().orElse(-1);
        if (notAPrime == -1) {
            return true;
        }
        return false;
    }
}
