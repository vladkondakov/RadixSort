package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        File file = new File("input.txt");
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        try{
            String str = reader.readLine();
            String[] numbers = str.split(" ");

            System.out.println("Input data:");
            for(String s : numbers){
                System.out.print(s + " ");
            }

            for(int i=0; i<numbers.length; i++){
                numbers[i] = Integer.toOctalString(Integer.parseInt(numbers[i]));
            }

            System.out.println();
            System.out.println();
            System.out.println("Input data in octal number system:");
            for(String s : numbers){
                System.out.print(s + " ");
            }

            numbers = radixSort(numbers, 8);

            System.out.println();
            System.out.println();
            System.out.println("After radixSort (result presented in octal number system):");
            for(String s : numbers){
                System.out.print(s + " ");
            }

            System.out.println();
            System.out.println();
            System.out.println("After radixSort (result presented in decimal number system):");
            for(String s : numbers){
                System.out.print(Integer.parseInt(s, 8) + " ");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch (IOException e){
            }
        }
    }

    public static String[] radixSort(String[] arr, int radix){
        int max_size = -1;
        for(String s : arr)
            if(s.length() > max_size) max_size = s.length();

        List<String>[] bucket = new ArrayList[radix];
        for(int i=0; i<bucket.length; i++)
            bucket[i] = new ArrayList<String>();

        int c = max_size, dp;
        while(c != 0){
            for(int i=0; i<arr.length; i++){
                dp = arr[i].length()-1 - (max_size-c);
                if(dp < 0) bucket[0].add(arr[i]);
                else
                    bucket[Character.getNumericValue(arr[i].charAt(dp))].add(arr[i]);
            }
            String str = "";
            for(int i=0; i<bucket.length; i++){
                for(int j=0; j<bucket[i].size(); j++)
                    str = str + bucket[i].get(j) + " ";
                bucket[i].clear();
            }
            arr = str.split(" ");
            c--;
        }
        return arr;
    }
}
