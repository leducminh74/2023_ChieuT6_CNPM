package vn.hcmuaf.edu.fit.lab6.random;

import java.util.Random;

public class RandomPassword {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

    private static Random generator = new Random();

    public static String randomAlphaNumeric() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    private static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        RandomPassword rand = new RandomPassword();
        System.out.println(rand.randomAlphaNumeric());
    }

}
