package com.schoolteacher.healthvitals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.Element2;
import com.schoolteacher.pojos.Measurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 9/22/2015.
 */
public class ViewHealthVitalInformation extends AppCompatActivity {
    ArrayList<String> listOfMeasurements = null;
    LinearLayout graph_contain, details_container;
    String name;
    Toolbar toolbar_healthvitals_individual;
    ArrayList<Measurement> elementsList;
    List<Element2> elements = null;
    LineChartView linechart;
    LineSet lineSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_health_vital);
        elementsList = new ArrayList();
        // graph_contain = (LinearLayout) findViewById(R.id.graph_contain);
        details_container = (LinearLayout) findViewById(R.id.details_container);
        Intent intent = getIntent();
        listOfMeasurements = intent.getStringArrayListExtra("indicatorsValue");
        elementsList = (ArrayList<Measurement>) intent.getSerializableExtra("elements");
        name = intent.getStringExtra("header_name");
        setUpActionBar();

        linechart = (LineChartView) findViewById(R.id.linechart);
    }

    private void setUpActionBar() {
        toolbar_healthvitals_individual = (Toolbar) findViewById(R.id.toolbar_healthvitals_individual);
        setSupportActionBar(toolbar_healthvitals_individual);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> datesList = new ArrayList<>();
        int n = 0;
        if (elementsList.size() > 5)
            n = elementsList.size();
        else
            n = elementsList.size();
        float[] objectstest = new float[n];
        float[] objects = new float[n];
        float[] objectsSystolic = new float[n];
        float[] objectsSystolictest = new float[n];


        for (int j = 0; j < n; j++) {


            Measurement measurementObject = elementsList.get(j);
            List<Element2> elements = measurementObject.getElements();
            datesList.add(CommonCode.getDateFromTimeStamp(elements.get(0).getMeasureOn()));
            objects[j] = Float.parseFloat(elements.get(0).getMeasuredValue());


            if (elements.size() == 2) {
                objectsSystolic[j] = Float.parseFloat(elements.get(1).getMeasuredValue());
            }
        }

      int nn=objectsSystolic.length;
        int iii=0;
        for (int i = nn-1; i >= 0; i--) {
            objectsSystolictest[iii]=objectsSystolic[i];
      iii++;
        }




        for(int i=0;i<nn;i++){
            objectsSystolic[i]=objectsSystolictest[i];
        }



        int ii=0;
        for (int i = n-1; i >= 0; i--) {
          objectstest[ii]=objects[i];


            ii++;
       }

for(int i=0;i<n;i++){
    objects[i]=objectstest[i];
}



        //  String[] values = new String[datesList.size()];
        String[] values = new String[n];

int iii4=0;
        for (int i = n-1; i >= 0; i--) {
            values[iii4] = datesList.get(i);
            iii4++;
        }




        LineSet dataset = new LineSet(values, objects);
        dataset.setColor(Color.parseColor("#008000"))
                //.setFill(Color.parseColor("#a34545"))
                //.setDotsColor(Color.parseColor("#a12345"))
                .setThickness(4)
                .setDotsStrokeColor(Color.parseColor("#2FDF3C"))
                .setDotsColor(Color.parseColor("#FFFFFF"))
                .setSmooth(false);


        ///// For Blood Pressure case  ///////////////////////
        LineSet dataset_systolic = null;

        if (elementsList.size() > 0 && elementsList.get(0).getElements().size() == 2) {

            dataset_systolic = new LineSet(values, objectsSystolic);

            dataset_systolic.setColor(Color.parseColor("#0000ff"))
                    //.setFill(Color.parseColor("#a34545"))
                    //.setDotsColor(Color.parseColor("#a12345"))
                    .setThickness(4)
                    .setDotsStrokeColor(Color.parseColor("#2FDF3C"))
                    .setDotsColor(Color.parseColor("#ffffff"))
                    .setSmooth(false);
        }

        linechart.setBackgroundColor(Color.parseColor("#ffffff"));

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor("#b9b5b2"));

        linechart.setGrid(LineChartView.GridType.FULL, paint);

        Arrays.asList(objects);
        if (objects.length > 0) {
            Arrays.sort(objects);
            float max_value;
            float max_default = objects[objects.length - 1];

            float max_val_systolic = 0;
            if (dataset_systolic != null) {
                Arrays.asList(objectsSystolic);
                Arrays.sort(objectsSystolic);
                max_val_systolic = objectsSystolic[objectsSystolic.length - 1];
            }

            if (max_default > max_val_systolic)
                max_value = max_default;
            else
                max_value = max_val_systolic;

            if (max_value > 10) {
                int value = (int) max_value;
                if (value < max_value)
                    linechart.setAxisBorderValues(0, (value + 1), (int) max_value / 10);
                else
                    linechart.setAxisBorderValues(0, value, (int) max_value / 10);
            } else
                linechart.setAxisBorderValues(0, 10, 1);
        }

        linechart.setScaleX(1.0f);
        linechart.setAxisColor(Color.parseColor("#00ff00"));
        linechart.setAxisThickness(2);
        linechart.setFontSize(18);

        linechart.addData(dataset);

        if (dataset_systolic != null)
            linechart.addData(dataset_systolic);

        linechart.show();
        showDetails();
    }

    private void showDetails() {
        Collections.reverse(elementsList);

        for (Measurement measurementObject : elementsList) {

            List<Element2> elements = measurementObject.getElements();

            LayoutInflater inflator = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // boolean added = false;
            View view = inflator.inflate(R.layout.detail_vital,
                    null);

            if (elements.size() == 2) {
                TextView date_time = (TextView) view.findViewById(R.id.date_time);
                TextView reading_value = (TextView) view.findViewById(R.id.reading_value_cms);

                date_time.setText(CommonCode.getDateFromTimeStamp(elements.get(0).getMeasureOn()) + " " + CommonCode.getTimeFromTimeStamp(elements.get(0).getMeasureOn()));
//              reading_value.setText(elementObject.getMeasuredValue() + " " + elementObject.getMeasurementUnitName());
                String measuredValues = elements.get(0).getMeasuredValue() + " " + elements.get(0).getMeasurementUnitName() + " | " + elements.get(1).getMeasuredValue() + " " + elements.get(1).getMeasurementUnitName();
                reading_value.setText(measuredValues);
                details_container.addView(view);
            } else {
                TextView date_time = (TextView) view.findViewById(R.id.date_time);
                TextView reading_value = (TextView) view.findViewById(R.id.reading_value_cms);

                date_time.setText(CommonCode.getDateFromTimeStamp(elements.get(0).getMeasureOn()) + " " + CommonCode.getTimeFromTimeStamp(elements.get(0).getMeasureOn()));
//                reading_value.setText(elementObject.getMeasuredValue() + " " + elementObject.getMeasurementUnitName());
                reading_value.setText(elements.get(0).getMeasuredValue() + " " + elements.get(0).getMeasurementUnitName());
                details_container.addView(view);
            }


        }
    }


}
