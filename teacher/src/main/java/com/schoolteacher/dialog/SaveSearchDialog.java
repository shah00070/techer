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
import android.widget.EditText;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.SaveSearchCommunicator;
import com.schoolteacher.mylibrary.util.CommonCode;

public class SaveSearchDialog extends DialogFragment {
	private SaveSearchCommunicator saveSearchCommunicator;
	private EditText search_value;
	private Button btn_save_proceed, btn_cancel_proceed;

	public static SaveSearchDialog newInstance() {
		SaveSearchDialog saveSearchDialog = new SaveSearchDialog();
		return saveSearchDialog;
	}

	// make sure the Activity implemented it
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.saveSearchCommunicator = (SaveSearchCommunicator) getActivity();
		} catch (final ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCompleteListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mCustomView = inflater.inflate(R.layout.save_search_dialog, null);
		search_value = (EditText) mCustomView.findViewById(R.id.search_value);
		btn_save_proceed = (Button) mCustomView
				.findViewById(R.id.btn_save_proceed);
		btn_cancel_proceed = (Button) mCustomView
				.findViewById(R.id.btn_cancel_proceed);
		btn_save_proceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (!CommonCode.isNullOrEmpty(search_value.getText().toString()
						.trim())) {

					saveSearchCommunicator.onClickSaveSearch(true, search_value
							.getText().toString().trim());
					dismiss();
				}

			}
		});
		btn_cancel_proceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveSearchCommunicator.onClickSaveSearch(false, null);
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
