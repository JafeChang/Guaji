package info.jafe.guaji.app;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.Entity.Display;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.DisplayAdapter;
import info.jafe.guaji.utils.Logs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private List<Display> displayList;
    private DisplayAdapter displayAdapter;
    private static App instance = null;
    private int bar = 0;//TODO delete

    @Override
    public void onCreate(){
        super.onCreate();
        displayList = new ArrayList<>();
        displayAdapter = new DisplayAdapter(displayList);
        instance = this;
    }
    public static synchronized App get(){
        return instance;
    }

//    public void addToList(Display display){
//        if(display.getIndex()>-1){
//            return;
//        }
//        display.setIndex(displayList.size());
//        displayList.add(display);
//        displayAdapter.notifyDataSetChanged();
//    }

    public void addDisplay(){
        if(bar>3){
            return;
        }
        String key = getResources().getStringArray(R.array.pair_keys)[bar];
        long value = getResources().getIntArray(R.array.pair_default_values)[bar];
        Display display = new Display(key,value,10000);
        display.setIndex(displayList.size());
        Logs.d("bar : " + bar + "\tkey : " + key + "\tvalue : " + value);
        bar++;
        displayList.add(display);
        displayAdapter.notifyDataSetChanged();
    }

    public void growUpdate(){
        for(Display display:displayList){
            display.grow();
        }
        displayAdapter.notifyDataSetChanged();
    }

    public void bindDisplayList(ListView listView){
        listView.setAdapter(displayAdapter);
    }
    public void foo(){
        Logs.d("bar - "+bar);
        addDisplay();
        growUpdate();
    }


}
