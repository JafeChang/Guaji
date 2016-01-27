package info.jafe.guaji.Entity;

import java.util.List;

import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Supplies extends Pair {

    public Supplies(int key, long value, long growth) {
        super(key, value, growth);
    }

    @Override
    public int getType() {
        return Pair.TYPE_SUPPLIES;
    }
}
