package org.jmartnz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasLightsTest {

    private ChristmasLights lights;

    @BeforeEach
    void setUp() {
        lights = new ChristmasLights();
    }

    @Test
    void turnOnAllLights() {
        lights.turnOn(Range.of(0, 0), Range.of(999, 999));
        assertEquals(1_000_000, lights.count());
    }

    @Test
    void toggleFirstLine() {
        lights.turnOn(Range.of(0, 0), Range.of(499, 0)); // turn first 500 lights on
        lights.toggle(Range.of(0, 0), Range.of(999, 0));
        assertEquals(2500, lights.count());
    }

    @Test
    void turnOfMiddleFourLights() {
        lights.turnOn(Range.of(0, 0), Range.of(999, 999)); // turn on all
        lights.turnOff(Range.of(499, 499), Range.of(500, 500));
        assertEquals(999_996, lights.count());
    }

    @Test
    void performSantasInstructions() {
        lights.turnOn(Range.of(887, 9), Range.of(959, 629));
        lights.turnOn(Range.of(454, 398), Range.of(844, 448));
        lights.turnOff(Range.of(539, 243), Range.of(559, 965));
        lights.turnOff(Range.of(370, 819), Range.of(676, 868));
        lights.turnOff(Range.of(145, 40), Range.of(370, 997));
        lights.turnOff(Range.of(301, 3), Range.of(808, 453));
        lights.turnOn(Range.of(351, 678), Range.of(951, 908));
        lights.toggle(Range.of(720, 196), Range.of(897, 994));
        lights.toggle(Range.of(831, 394), Range.of(904, 860));
        assertEquals(539_560, lights.count());
    }

    private static class ChristmasLights {

        private final int[][] grid = new int[1000][1000];

        public ChristmasLights() {
            for (var i = 0; i < grid.length; i++)
                for (var j = 0; j < grid[0].length; j++)
                    grid[i][j] = 0;
        }

        public void turnOn(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    grid[i][j]++;
        }

        public int count() {
            var total = 0;
            for (var i = 0; i < grid.length; i++)
                for (var j = 0; j < grid[0].length; j++)
                    total += grid[i][j];
            return total;
        }

        public void toggle(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    grid[i][j] += 2;
        }

        public void turnOff(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    if (grid[i][j] > 0) grid[i][j]--;
        }
    }

    private record Range(int x, int y) {
        public static Range of(int x, int y) {
            return new Range(x, y);
        }
    }
}
