package info.jafe.guaji.app;

import android.app.Application;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import info.jafe.guaji.entity.Price;
import info.jafe.guaji.entity.abstracts.Pair;
import info.jafe.guaji.entity.factories.PairFactory;
import info.jafe.guaji.managers.DataManager;
import info.jafe.guaji.ui.MainActivity;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private SparseArray<Pair> suppliesList;
    private SparseArray<Pair> buildingList;
    private static App instance = null;
    private DataManager dm;
    private Timer timer;

    public long getTimerPeriod() {
        return timerPeriod;
    }

    private long timerPeriod = 2000;

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


    /**
     *
     */
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


    /**
     * This method is for do consume to buy one pair.
     *  If the players have enough supplies to buy this pair, those pairs in price list of this
     *  pair will be reduced and this method returns true, or those reducing will rollback
     *  and this method will returns false.
     * @param pair the pair to consume
     * @return true - consumes well ;<br>
     *     false - consumes failed, cause of no enough supplies .
     */
    public boolean consumes(Pair pair) {
        int type = pair.getType();
        int key = pair.getKey();
        List<Price> priceList = PairFactory.getPrice(type, key);
        List<Pair> consumedPairs = new ArrayList<>();
        List<Long> addens = new ArrayList<>();
        for (Price price : priceList) {
            Pair pairToConsume = getPair(price.getType(), price.getKey());
            if (pairToConsume == null) {
                pairsRollback(consumedPairs, addens);
                return false;
            }
            long adden = price.getValue();
            pairToConsume.add(-adden);
            consumedPairs.add(pairToConsume);
            addens.add(adden);
            if (pairToConsume.getValue() < 0) {
                pairsRollback(consumedPairs, addens);
                return false;
            }
        }
        return true;
    }

    /**
     * To do rollback od the pairs in list one-by-one
     * @param pairs
     * @param addens
     */
    private void pairsRollback(List<Pair> pairs, List<Long> addens) {
        for (int i = 0; i < pairs.size(); i++) {
            pairs.get(i).add(addens.get(i));
        }
    }

    /**
     * This method is for the pair to do product.
     * @param pair the pair to product
     */
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

    /**
     * This method is the most important matter in Guaji.
     *  In each tick, this method would be invoked once to do something important.
     *  Remember, <b>DO NOT</b> remove this method no matter what happened,
     *  unless you want to kill this app.
     */
    public void doInTick(){
        for(int i=0;i<buildingList.size();i++){
            Pair pair = buildingList.valueAt(i);
            products(pair);
        }
        MainActivity.instance.refresh();
    }

    /**
     * To start a timer .
     */
    public void startTimer(long period){
        if(timer != null){
            timer.cancel();
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                doInTick();
            }
        };
        timer = new Timer(true);
        timerPeriod = period;
        timer.schedule(timerTask, 0, period);
    }

    /**
     * To stop the timer .
     */
    public void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * To modify the timer period
     * @param period new timer period
     */
    public void modifyTimer(long period){
        stopTimer();
        startTimer(period);
    }



}
