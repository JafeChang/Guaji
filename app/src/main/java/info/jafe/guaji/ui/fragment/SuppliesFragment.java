package info.jafe.guaji.ui.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import info.jafe.guaji.Entity.factories.PairFactory;
import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.SuppliesAdapter;
import info.jafe.guaji.app.App;
import info.jafe.guaji.ui.interfaces.OnFragmentInteractionListener;
import info.jafe.guaji.utils.Logs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SuppliesFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class SuppliesFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static SuppliesFragment instance;
    private String mParam1;
    private String mParam2;

    private final int type = Pair.TYPE_SUPPLIES;

    private View view;

    private Button bt;
    private ListView suppliesListView;

    private SuppliesAdapter suppliesAdapter;
    private SparseArray<Pair> suppliesList;


    private OnFragmentInteractionListener mListener;

    public SuppliesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuppliesFragment.
     */
    public static synchronized SuppliesFragment getInstance(String param1, String param2) {
        if(instance == null){
            instance = new SuppliesFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            instance.setArguments(args);
        }
        return instance;
    }

    public static SuppliesFragment get(){
        return  getInstance("","");
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
        view = inflater.inflate(R.layout.fragment_supplies, container, false);
        initView();
        return view;
    }

    private void initView(){
        bt = (Button) view.findViewById(R.id.supplies_button);
        suppliesListView = (ListView) view.findViewById(R.id.supplies_list);

        suppliesList = App.get().getPairList(Pair.TYPE_SUPPLIES);
        suppliesAdapter = new SuppliesAdapter(suppliesList);

        bt.setOnClickListener(this);
        suppliesListView.setAdapter(suppliesAdapter);
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


    private int temp = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.supplies_button:{
                App.get().doInTick();
//                Pair pair = PairFactory.newInstance(0,1);
//                Logs.d(pair.toString());
//                if(temp<4){
//                    Pair pair = PairFactory.new Instance(Pair.TYPE_SUPPLIES,temp);
//                    App.get().addPair(pair);
//    //                List<Pair> list = DataManager.get().readAll(Pair.TYPE_SUPPLIES);
//    //                Logs.d(list.size() + "");
//                    suppliesAdapter.notifyDataSetChanged();
//                    temp++;
//                }
                break;
            }
        }
    }

    public void refresh(){
        if(suppliesAdapter!=null){
            suppliesAdapter.notifyDataSetChanged();
        }
    }
}
