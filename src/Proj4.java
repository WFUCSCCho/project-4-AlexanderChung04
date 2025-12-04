/*
        ∗ @file: Proj4.java
∗ @description: This program performs the separate chaining hash table insert, search, and remove functions on already sorted, shuffled, and reversed datasets, times the performance on different numbers of lines, prints times to console and analysis.txt
        ∗ @author: Alexander Chung
∗ @date: December 4, 2025
                                              */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.*;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME

        ArrayList<Ramen> list = new ArrayList<>();
        int count = 0;
        while(inputFileNameScanner.hasNextLine() && count < numLines){
            String ln = inputFileNameScanner.nextLine().trim();
            if(!ln.isEmpty()){
                String[] data = ln.split(",");

                int reviewNum = Integer.parseInt(data[0].trim());
                String brand = data[1].trim();
                String variety = data[2].trim();
                String style = data[3].trim();
                String country = data[4].trim();
                double stars = Double.parseDouble(data[5].trim());

                Ramen newRamen = new Ramen(reviewNum, brand,variety, style,country, stars);
                list.add(newRamen);
                count++;
            }
        }
        inputFileNameScanner.close();
        inputFileNameStream.close();


        ArrayList<Ramen> sorted = new ArrayList<>(list);
        ArrayList<Ramen> shuffled = new ArrayList<>(list);
        ArrayList<Ramen> reversed = new ArrayList<>(list);

        Collections.shuffle(shuffled);
        Collections.sort(sorted);
        Collections.sort(reversed, Collections.reverseOrder());

        SeparateChainingHashTable<Ramen> schTable = new SeparateChainingHashTable<>();

        FileWriter analysisWrite = new FileWriter("analysis.txt", true);
        analysisWrite.write("lines,order,insert,search,remove \n");

        // for sorted
        System.out.println("Lines: " + numLines);
        long startInsertSorted = System.nanoTime();
        for( Ramen ramen : sorted){
            schTable.insert(ramen);
        }
        long endInsertSorted = System.nanoTime();
        System.out.println("Sorted insert time: " + (endInsertSorted - startInsertSorted));

        long startSearchSorted = System.nanoTime();
        for( Ramen ramen : sorted){
            schTable.contains(ramen);
        }
        long endSearchSorted = System.nanoTime();
        System.out.println("Sorted search time: " + (endSearchSorted - startSearchSorted));

        long startRemoveSorted = System.nanoTime();
        for( Ramen ramen : sorted){
            schTable.remove(ramen);
        }
        long endRemoveSorted = System.nanoTime();
        System.out.println("Sorted remove time: " + (endRemoveSorted - startRemoveSorted));

        analysisWrite.write( numLines + ",sorted," + (endInsertSorted - startInsertSorted) + "," + (endSearchSorted - startSearchSorted) + "," + (endRemoveSorted - startRemoveSorted) + "\n");
        schTable.makeEmpty();


        // for shuffled
        System.out.println("Lines: " + numLines);
        long startInsertShuffled = System.nanoTime();
        for( Ramen ramen : shuffled){
            schTable.insert(ramen);
        }
        long endInsertShuffled = System.nanoTime();
        System.out.println("Shuffled insert time: " + (endInsertShuffled - startInsertShuffled));

        long startSearchShuffled = System.nanoTime();
        for( Ramen ramen : shuffled){
            schTable.contains(ramen);
        }
        long endSearchShuffled = System.nanoTime();
        System.out.println("Shuffled search time: " + (endSearchShuffled - startSearchShuffled));

        long startRemoveShuffled = System.nanoTime();
        for( Ramen ramen : shuffled){
            schTable.remove(ramen);
        }
        long endRemoveShuffled = System.nanoTime();
        System.out.println("Shuffled remove time: " + (endRemoveShuffled - startRemoveShuffled));

        analysisWrite.write(numLines + ",shuffled," + (endInsertShuffled - startInsertShuffled) + "," + (endSearchShuffled - startSearchShuffled) + "," + (endRemoveShuffled - startRemoveShuffled) + "\n");
        schTable.makeEmpty();


        // for reversed
        System.out.println("Lines: " + numLines);
        long startInsertReversed = System.nanoTime();
        for( Ramen ramen : reversed){
            schTable.insert(ramen);
        }
        long endInsertReversed = System.nanoTime();
        System.out.println("Reversed insert time: "  + (endInsertReversed - startInsertReversed));

        long startSearchReversed = System.nanoTime();
        for( Ramen ramen : reversed){
            schTable.contains(ramen);
        }
        long endSearchReversed = System.nanoTime();
        System.out.println("Reversed search time: " + (endSearchReversed - startSearchReversed));

        long startRemoveReversed = System.nanoTime();
        for(Ramen ramen : reversed){
            schTable.remove(ramen);
        }
        long endRemoveReversed = System.nanoTime();
        System.out.println("Reversed remove time: " + (endRemoveReversed - startRemoveReversed));

        analysisWrite.write(numLines + ",reversed," + (endInsertReversed - startInsertReversed) + "," +  (endSearchReversed - startSearchReversed) + "," + (endRemoveReversed - startRemoveReversed) + "\n");
        schTable.makeEmpty();


        analysisWrite.close();

        }



    }

