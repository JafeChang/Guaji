package info.jafe.guaji.entity;

import info.jafe.guaji.entity.abstracts.Pair;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Supplies extends Pair {

    public Supplies(int key, long value, long growth, String title, String desc) {
        super(key, value, growth, title, desc);
    }

    @Override
    public int getType() {
        return Pair.TYPE_SUPPLIES;
    }
}
