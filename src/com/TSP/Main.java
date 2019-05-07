package com.TSP;

import java.util.ArrayList;

/**
 * Simple representation of travelling salesman problem
 * Attempt at brute-force calculation of optimal path, but not complete
 *
 * @author Cam Merz
 *
 */
public class Main {

    private static int num_cities;
    private static int map_height = 9;
    private static int map_width = 9;
    private static City[] cityArray = new City[5];

    /**
     * Sets up City objects for use in this
     * Name and x and y coordinates are determined for each city
     * Array of cities is also built
     *
      * @return true if successfully set up
     */
    private static boolean setUpCities() {

        City SF = new City();
        SF.setX_val(2);
        SF.setY_val(4);
        SF.setName("San Francisco");
        cityArray[0] = SF;

        City DalyCity = new City();
        DalyCity.setX_val(1);
        DalyCity.setY_val(2);
        DalyCity.setName("Daly City");
        cityArray[1] = DalyCity;

        City Marin = new City();
        Marin.setX_val(1);
        Marin.setY_val(8);
        Marin.setName("Marin");
        cityArray[2] = Marin;

        City Oakland = new City();
        Oakland.setX_val(6);
        Oakland.setY_val(4);
        Oakland.setName("Oakland");
        cityArray[3] = Oakland;

        City Fremont = new City();
        Fremont.setX_val(7);
        Fremont.setY_val(1);
        Fremont.setName("Fremont");
        cityArray[4] = Fremont;

        num_cities = cityArray.length;
        return true;
    }

    /**
     * Calculates distance between two cities
      * @param a City to calculate distance from
     * @param b City to calculate distance to
     * @return Distance between input cities
     */
    private static double distanceBetween(City a, City b) {
        return Math.sqrt(Math.pow((b.getX_val() - a.getX_val()), 2) + Math.pow((b.getY_val() - a.getY_val()), 2));
    }

    /**
     * Prints simple map of input cities to command line
     * @param cityArray array of cities to print
     */
    private static void printMap(City[] cityArray) {
        System.out.println(num_cities + " cities to print on map");
        System.out.println("Map will be " + (map_height + 1) + " units high and " + (map_width + 1) + " units wide");
        String buffer = "|\t";

        for (int i = map_height; i >= 0; --i) { //iterate through rows (y values)
            for (int j = 0; j <= map_width; ++j) { //iterate through columns (x values)
                for (int k = 0; k < num_cities; ++k)
                    if ((cityArray[k].getX_val() == j) && (cityArray[k].getY_val() == i)) {
                        buffer = "X\t";
                    } else {
                        buffer += "";
                    }
                System.out.print(buffer);
                buffer = "|\t";
            }
            System.out.println();
        }
    }

    /**
     * Prints each city name and coordinates
     */
    private static void printCities() {
        for (int i = 0; i < num_cities; ++i) {
            System.out.println(cityArray[i].getName() + " [" + cityArray[i].getX_val() + ", " + cityArray[i].getY_val() + "]");
        }
    }

    /**
     * Converts from coordinate plane representation to matrix graph representation
     * @return matrix of inter-city distances
     */
    private static double[][] calcDistMatrix() {
        double[][] distMatrix = new double[num_cities][num_cities];
        for (int i = 0; i < num_cities; ++i) {
            for (int j = 0; j < num_cities; ++j) {
                distMatrix[i][j] = distanceBetween(cityArray[i], cityArray[j]);
            }
        }
        return distMatrix;
    }

    /**
     * Prints matrix representation of graph to command line
     * @param distMatrix graph to print
     */
    private static void printDistMatrix(double[][] distMatrix) {
        System.out.println("Matrix Representation of Graph");
        for (int i = 0; i < num_cities; i++) {
            System.out.println();
            for (int j = 0; j < num_cities; j++) {
                System.out.print("\t" + String.format("%.2f", distMatrix[i][j]));
            }
        }
    }


    /**
     * Performs brute-force calculation of shortest Hamiltonian circuit
     */
    private static void naiveTSP() {

        ArrayList<City> route = new ArrayList<City>();

        //convert from array to ArrayList
        ArrayList<City> cityList = new ArrayList<City>();
        for (int i = 0; i < cityArray.length; ++i) {
            cityList.add(cityArray[i]);
        }
        recurseTSP(route, cityList);

    }


    /**
     * Recursive method for brute-force calculation
     * @param route best calculated route
     * @param citiesToVisit remaining cities on route
     */
    private static void recurseTSP(ArrayList<City> route, ArrayList<City> citiesToVisit) {
        //double min_distance = 0;

        //calculate distance for all paths
        if (citiesToVisit.isEmpty()) {
            //print route
            for (int i = 0; i < route.size(); ++i) {
                System.out.println(route.get(i).getName());
            }


        } else {
            for (int i = 0; i < citiesToVisit.size(); ++i) {
                //update route
                ArrayList<City> currentRoute = (ArrayList<City>) route.clone();
                City removed = citiesToVisit.remove(0);
                currentRoute.add(removed);

                //recurse
                recurseTSP(currentRoute, citiesToVisit);
                citiesToVisit.add(removed);
            }
        }
    }

    public static void main(String[] args) {
        setUpCities();
        printCities();
        printMap(cityArray);
        double[][] distMatrix = calcDistMatrix();
        printDistMatrix(distMatrix);
        naiveTSP();

    }

}
