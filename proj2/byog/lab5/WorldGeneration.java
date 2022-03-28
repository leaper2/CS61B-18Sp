package byog.lab5;

import java.util.Objects;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

public class WorldGeneration {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final long SEED = 10000003;
    private static final Random RANDOM = new Random(SEED);
    private static HashSet<Point> wall = new HashSet<>();
    private static HashSet<Point> floor = new HashSet<>();

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            String str = new String("( " + x + "," + y + " )");
            return str;
        }

        @Override
        public boolean equals(Object obj) {
            if (x == ((Point) obj).x && y == ((Point) obj).y) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initializeWorld(world);
        Point pp = new Point(0, 15);
        HashSet<Point> route = routeGenerate(pp, 1000);
        HashSet<Point> nbOfRoute = neighbOfRoute(route);
        fillWorld(world, route, nbOfRoute);
        ter.renderFrame(world);

        // testNeighbours();
    }

    private static void initializeWorld(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static Point maxOfRoute(HashSet<Point> route) {
        Point maxP = new Point(0, 0);
        for (Point pp : route) {
            if (pp.x > maxP.x) {
                maxP.x = pp.x;
                maxP.y = pp.y;
            }
        }
        return maxP;
    }

    private static Point breakPoint(HashSet<Point> route) {
        for (Point pp : route) {
            HashSet<Point> nb = findNeighbours(pp);
            nb = getRidOfChoosedNeib(route, nb);
            if (nb.size() >= 1) {
                return pp;
            }
        }
        return null;
    }

    private static HashSet<Point> neighbOfRoute(HashSet<Point> route) {
        HashSet<Point> neighbOfRoute = new HashSet<>();
        for (Point pp : route) {
            HashSet<Point> nb = findNeighbours(pp);
            nb = getRidOfChoosedNeib(route, nb);
            for (Point pt : nb) {
                neighbOfRoute.add(pt);
            }
        }
        return neighbOfRoute;
    }

    private static HashSet<Point> routeGenerate(Point pp, int length) {
        HashSet<Point> arl = new HashSet<>();
        arl.add(pp);
        Point current = pp;
        int randInt = 0;
        for (int i = 0; i < length; i++) {
            HashSet<Point> neighbours = findNeighbours(current);
            neighbours = getRidOfChoosedNeib(arl, neighbours);
            // neighbours = getRidOfBounds(neighbours);
            Object[] ppArray = neighbours.toArray();
            int neighbNum = neighbours.size();
            switch (neighbNum) {
                case 4:
                    randInt = RANDOM.nextInt(4);
                    current = (Point) ppArray[randInt];
                    arl.add(current);
                    break;
                case 3:
                    randInt = RANDOM.nextInt(3);
                    current = (Point) ppArray[randInt];
                    arl.add(current);
                    break;
                case 2:
                    randInt = RANDOM.nextInt(2);
                    current = (Point) ppArray[randInt];
                    arl.add(current);
                    break;
                case 1:
                    current = (Point) ppArray[0];
                    arl.add(current);
                    break;
                case 0:
                    current = maxOfRoute(arl);
                    break;
            }

        }
        // arl = getRidOfBounds(arl);
        return arl;
    }

    private static HashSet<Point> getRidOfBounds(HashSet<Point> route) {
        HashSet<Point> routeCopy = (HashSet<Point>) route.clone();
        for (Point pt : route) {
            if (pt.x == 0 || pt.x == WIDTH - 1 || pt.y == 0 || pt.y == HEIGHT) {
                routeCopy.remove(pt);
            }
        }
        return routeCopy;
    }

    private static void fillWorld(TETile[][] world, HashSet<Point> route, HashSet<Point> neighbOfRoute) {
        for (Point pp : route) {
            world[pp.x][pp.y] = Tileset.FLOOR;
        }
        for (Point pp : neighbOfRoute) {
            world[pp.x][pp.y] = Tileset.WALL;
        }
    }

    private static HashSet<Point> findStandAlone() {
        return null;
    }

    private static HashSet<Point> getRidOfChoosedNeib(HashSet<Point> route, HashSet<Point> neighbours) {
        HashSet<Point> neighbours2 = (HashSet<Point>) neighbours.clone();
        for (Point pp : neighbours) {
            if (route.contains(pp)) {
                neighbours2.remove(pp);
            }
        }
        return neighbours2;
    }

    private static int neighborsAsWall(HashSet<Point> neighbours) {
        int sum = 0;
        for (Point pp : neighbours) {
            if (wall.contains(pp)) {
                sum++;
            }
        }
        return sum;
    }

    private static int neighborsAsFloor(HashSet<Point> neighbours) {
        int sum = 0;
        for (Point pp : neighbours) {
            if (floor.contains(pp)) {
                sum++;
            }
        }
        return sum;
    }

    private static HashSet<Point> findNeighbours(Point pp) {
        HashSet<Point> neighbors = new HashSet<>();
        int x = pp.x;
        int y = pp.y;
        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x + 1, y));
                        break;
                    case HEIGHT - 1:
                        neighbors.add(new Point(x + 1, y));
                        neighbors.add(new Point(x, y - 1));
                        break;
                    default:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x + 1, y));
                        neighbors.add(new Point(x, y - 1));
                        break;
                }
                break;
            case WIDTH - 1:
                switch (y) {
                    case 0:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x - 1, y));
                        break;
                    case HEIGHT - 1:
                        neighbors.add(new Point(x, y - 1));
                        neighbors.add(new Point(x - 1, y));
                        break;
                    default:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x - 1, y));
                        neighbors.add(new Point(x, y - 1));
                        break;
                }
                break;
            default:
                switch (y) {
                    case 0:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x - 1, y));
                        neighbors.add(new Point(x + 1, y));
                        break;
                    case HEIGHT - 1:
                        neighbors.add(new Point(x, y - 1));
                        neighbors.add(new Point(x - 1, y));
                        neighbors.add(new Point(x + 1, y));
                        break;
                    default:
                        neighbors.add(new Point(x, y + 1));
                        neighbors.add(new Point(x + 1, y));
                        neighbors.add(new Point(x, y - 1));
                        neighbors.add(new Point(x - 1, y));
                        break;
                }
                break;

        }

        return neighbors;
    }

    public static void testNeighbours() {
        Point pp = new Point(3, 3);
        HashSet<Point> neighb = findNeighbours(pp);
        System.out.println(neighb.toString());
    }

    private static HashSet<Point> addRectangle(int length) {
        return null;
    }

    @Test
    public void testNeighboursAsWall() {
        Point pp = new Point(3, 3);
        HashSet<Point> wall = new HashSet<>();
        HashSet<Point> neighbours = findNeighbours(pp);
        wall.add(new Point(4, 3));
        wall.add(new Point(2, 3));
        // assertEquals(1, neighborsAsWall(neighbours, wall));
    }
}
