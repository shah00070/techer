package com.schoolteacher.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.pojos.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gaurav Gupta on 18-04-2015.
 */
public class NavigationDrawerAdapter extends
		RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
	List<NavDrawerItem> data = Collections.emptyList();
	private LayoutInflater inflater;

	public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
		inflater = LayoutInflater.from(context);
		this.data = data;
	}

	public void delete(int position) {
		data.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		NavDrawerItem current = data.get(position);
		holder.title.setText(current.getTitle());
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		TextView title;

		public MyViewHolder(View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.title);
		}
	}
}
