package com.schoolteacher.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class HeadsetStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
			int state = intent.getIntExtra("state", -1);
			switch (state) {
			case 0:
				// audio.setWiredHeadsetOn(false);
					audio.setSpeakerphoneOn(true);
				break;
			case 1:
				// audio.setWiredHeadsetOn(true);
				audio.setSpeakerphoneOn(false);
				break;
			}
		}
	}
}
