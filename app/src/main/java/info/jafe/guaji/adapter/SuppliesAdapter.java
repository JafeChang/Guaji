package info.jafe.guaji.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.jafe.guaji.entity.abstracts.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.abstracts.AbstractsAdapter;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Strs;

/**
 * Created by JafeChang on 16/1/14.
 */
public class SuppliesAdapter extends AbstractsAdapter{
    private ViewHolder holder;

    public SuppliesAdapter(SparseArray<Pair> list) {
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
            holder.tvTitle = (TextView)convertView.findViewById(R.id.supplies_unit_title);
            holder.tvValue = (TextView)convertView.findViewById(R.id.supplies_unit_value);
            holder.tvGrowth = (TextView)convertView.findViewById(R.id.supplies_unit_growth);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Pair pair = (Pair) getItem(position);
        holder.tvTitle.setText(pair.getTitle());
        holder.tvValue.setText(Strs.f(pair.getValue()));
        holder.tvGrowth.setText(Strs.f(pair.getGrowth()));
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle;
        TextView tvValue;
        TextView tvGrowth;
        ViewHolder(){}
    }
}
