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
     * @pre height > 0 AND 0 < fov < 360 AND -90 < angle < 90 AND aspectRatio > 0
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

        if (angle < -90 || angle > 90) {
            throw new IllegalArgumentException("-90 < angle < 90");
        }

        if (aspectRatio < 0) {
            throw new IllegalArgumentException("aspectRatio > 0");
        }
         
        //calculate verticalFov
        double phi = toRadians(fov / 2);
        double alpha = toRadians(angle);
        double beta = toRadians(verticalFov(fov, aspectRatio) / 2);

        //calculate distance between the back of the vision of the camera to the 
        //front of the view of the camera (on the ground)
        double d = height / Math.tan(toRadians(90) - beta + alpha) + height / Math.tan(toRadians(90) - beta - alpha);

        //calculate the distance from the camera to the front of the view of the camera on the ground
        double dFar = height / Math.cos(beta + alpha);
        //calculate the distance from the camera to the back of the view of the camera on the ground
        double dClose = height / Math.cos(beta - alpha);
        //calculate the width of the view of the back of the view of the camera on the ground;
        double wClose = 2 * dClose * Math.tan(phi);
        //calculate the width of the view of the front of the view of the camera on the ground;
        double wFar = 2 * dFar * Math.tan(phi);
        
        //calculate and return the surface area of the view of the camera
        return 0.5 * (wClose + wFar) * d;
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
     * @param degree degree to convert
     * @return radians value of degree
     */
    static double toRadians(double degree) {
        return degree * (Math.PI / 180);
    }
    
    /**
     * Converts radians to degrees
     * @param radian radian to convert
     * @return degree value of radian
     */
    static double toDegrees(double radian) {
        return radian * (180 / Math.PI);
    }
}
