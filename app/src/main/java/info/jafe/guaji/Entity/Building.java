package info.jafe.guaji.Entity;

import java.util.List;

import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.utils.Strs;

/**
 * Created by jianfei on 2016/1/22.
 */
public class Building extends Pair{

    public Building(int key, long value, long growth) {
        super(key, value, growth);
    }


    @Override
    public int getType() {
        return Pair.TYPE_BUILDING;
    }

}
