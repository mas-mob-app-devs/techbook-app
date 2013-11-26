package edu.gatech.techbook;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListAdapter extends ArrayAdapter<String> {
	
	List<String> values;
	public ListAdapter(Context context, int resource, List<String> values) {
		super(context, resource, values);
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}

}
