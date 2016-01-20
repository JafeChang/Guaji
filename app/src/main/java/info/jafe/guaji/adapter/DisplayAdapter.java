package info.jafe.guaji.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.jafe.guaji.Entity.Display;
import info.jafe.guaji.R;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class DisplayAdapter extends BaseAdapter{
    private List<Display> displayList;
    private ViewHolder holder;
    private LayoutInflater mInflater;

    public DisplayAdapter(List<Display> displayList){
//        mInflater = LayoutInflater.from(MainActivity.instance);
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
        if (null == convertView) {
            holder=new ViewHolder();
            if(null == mInflater){
                mInflater = LayoutInflater.from(MainActivity.instance);
            }
            convertView = mInflater.inflate(R.layout.display_unit, null);
            holder.tvKey = (TextView)convertView.findViewById(R.id.display_unit_key);
            holder.tvValue = (TextView)convertView.findViewById(R.id.display_unit_value);
            holder.tvGrowth = (TextView)convertView.findViewById(R.id.display_unit_growth);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvKey.setText(displayList.get(position).getKey());
        holder.tvValue.setText(Strs.f(displayList.get(position).getValue()));
        holder.tvGrowth.setText(Strs.f(displayList.get(position).getGrowth()));
        return convertView;
    }

    class ViewHolder{
        TextView tvKey;
        TextView tvValue;
        TextView tvGrowth;
        ViewHolder(){}
    }
}
