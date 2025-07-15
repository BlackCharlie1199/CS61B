public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        in.close(); 
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int planets_number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planets_number];
        for (int i = 0; i < planets_number; i++){
            double pos_x = in.readDouble();
            double pos_y = in.readDouble();
            double vel_x = in.readDouble();
            double vel_y = in.readDouble();
            double mass = in.readDouble();
            String photoName = in.readString();
            planets[i] = new Planet(pos_x, pos_y, vel_x, vel_y, mass, photoName);
        }
        return planets;
    }

    public static void main(String[] args) {
        /** Load all the stuff we need to draw the picture k */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /* Sets up the size of windows */
        StdDraw.setScale(-1 * radius, radius);

        /* Draw and show the background*/
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();
        
        /* Draw all of tha planets using the instance method draw */
        for (Planet i : planets){
            i.draw();    
        }

        /* Draw animation */

        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t < T) {
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            /* Store the netforce */
            for (int i = 0; i < planets.length; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Update the position, velocity, acceleration */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet i : planets) {
                i.draw();
            }
            StdDraw.show();
            
            /* Pause the animation for 10 milliseconds */
            StdDraw.pause(10);

            t += dt;
        }
        /* Print the desult */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
