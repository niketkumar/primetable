package niket.fc.primes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimesImplTest {

    @Test
    public void zeroOrLessNReturnsNothing() {
        assertEquals(0, new PrimesImpl().generate(0).length);
        assertEquals(0, new PrimesImpl().generate(-2).length);
    }

    @Test
    public void oneIsNotPrime() throws Exception {
        long[] primes = new PrimesImpl().generate(1);
        assertEquals(1, primes.length);
        assertEquals(2, primes[0]);
    }

    @Test
    public void nextPrimeAfter2() throws Exception {
        PrimesImpl primes = new PrimesImpl();
        assertEquals(3, primes.nextPrimeGreaterThan(2));
        assertEquals(5, primes.nextPrimeGreaterThan(3));
        assertEquals(53, primes.nextPrimeGreaterThan(49));
    }

    @Test
    public void primality() {
        PrimesImpl primes = new PrimesImpl();
        assertEquals(true, primes.isPrime(3));
        assertEquals(false, primes.isPrime(4));
        assertEquals(true, primes.isPrime(5));
        assertEquals(false, primes.isPrime(25));
    }
}