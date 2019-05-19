package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> weight = new ArrayList(Arrays.asList(0.5, 0.5, 1.0, 2.0, 0.5));
        ArrayList<Double> price = new ArrayList(Arrays.asList(7.0,6.0,9.0,9.0,8.0));
        double maxSize = 2;

        ArrayList<Double> weight2 = new ArrayList(Arrays.asList(1.0, 4.0, 3.0));
        ArrayList<Double> price2 = new ArrayList(Arrays.asList(1500.0, 3000.0, 2000.0));
        double maxSize2 = 4;

        ArrayList<Double> weight3 = new ArrayList(Arrays.asList(10.0,12.0,6.0,8.0,3.0,1.0,5.0));
        ArrayList<Double> price3 = new ArrayList(Arrays.asList(9.0,8.0,7.0,5.0,4.0,4.0,4.0));
        double maxSize3 = 9;

        //System.out.println("Max price is " + maxSize(weight, price, maxSize));
        //System.out.println("Max price is " + maxSize(weight2, price2, maxSize2));
        System.out.println("Max price is " + maxSize(weight3, price3, maxSize3));
    }

    public  static double maxSize(ArrayList<Double> weight, ArrayList<Double> price, double maxWeight) {
        if (weight.size() != price.size()) {
            System.out.println("Sizes are't the same");
            return -1;
        }

        for (int i = 0; i < weight.size(); i++) {
            if (weight.get(i) > maxWeight) {
                weight.remove(i);
                price.remove(i);
            }
        }

        double min_el = Collections.min(weight);
        int i_index = price.size();
        int j_index = (int)(maxWeight / min_el);

        double[][] arr = new double[i_index][j_index];

        for (int k = 0; k < j_index; k++)
            arr[0][k] = (weight.get(0) <= (k+1) * min_el) ? price.get(0) : 0;

        for (int i = 1; i < i_index; i++) {
            for (int j = 0; j < j_index; j++) {
                if (weight.get(i) > ((j+1) * min_el)) {
                    arr[i][j] = arr[i-1][j];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], (weight.get(i) == ((j+1) * min_el)) ?
                                                            price.get(i) :
                                            (price.get(i) + arr[i - 1][j - (int) (weight.get(i) / min_el)]));
                }
            }
            //print2DArr(arr, i_index, j_index);
        }
        return arr[i_index-1][j_index-1];
    }

    public static void print2DArr(double[][] arr, int i, int j) {
        for (int i1 = 0; i1 < i; i1++) {
            for (int k = 0; k < j; k++) {
                System.out.print(arr[i1][k] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
