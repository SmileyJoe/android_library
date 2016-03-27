package com.smileyjoedev.debug;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Handle Debugging to LogCat using {@link Log} Used to make the logging process
 * easier by including methods that accept an integer as a message and convert
 * it to a string for logging.
 * <p>
 * d - debug e - error i - info v - verbose w - warn
 * </p>
 *
 * @author Cody
 *
 */
public class Debug {

    // TODO: Temp tag //
    // TODO: Something for views, get the text of a text view, editText etc //
    // TODO: Dialog Fragment:
    // - When initiated, hold down menu key to launch
    // - Include Log from here ... will need to store all logs in a String or
    // - ArrayList or something
    // - Stack Trace
    // - Button to email the Log/StackTrace
    // - Button to save to sdCard as text file //
    // TODO: Remove monitor class and have it as part of this, so Debug debug =
    // new Debug(), debug.log(), debug.end() //

    private static String LOG_NAME = "";
    private static final String LINE_BREAK = "\n";
    private static boolean ENABLED = true;
    private static Monitor MONITOR;
    private static ArrayList<Integer> TYPES = new ArrayList<Integer>();

    // Types //
    public static final int DEBUG = 1;
    public static final int ERROR = 2;
    public static final int INFO = 3;
    public static final int VERBOSE = 4;
    public static final int WARNING = 5;
    public static final int ALL = 6;
    public static final int NONE = 7;

    public static void init(Context context){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            LOG_NAME = bundle.getString("debug_tag");
            ENABLED = bundle.getBoolean("debug_enabled");
        } catch (PackageManager.NameNotFoundException e) {
            //TODO: Proper error //
            Log.e("smileyjoedev", "Failed to logad meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e("smileyjoedev", "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
    }

    /****************************************************
     * Setters
     ***************************************************/

    public static void setLogName(String logName) {
        LOG_NAME = logName;
    }

    public static void enabled(boolean isEnabled) {
        ENABLED = isEnabled;
    }

    public static void setLogType(int... params) {
        TYPES = new ArrayList<Integer>();

        for (int i = 0; i < params.length; i++) {
            TYPES.add(params[i]);
        }
    }

    /*****************************************************
     * Getters
     ****************************************************/

    public static boolean isEnabled(){
        return ENABLED;
    }

    public static String getTag(){
        return LOG_NAME;
    }

    /****************************************************
     * Base Logs
     ***************************************************/

    public static void d(Object... params) {
        if (Debug.isLogging(DEBUG)) {
            Log.d(Debug.LOG_NAME, Debug.handleMessage(params));
        }
    }

    public static void e(Object... params) {
        if (Debug.isLogging(ERROR)) {
            Log.e(Debug.LOG_NAME, Debug.handleMessage(params));
        }
    }

    public static void i(Object... params) {
        if (Debug.isLogging(INFO)) {
            Log.i(Debug.LOG_NAME, Debug.handleMessage(params));
        }
    }

    public static void v(Object... params) {
        if (Debug.isLogging(VERBOSE)) {
            Log.v(Debug.LOG_NAME, Debug.handleMessage(params));
        }
    }

    public static void w(Object... params) {
        if (Debug.isLogging(WARNING)) {
            Log.w(Debug.LOG_NAME, Debug.handleMessage(params));
        }
    }

    /*******************************************************
     * Extra Logs
     ******************************************************/

    public static void trace(Context context) {
        trace(context, Debug.DEBUG);
    }

    public static void trace(Context context, int type) {
        boolean first = true;
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String packageName = context.getPackageName();
        String message = "";
        int starLength = 0;

        for (int i = 0; i < trace.length; i++) {
            StackTraceElement elm = trace[i];

            if (elm.getClassName().contains(packageName)) {
                if (first) {
                    first = false;
                    message += "************ TRACE - " + elm.getMethodName()
                            + " ************" + Debug.LINE_BREAK;
                    starLength = message.length() - 1;
                }

                message += elm.getClassName().replace(packageName + ".", "")
                        + " : " + elm.getMethodName() + " ("
                        + elm.getLineNumber() + ")" + Debug.LINE_BREAK;

            }
        }

        for (int i = 0; i < starLength; i++) {
            message += "*";
        }

        switch (type) {
            case Debug.DEBUG:
                d(message);
                break;
            case Debug.ERROR:
                e(message);
                break;
            case Debug.INFO:
                i(message);
                break;
            case Debug.VERBOSE:
                v(message);
                break;
            case Debug.WARNING:
                w(message);
                break;
            default:
                d(message);
                break;
        }
    }

    /**********************************************************
     * Monitor
     *********************************************************/

    public static void initMonitor(Context context) {
        Debug.initMonitor(context, null);
    }

    public static void initMonitor(Context context, String method) {
        MONITOR = new Monitor(context);

        MONITOR.start(method);
    }

    public static void endMonitor() {
        MONITOR.end();
    }

    public static void monitor() {
        Debug.monitor(null);
    }

    public static void monitor(String message) {
        MONITOR.log(message);
    }

    /*******************************************************
     * Internal
     ******************************************************/

    private static String convertToString(Object obj) {
        String message = "";

        // TODO: HashMap //

        try {
            if (obj instanceof String) {
                message = (String) obj;
            } else if (obj instanceof Boolean) {
                if ((Boolean) obj) {
                    message = "TRUE";
                } else {
                    message = "FALSE";
                }
            } else if (obj instanceof Integer) {
                message = Integer.toString((Integer) obj);
            } else if (obj instanceof Long) {
                message = Long.toString((Long) obj);
            } else if (obj instanceof ArrayList) {
                for (int i = 0; i < ((ArrayList) obj).size(); i++) {
                    message += Debug.LINE_BREAK + "[" + i + "]: "
                            + Debug.convertToString(((ArrayList) obj).get(i));
                }

                message += Debug.LINE_BREAK;
            } else if (obj instanceof Object[]) {
                boolean first = true;
                for (int i = 0; i < ((Object[]) obj).length; i++) {
                    if (first) {
                        first = false;
                        message += Debug.LINE_BREAK;
                    } else {
                        message += "--";
                    }
                    message += "[" + i + "]: "
                            + Debug.convertToString(((Object[]) obj)[i]);
                }

                message += Debug.LINE_BREAK;
            } else if (obj.getClass().isArray()) {
                try {
                    // TODO: Find a way to hadle arrays of primitive type //
                    message = Debug.LINE_BREAK + Arrays.asList(obj).toString()
                            + Debug.LINE_BREAK;
                } catch (Exception e) {
                    message = "UNKNOWN TYPE";
                }
            } else if (obj == null) {
                message = "--NULL--";
            } else {
                try {
                    message = obj.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "UNKNOWN TYPE";
                }
            }
        } catch (NullPointerException e) {
            message = "--NULL--";
        }

        if (message.trim().equals("")) {
            message = "--BLANK--";
        }

        return message;
    }

    private static String handleMessage(Object... params) {
        String message = "";
        boolean first = true;

        if (params.length == 0) {
            message = "*******************";
            return message;
        }

        for (int i = 0; i < params.length; i++) {
            Object param = params[i];

            if (param instanceof ArrayList || param instanceof Object[]) {
                first = true;
            }

            if (first) {
                first = false;
            } else {
                message += ": ";
            }

            if (param instanceof ArrayList || param instanceof Object[]) {
                first = true;
            }

            message += convertToString(param);
        }

        return message;
    }

    private static boolean isLogging(int type) {
        boolean isLogging = false;

        if (ENABLED) {
            if (TYPES.size() == 0) {
                TYPES.add(ALL);
            }
            if (TYPES.contains(type)) {
                isLogging = true;
            } else if (TYPES.contains(ALL)) {
                isLogging = true;
            }
        }

        return isLogging;
    }

    /***********************************************
     * Internal Class
     *
     * @author Cody
     *
     **********************************************/

    private static class Monitor {

        private int mStartLineLength = 0;
        private Context mContext;
        private String mMessage;

        public Monitor(Context context) {
            mContext = context;
            mMessage = "";
        }

        public void start(String method) {

            if (method == null) {
                mMessage += "************************";
            } else {
                mMessage += "************ " + method + " ************";
            }

            mMessage += Debug.LINE_BREAK;

            mStartLineLength = mMessage.length();

        }

        public void log(String message) {
            mMessage += Integer.toString(this.getLineNumber());

            if (message != null) {
                mMessage += " - " + message;
            } else {

            }

            mMessage += Debug.LINE_BREAK;

        }

        public void end() {
            for (int i = 0; i < mStartLineLength; i++) {
                mMessage += "*";
            }

            mMessage += Debug.LINE_BREAK;

            Debug.d(mMessage);

            mMessage = "";
        }

        private int getLineNumber() {
            int lineNumber = -1;

            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            String packageName = mContext.getPackageName();

            for (int i = 0; i < trace.length; i++) {
                StackTraceElement elm = trace[i];

                if (elm.getClassName().contains(packageName)) {
                    lineNumber = elm.getLineNumber();
                    break;
                }
            }

            return lineNumber;
        }

    }
}