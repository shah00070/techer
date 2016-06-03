package com.schoolteacher.library.main;

import com.schoolteacher.mylibrary.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TermsAndConditions extends ActionBarActivity {
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.termsconditions);
		toolbar = (Toolbar) findViewById(R.id.terms_toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setTitle("Terms of Use");
		findViewById(R.id.close)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();

					}
				});
	}

}
