package info.jafe.guaji.Entity.abstracts;


import java.util.List;

/**
 * Created by JafeChang on 16/1/14.
 */
public abstract class Pair {
    public static final int TYPE_BUILDING = 0;
    public static final int TYPE_SUPPLIES = 1;
    protected int key = 0;
    protected long value = 0;
    protected long growth = 0;
    protected String desc = "";
    protected String title = "";
    protected int index = -1;
    protected List<Pair> price;

    protected Pair(int key, long value, long growth, String title, String desc){
        this.key = key;
        this.value = value;
        this.growth = growth;
        this.title = title;
        this.desc = desc;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void grow() {
        this.value += growth;
    }

    public void add(long addend) {
        this.value += addend;
    }

    public void setGrowth(long growth) {
        this.growth = growth;
    }

    public long getGrowth() {
        return this.growth;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    abstract public int getType();

    public void setPrice(List<Pair> price) {
        this.price = price;
    }


    public List<Pair> getPrice() {
        return this.price;
    }

    public String toString() {
        return "[ type = " + getType() + ", key = " + key + ", value = " + value + ", growth = " + growth + ", desc = "+desc+" ]";
    }

}
