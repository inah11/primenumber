package com.sachin.controller;

import com.sachin.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

@RestController
public class PrimeNumbersController {

    private final PrimeNumberService primeNumberService;

    @Autowired
    public PrimeNumbersController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }


    @RequestMapping("/primes")
    public Integer[] getPrimes(HttpServletResponse response, @RequestParam(value = "until") int maxValue) throws ExecutionException, InterruptedException {
        if (maxValue < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return primeNumberService.getPrimesUntil(maxValue);
    }
}
