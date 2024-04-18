package com.qa.utils;

public class TimeUtil {
    public final static int DEFAULT_TIME = 500;
    public final static int DEFAULT_SHORT_TIME_TWO_SECONDS = 2;
    public final static int DEFAULT_MEDIUM_TIME_FIVE_SECONDS = 5;
    public final static int DEFAULT_LONG_TIME_TEN_SECONDS = 10;
    public final static int MAX_TIME_FIFTYTEEN_SECONDS = 15;

    public static void applyWait(int waitTime) {
        try {
            Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void defaultTime() {
        try {
            Thread.sleep(DEFAULT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void shortTime() {
        try {
            Thread.sleep(DEFAULT_SHORT_TIME_TWO_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mediumTime() {
        try {
            Thread.sleep(DEFAULT_MEDIUM_TIME_FIVE_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void longTime() {
        try {
            Thread.sleep(DEFAULT_LONG_TIME_TEN_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void maxTime() {
        try {
            Thread.sleep(MAX_TIME_FIFTYTEEN_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
