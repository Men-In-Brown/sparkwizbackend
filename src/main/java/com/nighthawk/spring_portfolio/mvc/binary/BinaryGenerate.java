package com.nighthawk.spring_portfolio.mvc.binary;

import jakarta.persistence.Id;
import java.util.Random;

public class BinaryGenerate {
    @Id
    public static double[] getBinary() {
        Random rand = new Random();
        double decimal = rand.nextDouble()*255;
        decimal = Math.floor(decimal);
        String binaryString = Long.toBinaryString((long) decimal);
        while (binaryString.length() < 8) {
            binaryString = "0" + binaryString;
        }
        double[] bools = new double[2];
        bools[0] = Long.parseLong(binaryString);
        bools[1] = decimal;
        return bools;
    };
    private int number;
}
