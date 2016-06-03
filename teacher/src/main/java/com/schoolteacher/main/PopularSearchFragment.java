package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.adapters.PopularSearchAdapter;


/**
 * Created by chandan on 16/12/15.
 */
public class PopularSearchFragment extends Fragment {
    private View rootView;

    private String[] itemList;
    private String[] date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.popular_search_fragment, container,
                false);
        setUiOnScreen();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Popular search fragment");
    }

    public void setUiOnScreen() {
        ListView listView = (ListView) rootView.findViewById(R.id.search_listview);
        itemList = getActivity().getResources().getStringArray(R.array.popular_search_list);
        date = getActivity().getResources().getStringArray(R.array.date_list);
        PopularSearchAdapter adapter = new PopularSearchAdapter(getActivity(), itemList,date);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
if(position==0){


}else if(position==1){

}
else if(position==2){

}
else if(position==3){

}
else if(position==4){

}
else if(position==5){

}
else if(position==6){

}
else if(position==7){

}
else if(position==8){

}
else if(position==9){

}


else if(position==10){

}

else if(position==11){

}

else if(position==12){

}

else if(position==13){

}

else if(position==14){

}


            }
        });
    }


}
