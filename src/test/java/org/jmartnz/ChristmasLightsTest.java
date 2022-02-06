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
        assertEquals(500, lights.count());
    }

    @Test
    void turnOfMiddleFourLights() {
        lights.turnOn(Range.of(0, 0), Range.of(999, 999)); // turn on all
        lights.turnOff(Range.of(499, 499), Range.of(500, 500));
        assertEquals(999_996, lights.count());
    }

    private static class ChristmasLights {

        private final boolean[][] grid = new boolean[1000][1000];

        public ChristmasLights() {
            for (var i = 0; i < grid.length; i++)
                for (var j = 0; j < grid[0].length; j++)
                    grid[i][j] = false;
        }

        public void turnOn(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    grid[i][j] = true;
        }

        public int count() {
            var total = 0;
            for (var i = 0; i < grid.length; i++)
                for (var j = 0; j < grid[0].length; j++)
                    if (grid[i][j]) total++;
            return total;
        }

        public void toggle(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    grid[i][j] = !grid[i][j];
        }

        public void turnOff(Range start, Range end) {
            for (var i = start.x; i <= end.x; i++)
                for (var j = start.y; j <= end.y; j++)
                    grid[i][j] = false;
        }
    }

    private record Range(int x, int y) {
        public static Range of(int x, int y) {
            return new Range(x, y);
        }
    }
}
