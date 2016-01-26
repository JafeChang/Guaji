package info.jafe.guaji.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void save(Object obj, String fileName){
        File file = new File(Strs.getSDPath()+"/"+fileName);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object read(String fileName){
        Object obj = null;
        File file = new File(Strs.getSDPath()+"/"+fileName);
        if(!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
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
