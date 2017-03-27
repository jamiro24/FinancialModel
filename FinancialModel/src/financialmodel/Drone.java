/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s140442
 */
public class Drone {

    static final String FILENAME = "drones.txt";

    String name;
    String id;
    double costDrone;//euros
    double costBattery; //euros
    double batteryLife; //hours
    double maxSpeed;  //km/h
    int maxWindSpeed; //max allowed windforce
    int fov; //degrees
    double energy; //wH
    double chargeTime; //hours
    boolean waterProof; 

    //add more drone properties here
    public Drone(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("length id must be larger than 0");
        }
        this.id = id;
        
        waterProof = false;
    }

    /**
     * generates a list of drones from FILENAME
     *
     * @return list of drones
     */
    static ArrayList<Drone> getDrones() {

        ArrayList<Drone> drones; //stores retrieved drones
        drones = new ArrayList<>();

        //get file containing drones
        File dronesFile = new File(FILENAME);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dronesFile), "UTF-8"))) {

            String line;
            Drone drone = null;

            //for every line in FILENAME
            while ((line = br.readLine()) != null) {
                // % seperates drones in FILENAME, also required on first and last line
                if (line.contains("%")) {
                    if (drone != null) {
                        System.out.println(drone);
                        drones.add(drone);
                    }
                    if (line.contains(":")) {
                        drone = new Drone(line.split(":")[1].trim());
                    }
                } else {

                    //split the line in a key and a value
                    String key = line.split(":")[0].trim();
                    String value = line.split(":")[1].trim();

                    switch (key) {

                    case "name":
                        drone.name = value;
                        break;

                    case "cost_drone":
                        drone.costDrone = Double.parseDouble(value);
                        break;

                    case "cost_battery":
                        drone.costBattery = Double.parseDouble(value);
                        break;

                    case "max_speed":
                        drone.maxSpeed = Double.parseDouble(value);
                        break;

                    case "max_wind_speed":
                        drone.maxWindSpeed = Integer.parseInt(value);
                        break;

                    case "fov":
                        drone.fov = Integer.parseInt(value);
                        break;

                    case "energy":
                        drone.energy = Double.parseDouble(value);
                        break;

                    case "charge_time":
                        drone.chargeTime = Double.parseDouble(value);
                        break;
                        
                    case "water_proof":
                        drone.waterProof = Boolean.parseBoolean(value);

                    //add more drone properties here
                    }
                }
            }

            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Drone.class.getName()).log(Level.SEVERE, null, ex);
        }
        return drones;
    }

    /**
     * deletes this drone from the drones file
     */
    public void delete() {
        //get file containing drones
        File dronesFile = new File(FILENAME); //get file with current drones
        File newDronesFile = new File("tempdrones.txt"); //file to write dronesFile with additions in

        //delete old entry (if it exists)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dronesFile), "UTF-8"))) {

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newDronesFile), "UTF-8"));

            String line;
            boolean delete = false; //whether to delete this line

            while ((line = br.readLine()) != null) {
                if (line.contains("%")) { //find start of drone
                    if (line.contains(":") && line.split(":")[1].trim().equals(id)) { //check if that drone is this drone
                        delete = true;
                    } else {
                        delete = false;
                    }
                }

                if (!delete) { //if we're currently in the area where this drone is stored don't write to the new file
                    out.write(line + System.getProperty("line.separator"));
                }
            }

            int count = 0;
            File file;

            br.close();
            out.close();

            mkDirBackup();

            //backup old file
            while ((file = new File("backups/backup" + count + FILENAME)).exists()) {
                count++;
            }
            dronesFile.renameTo(file); //backup old file
            dronesFile.delete(); //delete the old file

            newDronesFile.renameTo(new File(FILENAME)); //rename temp file to the original file name
            newDronesFile.delete(); //delete temp file

        } catch (IOException ex) {
            Logger.getLogger(Drone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * saves this drone to a file
     */
    public void save() {
        //get file containing drones
        File dronesFile = new File(FILENAME); //get file with current drones
        File newDronesFile = new File("tempdrones.txt"); //file to write dronesFile with additions in

        //delete old entry (if it exists)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dronesFile), "UTF-8"))) {

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newDronesFile), "UTF-8"));

            String line;
            boolean delete = false; //whether to delete this line

            while ((line = br.readLine()) != null) {
                if (line.contains("%")) { //find start of drone
                    if (line.contains(":")) {
                        if (line.split(":")[1].trim().equals(id)) { //check if that drone is this drone
                            delete = true;
                        } else {
                            delete = false;
                        }
                    } else {
                        delete = true;
                    }
                }

                if (!delete) { //if we're currently in the area where this drone is stored don't write to the new file
                    out.write(line + System.getProperty("line.separator"));
                }
            }

            //write info of new drone
            out.write("% : "              + id           + System.getProperty("line.separator"));
            out.write("name : "           + name         + System.getProperty("line.separator"));
            out.write("cost_drone : "     + costDrone    + System.getProperty("line.separator"));
            out.write("cost_battery : "   + costBattery  + System.getProperty("line.separator"));
            out.write("battery_life : "   + batteryLife  + System.getProperty("line.separator"));
            out.write("max_speed : "      + maxSpeed     + System.getProperty("line.separator"));
            out.write("max_wind_speed : " + maxWindSpeed + System.getProperty("line.separator"));
            out.write("energy : "         + energy       + System.getProperty("line.separator"));
            out.write("charge_time : "    + chargeTime   + System.getProperty("line.separator"));
            out.write("water_proof : "    + waterProof   + System.getProperty("line.seperator"));
            //add more drone properties here

            int count = 0;
            File file;

            br.close();
            out.close();

            mkDirBackup();

            //backup old file
            while ((file = new File("backups/backup" + count + FILENAME)).exists()) {
                count++;
            }

            dronesFile.renameTo(file); //backup old file
            dronesFile.delete(); //delete the old file

            newDronesFile.renameTo(new File(FILENAME)); //rename temp file to the original file name
            newDronesFile.delete(); //delete temp file

        } catch (IOException ex) {
            Logger.getLogger(Drone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * creates a folder that will contain backup files
     */
    public void mkDirBackup() {

        File theDir = new File("backups");

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {

            }
            if (result) {
                System.out.println("DIR created");
            }
        }

    }

    @Override
    public String toString() {
        return name;
    }   
}
