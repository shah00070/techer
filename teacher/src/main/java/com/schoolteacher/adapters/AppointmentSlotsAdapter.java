package com.schoolteacher.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.JeevSlot;

import java.util.Calendar;
import java.util.List;

public class AppointmentSlotsAdapter extends BaseAdapter {
	Activity activity;
	List<JeevSlot> slots;
	int selectedYear, selectedDay, selectedMonth, year, month, day;
	Calendar c;
	boolean defaultSet = false;

	public AppointmentSlotsAdapter(Activity activity, List<JeevSlot> slots,
			int yr, int dy, int mon) {
		c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		this.activity = activity;
		this.slots = slots;
		this.selectedYear = yr;
		this.selectedDay = dy;
		this.selectedMonth = mon;
	}

	@Override
	public int getCount() {
		return slots.size();
	}

	@Override
	public Object getItem(int location) {
		return slots.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.checked_edit_text, null);
			holder.checkedTextView = (CheckedTextView) convertView
					.findViewById(R.id.checkedTextView1);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// getting slots

		if (!(slots.get(position).isIsSlotAvailable())) {
			holder.checkedTextView.setEnabled(false);
			holder.checkedTextView.setFocusable(false);
			holder.checkedTextView.setFocusableInTouchMode(false);
			holder.checkedTextView.setClickable(false);
			holder.checkedTextView.setChecked(false);

			// holder.checkedTextView.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.chk_indicator_disable));
		} else if (!compareTimes(slots.get(position).getFromTime())) {
			holder.checkedTextView.setEnabled(false);
			holder.checkedTextView.setFocusable(false);
			holder.checkedTextView.setFocusableInTouchMode(false);
			holder.checkedTextView.setClickable(false);
			holder.checkedTextView.setChecked(false);

			// holder.checkedTextView.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.chk_indicator_disable));
		}
		// else {
		// if (!defaultSet) {
		// holder.checkedTextView.setChecked(true);
		// ((Appointments) activity).setBookedTimeValue(slots
		// .get(position).getName(),slots.get(position).getFromTime());
		// defaultSet = true;
		// }
		// holder.checkedTextView.setEnabled(true);
		// }

		holder.checkedTextView.setText(slots.get(position).getName());
		holder.checkedTextView.setTag(slots.get(position).getFromTime());
		return convertView;
	}

	public class ViewHolder {
		private CheckedTextView checkedTextView;

	}

	private boolean compareTimes(String time) {

		if (year == selectedYear) {

			if (selectedMonth <= month) {
				if (selectedDay <= day) {
					String currentTime = CommonCode.currentTime();
					int currentHour = Integer.parseInt(currentTime.substring(0,
							2));
					int currentMin = Integer.parseInt(currentTime.substring(3));

					int slotHour = Integer.parseInt(time.substring(0, 2));
					int slotMin = Integer.parseInt(time.substring(3, 5));

					if (slotHour < currentHour) {
						return false;
					} else if (slotHour > currentHour) {
						return true;
					} else if (slotMin < currentMin) {
						return false;
					} else return slotMin > currentMin;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

}