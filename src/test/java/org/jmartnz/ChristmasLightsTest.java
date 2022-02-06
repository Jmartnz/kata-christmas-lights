package org.jmartnz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasLightsTest {

    @Test
    void turnOnAllLights() {
        var lights = new ChristmasLights();
        lights.turnOn(new Range(0, 0), new Range(999, 999));
        assertEquals(1_000_000, lights.count());
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
    }

    private record Range(int x, int y) {}
}
