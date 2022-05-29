package com.github.PastaLaPate.LPC_LIB.util;

public class RandomUtil {

    public static int generateInt(int min, int max) {
        return (int) (Math.random()*(max-min+1)+min);
    }

    public static double generateDouble(int min, int max) {
        return (Math.random()*(max-min+1)+min);
    }

}
