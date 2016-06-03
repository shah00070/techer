package com.schoolteacher.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchAvailability;
import com.schoolteacher.pojos.JeevSearchCategory;
import com.schoolteacher.pojos.JeevSearchCriteria;
import com.schoolteacher.pojos.JeevSearchFilter;
import com.schoolteacher.pojos.JeevSearchGender;
import com.schoolteacher.pojos.JeevSearchRequisition;
import com.schoolteacher.pojos.JeevSearchTiming;
import com.schoolteacher.pojos.SavedSearch;
import com.schoolteacher.search.JeevSearchList;

import java.util.List;

public class UserSavedSearchListAdapter extends BaseAdapter {
	private Activity activity;
	private List<SavedSearch> listItems;
	// Animation animation;
	SavedSearch savedSearch;
	private Gson gson;

	public UserSavedSearchListAdapter(Activity activity,
			List<SavedSearch> searchResults) {
		this.activity = activity;
		this.listItems = searchResults;
		gson = new GsonBuilder().create();
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int location) {
		return listItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		savedSearch = listItems.get(position);
		JeevSearchCriteria searchCriteria = null;
		final JeevSearchFilter searchFilters;
		final JeevCriteria jeevCriteria = new JeevCriteria();
		JeevSearchAvailability availability;
		final String searchName;
		JeevSearchCategory jeevCategory = new JeevSearchCategory();
		JeevSearchAvailability jeevAvailability = new JeevSearchAvailability();
		JeevSearchGender jeevGender = new JeevSearchGender();
		JeevSearchRequisition jeevRequistion = new JeevSearchRequisition();
		JeevSearchTiming jeevTiming = new JeevSearchTiming();

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.save_search_item, null);
			holder.name_search = (TextView) convertView
					.findViewById(R.id.name_search);
			holder.date_search = (TextView) convertView
					.findViewById(R.id.date_search);
			holder.modify = (Button) convertView.findViewById(R.id.modify);
			holder.search = (Button) convertView.findViewById(R.id.search);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		searchCriteria = gson.fromJson(savedSearch.getCriteriaJson(),
				JeevSearchCriteria.class);
		searchFilters = gson.fromJson(savedSearch.getFilterJson(),
				JeevSearchFilter.class);

		// set search name
		holder.name_search.setText(savedSearch.getName());
		searchName = savedSearch.getName();

		// set date
		holder.date_search.setText(CommonCode.getDateFromTimeStamp(savedSearch
				.getSavedOnUTC()));

		// set category

		List<String> category = searchCriteria.getCategory();
		for (String string : category) {
			if (string.contains("Doctors"))
				jeevCategory.setDoctorClinic(true);

			if (string.contains("Medicine"))
				jeevCategory.setAlternateMedicine(true);

			if (string.contains("Gyms"))
				jeevCategory.setGymFitness(true);

			if (string.contains("Healing"))
				jeevCategory.setHealing(true);

			if (string.contains("support"))
				jeevCategory.setHealthcareSupport(true);

			if (string.contains("Nursing"))
				jeevCategory.setHospitalNursing(true);

			if (string.contains("Labs"))
				jeevCategory.setLabDiagnostic(true);

			if (string.contains("Pharmacies"))
				jeevCategory.setPharmacies(true);

			if (string.contains("Spa"))
				jeevCategory.setSpaWellness(true);
		}

		// set service requisition types
		List<String> serviceRequisitionTypes = searchCriteria
				.getServiceRequisitionTypes();
		for (String string : serviceRequisitionTypes) {
			if (string.contains("SG5"))
				jeevRequistion.setChat(true);

			if (string.contains("SG1"))
				jeevRequistion.setClinic(true);

			if (string.contains("SG6") || string.contains("SG7"))
				jeevRequistion.setEmail(true);

			if (string.contains("SG2"))
				jeevRequistion.setHome(true);

			if (string.contains("SG4"))
				jeevRequistion.setPhone(true);

			if (string.contains("SG3"))
				jeevRequistion.setVideo(true);
		}

		// set availabilities
		availability = searchCriteria.getAvailability();
		if (availability.isFriday())
			jeevAvailability.setFriday(true);

		if (availability.isMonday())
			jeevAvailability.setMonday(true);

		if (availability.isSaturday())
			jeevAvailability.setSaturday(true);

		if (availability.isSunday())
			jeevAvailability.setSunday(true);

		if (availability.isThrusday())
			jeevAvailability.setThrusday(true);

		if (availability.isTuesday())
			jeevAvailability.setTuesday(true);

		if (availability.isWednesday())
			jeevAvailability.setWednesday(true);

		if ("male".equalsIgnoreCase(searchCriteria.getGenderType()))
			jeevGender.setMale(false);
		else if ("female".equalsIgnoreCase(searchCriteria.getGenderType()))
			jeevGender.setFemale(false);
		else
			jeevGender.setBoth(true);

		if (!CommonCode.isNullOrEmpty(searchCriteria.getTimings())) {
			if (searchCriteria.getTimings().equalsIgnoreCase("morn"))
				jeevTiming.setMorning(true);

			else if (searchCriteria.getTimings().equals("after"))
				jeevTiming.setAfternoon(true);
			else
				jeevTiming.setEvening(false);
		} else {
			jeevTiming.setMorning(false);
			jeevTiming.setAfternoon(false);
			jeevTiming.setEvening(false);
		}

		jeevCriteria.setSearchString(searchCriteria.getSearchString());
		jeevCriteria.setCategory(jeevCategory);
		jeevCriteria.setAvailability(jeevAvailability);
		jeevCriteria.setGender(jeevGender);
		jeevCriteria.setRequisition(jeevRequistion);
		jeevCriteria.setTiming(jeevTiming);

	/*	holder.modify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openFilterActivity = new Intent(activity,
						JeevSearchFilters.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("criteria", jeevCriteria);
				bundle.putSerializable("filter", searchFilters);
				bundle.putBoolean("isNearMeChecked", false);
				bundle.putBoolean("isModifySearch", true);
				bundle.putString("nameOfSavedSearch", searchName);
				bundle.putInt("activity_type", 0); // 0 for advance search and 1
													// for
													// fiter
				openFilterActivity.putExtras(bundle);

				activity.startActivityForResult(openFilterActivity, 1);

			}
		});*/

		holder.search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent searchIntent = new Intent(activity, JeevSearchList.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("object", jeevCriteria);
				bundle.putSerializable("filter", searchFilters);
				if (!CommonCode.isNullOrEmpty(searchFilters.getLocation())) {
					bundle.putBoolean("isNearMeChecked", true);
				} else {
					bundle.putBoolean("isNearMeChecked", false);
				}
				searchIntent.putExtras(bundle);
				activity.startActivity(searchIntent);

			}
		});

		return convertView;
	}

	public static class ViewHolder {
		private TextView name_search;
		private TextView date_search;
		private Button modify;
		private Button search;

	}
}