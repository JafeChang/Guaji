package info.jafe.guaji.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.abstracts.AbstractsAdapter;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Strs;

/**
 * Created by jianfei on 2016/1/27.
 */
public class BuildingAdapter extends AbstractsAdapter {

    private ViewHolder holder;
    public BuildingAdapter(SparseArray<Pair> list){
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
            holder.tvTitle = (TextView)convertView.findViewById(R.id.building_unit_title);
            holder.tvValue = (TextView)convertView.findViewById(R.id.building_unit_value);
            holder.imBuilding = (ImageView)convertView.findViewById(R.id.building_unit_img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Pair pair = (Pair) getItem(position);
        holder.tvTitle.setText(pair.getTitle());
        holder.tvValue.setText(Strs.f(pair.getValue()));
        return convertView;
    }


    class ViewHolder{
        ImageView imBuilding;
        TextView tvTitle;
        TextView tvValue;
        ViewHolder(){}
    }
}
