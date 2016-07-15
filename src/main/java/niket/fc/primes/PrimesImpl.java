package niket.fc.primes;

/**
 * Generates first N prime numbers using basic primality check method.
 */
class PrimesImpl implements Primes {
    @Override
    public long[] generate(int n) {
        if (n < 1)
            return new long[0];
        else {
            long[] primes = new long[n];
            primes[0] = 2;
            for (int i = 1; i < n; i++) {
                primes[i] = nextPrimeGreaterThan(primes[i - 1]);
            }
            return primes;
        }
    }

    long nextPrimeGreaterThan(long p) {
        long x = p + 1;
        while (!isPrime(x)) {
            x = x + 1;
        }
        return x;
    }

    /**
     * n > 2
     *
     * @param n
     * @return
     */
    boolean isPrime(long n) {
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
