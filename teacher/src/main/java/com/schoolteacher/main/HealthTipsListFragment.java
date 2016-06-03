package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;

/**
 * Created by chandan on 24/12/15.
 */
public class HealthTipsListFragment extends Fragment {


    private GlobalAlert globalAlert;
    String[] mobileArray = {
            "Both positive and negative comments can stimulate learning, but positive comments seem to be most effective. Praise what the student has done right. It builds self-confidence.",
            "Create Projects that relate locally - Pulling a students' everyday life into the classroom makes a big difference.",
            "Journal writing is a great way to close a lesson. It keeps the kids focused and provides an opportunity for them to express their thoughts in a constructive way.",
            "Use the last five minutes of a lesson to get your students to tell you what they learned as a result of the lesson. Revision works wonders in retaining and memorizing.",
            "Teach your students all emergency procedures that are needed in case they are faced with crisis situations in the school compound.",
            "Make the first day of the class count. Discuss a core idea, pose a typical problem, or ask students to complete a group exercise.",
            "Shopping Games - Students love to buy things. What a great way to learn math facts quickly and with purpose.",
            "Having trouble getting your students to read? Send them on a treasure hunt. Chose several sections of text and ask students to find the most important point, idea, argument.",
            "To get the discussion started ask students to generate comments or questions and post them on the discussion board between courses. Participate in online discussion with students.",
            "For an effective lesson, summarize your points. Avoid the use of complete sentences. Use boldface or italic type instead of underlining. Use color sparingly.",

    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.health_tips_list_fragment, container,
                false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) rootView.findViewById(R.id.listVieww);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Health tips listing");
    }




    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }
}

