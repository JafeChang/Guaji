package info.jafe.guaji.utils;

import android.util.Log;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Logs {
    private static boolean isDebug = true;

    public static void d(String msg){
        if(!isDebug){
            return;
        }
        Exception e = new Exception();
        StackTraceElement ste = e.getStackTrace()[1];
        String filename = ste.getFileName();
        StringBuffer toStringBuffer = new StringBuffer().append(ste.getMethodName())
                .append("() [Line ")
                .append(ste.getLineNumber())
                .append("] ");
        Log.d(filename,toStringBuffer.toString()+msg);
    }
}
