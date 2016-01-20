package info.jafe.guaji.utils;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Strs {
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
}
