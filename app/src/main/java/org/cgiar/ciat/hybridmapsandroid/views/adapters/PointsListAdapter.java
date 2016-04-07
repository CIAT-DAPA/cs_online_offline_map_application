package org.cgiar.ciat.hybridmapsandroid.views.adapters;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.cgiar.ciat.hybridmapsandroid.R;
import org.cgiar.ciat.hybridmapsandroid.models.points.source.Points;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HSOTELO on 4/7/2016.
 */
public class PointsListAdapter extends BaseAdapter {
    //Members Class
    private Activity activity;
    private List<Points> points;

    /**
     * Method Construct
     * @param activity
     * @param points
     */
    public PointsListAdapter( Activity activity, List<Points> points){
        this.activity = activity;
        this.points = points;
    }

    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Object getItem(int position) {
        return points.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Points p = points.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_points_list_element, null, true);
        TextView title = (TextView)view.findViewById(R.id.pnt_frg_elm_title);
        TextView lat = (TextView)view.findViewById(R.id.pnt_frg_elm_lat);
        TextView lon = (TextView)view.findViewById(R.id.pnt_frg_elm_lon);

        title.setText(String.valueOf(p.getId()) + " - " + p.getName());
        lat.setText("Latitude: " + String.valueOf(p.getLat()));
        lon.setText("Longitude: " + String.valueOf(p.getLon()));
        return view;
    }
}
