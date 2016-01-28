package info.jafe.guaji.managers;

import java.util.List;

import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.app.App;

/**
 * Created by jianfei on 2016/1/27.
 */
public class PairManager {
    private static PairManager instance;



    public static PairManager get(){
        if(instance==null){
            synchronized (PairManager.class){
                if (instance == null){
                    instance = new PairManager();
                }
            }
        }
        return instance;
    }

    private PairManager(){}



}
