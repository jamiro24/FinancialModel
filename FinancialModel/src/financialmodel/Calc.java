/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmodel;

/**
 *
 * @author s140442
 */
public class Calc {

    static double DISTANCELIMIT = 150D;
    static double CAMPUSSURFACE = 0.74D;

    /**
     * calculates the surface area that the camera of the drone can see at any
     * time in km^2
     *
     * @param height flight height of drone in meters
     * @param fov horizontal field of view of the drone in degrees
     * @param angle rotation along the z-axis at which the camera of the drone
     * @param aspectRatio ratio of height/width of the footage of the camera
     * looks at the ground
     * @return surface area that the camera of the drone can see at any time in
     * km^2
     * @pre height > 0 AND 0 < fov < 360 AND 0 < angle < 90 AND aspectRatio > 0
     * @post return > 0
     */
    static double surfaceArea(double height, double fov, double angle, double aspectRatio) {

        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 360");
        }

        if (angle < 0 || angle > 90) {
            throw new IllegalArgumentException("-90 < angle < 90");
        }

        if (aspectRatio < 0) {
            throw new IllegalArgumentException("aspectRatio > 0");
        }

        //calculate verticalFov
        double phi = toRadians(fov / 2);
        double alpha = toRadians(angle);
        double beta = toRadians(verticalFov(fov, aspectRatio) / 2);
        double betaCut = toRadians(verticalFov(fov, aspectRatio) / 2);

        //calculate distance between the back of the vision of the camera to the 
        //front of the view of the camera (on the ground)
        double d1 = height / Math.tan(toRadians(90) - beta - alpha);

        //if the view exceeds the distance limit, cut to the distance limit and 
        //recalculate beta to accomadate for the new distance (only for the calculations
        //on the end of the view).
        if (d1 > DISTANCELIMIT) {
            d1 = DISTANCELIMIT;

            betaCut = Math.atan(toRadians(DISTANCELIMIT / height)) - alpha;
        }

        double d2 = height / Math.tan(toRadians(90) - beta + alpha);
        double d = d1 + d2;

        //calculate the distance from the camera to the front of the view of the camera on the ground
        double dFar = height / Math.cos(betaCut + alpha);
        //calculate the distance from the camera to the back of the view of the camera on the ground
        double dClose = height / Math.cos(beta - alpha);
        //calculate the width of the view of the back of the view of the camera on the ground
        double wClose = 2 * dClose * Math.tan(phi);
        //calculate the width of the view of the front of the view of the camera on the ground
        double wFar = 2 * dFar * Math.tan(phi);

        //calculate and return the surface area of the view of the camera
        return (0.5 * (wClose + wFar) * d) / 1000000;
    }

    /**
     * calculate the width of the view of the front of the view of the camera on
     * the ground
     *
     * @param height flight height of drone in meters
     * @param fov horizontal field of view of the drone in degrees
     * @param angle rotation along the z-axis at which the camera of the drone
     * @param aspectRatio ratio of height/width of the footage of the camera
     * @return width of the view of the front of the view of the camera on the
     * ground
     * @pre height > 0 AND 0 < fov < 360 AND 0 < angle < 90 AND aspectRatio > 0
     * @post return > 0
     */
    static double widthFar(double height, double fov, double angle, double aspectRatio) {
        
        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 360");
        }

        if (angle < 0 || angle > 90) {
            throw new IllegalArgumentException("-90 < angle < 90");
        }

        if (aspectRatio < 0) {
            throw new IllegalArgumentException("aspectRatio > 0");
        }

        double phi = toRadians(fov / 2); //halve of the horizontal fov
        double alpha = toRadians(angle); //radians of angle
        double betaCut = toRadians(verticalFov(fov, aspectRatio) / 2);//halve the vertical fov in radians
        //distance between the back of the vision of the camera to the 
        //front of the view of the camera (on the ground)
        double d1 = height / Math.tan(toRadians(90) - betaCut - alpha);
        //if the view exceeds the distance limit, cut to the distance limit and 
        //recalculate beta to accomadate for the new distance (only for the calculations
        //on the end of the view).
        if (d1 > DISTANCELIMIT) {
            d1 = DISTANCELIMIT;

            betaCut = Math.atan(toRadians(DISTANCELIMIT / height)) - alpha;
        }

        //calculate the width of the view of the front of the view of the camera on the ground
        double dFar = height / Math.cos(betaCut + alpha);
        //return and calculate the width of the view of the front of the view of the camera on the ground
        return (2 * dFar * Math.tan(phi) / 1000);
    }

    /**
     * calculates the coverage a drone has when:
     * flying at a height of height,
     * filming with a fov of fov,
     * filming at an angle of angle,
     * filming with an aspect ratio of aspectRatio,
     * flying at a speed of  speed,
     * during a time frame of time
     * 
     * @param height flight height of drone in meters
     * @param fov horizontal field of view of the drone in degrees
     * @param angle rotation along the z-axis at which the camera of the drone
     * @param aspectRatio ratio of height/width of the footage of the camera
     * @param speed speed the drones fly in km/h
     * @param time time frame in which to measure the coverage in hours
     * @return the coverage a drone has when:
     * flying at a height of height,
     * filming with a fov of fov,
     * filming at an angle of angle,
     * filming with an aspect ratio of aspectRatio,
     * flying at a speed of  speed,
     * during a time frame of time
     * @pre height > 0 AND 0 < fov < 360 AND 0 < angle < 90 AND aspectRatio > 0 AND speed > 0 AND time > 0
     * @post return > 0
     */
    static double coverage(double height, double fov, double angle, double aspectRatio, double speed, double time) {
        
        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 360");
        }

        if (angle < 0 || angle > 90) {
            throw new IllegalArgumentException("-90 < angle < 90");
        }

        if (aspectRatio < 0) {
            throw new IllegalArgumentException("aspectRatio > 0");
        }
        
        if (speed < 0) {
            throw new IllegalArgumentException("speed > 0");
        }
        
        if (time < 0) {
            throw new IllegalArgumentException("time > 0");
        }

        double surfaceArea = surfaceArea(height, fov, angle, aspectRatio); //calculate surface area
        double widthFar = widthFar(height, fov, angle, aspectRatio); //calculate the width of the view of the front of the view of the camera on the ground
        double moveArea = widthFar * speed * time; //calculate the viewed area at speed speed and during time time disregarding the initial surfacearea
        
        System.out.println("surfaceArea: " + surfaceArea + " km^2");
        System.out.println("widthFar: " + widthFar + " km");
        System.out.println("speed: " + speed + " km/h");
        System.out.println("time frame: " + time + " hour");
        System.out.println("move Area: " + moveArea + " km^2");
        System.out.println("percentage coverage: " + ((surfaceArea + moveArea) / CAMPUSSURFACE) * 100 + " %");
        
        //return total coverage area
        return surfaceArea + moveArea;
    }
    
    /**
     * calculates the percentage of coverage of the campus surface
     * @param coverage area in km/2
     * @return the percentage of coverage of the campus surface
     * @pre coverage > 0
     * @post return > 0
     */
    static double percentageCoverage(double coverage) {
        
        if (coverage < 0) {
            throw new IllegalArgumentException("coverage > 0");
        }
        return (coverage / CAMPUSSURFACE) * 100;
    }

    /**
     * Calculates the vertical fov from the horizontal fov using the aspectratio
     *
     * @param fov horizontal field of view of the drone in degrees
     * @param aspectRatio ratio of height/width of the footage of the camera
     * @return vertical fov of the view of the drone in degrees
     * @pre 0 < fov < 360 AND aspectRatio > 0
     * @post 0 < return < 360
     */
    static double verticalFov(double fov, double aspectRatio) {

        //check preconditions
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 360");
        }

        if (aspectRatio < 0) {
            throw new IllegalArgumentException("aspectRatio > 0");
        }

        double hFovRad; //horizontal fov in radians
        double vFovRad; //vertical fov in radians

        //convert the horizontal fov to radians
        hFovRad = toRadians(fov);

        //calculate the vertical fov in radians
        vFovRad = 2 * Math.atan(Math.tan(hFovRad / 2) * aspectRatio);

        //convert and return the vertical fov in degrees
        return toDegrees(vFovRad);
    }

    /**
     * Converts degrees to radians
     *
     * @param degree degree to convert
     * @return radians value of degree
     */
    static double toRadians(double degree) {
        return degree * (Math.PI / 180);
    }

    /**
     * Converts radians to degrees
     *
     * @param radian radian to convert
     * @return degree value of radian
     */
    static double toDegrees(double radian) {
        return radian * (180 / Math.PI);
    }
}
