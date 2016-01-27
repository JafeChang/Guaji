package info.jafe.guaji.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by jianfei on 2016/1/23.
 */
public class Disk {
    private interface Names{
        String TO_SAVE_LIST = "tosavelist";
    }

    public static void saveObject(Object obj, String fileName){
        File file = new File(Strs.getSDPath()+"/"+fileName);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static Object readObject(String fileName){
        Object obj = null;
        File file = new File(Strs.getSDPath()+"/"+fileName);
        if(!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            obj = ois.readObject();
        }  catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static void saveString(String text, String fileName){
        File file = new File(Strs.getSDPath()+"/"+fileName);
        saveString(text,file);
    }

    public static void saveString(String text, File file){
        if(!file.exists()){
            file.mkdirs();
        }
        FileWriter fw = null;
        try{
            fw = new FileWriter(file);
            fw.write(text);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static  String readString(String fileName){
        File file = new File(Strs.getSDPath()+"/"+fileName);
        return readString(file);
    }

    public static String readString(File file){
        if(!file.exists()) {
            return null;
        }
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new FileReader(file));
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String readStringFromAssets(String fileName,Context context){
        InputStreamReader isr;
        String line;
        StringBuilder sb = new StringBuilder();
        try{
            isr = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(isr);
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void saveAll(final Map<String,List<?>> listMap){
//        new Thread(){
//            @Override
//            public void run(){
//                ToSaveList toSaveList = new ToSaveList(listMap);
//                save(toSaveList,Names.TO_SAVE_LIST);
//            }
//        }.start();
    }

    public static void readAll(){

    }
}
