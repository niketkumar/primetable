package niket.fc.primes;

/**
 * Generates first N prime numbers.
 */
public interface Primes {
    long[] generate(int n);

    class Factory {
        public Primes create() {
            return new PrimesImpl();
        }
    }
}
