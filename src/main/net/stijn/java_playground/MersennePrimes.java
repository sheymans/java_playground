package net.stijn.java_playground;

import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class MersennePrimes {

    public static void main(String[] args) throws IOException {
        mersennePrimes().limit(20)
                        .forEach(System.out::println);
    }

    /**
     * Mersenne numbers are of the form 2^p - 1. When p is prime as well as 2^p - 1 is prime, we call the latter a Mersenne prime.
     * @return A Stream of Mersenne primes.
     */
    private static Stream<BigInteger> mersennePrimes() {
        return primes().map(p -> calculateMersenne(p.intValue()))
                       .filter(mersenne -> mersenne.isProbablePrime(50));
    }

    /**
     * Calculates a Mersenne number 2^p - 1 given the exponent p.
     */
    private static BigInteger calculateMersenne(int exponent) {
        return BigInteger.TWO.pow(exponent).subtract(BigInteger.ONE);
    }

    /**
     * Calculates an infinite stream of primes.
     *
     * @return a {@link Stream} of {@link BigInteger}s that are primes.
     */
    private static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
