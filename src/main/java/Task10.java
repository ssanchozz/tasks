import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Task10 {

    public static void main(String[] args) {

        Map<Integer, Integer> countingMap = Maps.newHashMap();

        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int testDatum : generateTestArray(-1000000, 1000000, 1000000)) {
            Set<Integer> multipliers = getMultipliers(testDatum);
            for (Integer multiplier : multipliers) {
                countingMap.computeIfPresent(multiplier, (integer, integer2) -> integer2 + 1);
                countingMap.putIfAbsent(multiplier, 1);
            }
        }

        Integer max = countingMap.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();

        System.out.println(String.format("max = %d, in %s", max, stopwatch));
    }

    private static Set<Integer> getMultipliers(int inputNumber) {
        Set<Integer> multipliers = Sets.newHashSet();
        if (inputNumber == 0) {
            return multipliers;
        }
        int multiplier = 2;
        int num = Math.abs(inputNumber);
        while (num != 1) {
            if (num % multiplier == 0) {
                multipliers.add(multiplier);
                num = num / multiplier;
            } else {
                multiplier++;
            }
        }
        return multipliers;
    }

    private static int[] generateTestArray(int min, int max, int size) {
        return ThreadLocalRandom.current()
                .ints(size, min, max)
                .toArray();
    }
}
