package niket.fc;

import niket.fc.primes.Primes;
import niket.fc.table.MultiplicationTable;

/**
 * Main class to provide multiplication table of N prime numbers.
 *
 * Expects an optional argument, defaults to 10.
 */
public class Solution {
    public static void main(String[] args) throws MultiplicationTable.InvalidDataException {
        int defaultN = 10;
        long[] primes = processArgsAndGeneratePrimes(args, defaultN);
        new MultiplicationTable.Factory().create(primes).print();
    }

    private static long[] processArgsAndGeneratePrimes(String[] args, int defaultN) {
        if (args == null || args.length == 0)
            return generatePrimes(defaultN);
        else {
            return generatePrimes(extractN(args[0], defaultN));
        }
    }

    private static int extractN(String arg, int defaultN) {
        try {
            return Integer.parseInt(arg);
        } catch (Exception e) {
            System.err.println("Failed to extract N, will use default value of " + defaultN);
            return defaultN;
        }
    }

    private static long[] generatePrimes(int n) {
        return new Primes.Factory().create().generate(n);
    }
}
