package app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import app.constants.FSConst;
import app.delegates.SharedPreferenceDelegate;
import app.models.SingleDetection;
import app.models.TypeAnalysis;
import app.tabsample.R;

/**
 * @author Adil Soomro
 */
public class DataActivity extends Activity {
    private SharedPreferenceDelegate sharedPreferenceDelegate = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sharedPreferenceDelegate = new SharedPreferenceDelegate(this);


    }

    public void showMenuPage(View view) {
        // inflate listView
        LinearLayout data_menu = (LinearLayout) inflateLayout(R.layout.activity_data_menu);
        // add to frameLayout
//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.data_activity_layout);
//        frameLayout.addView(data_menu);
        addToFrame(data_menu);
    }

    public void showHistory(View view) {
        LinearLayout history = (LinearLayout) inflateLayout(R.layout.activity_data_history);
        addToFrame(history);
        populateListViewHistory();
    }

    public void showBreastMilk(View view) {
        showTypeAnalysis(FSConst.TYPE_BREAST_MILK);
    }

    public void showMilkPowder(View view) {
        showTypeAnalysis(FSConst.TYPE_MILK_POWDER);
    }

    public void showSupplementaryFood(View view) {
        showTypeAnalysis(FSConst.TYPE_SUPPLEMENTARY_FOOD);
    }

    public void showTypeAnalysis(String typeName) {
        LinearLayout b_milk = (LinearLayout) inflateLayout(R.layout.activity_data_type_analysis);
        addToFrame(b_milk);
        populateListViewTypeAnalysis(typeName);
    }

    private View inflateLayout(int layoutID) {
//        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        return inflater.inflate(layoutID, null);
        View view = getLayoutInflater().inflate(layoutID, null);
        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showHistory(null);
    }

    private ArrayList<SingleDetection> getDetectionList() {
        return sharedPreferenceDelegate.getDetectionList();
    }

    // list view for all detections
    private void populateListViewHistory() {
        ArrayList<SingleDetection> array = getDetectionList();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, array);
        ListView lv1 = (ListView) this.findViewById(R.id.listview_data);
        lv1.setAdapter(adapter);
    }


    private void populateListViewTypeAnalysis(String typeName) {
        TypeAnalysis typeAnalysis = sharedPreferenceDelegate.getTypeAnalysis(typeName);
//        ArrayList<String> array = new ArrayList<String>();
//        for (ElementAnalysis ea : typeAnalysis.getDetails()) {
//            array.add(ea.toString());
//        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.detection_list_item, R.id.textView1, typeAnalysis.getDetails());
        ListView lv1 = (ListView) this.findViewById(R.id.listview_type_analysis);
        lv1.setAdapter(adapter);
    }

    private void addToFrame(View view) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.data_activity_layout);
        frameLayout.removeAllViewsInLayout();
        frameLayout.addView(view);
    }
}
