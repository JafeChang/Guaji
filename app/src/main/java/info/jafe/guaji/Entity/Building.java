package info.jafe.guaji.entity;

import info.jafe.guaji.entity.abstracts.Pair;

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
