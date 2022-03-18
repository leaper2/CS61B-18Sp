public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double gConst = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet y) {
        xxPos = y.xxPos;
        yyPos = y.yyPos;
        xxVel = y.xxVel;
        yyVel = y.yyVel;
        mass = y.mass;
        imgFileName = y.imgFileName;
    }

    public double calcDistance(Planet buddy) {
        double xoffs = buddy.xxPos - this.xxPos;
        double yoffs = buddy.yyPos - this.yyPos;

        return Math.sqrt((square(xoffs) + square(yoffs)));
    }

    private double square(double num) {
        return num * num;
    }

    public double calcForceExertedBy(Planet buddy) {
        return (gConst * mass * buddy.mass) / square(calcDistance(buddy));
    }

    public double calcForceExertedByX(Planet buddy) {
        return calcForceExertedBy(buddy) * (buddy.xxPos - xxPos) / calcDistance(buddy);
    }

    public double calcForceExertedByY(Planet buddy) {
        return calcForceExertedBy(buddy) * (buddy.yyPos - yyPos) / calcDistance(buddy);
    }

    public double calcNetForceExertedByX(Planet[] buddies) {
        double runningSum = 0;
        for (int i = 0; i < buddies.length; i++) {
            if (this.equals(buddies[i])) {
                continue;
            }
            runningSum += calcForceExertedByX(buddies[i]);
        }
        return runningSum;
    }

    public double calcNetForceExertedByY(Planet[] buddies) {
        double runningSum = 0;
        for (int i = 0; i < buddies.length; i++) {
            if (this.equals(buddies[i])) {
                continue;
            }
            runningSum += calcForceExertedByY(buddies[i]);
        }
        return runningSum;
    }

    public void update(double dt, double fX, double fY) {
        double accX = fX / mass;
        double accY = fY / mass;

        xxVel = xxVel + dt * accX;
        yyVel = yyVel + dt * accY;

        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}