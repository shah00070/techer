package com.schoolteacher.library.main;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.schoolteacher.library.apiInterfaces.ActivityListner;
import com.schoolteacher.mylibrary.R;


/**
 * Created by user on 8/26/2015.
 */
public class RoleSelectionDialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener{

    private boolean isProfessional,isBusiness;


    public static RoleSelectionDialog newInstance() {
        RoleSelectionDialog transactionDialog = new RoleSelectionDialog();

               return transactionDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mCustomView = inflater.inflate(R.layout.role_selection,
                null);


        CheckBox professional_checkbox=(CheckBox)mCustomView.findViewById(R.id.professional_checkbox);
        CheckBox business_checkbox=(CheckBox)mCustomView.findViewById(R.id.business_checkbox);
        professional_checkbox.setOnCheckedChangeListener(this);
        business_checkbox.setOnCheckedChangeListener(this);

        Button explore_button=(Button)mCustomView.findViewById(R.id.explore_button);

        // implement onclick listner on explore button

        explore_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBusiness || isProfessional)
                {
                    mListener.callFinishOnActivity();
                    Toast.makeText(getActivity(),"Go to Play Store For Jeevom Biz", Toast.LENGTH_SHORT).show();
                    dismissAllowingStateLoss();
                }else
                {
                    mListener.callFinishOnActivity();
                    dismissAllowingStateLoss();
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id=buttonView.getId();

        if(id==R.id.business_checkbox)
        {
            if(isChecked)
                isBusiness=true;
            else
                isBusiness=false;
        }

        if(id==R.id.professional_checkbox)
        {
            if(isChecked)
                isProfessional=true;
            else
                isProfessional=false;
        }
    }

    private ActivityListner mListener;

    @Override
    public void onAttach(Activity activity) {
        mListener = (ActivityListner) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}