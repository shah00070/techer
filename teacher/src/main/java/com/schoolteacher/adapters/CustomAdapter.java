package com.schoolteacher.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.pojos.HealthUpdateMetaData;

import java.util.List;

/**
 * Created by chandan on 15/12/15.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TabItemViewHolder> {


    private List<HealthUpdateMetaData> healthUpdateMetaDataList;

    public CustomAdapter(List<HealthUpdateMetaData> healthUpdateMetaDataList) {
        this.healthUpdateMetaDataList = healthUpdateMetaDataList;
    }

    @Override
    public TabItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_item_layout, parent, false);
        return new TabItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TabItemViewHolder holder, int position) {
        HealthUpdateMetaData healthUpdateMetaData = healthUpdateMetaDataList.get(position);
        holder.title.setText(healthUpdateMetaData.getTitle());
        holder.description.setText(healthUpdateMetaData.getDescription());
    }

    @Override
    public int getItemCount() {
        return healthUpdateMetaDataList.size();
    }


    public static class TabItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected TextView description;

        public TabItemViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
        }

    }
}
