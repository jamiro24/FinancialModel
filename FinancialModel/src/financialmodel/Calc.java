/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmodel;

import javax.swing.JTextArea;

/**
 *
 * @author s140442
 */
public class Calc {

    static double DISTANCELIMIT = 150D;
    static double CAMPUSSURFACE = 0.74D;
    static double AVGDISTANCE = 0.4D; //average distance to the recharge station
    static double CURVECORRECTION = 0.9D;
    static double RELOADTIME = 3 / 60D; //time for a drone to land, receive a new battery and lift of again
    static double RAINYDAYSPERCENTAGE = 170 / 365D;
    static double KWHCOST = 0.22; //cost of 1 kwh in euros
    static double nCameras = 50;
    static double nCamCost = 3500;
    static double nEmployees = 3;
    static double nYearlySalary = 50000;

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
     * @pre height > 0 AND 0 < fov < 180 AND 0 < angle < 90 AND aspectRatio > 0
     * @
     * post return > 0
     */
    static double surfaceArea(double height, double fov, double angle, double aspectRatio) {

        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 180");
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
     * @pre height > 0 AND 0 < fov < 180 AND 0 < angle < 90 AND aspectRatio > 0
     * @
     * post return > 0
     */
    static double widthFar(double height, double fov, double angle, double aspectRatio) {

        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 180");
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
     * calculates the coverage a drone has when: flying at a height of height,
     * filming with a fov of fov, filming at an angle of angle, filming with an
     * aspect ratio of aspectRatio, flying at a speed of speed, during a time
     * frame of time
     *
     * @param height flight height of drone in meters
     * @param fov horizontal field of view of the drone in degrees
     * @param angle rotation along the z-axis at which the camera of the drone
     * @param aspectRatio ratio of height/width of the footage of the camera
     * @param speed speed the drones fly in km/h
     * @param time time frame in which to measure the coverage in minutes
     * @return the coverage a drone has when: flying at a height of height,
     * filming with a fov of fov, filming at an angle of angle, filming with an
     * aspect ratio of aspectRatio, flying at a speed of speed, during a time
     * frame of time
     * @pre height > 0 AND 0 < fov < 180 AND 0 < angle < 90 AND aspectRatio > 0
     * AND speed > 0 AND time > 0
     * @post return > 0
     */
    static double coverage(JTextArea t, double height, double fov, double angle, double aspectRatio, double speed, double time) {

        //check preconditions
        if (height < 0) {
            throw new IllegalArgumentException("height > 0");
        }
        if (fov < 0 || fov > 360) {
            throw new IllegalArgumentException("0 < fov < 180");
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

        time = time / 60;

        double surfaceArea = surfaceArea(height, fov, angle, aspectRatio); //calculate surface area
        double widthFar = widthFar(height, fov, angle, aspectRatio); //calculate the width of the view of the front of the view of the camera on the ground
        double moveArea = widthFar * speed * time; //calculate the viewed area at speed speed and during time time disregarding the initial surfacearea

        t.append("Percentage coverage per drone: " + Math.round(10000 * (surfaceArea + CURVECORRECTION * moveArea) / CAMPUSSURFACE) / 100D + " %" + "\n");

        //return total coverage area
        return Math.round(10000 * (surfaceArea + CURVECORRECTION * moveArea) / CAMPUSSURFACE) / 100D;
    }

    /**
     * calculates the percentage of coverage of the campus surface
     *
     * @param coverage area in km/2
     * @return the percentage of coverage of the campus surface
     * @pre coverage > 0
     * @post return > 0
     */
    static double percentageCoverage(double coverage) {

        if (coverage < 0) {
            throw new IllegalArgumentException("coverage > 0");
        }

        double percentage = Math.min(100, coverage / CAMPUSSURFACE * 100);
        return percentage;
    }

    /**
     * Calculates the vertical fov from the horizontal fov using the aspectratio
     *
     * @param fov horizontal field of view of the drone in degrees
     * @param aspectRatio ratio of height/width of the footage of the camera
     * @return vertical fov of the view of the drone in degrees
     * @pre 0 < fov < 180 AND aspectRatio > 0
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

    /*
    * Gives the Cumulative value relative to location
    * <i>Perfection is seldom achieved</i>
     */
    static double windPercent(double windSpeed) {
        if (windSpeed < 0.9) {
            return 5.15;
        } else if (windSpeed <= 2) {
            return 21.47;
        } else if (windSpeed <= 3) {
            return 36.72;
        } else if (windSpeed <= 4) {
            return 55.01;
        } else if (windSpeed <= 5) {
            return 69.72;
        } else if (windSpeed <= 6) {
            return 81.17;
        } else if (windSpeed <= 7) {
            return 88.30;
        } else if (windSpeed <= 8) {
            return 93.40;
        } else if (windSpeed <= 9) {
            return 96.37;
        } else if (windSpeed <= 10) {
            return 98.12;
        } else if (windSpeed <= 11) {
            return 99.06;
        } else if (windSpeed <= 12) {
            return 99.48;
        } else if (windSpeed <= 13) {
            return 99.75;
        } else if (windSpeed <= 14) {
            return 99.87;
        } else if (windSpeed <= 15) {
            return 99.94;
        } else if (windSpeed <= 16) {
            return 99.97;
        } else if (windSpeed <= 17) {
            return 99.98;
        } else if (windSpeed <= 19) {
            return 99.99;
        } else {
            return 100;
        }
    }

    /**
     * calculates the percentage that a drone can fly during a year
     *
     * @param d a drone
     * @return percentage that a drone can fly during a year
     * @pre d != null
     * @post 0 < /return < 100
     */
    static public double upTime(JTextArea t, Drone d) {

        if (d == null) {
            throw new IllegalArgumentException("d was null");
        }

        double rainUpTime;

        //only non-water proof drones can fly during rainy days
        if (d.waterProof) {
            rainUpTime = 1D;
        } else {
            rainUpTime = RAINYDAYSPERCENTAGE;
        }
        System.out.println(d.batteryLife + "\n" + d.maxSpeed + "\n" + windPercent(d.maxWindSpeed));
        double batteryTime = d.batteryLife / (d.batteryLife + RELOADTIME + AVGDISTANCE / d.maxSpeed); //percentage of the time this drone can
        double upTime = 100 * (windPercent(d.maxWindSpeed) / 100) * batteryTime * rainUpTime;

        if (t != null) {
            t.append("Percentage of yearly uptime: " + round(upTime) + " % \n");
        }

        return upTime;
    }

    /**
     * return the cost power for running a drone for 10 years
     *
     * @param d a drone
     * @return the cost power for running a drone for 10 years
     * @pre d != null
     * @post /return > 0
     */
    static public double energyCost(Drone d) {

        if (d == null) {
            throw new IllegalArgumentException("d was null");
        }
        System.out.println("uptime: " + upTime(null, d));
        return upTime(null, d) * (d.energy * 87658.2) / (100000 * d.batteryLife * KWHCOST);
    }

    /**
     * returns the amount of batteries that are needed to fly drone d
     * continuously
     *
     * @param d a drone
     * @return the amount of batteries that are needed to fly drone d
     * continuously
     * @pre d != null
     * @post /return > 0
     */
    static public double amountOfBatteries(JTextArea t, Drone d, boolean print) {

        if (d == null) {
            throw new IllegalArgumentException("d was null");
        }

        double bAmount = Math.ceil(d.chargeTime / d.batteryLife);

        if (print) {
            t.append("Required amount of extra batteries per drone: " + bAmount + "\n");
        }

        return bAmount;
    }

    /**
     * calculates the cost
     *
     * @param d drone
     * @param droneAmount amount of drones
     * @param cameraCost cost of a camera
     * @param camerasReplaced amount of cameras replaced
     * @param yearlySalary yearly salary of a security guard
     * @param employeesReplaced amount of employees replaced
     * @param lifeTime how many years a drone takes to break
     * @param softwareCost cost of required software
     * @return cost of running a drone for 10 years
     */
    static public double cost(JTextArea t, Drone d, double cameraCost, double camerasReplaced, double yearlySalary, double employeesReplaced, double softwareCost, double coveragePercentage) {

        double droneAmount = Math.ceil(100 / coveragePercentage);
        double cost = d.costDrone * droneAmount * 10 / d.lifeTime
                + d.costBattery * amountOfBatteries(t, d, true) * droneAmount
                - cameraCost * camerasReplaced
                - 10 * yearlySalary * employeesReplaced
                + softwareCost
                + energyCost(d);

        t.append(droneAmount + " Drones needed for 100% coverage in the given time frame" + "\n");
        t.append("Cost of buying " + droneAmount + " drones for 10 years: € " + d.costDrone * droneAmount * 10 / d.lifeTime + "\n");
        t.append("Drones will need to be replaced every " + droneAmount * 10 / d.lifeTime + " years \n");
        t.append("Cost of " + droneAmount * amountOfBatteries(t, d, false) + " batteries: € " + d.costBattery * amountOfBatteries(t, d, false) * droneAmount + "\n");

        if (camerasReplaced != 0) {
            t.append("Reduced cost of " + camerasReplaced + " replaced cameras: € " + cameraCost * camerasReplaced + "\n");
        }

        if (employeesReplaced != 0) {
            t.append("Reduced cost of " + employeesReplaced + " replaced employees: € " + 10 * yearlySalary * employeesReplaced + "\n");
        }

        t.append("Cost of software: € " + softwareCost + "\n");
        t.append("Cost of energy for running the drones for 10 years: € " + round(energyCost(d)) + "\n");
        t.append("Total additional costs for 10 years: € " + round(cost) + "\n");
        t.append("Average yearly additional costs: € " + round(cost / 10) + "\n");
        t.append("\n");
        t.append("Current 10 year cost for " + nEmployees + " employees: € " + nEmployees * nYearlySalary * 10 + "\n");
        t.append("Current 10 year cost for " + nCameras + " cameras: € " + nCameras * nCamCost + "\n");
        t.append("\n");
        t.append("Total 10 year costs with drones: € " + round(round(cost) + nEmployees * nYearlySalary * 10 + nCameras * nCamCost) + "\n");
        t.append("Total 10 year costs without drones: € " + (nEmployees * nYearlySalary * 10 + nCameras * nCamCost) + "\n");
        t.append("\n");
        t.append("Additional one time costs: " + "\n");
        t.append("drone training € 1500,- per person flying the drones" + "\n");

        return cost;
    }

    static public double round(double d) {
        return Math.round(100 * d) / 100D;
    }
}
