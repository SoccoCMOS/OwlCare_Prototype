package co.owlmed_dz.owlcare;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import android.widget.TextView;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import co.owlmed_dz.owlcare.R;

public class BPFragment extends Fragment {

    LineChart lineChart;
    LineData lineData;
    ArrayList<Float> values, values1;

    public BPFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values=new ArrayList<Float>();
        values1=new ArrayList<Float>();
        for (int i=0; i<86400; i++)
        {
            values.add((float)Math.random()*50+100);
            values1.add((float)Math.random()*40+50);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_monitor, container, false);
        ((TextView)rootView.findViewById(R.id.titleGraph)).setText("Daily Blood Pressure");
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

            ILineDataSet set = data.getDataSetByIndex(0);
            ILineDataSet warnSet = data.getDataSetByIndex(1);
            ILineDataSet set1 = data.getDataSetByIndex(2);
            ILineDataSet warnSet1 = data.getDataSetByIndex(3);
            // set.addEntry(...); // can be called as well

            if (set == null || set1==null || warnSet ==null || warnSet1==null) {
                set = createSet(ColorTemplate.getHoloBlue(),Color.BLACK,"Systole");
                warnSet = createSet(Color.TRANSPARENT,Color.RED,"Abnormal Systole");
                set1 = createSet(ColorTemplate.MATERIAL_COLORS[1],Color.BLACK,"Diastole");
                warnSet1 = createSet(Color.TRANSPARENT,Color.RED,"Abnormal Diastole");
                data.addDataSet(set);
                data.addDataSet(warnSet);
                data.addDataSet(set1);
                data.addDataSet(warnSet1);
            }

            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.SECOND) + 3600 * c.get(Calendar.HOUR) + 60* c.get(Calendar.MINUTE);

            Entry en=new Entry(time,values.get(set.getEntryCount()));
            data.addEntry(en, 0);
            if(en.getY()>140 || en.getY()<110)
            {
                //lineChart.highlightValue(en.getX(),en.getY(),0);
                data.addEntry(en,1);
            }

            Entry en1=new Entry(time,values.get(set.getEntryCount()));
            data.addEntry(en1, 2);
            if(en.getY()>80 || en.getY()<50)
            {
                //lineChart.highlightValue(en.getX(),en.getY(),0);
                data.addEntry(en1,3);
            }

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
        // Affichage
        feedMultiple();
        Legend l=lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setEnabled(true);
        lineChart.invalidate(); // refresh
    }
}
