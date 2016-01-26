package info.jafe.guaji.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.Entity.factories.PairFactory;
import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.app.App;

/**
 * Created by jianfei on 2016/1/26.
 */
public class DataManager {
    private static DataManager instance;
    private final String TABLE_NAMES[] = {  "building","supplies"};
    private final String databaseName = "guajidb.db";
    private SQLiteDatabase db;
    private DataManager(){
        db = App.get().openOrCreateDatabase(databaseName, Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists "+TABLE_NAMES[Pair.TYPE_BUILDING]+" (id integer primary key autoincrement, key integer, value integer,growth integer )");
        db.execSQL("create table if not exists "+TABLE_NAMES[Pair.TYPE_SUPPLIES]+" (id integer primary key autoincrement, key integer, value integer,growth integer )");
    }

    public static DataManager get(){
        if(instance==null){
            synchronized (DataManager.class){
                if(instance==null){
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    private void add(Pair pair){
        int key = pair.getKey();
        long value = pair.getValue();
        long growth = pair.getGrowth();
        String tableName = TABLE_NAMES[pair.getType()];
        String sql = "insert into "+tableName+" values(null,?,?,?)";
        db.execSQL(sql,new String[]{key+"",value+"",growth+""});
    }

    private boolean isHad(Pair pair){
        int key = pair.getKey();
        String tableName = TABLE_NAMES[pair.getType()];
        String sql = "select count(*) from "+tableName+" where key=?";
        Cursor c = db.rawQuery(sql,new String[]{key+""});
        c.moveToNext();
        return c.getInt(0)>0;
    }

    private void set(Pair pair){
        int key = pair.getKey();
        long value = pair.getValue();
        long growth = pair.getGrowth();
        String tableName = TABLE_NAMES[pair.getType()];
        String sql = "update "+tableName+" set value=?, growth=? where key=?";
        db.execSQL(sql,new String[]{value+"",growth+"",key+""});
    }

    public void save(Pair pair){
        if (isHad(pair)){
            set(pair);
        }else{
            add(pair);
        }
    }

    public void saveAll(List<Pair> pairs){
        for(Pair pair:pairs){
            save(pair);
        }
    }

    public List<Pair> readAll(int type){
        String tableName = TABLE_NAMES[type];
        String sql = "select * from "+tableName;
        Cursor c = db.rawQuery(sql,null);
        List<Pair> list = new ArrayList<>();
        while(c.moveToNext()){
            int key = c.getInt(c.getColumnIndex("key"));
            long value = c.getLong(c.getColumnIndex("value"));
            long growth = c.getLong(c.getColumnIndex("growth"));
            Pair pair = PairFactory.newInstance(type, key, value, growth);
            list.add(pair);
        }
        return list;
    }

    public void close(){
        if(db != null && db.isOpen()){
            db.close();
        }
    }
}
