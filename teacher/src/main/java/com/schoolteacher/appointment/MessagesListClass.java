package com.schoolteacher.appointment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolteacher.mylibrary.util.CommonCode;
import com.squareup.picasso.Picasso;
import com.schoolteacher.pojos.Message;
import java.util.List;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessagesListClass extends ActionBarActivity {
	LinearLayout messages_container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages_list);
		List<Message> messages_list = (List<Message>) getIntent()
				.getSerializableExtra("messages_list");
		messages_container = (LinearLayout) findViewById(R.id.messages_container);

		if (messages_list.size() > 0) {

			for (Message object : messages_list) {
				LayoutInflater inflator_row = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View message_view = inflator_row.inflate(R.layout.messages_row,
						null);
				ImageView image_view = (ImageView) message_view
						.findViewById(R.id.image_view);
				TextView msg = (TextView) message_view.findViewById(R.id.msg);
				if (!CommonCode.isNullOrEmpty(object.getMessage())) {

					if (object.getFromMemberId() > 0) {
						if (!CommonCode.isNullOrEmpty(object
								.getFromConsumerPhoto()))
							Picasso.with(this)
									.load(object.getFromConsumerPhoto())
									.placeholder(R.drawable.jeevom_back)
									.error(R.drawable.jeevom_back)
									.into(image_view);

					} else if (object.getFromProfessionalId() > 0) {
						if (!CommonCode.isNullOrEmpty(object
								.getFromProfessionalPhoto()))
							Picasso.with(this)
									.load(object.getFromProfessionalPhoto())
									.placeholder(R.drawable.jeevom_back)
									.error(R.drawable.jeevom_back)
									.into(image_view);

					} else if (object.getFromFacilityId() > 0) {
						if (!CommonCode.isNullOrEmpty(object
								.getFromFacilityPhoto()))
							Picasso.with(this)
									.load(object.getFromFacilityPhoto())
									.placeholder(R.drawable.jeevom_back)
									.error(R.drawable.jeevom_back)
									.into(image_view);

					}
					msg.setText(object.getMessage());

					messages_container.addView(message_view);
				}
			}
		}
	}
}
