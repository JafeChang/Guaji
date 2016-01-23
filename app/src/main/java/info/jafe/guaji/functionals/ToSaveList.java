package info.jafe.guaji.functionals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianfei on 2016/1/23.
 */
public class ToSaveList implements Serializable{
    private Map<String,List<?>> listMap;
    public ToSaveList(Map<String,List<?>> listMap){
        this.listMap = listMap;
    }

    public void setListMap(Map<String,List<?>> listMap){
        this.listMap = listMap;
    }

    public Map<String,List<?>> getListMap(){
        return listMap;
    }
}
