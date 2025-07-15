public class Planet {
    public static double constant_G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet (double par1, double par2, double par3, double par4, double par5, String par6) {
        /** constructor of the Planet class */
        xxPos = par1;
        yyPos = par2;
        xxVel = par3;
        yyVel = par4;
        mass = par5;
        imgFileName = par6;
    }

    public Planet (Planet p) {
        /** return the copy of planet */
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        /** calculate the distance between two planets*/
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        /** calculate the force between two planets and then return value with double */
        double distance = calcDistance(p);
        double force = (Planet.constant_G * mass * p.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double distance = calcDistance(p);
        double dx = xxPos - p.xxPos;
        double force_x = calcForceExertedBy(p) * dx / distance;
        return force_x;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = calcDistance(p);
        double dy = yyPos - p.yyPos;
        double force_y = calcForceExertedBy(p) * dy / distance;
        return force_y;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double total_force_x = 0;
        for (Planet i : p) {
            if (!i.equals(this)){
                total_force_x += calcForceExertedByX(i);
            }
        }
        return total_force_x;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double total_force_Y = 0;
        for (Planet i : p) {
            if (!i.equals(this)){
                total_force_Y += calcForceExertedByY(i);
            }
        }
        return total_force_Y;
    }

    public void update(double dt, double fx, double fy) {
        /** update the position if planet after pushing by fx, fy for dt seconds */
        xxVel += fx / mass * dt;
        yyVel += fy / mass * dt;
        xxPos += xxVel * dt;        
        yyPos += yyVel * dt;        
    }

    /* Draw the planet itself */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }

}
