package co.owlmed_dz.owlcare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import android.widget.ListView;

/**
 * Created by DUALCOMPUTER on 1/11/2017.
 */
public class FUAFragment extends Fragment {

    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_follow_up, container, false);
        ListView listView=(ListView) getActivity().findViewById(R.id.listContacts);
        return rootView;
    }
}
