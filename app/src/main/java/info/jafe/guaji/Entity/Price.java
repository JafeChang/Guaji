package info.jafe.guaji.Entity;

/**
 * Created by jianfei on 2016/1/30.
 */
public class Price {
    public Price(int type, int key, long value){
        this.type = type;
        this.key = key;
        this.value = value;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int type;
    private int key;
    private long value;
}
