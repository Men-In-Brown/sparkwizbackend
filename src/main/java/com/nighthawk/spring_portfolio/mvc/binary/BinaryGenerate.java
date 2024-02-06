package com.nighthawk.spring_portfolio.mvc.binary;

import jakarta.persistence.Id;
import java.util.Random;

public class BinaryGenerate {
    @Id
    public static double[] getBinary() {
        Long binary;
        Random rand = new Random();
        double decimal = rand.nextDouble()*255;
        binary = Double.doubleToLongBits(decimal);
        double[] bools = new double[2];
        bools[0] = binary;
        bools[1] = decimal;
        return bools;
    };
    private int number;
}
