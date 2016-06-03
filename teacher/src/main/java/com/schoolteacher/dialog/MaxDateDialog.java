package com.schoolteacher.dialog;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class MaxDateDialog extends DialogFragment // implements
// DatePickerDialog.OnDateSetListener {
{
	DatePickerDialog dialog;
	Calendar calendar;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		Bundle mArgs = getArguments();
		int year = mArgs.getInt("year");
		int month = mArgs.getInt("month");
		int day = mArgs.getInt("day");

		calendar = Calendar.getInstance();

		dialog = new DatePickerDialog(getActivity(),
				(OnDateSetListener) getActivity(), year, month, day);

		dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
		return dialog;

	}
}
