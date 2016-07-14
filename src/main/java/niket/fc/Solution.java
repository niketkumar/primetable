package niket.fc;

import niket.fc.table.MultiplicationTable;

/**
 * Created by nbhumihar on 7/14/16.
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
        long[] data = new long[n];
        for (int i = 0; i < n; i++)
            data[i] = i + 1;
        return data;
    }
}
