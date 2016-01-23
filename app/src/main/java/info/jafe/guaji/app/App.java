package info.jafe.guaji.app;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.SuppliesAdapter;
import info.jafe.guaji.utils.Disk;
import info.jafe.guaji.utils.Logs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private List<Supplies> suppliesList;
    private List<Building> buildingList;
    private static App instance = null;
    private int bar = 0;//TODO delete

    @Override
    public void onCreate(){
        super.onCreate();
        init();
        instance = this;
    }

    private void init() {
        Disk.readAll();
    }

    @Override
    public void onTerminate(){//TODO
//        Map<String,List<?>> listMap = new Hi
//        Disk.saveAll();
        super.onTerminate();
    }

    public static synchronized App get(){
        return instance;
    }










}
