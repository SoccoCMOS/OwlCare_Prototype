package co.owlmed_dz.owlcare;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.ArrayList;
import android.widget.TextView;

import android.graphics.Color;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerImage;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.Calendar;

import co.owlmed_dz.owlcare.R;

public class MonitorFragment extends Fragment {

    LineChart lineChart;
    LineData lineData;
    //IMarker markerImage;
    ArrayList<Float> values;
    ILineDataSet set;
    ILineDataSet setWarn;

    public MonitorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values = new ArrayList<Float>();

        for (int i = 0; i < 86400; i++) {
            values.add((float) Math.random() * 70 + 50);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_monitor, container, false);
        ((TextView)rootView.findViewById(R.id.titleGraph)).setText("Daily Heart Rate");
        lineChart = (LineChart) rootView.findViewById(R.id.chart);

        lineData = new LineData();
        lineData.getDataSetLabels();
        // Setting data
        lineChart.setData(lineData);
        setupChart();

        fillChart();
        return rootView;
    }

    public void setupChart()
    {
        lineChart.setDescription(null);
        lineChart.setTouchEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.9f);
        lineChart.getXAxis().setDrawLabels(true);
        lineChart.getAxisRight().setDrawLabels(true);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int millis=(int) value;
                String s=String.format("%02d:%02d:%02d",
                        TimeUnit.SECONDS.toHours(millis),
                        TimeUnit.SECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(millis)), // The change is in this line
                        TimeUnit.SECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(millis)));
                return s;
            }
        });
    }

    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    private LineDataSet createSet(int color, int ccolor, String desc) {

        LineDataSet set = new LineDataSet(null,desc);
        set.setColors(new int[] { color});
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setCircleColor(ccolor);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(color);
        set.setHighLightColor(Color.rgb(200, 117, 117));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(9f);
        set.setDrawValues(false);

        //Highlighting parameters
        //set.setHighlightEnabled(true); // allow highlighting for DataSet
        //set.setDrawHighlightIndicators(true);
        //set.setHighLightColor(Color.YELLOW); // color for highlight indicator

        return set;
    }

    public void addEntry()
    {
        LineData data = lineChart.getData();

        if (data != null) {

            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.SECOND)+c.get(Calendar.HOUR)*3600+c.get(Calendar.MINUTE)*60;
            Entry en=new Entry(time,values.get(time));
            if(en.getY()>110 || en.getY()<60)
            {
                //lineChart.highlightValue(en.getX(),en.getY(),0);
                data.addEntry(en,1);
            }

            data.addEntry(en, 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            lineChart.notifyDataSetChanged();

            // limit the number of visible entries
            lineChart.setVisibleXRangeMaximum(120);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            lineChart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private Thread thread;

    private void feedMultiple() {

        if (thread != null)
            thread.interrupt();

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                addEntry();
            }
        };

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    getActivity().runOnUiThread(runnable);
                    //getView().post(runnable);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void fillChart() {

        LineData data = lineChart.getData();

        set = data.getDataSetByIndex(0);
        setWarn = data.getDataSetByIndex(1);
        // set.addEntry(...); // can be called as well

        if (set == null || setWarn==null) {
            set = createSet(ColorTemplate.getHoloBlue(),Color.BLACK,"Normal");
            setWarn = createSet(Color.RED,Color.RED,"Abnormal");
            data.addDataSet(set);
            data.addDataSet(setWarn);
        }
/*
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.SECOND)+c.get(Calendar.HOUR)*3600+c.get(Calendar.MINUTE)*60;

        Entry en;

        for(int i=0; i<time; i++)
        {
            en=new Entry(i,values.get(i));
            if(en.getY()>110 || en.getY()<60)
            {
                data.addEntry(en,1);
            }

            data.addEntry(en, 0);
        }
*/
        //data.notifyDataChanged();

        // let the chart know it's data has changed
        ///lineChart.notifyDataSetChanged();

        // Affichage
        feedMultiple();
        Legend l=lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setEnabled(true);
        lineChart.invalidate(); // refresh
    }
}
