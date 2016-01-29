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

    private static final String PREFIX = "SCHOOL_";
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     * Send a INFO log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void i(String tag, String text) {
        logMessage(Log.INFO, tag, text);
    }

    /**
     * Send a ERROR log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void e(String tag, String text) {
        logMessage(Log.ERROR, tag, text);
    }

    /**
     * Send a WARN log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void w(String tag, String text) {
        logMessage(Log.WARN, tag, text);
    }

    /**
     * Send a DEBUG log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void d(String tag, String text) {
        logMessage(Log.DEBUG, tag, text);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void v(String tag, String text) {
        logMessage(Log.VERBOSE, tag, text);
    }

    /**
     * Send a ASSERT log message.
     *
     * @param tag  Used to identify the source of a log message.  It usually identifies
     *             the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void a(String tag, String text) {
        logMessage(Log.ASSERT, tag, text);
    }


    /**
     * Low-level logging call.
     * If logging enabled in Build config, logs text with priority and tag.
     * If text is too long, splits it by LOGCAT_BUFFER_SIZE
     *
     * @param priority The priority/type of this log message
     * @param tag      Used to identify the source of a log message.  It usually identifies
     *                 the class or activity where the log call occurs.
     * @param text     The message you would like logged.
     */
    private static void logMessage(int priority, String tag, String text) {
        if (BuildConfig.IS_LOGCAT_LOGGER_ENABLED) {
            int start = 0;
            String prefixWithTag = PREFIX + tag;
            for (int end : generateOffsetList(text.length())) {
                Log.println(priority, prefixWithTag, text.substring(start, end));
                start = end;
            }
        }
    }

    /**
     * Generate list of offsets in a string.
     * Added because it's easy to test.
     *
     * @param lenght text lenght
     * @return The list of offsets
     */
    private static ArrayList<Integer> generateOffsetList(int lenght) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < (lenght / LOGCAT_BUFFER_SIZE) + 1; i++) {
            list.add(i * LOGCAT_BUFFER_SIZE);
        }
        if (lenght % LOGCAT_BUFFER_SIZE > 0) list.add(lenght);

        return list;
    }
}
