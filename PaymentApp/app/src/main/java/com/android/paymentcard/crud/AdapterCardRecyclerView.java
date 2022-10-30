package com.android.paymentcard.crud;

import android.annotation.SuppressLint;
import android.content.*;

import android.graphics.Color;
import android.view.*;

import androidx.recyclerview.widget.RecyclerView;

import com.android.paymentcard.R;

import java.util.*;

public class AdapterCardRecyclerView extends RecyclerView.Adapter<ItemCardViewHolder>
{
	private Context context;
	private ArrayList<CardModel> daftarCardModel;
	private FirebaseDataListener listener;
	
	public AdapterCardRecyclerView(Context context, ArrayList<CardModel> daftarCardModel){
		this.context = context;
		this.daftarCardModel = daftarCardModel;
		this.listener = (FirebaseDataListener)context;
	}

	@Override
	public ItemCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		// TODO: Implement this method
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
		ItemCardViewHolder holder = new ItemCardViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ItemCardViewHolder holder, @SuppressLint("RecyclerView") final int position)
	{
		// TODO: Implement this method
		holder.name.setText("Name   : "+ daftarCardModel.get(position).getCardNumber());
		holder.number.setText("Number     : "+ daftarCardModel.get(position).getNameOnCard());
		holder.exp.setText("Exp   : "+ daftarCardModel.get(position).getExpireDate());
		holder.ccv.setText("CCV   : "+ daftarCardModel.get(position).getCcv());

		holder.edit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				listener.onDataClick(daftarCardModel.get(position), position);
			}
		});

		holder.view.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				holder.view.setBackgroundColor(Color.GREEN);
			}
		});

	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return daftarCardModel.size();
	}
	
	
	//interface data listener
	public interface FirebaseDataListener {
		void onDataClick(CardModel cardModel, int position);
	}
}
