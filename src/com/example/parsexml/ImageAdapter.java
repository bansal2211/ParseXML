package com.example.parsexml;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends ArrayAdapter{
	ViewHolder holder;
	Context ctx;
	List<ItemData> list;
	LoadChart loader;
	public ImageAdapter(Context context, int textViewResourceId,
			List<ItemData> objects) {
		super(context, textViewResourceId, objects);
		ctx = context;
		list = objects;
		
		// TODO Auto-generated constructor stub
	}
	private class ViewHolder{
		TextView titleView;
		ImageView img;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.imagelist, null);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.linkimage);
			holder.titleView = (TextView) convertView.findViewById(R.id.title);
			
			convertView.setTag(holder);
			
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.titleView.setText(list.get(position).title);
		loader = new LoadChart(ctx, holder.img, list.get(position).link);
		loader.execute();
		
		return convertView;
		
	}
	
	

}
