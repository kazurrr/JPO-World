package pl.masuhr.pg.jpo.util;

import pl.masuhr.pg.jpo.controller.World;

import static pl.masuhr.pg.jpo.gui.Properties.WORLD_SIZE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Position {
    private int sizeOfWorld = WORLD_SIZE;
    private World world;
    private Point currentPosition;

    public Point getRandom() {
        int x = getRandomInt(0, sizeOfWorld - 1);
        int y = getRandomInt(0, sizeOfWorld - 1);

        return new Point(x, y);
    }

    public Point getNext(Point currentPoint) {
        Point newPosition;
        do {
            newPosition = getRandomAdjacentField(currentPoint);
        } while (newPosition.equals(currentPoint) || !isPointInWorld(newPosition));

        return newPosition;
    }

    public Point getNextFree(World world, Point currentPoint) {
        this.world = world;

        List<Point> freePositions = getAllFreeAdjacentFields(currentPoint);

        if (freePositions.size() == 0) {
            return null;
        } else {
            return freePositions.get(getRandomInt(freePositions.size() - 1));
        }
    }

    private List<Point> getAllFreeAdjacentFields(Point currentPoint) {
        List<Point> freePositions = new ArrayList<>();

        for (int x = currentPoint.x - 1; x <= currentPoint.x + 1; x++) {
            for (int y = currentPoint.y - 1; y <= currentPoint.y + 1; y++) {
                Point check = new Point(x, y);
                if (isPointInWorld(check) && !world.isFieldOccupied(check) && currentPoint != check) {
                    freePositions.add(new Point(x, y));
                }
            }
        }

        return freePositions;
    }

    private Point getRandomAdjacentField(Point currentPoint) {
        int x = currentPoint.x + getRandomInt(-1, 2);
        int y = currentPoint.y + getRandomInt(-1, 2);

        return new Point(x, y);
    }

    private boolean isPointInWorld(Point point) {
        boolean plus = point.x < sizeOfWorld && point.y < sizeOfWorld;
        boolean minus = point.x >= 0 && point.y >= 0;
        return plus && minus;
    }

    private int getRandomInt(int max) {
        return getRandomInt(0, max);
    }

    private int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
