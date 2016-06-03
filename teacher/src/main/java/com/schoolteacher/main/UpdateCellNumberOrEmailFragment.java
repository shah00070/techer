package com.schoolteacher.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.UpdateCellNumberOrEmailFragmentListener;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class UpdateCellNumberOrEmailFragment extends DialogFragment {

    public static UpdateCellNumberOrEmailFragment newInstance(String title) {
        UpdateCellNumberOrEmailFragment frag = new UpdateCellNumberOrEmailFragment();
        return frag;
    }

    private UpdateCellNumberOrEmailFragmentListener fragmentListner;
    private String email_phone_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mCustomView = inflater.inflate(R.layout.update_cel_email_layout, null);

        Button sendVerCode = (Button) mCustomView.findViewById(R.id.send_ver_code);
        TextView title_type = (TextView) mCustomView.findViewById(R.id.title_type);
        final EditText email_phone = (EditText) mCustomView.findViewById(R.id.email_phone);
        if (email_phone_type.equalsIgnoreCase(JeevOMUtil.PHONE_TYPE)) {
            title_type.setText("To enable this feature,you need to verify your phone number");
            email_phone.setHint("Cell Number");
            email_phone.setRawInputType(InputType.TYPE_CLASS_PHONE);
            int maxLength = 10;
            email_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        } else if (email_phone_type.equalsIgnoreCase(JeevOMUtil.EMAIL_TYPE)) {
            title_type.setText("Please Enter EmailId");
            email_phone.setHint("Email Id");
            int maxLength = 50;
            email_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }

        sendVerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentListner != null) {
                    if (email_phone_type.equalsIgnoreCase(JeevOMUtil.PHONE_MANDATORY_FOR_REQUEST)) {
                        if (CommonCode.validatePhone(email_phone.getText().toString())) {
                            fragmentListner.inContextUpdateCellNumberOrEmail(email_phone.getText().toString().trim(), email_phone_type);
                            dismiss();
                        } else {
                            Crouton.makeText(getActivity(), JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                        }
                    } else if (email_phone_type.equalsIgnoreCase(JeevOMUtil.EMAIL_MANDATORY_FOR_REQUEST)) {
                        if (CommonCode.validateEmail(email_phone.getText().toString())) {
                            fragmentListner.inContextUpdateCellNumberOrEmail(email_phone.getText().toString().trim(), email_phone_type);
                            dismiss();
                        } else {
                            Crouton.makeText(getActivity(), JeevOMUtil.VALID_EMAIL, Style.ALERT).show();
                        }
                    }
                }
            }
        });

        return mCustomView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Update Number or Email Fragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setUpdateCellOrEmailDialogFragmentListner(UpdateCellNumberOrEmailFragmentListener fragmentListner, String email_phone_type) {
        this.fragmentListner = fragmentListner;
        this.email_phone_type = email_phone_type;

    }

}
