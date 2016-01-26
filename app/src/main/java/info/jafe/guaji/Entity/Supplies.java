package info.jafe.guaji.Entity;

import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Supplies implements Pair{
    private int key = 0;
    private long value = 0;
    private long growth = 0;
    private int index = -1;
    private String desc = "";

    public Supplies(int key, long value, long growth){
        this.key = key;
        this.value = value;
        this.growth = growth;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public void grow() {
        this.value += growth;
    }

    @Override
    public void add(long addend) {
        this.value += addend;
    }

    @Override
    public void setGrowth(long growth) {
        this.growth = growth;
    }

    @Override
    public long getGrowth() {
        return this.growth;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int getType() {
        return Pair.TYPE_SUPPLIES;
    }

}
