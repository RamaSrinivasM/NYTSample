package com.nytsample.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class Display {
    Context context;
    public static Toast toast;

    public Display(Context cont) {
        context = cont;
    }

    public static void DisplayLogI(String tag , String msg) {
        Log.e(tag, ""+msg);
    }

    public static void DisplayLogD(String tag , String msg) {
        Log.d(tag, msg);
    }

    public static void DisplayLogE(String tag , String msg) {
        Log.e(tag, msg);
    }

    public static void DisplayLogII(String tag , String msg) {
        Log.i(tag, ""+msg);
    }

    /**
     * Method is used to print the mandatory Toast message
     * @param msg string type, message
     * @param context
     */
    public static void DisplayToastMust(Context context, String msg) {
        try {
            try {
                if(toast != null) {
                    toast.cancel();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setDuration(2000);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
