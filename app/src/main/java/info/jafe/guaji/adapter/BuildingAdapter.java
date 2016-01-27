package info.jafe.guaji.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.abstracts.AbstractsAdapter;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Strs;

/**
 * Created by jianfei on 2016/1/27.
 */
public class BuildingAdapter extends AbstractsAdapter {

    private ViewHolder holder;
    public BuildingAdapter(List<Pair> list){
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            holder=new ViewHolder();
            if(null == mInflater){
                mInflater = LayoutInflater.from(MainActivity.instance);
            }
            convertView = mInflater.inflate(R.layout.unit_building, null,false);
            holder.tvKey = (TextView)convertView.findViewById(R.id.building_unit_key);
            holder.tvValue = (TextView)convertView.findViewById(R.id.building_unit_value);
            holder.imBuilding = (ImageView)convertView.findViewById(R.id.building_unit_img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Pair pair = (Pair) getItem(position);
        holder.tvKey.setText(app.getKeyStr(pair));
        holder.tvValue.setText(Strs.f(pair.getValue()));
        return convertView;
    }


    class ViewHolder{
        ImageView imBuilding;
        TextView tvKey;
        TextView tvValue;
        ViewHolder(){}
    }
}
