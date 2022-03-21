public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        // StdDraw.setScale(-1.05, 2.05);
        // StdDraw.setXscale(-1.0,1.1);
        // StdDraw.setYscale(-0.5,0.1 );
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

    public static double readRadius(String path) {
        In file = new In(path);

        String[] strs = file.readAllLines();

        return Double.parseDouble(strs[1]);

    }

    public static Planet[] readPlanets(String path) {
        In file = new In(path);
        String[] strs = file.readAllLines();
        int numOfbody = Integer.parseInt(strs[0]);

        Planet[] bodies = new Planet[numOfbody];

        for (int i = 0; i < numOfbody; i++) {
            bodies[i] = readPlanet(strs[i + 2]);
        }
        return bodies;

    }

    private static Planet readPlanet(String descOfPlanet) {
        String[] paramsOfPlanet = descOfPlanet.trim().split("\\s+");
        // should always to do unit test, for we might make the wrong assumptions
        // about the provided data and APIs.
        // don't forget to trim
        // for(String s:paramsOfPlanet){
        // System.out.println(s);
        // }
        return new Planet(
                Double.parseDouble(paramsOfPlanet[0]),
                Double.parseDouble(paramsOfPlanet[1]),
                Double.parseDouble(paramsOfPlanet[2]),
                Double.parseDouble(paramsOfPlanet[3]),
                Double.parseDouble(paramsOfPlanet[4]),
                paramsOfPlanet[5]);
    }
}
