package com.schoolteacher.appointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.util.JeevomUtilsClass;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AppointmentAdapter extends BaseAdapter {

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_SEPARATOR = 1;

	private List<ServiceRequisitionData> mData = new ArrayList<ServiceRequisitionData>();
	private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

	private LayoutInflater mInflater;

	public AppointmentAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void addItem(List<ServiceRequisitionData> upComingAppointment) {
		mData.addAll(upComingAppointment);
		notifyDataSetChanged();
	}

	public void addSectionHeaderItem(ServiceRequisitionData object) {
		mData.add(object);
		sectionHeader.add(mData.size() - 1);
		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {
		return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public ServiceRequisitionData getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		int rowType = getItemViewType(position);
		String drName;
		if (convertView == null) {
			holder = new ViewHolder();
			switch (rowType) {
			case TYPE_ITEM:
				convertView = mInflater.inflate(
						R.layout.my_apppointment_list_item, null);
				holder.drName = (TextView) convertView
						.findViewById(R.id.dr_name);

				holder.appointmentType = (TextView) convertView
						.findViewById(R.id.appointment_type);

				holder.clinicName = (TextView) convertView
						.findViewById(R.id.clinic_name);

				holder.time = (TextView) convertView
						.findViewById(R.id.time_address);

				holder.date = (TextView) convertView.findViewById(R.id.date);

				break;
			case TYPE_SEPARATOR:
				convertView = mInflater.inflate(R.layout.text_seprator, null);
				holder.seperator = (TextView) convertView
						.findViewById(R.id.upcoming);

				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ServiceRequisitionData data = mData.get(position);
		if (sectionHeader.contains(position)) {
			holder.seperator.setText(data.getUpComing());
		} else {
			String[] formattedDate = JeevomUtilsClass.formatDate(
					data.getAppointmentDate()).split("-");
			String date = formattedDate[0] + "\n" + formattedDate[1];

			if (data.getToProfessionalId() > 0) {
				drName = "Dr." + " " + data.getToProfessionalFirstName() + " "
						+ data.getToProfessionalLastName();
			} else {
				drName = data.getToFacilityName();
			}
			String appointmentTypeName = data.getServiceConfigurationName();

			String appointmentTypeCode = data.getServiceConfigurationCode();
			if (appointmentTypeCode.equals(AppData.SG1)) {
				if (data.getToFacilityName() != null) {
					holder.clinicName.setText(data.getToFacilityName());
					holder.clinicName.setVisibility(View.VISIBLE);
				}
			}

			String remainingTime = setAppointmentTime(data) != null ? ", "
					+ setAppointmentTime(data) : "";

			holder.time.setText(JeevomUtilsClass.formatTime(data
					.getAppointmentTime()) + remainingTime);
			holder.appointmentType.setText(appointmentTypeName + ", "
					+ data.getStatusText());
			holder.date.setText(date);
			holder.drName.setText(drName);

		}

		return convertView;
	}

	public static class ViewHolder {
		public TextView seperator;
		public TextView drName;
		public TextView appointmentType;
		public TextView clinicName;
		public TextView time;
		public TextView date;
	}

	public void clearList() {
		mData.clear();
		sectionHeader.clear();
	}

	public boolean getSectionHeaderPosition(int position) {
		return sectionHeader.contains(position);

	}

	private String setAppointmentTime(ServiceRequisitionData data) {
		String appointmentDate = JeevomUtilsClass.formatDate(data
				.getAppointmentDate());
		String appointmentTime = JeevomUtilsClass.formatTime(data
				.getAppointmentTime());

		long dateToMillis = JeevomUtilsClass.dateToMillis(appointmentDate + " "
				+ appointmentTime);
		if (dateToMillis > 0) {
			String hourFormatTime = JeevomUtilsClass.milliToDate(dateToMillis);
			return hourFormatTime;
		} else {
			// String hourFormatTime = JeevomUtilsClass.milliToDate(dateToMillis
			// * (-1));
			return null;
		}
	}

}
