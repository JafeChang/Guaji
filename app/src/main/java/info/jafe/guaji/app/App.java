package info.jafe.guaji.app;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.managers.DataManager;
import info.jafe.guaji.utils.Disk;
import info.jafe.guaji.utils.Logs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private List<Pair> suppliesList;
    private List<Pair> buildingList;
    private static App instance = null;
    private String buildingNames[];
    private String suppliesNames[];
    private DataManager dm;
    private int bar = 0;//TODO delete

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        buildingNames = getResources().getStringArray(R.array.buildings_keys);
        suppliesNames = getResources().getStringArray(R.array.supplies_keys);
        dm = DataManager.get();
        suppliesList = dm.readAll(Pair.TYPE_SUPPLIES);
        buildingList = dm.readAll(Pair.TYPE_BUILDING);
        Disk.readAll();
    }

    @Override
    public void onTerminate() {//TODO
//        Map<String,List<?>> listMap = new Hi
//        Disk.saveAll();
        super.onTerminate();

    }

    public void onClose(){
        dm.saveAll(suppliesList);
        dm.saveAll(buildingList);
        List<Pair> list = DataManager.get().readAll(Pair.TYPE_SUPPLIES);
        Logs.d(list.size() + "");
        dm.close();

    }

    public static synchronized App get() {
        return instance;
    }

    public String getKeyStr(Pair pair) {
        int type = pair.getType();
        int key = pair.getKey();
        switch (type) {
            case Pair.TYPE_BUILDING: {
                return buildingNames[key];
            }
            case Pair.TYPE_SUPPLIES: {
                return suppliesNames[key];
            }
            default: {
                return "";
            }
        }

    }

    public void addPair(Pair pair){
        int type = pair.getType();
        switch (type){
            case Pair.TYPE_BUILDING:{
                buildingList.add(pair);
                break;
            }
            case Pair.TYPE_SUPPLIES:{
                suppliesList.add(pair);
                break;
            }
            default:{
                break;
            }
        }
    }

    public List<Pair> getPairList(int type){
        switch (type){
            case Pair.TYPE_BUILDING:{
                return buildingList;
            }
            case Pair.TYPE_SUPPLIES:{
                return suppliesList;
            }
            default:{
                return null;
            }
        }
    }


}
