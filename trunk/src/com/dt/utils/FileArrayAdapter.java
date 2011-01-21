package com.dt.utils;

import java.util.List;

import com.dt.activities.R;

import android.content.Context;

import android.opengl.Visibility;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<Option> {

	private Context c;

	private List<Option> items;

	public FileArrayAdapter(Context context, int textViewResourceId,

	List<Option> objects) {

		super(context, textViewResourceId, objects);

		c = context;

		items = objects;

	}

	public Option getItem(int i)

	{

		return items.get(i);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {

			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = vi.inflate(R.layout.list_view_layout, null);

		}

		final Option o = items.get(position);

		if (o != null) {
			/*CheckBox chkbx = (CheckBox) v.findViewById(R.id.row_chbox);
			if ((o.getData().equalsIgnoreCase(" "))
					|| o.getData().equalsIgnoreCase("parent directory"))
			{
				chkbx.setVisibility(View.INVISIBLE);
			}
			else chkbx.setVisibility(View.VISIBLE);*/
			TextView t1 = (TextView) v.findViewById(R.id.TextView01);

			TextView t2 = (TextView) v.findViewById(R.id.TextView02);

			if (t1 != null)

				t1.setText(o.getName());

			if (t2 != null)

				t2.setText(o.getData());

		}
		return v;

	}

}


