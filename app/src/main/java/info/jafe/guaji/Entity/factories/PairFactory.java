package info.jafe.guaji.Entity.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.app.App;
import info.jafe.guaji.utils.Disk;

/**
 * Created by jianfei on 2016/1/26.
 */
public class PairFactory {
    private static JSONObject joPrice;
    private static JSONArray jaBuildings;
    private static JSONArray jaSupplies;
    private static final String FILENAME_PRICE = "price.json";
    private static final String FILENAME_BUILDING = "building.json";
    private static final String FILENAME_SUPPLIES = "supplies.json";

    public static Pair newInstance(int type,int key){
        return jsonToPair(getJOPair(type, key));
    }

    public static Pair newInstance(int arguments[]){
        return newInstance(arguments[0],arguments[1]);
    }

    private static Pair newInstance(JSONObject jo){
        return jsonToPair(jo);
    }

    private static JSONObject getJoPrice() {
        if (joPrice == null) {
            String joString = Disk.readStringFromAssets(FILENAME_PRICE, App.get());
            try {
                joPrice = new JSONObject(joString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return joPrice;
    }

    private static JSONArray getJaBuildings() {
        if(jaBuildings == null){
            String jaString = Disk.readStringFromAssets(FILENAME_BUILDING, App.get());
            try{
                jaBuildings = new JSONArray(jaString);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return jaBuildings;
    }

    private static JSONArray getJaSupplies() {
        if(jaSupplies == null){
            String jaString = Disk.readStringFromAssets(FILENAME_SUPPLIES, App.get());
            try{
                jaSupplies = new JSONArray(jaString);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return jaSupplies;
    }

    private static JSONObject getJOPair(int type, int key){
        JSONArray ja;
        switch (type){
            case Pair.TYPE_BUILDING:{
                ja = getJaBuildings();
                break;
            }
            case Pair.TYPE_SUPPLIES:{
                ja = getJaSupplies();
                break;
            }
            default:return null;
        }
        for(int i=0;i<ja.length();i++){
            try{
                JSONObject jo = ja.getJSONObject(i);
                if(jo.optInt("key",-1)==key){
                    return jo;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Pair> getPrice(Pair pair) {
        List<Pair> list = new ArrayList<>();
        try {
            JSONArray joPairs = getJoPrice().getJSONArray(pair.getType() + "");
            JSONObject joPair = joPairs.getJSONObject(pair.getKey());
            if (joPair.getInt("key") != pair.getKey()) {
                throw new JSONException("错误的JSON格式,文件\"" + FILENAME_PRICE + "\", type=" + pair.getType() + ", key=" + joPair.getInt("key") + ",传入" + pair.getKey());
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

    private static Pair jsonToPair(JSONObject jo){
        int type = jo.optInt("type",-1);
        int key = jo.optInt("key",-1);
        long value = jo.optLong("value", -1);
        long growth = jo.optLong("growth",-1);
        String title = jo.optString("title");
        String desc = jo.optString("desc");
        if(type==-1||key==-1||value==-1|growth==-1){
            return null;
        }
        switch (type){
            case Pair.TYPE_BUILDING:{
                return new Building(key,value,growth, title, desc);
            }
            case Pair.TYPE_SUPPLIES:{
                return new Supplies(key,value,growth, title, desc);
            }
            default:return null;
        }
    }



}
