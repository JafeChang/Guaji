package info.jafe.guaji.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.R;
import info.jafe.guaji.ui.MainActivity;

/**
 * Created by JafeChang on 16/1/14.
 */
public class DisplayAdapter extends BaseAdapter{
    private List<Map<String, Object>> displayList;
    private ViewHolder holder;
    private LayoutInflater mInflater;

    private interface Keys{
        String DIS_KEY = "key";
        String DIS_VALUE = "value";
        String DIS_GROWTH = "growth";
    }

    public DisplayAdapter(List<Map<String, Object>> displayList){
        mInflater = LayoutInflater.from(MainActivity.instance);
        this.displayList = displayList;
    }

    @Override
    public int getCount() {
        return displayList.size();
    }

    @Override
    public Object getItem(int position) {
        return displayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.display_unit, null);
            holder.tvKey = (TextView)convertView.findViewById(R.id.display_unit_key);
            holder.tvValue = (TextView)convertView.findViewById(R.id.display_unit_value);
            holder.tvGrowth = (TextView)convertView.findViewById(R.id.display_unit_growth);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        String key = (String) displayList.get(position).get(Keys.DIS_KEY);
        String value = displayList.get(position).get(Keys.DIS_VALUE)+"";
        String growth = displayList.get(position).get(Keys.DIS_GROWTH)+"";
        holder.tvKey.setText(key);
        holder.tvValue.setText(value);
        holder.tvGrowth.setText(growth);
        return convertView;
    }

    public Map<String, Object> unit(String key, long value, long growth){
        Map<String, Object> map = new HashMap<>();
        map.put(Keys.DIS_KEY,key);
        map.put(Keys.DIS_VALUE,value);
        map.put(Keys.DIS_GROWTH,growth);
        return map;
    }

    class ViewHolder{
        TextView tvKey;
        TextView tvValue;
        TextView tvGrowth;
        ViewHolder(){}
    }
}
