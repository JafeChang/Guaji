package info.jafe.guaji.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.jafe.guaji.R;
import info.jafe.guaji.adapter.NewsAdapter;
import info.jafe.guaji.ui.listeners.OnFragmentInteractionListener;
import info.jafe.guaji.utils.Logs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements OnFragmentInteractionListener,View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static NewsFragment instance;

    private View view;

    private NewsAdapter newsAdapter;
    private List<String> list;
    private List<Integer> listNum;
    private ListView listView;
    private Button button;


    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    public static synchronized NewsFragment getInstance(String param1, String param2) {
        if (instance == null) {
            instance = new NewsFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            instance.setArguments(args);
        }
        return instance;
    }

    public static NewsFragment get() {
        return getInstance("", "");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }
        initView();
        return view;
    }

    private void initView() {
        button = (Button) view.findViewById(R.id.news_button);
        listView = (ListView) view.findViewById(R.id.news_list);
        listNum = new ArrayList<>();
        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(list);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new OnItemClick());
        button.setOnClickListener(this);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_button: {
                int now = listNum.size();
                listNum.add(now);
                list.add((now + 30) + "");
                newsAdapter.notifyDataSetChanged();
                break;
            }
            default:break;
        }
    }
}

class OnItemClick implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Logs.d(view.getHeight()+"");
        Logs.d(view.getMeasuredHeight()+"");
    }
}

