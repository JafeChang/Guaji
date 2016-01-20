package info.jafe.guaji.app;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.Entity.Display;
import info.jafe.guaji.adapter.DisplayAdapter;

/**
 * Created by JafeChang on 16/1/14.
 */
public class App extends Application {
    private List<Map<String,Object>> displayList;
    private DisplayAdapter displayAdapter;
    private static App instance = null;
    private App(){
        displayList = new ArrayList<>();
        displayAdapter = new DisplayAdapter(displayList);
    }
    public static synchronized App get(){
        if(instance==null){
            instance = new App();
        }
        return instance;

    }

    public void addToList(Display display){
        if(display.getIndex()>-1){
            return;
        }
        display.setIndex(displayList.size());
        displayList.add(displayAdapter.unit(display.getKey(), display.getValue(),display.getGrowth()));
        displayAdapter.notifyDataSetChanged();
    }

    public void growUpdate(Display display){
        //TODO
    }

    public void bindDisplayList(ListView listView){
        listView.setAdapter(displayAdapter);
    }

}
