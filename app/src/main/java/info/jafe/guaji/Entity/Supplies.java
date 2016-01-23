package info.jafe.guaji.Entity;

import info.jafe.guaji.Entity.interfaces.Growable;
import info.jafe.guaji.Entity.interfaces.IndexGettable;
import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.utils.Logs;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class Supplies implements Pair, Growable, IndexGettable{
    private String key = "";
    private long value = 0;
    private long growth = 0;
    private int index = -1;
    public Supplies(){}
    public Supplies(String key, long value, long growth){
        this.key = key;
        this.value = value;
        this.growth = growth;
    }

    @Override
    public String getKey() {
        return Strs.getEmptyIfNull(key);
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void setKey(String key) {
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

    public String test(){
        return "";
    }
}