package info.jafe.guaji.app;

import android.app.Application;
import android.util.SparseArray;

import java.util.List;

import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.Entity.factories.PairFactory;
import info.jafe.guaji.R;
import info.jafe.guaji.managers.DataManager;
import info.jafe.guaji.utils.Logs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private SparseArray<Pair> suppliesList;
    private SparseArray<Pair> buildingList;
    private static App instance = null;
    private DataManager dm;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        dm = DataManager.get();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        dm.close();

    }


    public void read(){
        suppliesList = dm.readAll(Pair.TYPE_SUPPLIES);
        buildingList = dm.readAll(Pair.TYPE_BUILDING);
    }

    public void save(){
        dm.saveAll(suppliesList);
        dm.saveAll(buildingList);
        SparseArray<Pair> list = DataManager.get().readAll(Pair.TYPE_SUPPLIES);
        Logs.d(list.size() + "");

    }

    public static synchronized App get() {
        return instance;
    }

    private void addPair(Pair pair){
        int type = pair.getType();
        int key = pair.getKey();
        if(getPair(type,key)!=null){
            return;
        }
        switch (type){
            case Pair.TYPE_BUILDING:{
                buildingList.put(key, pair);
                break;
            }
            case Pair.TYPE_SUPPLIES:{
                suppliesList.put(key, pair);
                break;
            }
            default:{
                break;
            }
        }
    }

    public Pair getPair(int type, int key){
        return getPairList(type).get(key);
    }

    public Pair newPair(int type, int key){
        Pair pair = PairFactory.newInstance(type, key);
        addPair(pair);
        return pair;
    }

    public Pair getOrNewPair(int type, int key){
        Pair pair = getPair(type, key);
        if(pair==null){
            pair = PairFactory.newInstance(type, key);
            addPair(pair);
        }
        return pair;
    }



    public SparseArray<Pair> getPairList(int type){
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

    public void reset(){
        getPairList(0).clear();
        getPairList(1).clear();

        dm.reset();
    }

    public int getKeysAmount(int type){
        switch (type){
            case Pair.TYPE_BUILDING:{
                return PairFactory.getJaBuildings().length();
            }
            case Pair.TYPE_SUPPLIES:{
                return PairFactory.getJaSupplies().length();
            }
            default:{
                return 0;
            }
        }
    }


}
