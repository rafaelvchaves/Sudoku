import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    private ArrayList<Integer> numberArr = new ArrayList();

    public int[][] getIntArr(String fileName){
        numberArr = new ArrayList();
        int[][] arr = new int[9][9];
        System.out.println("test");
        File myFile = new File("src/"+fileName);
        try {

            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String i=sc.nextLine();
                for (Character j: (i.toCharArray())) {
                    if (j.toString().equals("0")) {
                        numberArr.add(0);
                    }
                    if (j.toString().equals("1")) {
                        numberArr.add(1);
                    }
                    if (j.toString().equals("2")) {
                        numberArr.add(2);
                    }
                    if (j.toString().equals("3")) {
                        numberArr.add(3);
                    }
                    if (j.toString().equals("4")) {
                        numberArr.add(4);
                    }
                    if (j.toString().equals("5")) {
                        numberArr.add(5);
                    }
                    if (j.toString().equals("6")) {
                        numberArr.add(6);
                    }
                    if (j.toString().equals("7")) {
                        numberArr.add(7);
                    }
                    if (j.toString().equals("8")) {
                        numberArr.add(8);
                    }
                    if (j.toString().equals("9")) {
                        numberArr.add(9);
                    }
                }

            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int r=0; r<arr.length;r++){
            for (int c=0; c<arr[0].length;c++){
                arr[r][c]=numberArr.get(0);
                numberArr.remove(0);
            }
        }
        return arr;
    }




    }




