package info.jafe.guaji.adapter.abstracts;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import info.jafe.guaji.app.App;

/**
 * Created by jianfei on 2016/1/22.
 */
public abstract class AbstractsAdapter <Pair>extends BaseAdapter{
    protected SparseArray<Pair> list;
    protected LayoutInflater mInflater;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public AbstractsAdapter(SparseArray<Pair> list){
        this.list = list;
    }




}
