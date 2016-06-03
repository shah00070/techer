package com.schoolteacher.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.MessageCommunicator;

public class ServiceRequestPopUp extends DialogFragment {
	private MessageCommunicator messageCommunicator;
	private Button btn_yes, btn_no;

	private String title_text;
	private TextView title;

	public static ServiceRequestPopUp newInstance(String title_text) {
		ServiceRequestPopUp serviceRequestPopUp = new ServiceRequestPopUp();
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putString("title", title_text);
		serviceRequestPopUp.setArguments(args);
		return serviceRequestPopUp;
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
				R.layout.service_request_proceed_popup, null);
		title = (TextView) mCustomView.findViewById(R.id.title);
		title_text = getArguments().getString("title");
		title.setText(title_text);

		btn_yes = (Button) mCustomView.findViewById(R.id.btn_yes);

		btn_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				messageCommunicator.sendMessage("yes");
				dismiss();
			}
		});
		btn_no = (Button) mCustomView.findViewById(R.id.btn_no);

		btn_no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
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

}
