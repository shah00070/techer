package com.schoolteacher.mylibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.schoolteacher.mylibrary.R;

public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment newInstance() {
        ProgressDialogFragment frag = new ProgressDialogFragment();

        return frag;
    }

    // private MyInterface mListener;

    @Override
    public void onAttach(Activity activity) {
        // mListener = (MyInterface) activity;
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        // mListener = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mCustomView = inflater.inflate(R.layout.progress_dialog, null);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setRetainInstance(true);
        return mCustomView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}