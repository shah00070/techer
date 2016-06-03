package com.schoolteacher.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.MessageCommunicator;
import com.schoolteacher.mylibrary.util.CommonCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceRequestRescheduleActionDialog extends DialogFragment
		implements OnItemSelectedListener {
	private MessageCommunicator messageCommunicator;
	private EditText message_value;
	private Button btn_send_message;
	private Spinner message_spinner;
	List<String> messageDropDownItems = new ArrayList<String>();
	private String messageValue;
	private RelativeLayout message_layout;

	public static ServiceRequestRescheduleActionDialog newInstance() {
		ServiceRequestRescheduleActionDialog serviceRequestRescheduleActionDialog = new ServiceRequestRescheduleActionDialog();
		return serviceRequestRescheduleActionDialog;
	}

	// make sure the Activity implemented it
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.messageCommunicator = (MessageCommunicator) getActivity();
		} catch (final ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCompleteListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mCustomView = inflater.inflate(
				R.layout.service_request_message_popup, null);
		message_value = (EditText) mCustomView.findViewById(R.id.message_value);
		message_spinner = (Spinner) mCustomView
				.findViewById(R.id.message_spinner);
		message_spinner.setOnItemSelectedListener(this);
		btn_send_message = (Button) mCustomView
				.findViewById(R.id.btn_send_message);
		message_layout = (RelativeLayout) mCustomView
				.findViewById(R.id.message_layout);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.spinner_item, Arrays.asList(getResources()
				.getStringArray(
						R.array.reschedule_status_drop_down_values)));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		message_spinner.setAdapter(adapter);

		btn_send_message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (message_layout.getVisibility() == View.VISIBLE) {
					if (CommonCode.isNullOrEmpty(message_value.getText()
							.toString())) {
						message_value.requestFocus();
						message_value.setHintTextColor(R.color.warning);
					} else {
						messageCommunicator.sendMessage(message_value.getText()
								.toString().trim());
						dismiss();
					}
				} else {
					messageCommunicator.sendMessage(messageValue);
					dismiss();
				}
			}
		});

		return mCustomView;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return dialog;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		int id = parent.getId();
		if (id == R.id.message_spinner) {
			messageValue = (Arrays.asList(getResources().getStringArray(
					R.array.cancel_status_drop_down_values)).get(position));

			if ("Others".equals(messageValue)) {
				message_layout.setVisibility(View.VISIBLE);
			} else {
				message_layout.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
