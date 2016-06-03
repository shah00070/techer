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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.MessageCommunicator;
import com.schoolteacher.mylibrary.util.CommonCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceRequestMessageActionDialog extends DialogFragment implements
		OnItemSelectedListener {
	private MessageCommunicator messageCommunicator;
	private EditText message_value;
	private Button btn_send_message;
	private Spinner message_spinner;
	List<String> messageDropDownItems = new ArrayList<String>();
	private String messageValue;
	private RelativeLayout message_layout;
	private String title_text;
	private TextView title;

	public static ServiceRequestMessageActionDialog newInstance(
			String title_text) {
		ServiceRequestMessageActionDialog serviceRequestMessageActionDialog = new ServiceRequestMessageActionDialog();
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putString("title", title_text);
		serviceRequestMessageActionDialog.setArguments(args);
		return serviceRequestMessageActionDialog;
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
		title_text = getArguments().getString("title");
		title = (TextView) mCustomView.findViewById(R.id.introduction_first);
		title.setText(title_text);
		message_value = (EditText) mCustomView.findViewById(R.id.message_value);
		message_spinner = (Spinner) mCustomView
				.findViewById(R.id.message_spinner);
		message_spinner.setOnItemSelectedListener(this);
		btn_send_message = (Button) mCustomView
				.findViewById(R.id.btn_send_message);
		message_layout = (RelativeLayout) mCustomView
				.findViewById(R.id.message_layout);

		message_spinner.setVisibility(View.GONE);
		message_layout.setVisibility(View.VISIBLE);
		btn_send_message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (CommonCode
						.isNullOrEmpty(message_value.getText().toString())) {
					message_value.requestFocus();
					message_value.setHintTextColor(R.color.warning);
				} else {
					messageCommunicator.sendMessage(message_value.getText()
							.toString().trim());
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
