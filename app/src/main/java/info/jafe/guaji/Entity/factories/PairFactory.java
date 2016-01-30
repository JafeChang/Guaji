package info.jafe.guaji.Entity.factories;

import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.Entity.Building;
import info.jafe.guaji.Entity.Price;
import info.jafe.guaji.Entity.Supplies;
import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.app.App;
import info.jafe.guaji.utils.Disk;

/**
 * Created by jianfei on 2016/1/26.
 */
public class PairFactory {
//    private static JSONObject joPrice;
//    private static JSONArray jaBuildings;
//    private static JSONArray jaSupplies;
    private static JSONArray jaPairs;
    private static SparseArray<Pair> buildings;
    private static SparseArray<List<Price>> buildingsPrices;
    private static SparseArray<List<Price>> buildingsProductions;
    private static SparseArray<Pair> supplies;
    private static SparseArray<List<Price>> suppliesPrices;
    private static SparseArray<List<Price>> suppliesProductions;
//    private static final String FILENAME_PRICE = "price.json";
//    private static final String FILENAME_BUILDING = "building.json";
//    private static final String FILENAME_SUPPLIES = "supplies.json";
    private static final String FILENAME_PAIRS = "pairs.json";

    /**
     * 从模板中复制
     * @param type
     * @param key
     * @return
     */
    public static Pair newInstance(int type,int key){
        Pair ePair = null;
        Pair pair = null;
        switch (type){
            case Pair.TYPE_BUILDING:{
                ePair = buildings.get(key);
                pair = new Building(type,key,ePair.getGrowth(),ePair.getTitle(),ePair.getDesc());
                break;
            }
            case Pair.TYPE_SUPPLIES:{
                ePair = supplies.get(key);
                pair = new Supplies(type,key,ePair.getGrowth(),ePair.getTitle(),ePair.getDesc());
                break;
            }
            default:break;
        }
        if(ePair == null){
            return null;
        }
        return pair;
    }

//    public static Pair newInstance(int arguments[]){
//        return newInstance(arguments[0], arguments[1]);
//    }
//
//    private static Pair newInstance(JSONObject jo){
//        return jsonToPair(jo);
//    }

    private static synchronized JSONArray getJaPairs(){
        if(jaPairs == null){
            String strJaPairs = Disk.readStringFromAssets(FILENAME_PAIRS,App.get());
            try{
                jaPairs = new JSONArray((strJaPairs));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return jaPairs;
    }

    private static synchronized  SparseArray<Pair> getBuildings(){
        if(buildings == null){
            buildings = new SparseArray<>();
            JSONArray array = getJaPairs();
            for(int i=0;i<array.length();i++){
                try{
                    JSONObject jo = array.getJSONObject(i);
                    Pair pair = jsonToPair(jo);
                    if(pair!=null){
                        buildings.put(pair.getKey(), pair);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        return buildings;
    }

    private static synchronized  SparseArray<Pair> getSupplies(){
        if(supplies == null){
            supplies = new SparseArray<>();
            JSONArray array = getJaPairs();
            for(int i=0;i<array.length();i++){
                try{
                    JSONObject jo = array.getJSONObject(i);
                    Pair pair = jsonToPair(jo);
                    if(pair!=null){
                        supplies.put(pair.getKey(), pair);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        return supplies;
    }

//    private static JSONObject getJoPrice() {
//        if (joPrice == null) {
//            String joString = Disk.readStringFromAssets(FILENAME_PRICE, App.get());
//            try {
//                joPrice = new JSONObject(joString);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return joPrice;
//    }
//
//    public static synchronized JSONArray getJaBuildings() {
//        if(jaBuildings == null){
//            String jaString = Disk.readStringFromAssets(FILENAME_BUILDING, App.get());
//            try{
//                jaBuildings = new JSONArray(jaString);
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        return jaBuildings;
//    }
//
//    public static synchronized JSONArray getJaSupplies() {
//        if(jaSupplies == null){
//            String jaString = Disk.readStringFromAssets(FILENAME_SUPPLIES, App.get());
//            try{
//                jaSupplies = new JSONArray(jaString);
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        return jaSupplies;
//    }

//    private static JSONObject getJOPair(int type, int key){
//        JSONArray ja;
//        JSONObject jo = null;
//        switch (type){
//            case Pair.TYPE_BUILDING:{
//                ja = getJaBuildings();
//                break;
//            }
//            case Pair.TYPE_SUPPLIES:{
//                ja = getJaSupplies();
//                break;
//            }
//            default:return null;
//        }
//        for(int i=0;i<ja.length();i++){
//            try{
//                jo = ja.getJSONObject(i);
//                if(jo.optInt("key",-1)==key){
//                    break;
//                }
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        return jo;
//    }

//    public static SparseArray<Pair> getPrice(Pair pair) {
//        SparseArray<Pair> list = new SparseArray<>();
//        try {
//            JSONArray joPairs = getJoPrice().getJSONArray(pair.getType() + "");
//            JSONObject joPair = joPairs.getJSONObject(pair.getKey());
//            if (joPair.getInt("key") != pair.getKey()) {
//                throw new JSONException("错误的JSON格式,文件\"" + FILENAME_PRICE + "\", type=" + pair.getType() + ", key=" + joPair.getInt("key") + ",传入" + pair.getKey());
//            }
//            JSONArray _joPrices = joPair.getJSONArray("price");
//            for(int i=0;i<_joPrices.length();i++){
//                list.put(_joPrices.getJSONObject(i).getInt("key"), PairFactory.newInstance(_joPrices.getJSONObject(i)));
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return list;
//    }

    private static List<Price> parsePrice(String strPrices){
        List<Price> list = new ArrayList<>();
        String splits[] = strPrices.split(";");
        for(String str:splits){
            String s[] = str.split(",");
            if(s.length<3){
                break;
            }
            Price price = new Price(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Long.parseLong(s[2]));
            list.add(price);
        }
        return list;
    }

    private static Pair jsonToPair(JSONObject jo){
        Pair pair = null;
        int type = jo.optInt("type",-1);
        int key = jo.optInt("key",-1);
        long value = jo.optLong("value", -1);
        long growth = jo.optLong("growth", -1);
        String title = jo.optString("title");
        String desc = jo.optString("desc");
        String strPrices = jo.optString("price","");
        String strProductions = jo.optString("productions","");
        List<Price> prices = parsePrice(strPrices);
        List<Price> productions = parsePrice(strProductions);
        if(key==-1||value==-1|growth==-1){
            return null;
        }
        switch (type){
            case Pair.TYPE_BUILDING:{
                pair = new Building(key,value,growth, title, desc);
                buildingsPrices.put(key,prices);
                buildingsProductions.put(key,productions);
                break;
            }
            case Pair.TYPE_SUPPLIES:{
                pair = new Supplies(key,value,growth, title, desc);
                suppliesPrices.put(key,prices);
                suppliesProductions.put(key,productions);
                break;
            }
            default:break;
        }
        return pair;
    }



}
