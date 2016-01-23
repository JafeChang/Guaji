package info.jafe.guaji.adapter.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import info.jafe.guaji.Entity.Supplies;

/**
 * Created by jianfei on 2016/1/22.
 */
public abstract class AbstractsAdapter <T>extends BaseAdapter{
    protected List <T> list;
    protected LayoutInflater mInflater;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public AbstractsAdapter(List<T> list){
        this.list = list;
    }




}
