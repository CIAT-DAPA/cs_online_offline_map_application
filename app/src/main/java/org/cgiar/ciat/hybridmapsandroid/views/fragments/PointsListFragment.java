package org.cgiar.ciat.hybridmapsandroid.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.cgiar.ciat.hybridmapsandroid.R;
import org.cgiar.ciat.hybridmapsandroid.models.points.repositories.PointsDB;
import org.cgiar.ciat.hybridmapsandroid.models.points.source.Points;
import org.cgiar.ciat.hybridmapsandroid.views.adapters.PointsListAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class PointsListFragment extends Fragment {

    ListView fgm_points_list_list_view;

    public PointsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fgm_points_list_list_view=(ListView)getView().findViewById(R.id.fgm_points_list_list_view);
        try{
            fgm_points_list_list_view.setAdapter(new PointsListAdapter(getActivity(),getPoints()));
            //fgm_points_list_list_view.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, getPoints()));
        }
        catch (Exception e){
            Log.d("VIEW_FRAG_POINT", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_points_list, container, false);
    }

    private List<Points> getPoints(){
        String[] res=null;
        PointsDB db = new PointsDB(getActivity().getApplicationContext());
        PointsDB.PointsEntity entity = db.new PointsEntity();
        return entity.all();
        /*List<Points> points=entity.all();
        res=new String[points.size()];
        for(int i=0;i<points.size();i++)
            res[i]=points.get(i).toString();
        return res;*/
    }

}
