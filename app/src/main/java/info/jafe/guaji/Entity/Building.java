package info.jafe.guaji.Entity;

import info.jafe.guaji.Entity.abstracts.Pair;

/**
 * Created by jianfei on 2016/1/22.
 */
public class Building extends Pair{

    public Building(int key, long value, long growth, String title, String desc) {
        super(key, value, growth, title, desc);
    }


    @Override
    public int getType() {
        return Pair.TYPE_BUILDING;
    }

}
