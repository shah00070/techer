package com.schoolteacher.mylibrary.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.schoolteacher.mylibrary.util.JsonParse;

public class SuggestionAdapter extends ArrayAdapter<String> {

	protected static final String TAG = "SuggestionAdapter";
	private List<String> suggestions;
	private Activity context;
	private String lookupUrl;
	private String type;
	private List<String> list;

	public SuggestionAdapter(Activity context,List<String> list, String lookupUrl, String type, String nameFilter) {
		super(context, android.R.layout.simple_spinner_dropdown_item);
		this.context = context;
		this.list = list;
		this.lookupUrl = lookupUrl;
		this.type = type;
		suggestions = new ArrayList<String>();
	}

	@Override
	public int getCount() {
		return suggestions.size();
	}

	@Override
	public String getItem(int index) {
		return suggestions.get(index);
	}

	@Override
	public Filter getFilter() {
		Filter myFilter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				JsonParse jp = new JsonParse();
				if (constraint != null) {
					List<String> new_suggestions;
					// A class that queries a web API, parses the data and
					// returns an ArrayList<GoEuroGetSet>
					if (type.equals("speciality_lookup")) {
						new_suggestions = jp.getParseJsonWCF(constraint.toString(), lookupUrl, type, context);
					}else {
						new_suggestions = getFilteredDictionary(list, constraint);
					}
					suggestions.clear();
					for (int i = 0; i < new_suggestions.size(); i++) {
						suggestions.add(new_suggestions.get(i));
					}

					// Now assign the values and count to the FilterResults
					// object
					filterResults.values = suggestions;
					filterResults.count = suggestions.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence contraint, FilterResults results) {
				if (results != null && results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}
		};
		return myFilter;
	}
	
	public List<String> getFilteredDictionary(List<String> list, CharSequence searchCharacter) {
		List<String> newList = new ArrayList<String>();
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if (item.startsWith((String) searchCharacter)) {
				newList.add(item);
			}
		}
		return newList;
	}

}
