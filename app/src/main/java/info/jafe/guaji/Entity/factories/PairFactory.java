package info.jafe.guaji.Entity.factories;

import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.app.App;
import info.jafe.guaji.utils.Disk;

/**
 * Created by jianfei on 2016/1/26.
 */
public class PairFactory {
    private static JSONObject joPrice;
    private static final String PRICE_FILENAME = "price.json";

    public static Pair newInstance(int type,int key, long value, long growth){
        switch (type){
            case Pair.TYPE_BUILDING:{
                return new Building(key,value,growth);
            }
            case Pair.TYPE_SUPPLIES:{
                return new Supplies(key, value, growth);
            }
            default:{
                return null;
            }
        }
    }

    public static Pair newInstance(long arguments[]){
        return newInstance((int)arguments[0],(int)arguments[1],arguments[2],arguments[3]);
    }

    public static Pair newInstance(JSONObject jo){
        try{
            int type = jo.getInt("type");
            int key = jo.getInt("key");
            long value = jo.getLong("value");
            long growth = jo.optLong("growth",0);
            String desc = jo.optString("desc");
            Pair p = newInstance(type,key,value,growth);
            if(p!=null){
                p.setDesc(desc);
            }
            return p;
        }catch (JSONException e){
            return null;
        }
    }

    private static JSONObject getPriceJson() {
        if (joPrice == null) {
            String joString = Disk.readStringFromAssets(PRICE_FILENAME, App.get());
            try {
                joPrice = new JSONObject(joString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return joPrice;
    }

    public static List<Pair> getPrice(Pair pair) {
        List<Pair> list = new ArrayList<>();
        try {
            JSONArray joPairs = getPriceJson().getJSONArray(pair.getType() + "");
            JSONObject joPair = joPairs.getJSONObject(pair.getKey());
            if (joPair.getInt("key") != pair.getKey()) {
                throw new JSONException("错误的JSON格式,文件\"" + PRICE_FILENAME + "\", type=" + pair.getType() + ", key=" + joPair.getInt("key") + ",传入" + pair.getKey());
            }
            JSONArray _joPrices = joPair.getJSONArray("price");
            for(int i=0;i<_joPrices.length();i++){
                list.add(PairFactory.newInstance(_joPrices.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }



}
