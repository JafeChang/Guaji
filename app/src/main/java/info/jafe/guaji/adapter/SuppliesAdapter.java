package info.jafe.guaji.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.jafe.guaji.Entity.interfaces.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.abstracts.AbstractsAdapter;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class SuppliesAdapter extends AbstractsAdapter{
    private ViewHolder holder;

    public SuppliesAdapter(List<Pair> list) {
       super(list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = null;
        if (null == convertView) {
            holder=new ViewHolder();
            if(null == mInflater){
                mInflater = LayoutInflater.from(MainActivity.instance);
            }
            convertView = mInflater.inflate(R.layout.unit_supplies, null,false);
            holder.tvKey = (TextView)convertView.findViewById(R.id.supplies_unit_key);
            holder.tvValue = (TextView)convertView.findViewById(R.id.supplies_unit_value);
            holder.tvGrowth = (TextView)convertView.findViewById(R.id.supplies_unit_growth);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Pair pair = (Pair) getItem(position);
        holder.tvKey.setText(app.getKeyStr(pair));
        holder.tvValue.setText(Strs.f(pair.getValue()));
        holder.tvGrowth.setText(Strs.f(pair.getGrowth()));
        return convertView;
    }

    class ViewHolder{
        TextView tvKey;
        TextView tvValue;
        TextView tvGrowth;
        ViewHolder(){}
    }
}
