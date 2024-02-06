package com.familypot.utils;

import java.io.FileWriter;
import java.io.IOException;
public class FileUtils {
    public static void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + filename);
            e.printStackTrace();
        }
    }
    public static String generateFilename(){
        String[] positiveAdjectives = new String[]{
                "Amazing", "Brilliant", "Charming", "Delightful", "Excellent",
                "Fantastic", "Glorious", "Happy", "Incredible", "Joyful",
                "Kind", "Lovely", "Magnificent", "Nice", "Outstanding",
                "Perfect", "Radiant", "Superb", "Terrific", "Wonderful"
        };
        String[] negativeAdjectives = {
                "Awful", "Bad", "Cruel", "Dreadful", "Evil",
                "Foul", "Gloomy", "Hateful", "Irritating", "Jinxed",
                "Lousy", "Miserable", "Nasty", "Obnoxious", "Pathetic",
                "Repulsive", "Shameful", "Terrible", "Unpleasant", "Vile"
        };
        StringBuilder builder = new StringBuilder();
        builder.append("The ");
        builder.append(positiveAdjectives[(int)(Math.random() * 20)]);
        builder.append(" yet ");
        builder.append(negativeAdjectives[(int)(Math.random() * 20)]);
        builder.append(" Poker Hand");
        return builder.toString();
    }
}
