package com.TSP;

import java.lang.Math;

public class Main {

    private int num_ants;
    private static int num_cities;
    private static int map_height = 9;
    private static int map_width = 9;
    private static City[] cityArray = new City[5];

    //TODO javadoc
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

    //TODO javadoc
    private static double distanceBetween(City a, City b) {
        return Math.sqrt(Math.pow((b.getX_val() - a.getX_val()), 2) + Math.pow((b.getY_val() - a.getY_val()), 2));
    }

    //TODO javadoc
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

    private static void printCities() {
        for (int i = 0; i < num_cities; ++i) {
            System.out.println(cityArray[i].getName() + " [" + cityArray[i].getX_val() + ", " + cityArray[i].getY_val() + "]");
        }
    }



    public static void main(String[] args) {
        setUpCities();
        printCities();
        printMap(cityArray);


    }

}
