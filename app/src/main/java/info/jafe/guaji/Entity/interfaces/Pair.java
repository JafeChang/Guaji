package info.jafe.guaji.Entity.interfaces;


import info.jafe.guaji.Entity.Supplies;

/**
 * Created by JafeChang on 16/1/14.
 */
public interface Pair {
    int TYPE_BUILDING = 0;
    int TYPE_SUPPLIES = 1;

    int getKey();
    void setKey(int key);
    long getValue();
    void setValue(long value);
    long getGrowth();
    void setGrowth(long growth);
    void grow();
    void add(long addend);
    void setIndex(int index);
    int getIndex();
    String getDesc();
    void setDesc(String desc);
    int getType();

}
