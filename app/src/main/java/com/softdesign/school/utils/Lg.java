package com.softdesign.school.utils;

import android.util.Log;

import com.softdesign.school.BuildConfig;

import java.util.ArrayList;

/**
 * Зарефакторить код логера в соответствии с данными на лекции рекомендациями, исспользовать подход DRY Don’t repeat yourself (не повторяй себя) -
 * т.е. избегаем повторения уже ранее написанного кода + Javadoc,
 * логер должен исспользовать различные уровни вывода логов (Verbose, debug, info, error, warn, assert ).
 */
public class Lg {

    private static final String PREFIX = "HTC ";
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    private static boolean shouldLog() {
        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
    }

    public static void i(String tag, String text) {
        logMessage(Log.INFO, tag, text);
    }

    public static void e(String tag, String text) {
        logMessage(Log.ERROR, tag, text);
    }

    public static void w(String tag, String text) {
        logMessage(Log.WARN, tag, text);
    }

    public static void d(String tag, String text) {
        logMessage(Log.DEBUG, tag, text);
    }

    public static void v(String tag, String text) {
        logMessage(Log.VERBOSE, tag, text);
    }

    public static void a(String tag, String text) {
        logMessage(Log.ASSERT, tag, text);
    }

    private static void logMessage(int priority, String tag, String text) {
        if (shouldLog()) {
            int start = 0;
            String prefixWithTag = PREFIX + tag;
            for (int end : split(text.length())) {
                Log.println(priority, prefixWithTag, text.substring(start, end));
                start = end;
            }
        }
    }

    private static ArrayList<Integer> split(int lenght) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < lenght / LOGCAT_BUFFER_SIZE; ++i) {
            list.add(i * LOGCAT_BUFFER_SIZE);
        }
        if (lenght % LOGCAT_BUFFER_SIZE > 0) list.add(lenght);

        return list;
    }
}
