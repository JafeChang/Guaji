package info.jafe.guaji.ui.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import info.jafe.guaji.Entity.factories.PairFactory;
import info.jafe.guaji.Entity.abstracts.Pair;
import info.jafe.guaji.R;
import info.jafe.guaji.adapter.BuildingAdapter;
import info.jafe.guaji.app.App;
import info.jafe.guaji.ui.interfaces.OnFragmentInteractionListener;
import info.jafe.guaji.utils.Hand;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildingFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static BuildingFragment instance;

    private String mParam1;
    private String mParam2;

    private final int type = Pair.TYPE_BUILDING;

    private View view;

    private Button bt;
    private GridView buildingListView;

    private BuildingAdapter buildingAdapter;
    private SparseArray<Pair> buildingList;

    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildingsFragment.
     */
    public static synchronized BuildingFragment getInstance(String param1, String param2) {
        if(instance == null){
            instance = new BuildingFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            instance.setArguments(args);
        }
        return instance;
    }

    public BuildingFragment() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_building, container, false);
        initView();
        return view;
    }

    private void initView() {
        bt = (Button)view.findViewById(R.id.building_button);
        buildingListView = (GridView) view.findViewById(R.id.building_grid);

        buildingList = App.get().getPairList(Pair.TYPE_BUILDING);
        buildingAdapter = new BuildingAdapter(buildingList);

        bt.setOnClickListener(this);
        buildingListView.setAdapter(buildingAdapter);
        buildingListView.setOnItemClickListener(new OnBuildingClickListener());

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.building_button:{
//                Pair pair = PairFactory.newInstance(0,0,1,1);
//                List<Pair> list = new Price().getPrice(pair);
//                for(Pair p:list){
//                    Logs.d(p.toString());
//                }
//                if(temp<4){
//                    Pair pair = PairFactory.newInstance(Pair.TYPE_BUILDING, temp<=App.get().getKeysAmount(type)?);
//                    App.get().addPair(pair);
//                    //                List<Pair> list = DataManager.get().readAll(Pair.TYPE_SUPPLIES);
//                    //                Logs.d(list.size() + "");
//                    buildingAdapter.notifyDataSetChanged();
//                }
//                temp = temp>=4?0:temp+1;
                for(int i=0;i<PairFactory.getKeysAmount(type);i++){
                    Pair pair = App.get().getPair(type,i);
                    if(pair==null){
                        App.get().newPair(type,i);
                        break;
                    }else{
                        pair.setGrowth(1);
                        pair.grow(1);
                    }

                }
                refresh();
                break;
            }
        }

    }

    public void refresh(){
        if(buildingAdapter!=null){
            buildingAdapter.notifyDataSetChanged();
        }
    }


}

class OnBuildingClickListener implements AdapterView.OnItemClickListener{


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BuildingAdapter buildingAdapter = (BuildingAdapter) parent.getAdapter();
        Pair pair = (Pair) buildingAdapter.getItem(position);
        App.get().products(pair);
        Hand.send(Hand.What.REFRESH_ADAPTER);
    }
}

