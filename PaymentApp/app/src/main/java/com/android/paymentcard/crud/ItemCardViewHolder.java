package com.android.paymentcard.crud;

import android.view.*;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;

import com.android.paymentcard.R;

public class ItemCardViewHolder extends RecyclerView.ViewHolder
{
	public TextView name;
	public TextView number;
	public TextView exp;
	public TextView ccv;
	public View view;
	public Button edit;
	
	public ItemCardViewHolder(View view){
		super(view);
		
		name = (TextView)view.findViewById(R.id.item_name);
		number = (TextView)view.findViewById(R.id.item_number);
		exp = (TextView)view.findViewById(R.id.item_exp);
		ccv = (TextView)view.findViewById(R.id.item_ccv);
		edit = (Button) view.findViewById(R.id.editbutton);
		this.view = view;
	}
}
