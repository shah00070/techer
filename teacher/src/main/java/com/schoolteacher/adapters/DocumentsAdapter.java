package com.schoolteacher.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.ImageDownloadOptionClick;
import com.schoolteacher.medvault.MedVaultActivity;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.DocumentList;

import java.util.List;

public class DocumentsAdapter extends BaseAdapter {
	private Context context;
	private List<DocumentList> documentList;
	ImageDownloadOptionClick callback;

	public DocumentsAdapter(Context context, List<DocumentList> documentList2) {
		this.context = context;
		this.documentList = documentList2;
		callback = (MedVaultActivity) context;
	}

	@Override
	public int getCount() {
		return documentList.size();
	}

	@Override
	public Object getItem(int position) {
		return documentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.document_row, null);
			holder.file_name = (TextView) convertView
					.findViewById(R.id.file_name);
			holder.file_tags = (TextView) convertView
					.findViewById(R.id.file_tags);
			holder.file_description = (TextView) convertView
					.findViewById(R.id.file_description);
			holder.file_type = (TextView) convertView
					.findViewById(R.id.file_type);
			holder.download = (ImageView) convertView
					.findViewById(R.id.download);
			holder.file_date = (TextView) convertView
					.findViewById(R.id.file_date);
			convertView.setTag(holder);

			holder.thumbnail = (ImageView) convertView
					.findViewById(R.id.thumbnail);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// getting the index
		holder.download.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				callback.getIndexOfRow(position);

			}
		});

		holder.file_name.setText(documentList.get(position).getName().trim());

		if (CommonCode.isNullOrEmpty(documentList.get(position).getTags())) {
			holder.file_tags.setText("Tags : N/A");
		} else {
			holder.file_tags.setText(documentList.get(position).getTags()
					.trim());
		}

		if (CommonCode.isNullOrEmpty(documentList.get(position)
				.getDescription())) {
			holder.file_description.setText("Description : N/A");
		} else {
			holder.file_description.setText(documentList.get(position)
					.getDescription().trim());
		}

		// set type and image according to type
		holder.file_type.setText(documentList.get(position).getDocumentType()
				.trim());

		if (documentList.get(position).getDocumentType().trim()
				.contains("Prescription"))
			holder.thumbnail.setImageResource(R.drawable.prescription);
		else if (documentList.get(position).getDocumentType().trim()
				.contains("ECG"))
			holder.thumbnail.setImageResource(R.drawable.ecg);
		else if (documentList.get(position).getDocumentType().trim()
				.contains("Diagnostic"))
			holder.thumbnail.setImageResource(R.drawable.diagnostic_report);
		else if (documentList.get(position).getDocumentType().trim()
				.contains("Ultrasound"))
			holder.thumbnail.setImageResource(R.drawable.ultrasound);
		else if (documentList.get(position).getDocumentType().trim()
				.contains("X"))
			holder.thumbnail.setImageResource(R.drawable.xray);
		else
			holder.thumbnail.setImageResource(R.drawable.other);

		holder.file_date
				.setText(documentList.get(position).getDocDate().trim());

		return convertView;
	}

	public static class ViewHolder {
		private TextView file_name;
		private TextView file_tags;
		private TextView file_description;
		private TextView file_type;
		private TextView file_date;
		private ImageView download;
		private ImageView thumbnail;

	}
}
