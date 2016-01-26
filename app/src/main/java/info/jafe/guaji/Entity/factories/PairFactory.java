package info.jafe.guaji.Entity.factories;

import android.util.SparseArray;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.Entity.interfaces.Pair;

/**
 * Created by jianfei on 2016/1/26.
 */
public class PairFactory {

    public static Pair newInstance(int type,int key, long value, long growth){
        switch (type){
            case Pair.TYPE_BUILDING:{
                return new Building(key,value,growth);
            }
            case Pair.TYPE_SUPPLIES:{
                return new Supplies(key, value, growth);
            }
            default:{
                return null;
            }
        }
    }


}
