package info.jafe.guaji.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.R;
import info.jafe.guaji.ui.MainActivity;

/**
 * Created by jianfei on 2016/3/2.
 */
public class NewsAdapter extends BaseAdapter {
    private ViewHolder holder;
    private LayoutInflater inflater;
    private List<String> list;

    public NewsAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            if (inflater == null) {
                inflater = LayoutInflater.from(MainActivity.instance);
            }
            convertView = inflater.inflate(R.layout.unit_news, null, false);
            holder.tvMain = (TextView) convertView.findViewById(R.id.impedance_unit_tv_main);
            holder.tvSub = (TextView) convertView.findViewById(R.id.impedance_unit_tv_sub);
            holder.tvFreq = (TextView) convertView.findViewById(R.id.impedance_unit_tv_freq);
            holder.tvFreqStart = (TextView) convertView.findViewById(R.id.impedance_unit_tv_freq_start);
            holder.tvFreqEnd = (TextView) convertView.findViewById(R.id.impedance_unit_tv_freq_end);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        String i = getItem(position);
        holder.tvMain.setText("Main "+i);
        holder.tvSub.setText("Sub "+i);
        holder.tvFreq.setText(i);
        holder.tvFreqStart.setText("30");
        holder.tvFreqEnd.setText("50");
        return convertView;
    }

    class ViewHolder {
        TextView tvMain;
        TextView tvSub;
        TextView tvFreq;
        TextView tvFreqStart;
        TextView tvFreqEnd;
        ImageView ivMain;
    }

}
