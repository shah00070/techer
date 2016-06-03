package com.schoolteacher.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.appointment.AppData.Days;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.util.JeevomUtilsClass;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class AppointmentFragment extends Fragment implements
		OnItemClickListener, OnRefreshListener {
	private GlobalAlert globalAlert;
	private ProgressDialogFragment newFragment;
	// JeevomLocalSession jeevomLocalSession;
	private List<ServiceRequisitionData> upComingAppointment;
	private List<ServiceRequisitionData> pastAppointment;
	private List<ServiceRequisitionData> todayAppointmentList;
	private ListView list_view;
	private AppointmentAdapter adapter;
	JeevomSession session;
	String authToken = null;
	private SwipeRefreshLayout swipeLayout;
	private int flag = 0;
	private View view;
	private int listSize = 0;
	private Toolbar mToolbar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.my_appointment, container, false);
		return view;
	}

	// @SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		globalAlert = new GlobalAlert(getActivity());
		// jeevomLocalSession = new JeevomLocalSession(getActivity());
		session = new JeevomSession(getActivity().getApplicationContext());
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		swipeLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		upComingAppointment = new ArrayList<ServiceRequisitionData>();
		todayAppointmentList = new ArrayList<ServiceRequisitionData>();
		pastAppointment = new ArrayList<ServiceRequisitionData>();
		list_view = (ListView) getActivity().findViewById(R.id.list_view);
		list_view.setOnItemClickListener(this);
		adapter = new AppointmentAdapter(getActivity());
		list_view.setAdapter(adapter);
		getEmailRequests();
		// onRefresh();
	}

	private void getEmailRequests() {
		if (flag == 1) {
			swipeLayout.setRefreshing(true);
		} else {

			newFragment = ProgressDialogFragment.newInstance();
			newFragment.show(getActivity().getSupportFragmentManager(),
					"dialog");
			newFragment.setCancelable(false);
		}
		String publicId = session.getConsumerIds().get(
				JeevomSession.JEEVOM_MEMBER_UNIQUE_ID);
		RestAdapter emailRequestAdapter = new RestAdapter.Builder()
				.setLog(new AndroidLog("Appointment"))
				.setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
				.build();
		AppointmentURL emailURL = emailRequestAdapter
				.create(AppointmentURL.class);
		emailURL.getAllEmailRequest(publicId, "true", authToken,
				new Callback<AppointmentRequestResponse>() {

					@Override
					public void success(AppointmentRequestResponse result,
							Response arg1) {

						if (flag == 1) {
							setSwipingFalse();
						}
						newFragment.dismissAllowingStateLoss();
						String code = result.getStatus().getCode();
						if (code.equals("Success")) {
							adapter.clearList();
							clearAppointmentList();
							dataFillwork(result);
						}
					}

					@Override
					public void failure(RetrofitError arg0) {
						failureWork(newFragment, arg0);
					}
				});
	}

	private void dataFillwork(AppointmentRequestResponse result) {
		List<ServiceRequisitionData> serviceRequisitions = result.getData()
				.getServiceRequisitions();
		if (listSize == serviceRequisitions.size()) {
			Toast.makeText(getActivity(), "Up to date", Toast.LENGTH_SHORT)
					.show();

		}
		listSize = serviceRequisitions.size();
		filterAppointment(serviceRequisitions);

	}

	private void filterAppointment(
			List<ServiceRequisitionData> serviceRequisitions) {
		for (ServiceRequisitionData serviceRequisitionData : serviceRequisitions) {
			String appointmentTime = null;
			if (!CommonCode.isNullOrEmpty(serviceRequisitionData
					.getAppointmentTime())) {
				appointmentTime = JeevomUtilsClass
						.formatTime(serviceRequisitionData.getAppointmentTime());
			}

			String appointmentDate = null;
			if (!CommonCode.isNullOrEmpty(serviceRequisitionData
					.getAppointmentDate())) {
				appointmentDate = JeevomUtilsClass
						.formatDate(serviceRequisitionData.getAppointmentDate());
			}

			long dateToMillis = JeevomUtilsClass.dateToMillis(appointmentDate
					+ " " + appointmentTime);
			if (dateToMillis > 30000) {
				Days days = null;
				try {
					days = JeevomUtilsClass.formatToday(appointmentDate + " "
							+ appointmentTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Days.TODAY == days) {
					todayAppointmentList.add(serviceRequisitionData);
				} else {
					upComingAppointment.add(serviceRequisitionData);
				}
			} else {
				pastAppointment.add(serviceRequisitionData);
			}
		}

		if (todayAppointmentList.size() > 0) {
			ServiceRequisitionData data = new ServiceRequisitionData();
			data.setUpComing("Today");
			adapter.addSectionHeaderItem(data);
		}
		adapter.addItem(todayAppointmentList);

		if (upComingAppointment.size() > 0) {
			ServiceRequisitionData data = new ServiceRequisitionData();
			data.setUpComing("UpComing");
			adapter.addSectionHeaderItem(data);

		}
		adapter.addItem(upComingAppointment);
		if (pastAppointment.size() > 0) {
			ServiceRequisitionData dataPast = new ServiceRequisitionData();
			dataPast.setUpComing("Past");
			adapter.addSectionHeaderItem(dataPast);
		}
		adapter.addItem(pastAppointment);
	}

	private void failureWork(final ProgressDialogFragment newFragment,
			RetrofitError arg0) {
		if (flag == 1) {
			setSwipingFalse();
		} else {
			newFragment.dismissAllowingStateLoss();
		}
		if (arg0.isNetworkError()) {
			if (!(Connectivity.checkConnectivity(getActivity()))) {
				showAlert(JeevOMUtil.INTERNET_CONNECTION);
			} else if (arg0.getCause() instanceof SocketTimeoutException) {
				showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
			} else if (arg0.getResponse() == null) {
				showAlert(JeevOMUtil.SOMETHING_WRONG);
			}
		} else if (arg0.getResponse().getStatus() > 400) {
			showAlert(JeevOMUtil.SOMETHING_WRONG);
		} else {
			String json = new String(((TypedByteArray) arg0.getResponse()
					.getBody()).getBytes());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			AppointmentRequestResponse serReqAppErrors = gson.fromJson(json,
					AppointmentRequestResponse.class);
			String code = serReqAppErrors.getStatus().getCode();
			String message = serReqAppErrors.getStatus().getMessage();
			if (code.equals("BE-1001")) {
				showAlert(message);
			} else if (code.equals("BE-700")) {
				showAlert(message);
			} else if (code.equals("DE-1001")) {
				showAlert(message);
			} else if (code.equals("BE-1002")) {
				showAlert(message);
			} else if (code.equals("DE-700")) {
				showAlert(message);
			} else if (code.equals("BE-1004")) {
				showAlert(message);
			}
		}
	}

	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (!adapter.getSectionHeaderPosition(position)) {
			Intent intent = new Intent(getActivity(),
					IndivisualAppointmentActivity.class);
			intent.putExtra("data", adapter.getItem(position));
			startActivity(intent);

		}

	}

	@Override
	public void onRefresh() {
		flag = 1;
		getEmailRequests();
	}

	private void clearAppointmentList() {
		todayAppointmentList.clear();
		upComingAppointment.clear();
		pastAppointment.clear();
	}

	private void setSwipingFalse() {
		if (flag == 1) {
			swipeLayout.setRefreshing(false);
			flag = 0;
		}
	}

}
