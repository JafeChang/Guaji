package info.jafe.guaji.utils;

import android.os.Environment;

import java.text.DecimalFormat;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Strs {
    private static DecimalFormat df=new DecimalFormat("#.00");
    /**
     * 判断字符串是否为null或空字符串""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return null!=str&&str.length()>0;
    }

    /**
     * 将输入字符串变为空字符串""并返回
     * @param str
     * @return
     */
    public static String getEmpty(String str){
        str = "";
        return  str;
    }

    /**
     * 当输入字符串为null，返回空字符串""<br>
     * 否则返回原字符串
     * @param str
     * @return
     */
    public static String getEmptyIfNull(String str){
        if(null == str){
            return getEmpty(str);
        }else{
            return str;
        }
    }

    /**
     * 格式化数字
     * @param l
     * @return
     */
    public static String f(long l){
        double d = (double) l;
        if(d>1e9){
            return df.format(d/1e9)+" G";
        }else if(d>1e6){
            return df.format(d/1e6)+" M";
        }else if(d>1e3){
            return df.format(d/1e3)+" K";
        }else{
            return l+"";
        }
    }

    public static String getSDPath(){
        String sdPath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return sdPath;
    }
}
