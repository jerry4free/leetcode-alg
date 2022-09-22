package Util;

public class Utils {

    public static void show(int[] n){
        System.out.print("show array:");
        for (int i : n){
            System.out.print(i + ", ");
        }
        System.out.println("");
    }

    public static void show2D(int[][] n){
        for (int[] a : n){
            for (int i: a){
                System.out.print(i + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
