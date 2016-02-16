package info.jafe.guaji.app;

import android.app.Application;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.Entity.Price;
import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.Entity.factories.PairFactory;
import info.jafe.guaji.managers.DataManager;
import info.jafe.guaji.utils.Hand;
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


    public void read() {
        if (suppliesList == null) {
            suppliesList = new SparseArray<>();
        }
        if (buildingList == null) {
            buildingList = new SparseArray<>();
        }
        dm.readAll(Pair.TYPE_SUPPLIES, suppliesList);
        dm.readAll(Pair.TYPE_BUILDING, buildingList);
    }

    public void save() {
        dm.saveAll(suppliesList);
        dm.saveAll(buildingList);
//        SparseArray<Pair> list = DataManager.get().readAll(Pair.TYPE_SUPPLIES);
//        Logs.d(list.size() + "");

    }

    public static synchronized App get() {
        return instance;
    }

    private void addPair(Pair pair) {
        if (pair == null) {
            return;
        }
        int type = pair.getType();
        int key = pair.getKey();
        if (getPair(type, key) != null) {
            return;
        }
        switch (type) {
            case Pair.TYPE_BUILDING: {
                buildingList.put(key, pair);
                break;
            }
            case Pair.TYPE_SUPPLIES: {
                suppliesList.put(key, pair);
                break;
            }
            default: {
                break;
            }
        }
    }

    public Pair getPair(int type, int key) {
        return getPairList(type).get(key);
    }

    public Pair newPair(int type, int key) {
        Pair pair = PairFactory.newInstance(type, key);
        addPair(pair);
        return pair;
    }

    public Pair getOrNewPair(int type, int key) {
        Pair pair = getPair(type, key);
        if (pair == null) {
            pair = PairFactory.newInstance(type, key);
            addPair(pair);
        }
        return pair;
    }


    public SparseArray<Pair> getPairList(int type) {
        switch (type) {
            case Pair.TYPE_BUILDING: {
                return buildingList;
            }
            case Pair.TYPE_SUPPLIES: {
                return suppliesList;
            }
            default: {
                return null;
            }
        }
    }

    public void reset() {
        getPairList(0).clear();
        getPairList(1).clear();
//        Hand.send(Hand.What.REFRESH_ADAPTER);
        dm.reset();
    }

    public boolean consumes(Pair pair) {
        int type = pair.getType();
        int key = pair.getKey();
        List<Price> priceList = PairFactory.getPrice(type, key);
        List<Pair> consumedPairs = new ArrayList<>();
        List<Long> addens = new ArrayList<>();
        for (Price price : priceList) {
            Pair pairToConsume = getPair(price.getType(), price.getKey());
            if (pairToConsume == null) {
                pairsRollsBack(consumedPairs, addens);
                return false;
            }
            long adden = price.getValue();
            pairToConsume.add(-adden);
            consumedPairs.add(pairToConsume);
            addens.add(adden);
            if (pairToConsume.getValue() < 0) {
                pairsRollsBack(consumedPairs, addens);
                return false;
            }
        }
        return true;
    }

    private void pairsRollsBack(List<Pair> pairs, List<Long> addens) {
        for (int i = 0; i < pairs.size(); i++) {
            pairs.get(i).add(addens.get(i));
        }
    }

    public void products(Pair pair) {
        int type = pair.getType();
        int key = pair.getKey();
        long value = pair.getValue();
        List<Price> productionList = PairFactory.getProduction(type, key);
        for (Price production : productionList) {
            Pair pairToProduct = getOrNewPair(production.getType(), production.getKey());
            pairToProduct.add(value * production.getValue());
        }

    }


}
