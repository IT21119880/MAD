package com.android.paymentcard;
import android.app.Dialog;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

import android.app.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.paymentcard.crud.AdapterCardRecyclerView;
import com.android.paymentcard.crud.CardModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omarshehe.forminputkotlin.FormInputText;

/* 13.april.2018
* created by Hendriyawan
* thanks to https://www.twoh.co
*/

public class CardListActivity extends AppCompatActivity implements AdapterCardRecyclerView.FirebaseDataListener
{
	//variabel fields
	private Toolbar mToolbar;
	private FloatingActionButton mFloatingActionButton;
	private FormInputText cardno;
	private FormInputText name;
	private FormInputText expiredate;
	private FormInputText ccv;

	private RecyclerView mRecyclerView;
	private AdapterCardRecyclerView mAdapter;
	private ArrayList<CardModel> daftarCardModel;
	
	private DatabaseReference mDatabaseReference;
	private FirebaseDatabase mFirebaseInstance;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


		Button continueButtonPay = findViewById(R.id.continueButtonPay);
		continueButtonPay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(CardListActivity.this, OtpActivity.class);
				intent.putExtra("name", getIntent().getStringExtra("name"));
				intent.putExtra("phoneNumber",  getIntent().getStringExtra("phoneNumber"));
				intent.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(intent);
			}
		});
		
		
		mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		
		
		FirebaseApp.initializeApp(this);
		mFirebaseInstance = FirebaseDatabase.getInstance();
		mDatabaseReference = mFirebaseInstance.getReference("payment");
		mDatabaseReference.child("card").addValueEventListener(new ValueEventListener(){
			@Override
			public void onDataChange(DataSnapshot dataSnapshot){
				
				daftarCardModel = new ArrayList<>();
				for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()){
					CardModel cardModel = mDataSnapshot.getValue(CardModel.class);
					cardModel.setKey(mDataSnapshot.getKey());
					daftarCardModel.add(cardModel);
				}
				//set adapter RecyclerView
				mAdapter = new AdapterCardRecyclerView(CardListActivity.this, daftarCardModel);
				mRecyclerView.setAdapter(mAdapter);
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError){
				// TODO: Implement this method
				Toast.makeText(CardListActivity.this, databaseError.getDetails()+" "+databaseError.getMessage(), Toast.LENGTH_LONG).show();
			}
				
		});
		
		
		mFloatingActionButton = (FloatingActionButton)findViewById(R.id.tambah_barang);
		mFloatingActionButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//tambah CardDetails
				dialogCreateCardDetails();
			}
		});
    }
	

	@Override
	public void onDataClick(final CardModel cardModel, int position){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Edit card details");
		
		builder.setPositiveButton("Update", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				dialogUpdateCardDetails(cardModel);
			}
		});
		builder.setNegativeButton("Delete", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				deleteCardDetails(cardModel);
			}
		});
		builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
			}
		});
		
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	
	
	//setup android toolbar
	private void setupToolbar(int id){
//		mToolbar = (Toolbar)findViewById(id);
//		setSupportActionBar(mToolbar);
	}



	//dialog tambah CardDetails / alert dialog
	private void dialogCreateCardDetails(){

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Create card");
		View view = getLayoutInflater().inflate(R.layout.layout_create_card, null);

		cardno = (FormInputText)view.findViewById(R.id.cardno);
		name = (FormInputText)view.findViewById(R.id.name);
		expiredate = (FormInputText)view.findViewById(R.id.expiredate);
		ccv = (FormInputText)view.findViewById(R.id.ccv);

		builder.setView(view);

		builder.setPositiveButton("Create", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int id){

					String CardNumber = cardno.getValue().toString();
					String nameOnCard = name.getValue().toString();
					String expireDate = expiredate.getValue().toString();
					String ccvNo = ccv.getValue().toString();

					System.out.println("---------------");
					System.out.println(CardNumber);
					System.out.println(nameOnCard);
					System.out.println(expireDate);
					System.out.println(ccvNo);
					System.out.println("-----------------");

					if(!CardNumber.isEmpty() && !nameOnCard.isEmpty() && !expireDate.isEmpty() && !ccvNo.isEmpty()){
						submitDataCardDetails(new CardModel(CardNumber, nameOnCard, expireDate, ccvNo));
					}
					else {
						Toast.makeText(CardListActivity.this, "Please enter values!", Toast.LENGTH_LONG).show();
					}
				}
			});

		//button kembali / batal
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int id){
					dialog.dismiss();
				}
			});
		Dialog dialog = builder.create();
		dialog.show();
	}



	//dialog update CardDetails / update data CardDetails
	private void dialogUpdateCardDetails(final CardModel cardModel){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Edit Data CardDetails");
		View view = getLayoutInflater().inflate(R.layout.layout_edit_card, null);

		cardno = (FormInputText)view.findViewById(R.id.cardno);
		name = (FormInputText)view.findViewById(R.id.name);
		expiredate = (FormInputText)view.findViewById(R.id.expiredate);
		ccv = (FormInputText)view.findViewById(R.id.ccv);

		cardno.setValue(cardModel.getCardNumber());
		name.setValue(cardModel.getNameOnCard());
		expiredate.setValue(cardModel.getExpireDate());
		ccv.setValue(cardModel.getCcv());
		builder.setView(view);
		
		//final CardDetails mCardDetails = (CardDetails)getIntent().getSerializableExtra("
		if (cardModel != null){
			builder.setPositiveButton("Update", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int id){
					cardModel.setCardNumber(name.getValue().toString());
					cardModel.setNameOnCard(cardno.getValue().toString());
					cardModel.setExpireDate(expiredate.getValue().toString());
					cardModel.setCcv(ccv.getValue().toString());
					updateDataCardDetails(cardModel);
				}
			});
		}
		builder.setNegativeButton("cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
			}
		});
		Dialog dialog = builder.create();
		dialog.show();

	}
	
	
	/**
	 * submit data CardDetails
	 * ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
	 * set onSuccessListener yang berisi kode yang akan dijalankan
	 * ketika data berhasil ditambahkan
	 */
	private void submitDataCardDetails(CardModel cardModel){
		System.out.println(">>>>>>>>>>>>>>");
		System.out.println(cardModel.getNameOnCard());
		System.out.println(cardModel.getCardNumber());
		System.out.println(cardModel.getExpireDate());
		System.out.println(cardModel.getCcv());
		mDatabaseReference.child("card").push().setValue(cardModel).addOnSuccessListener(this, new OnSuccessListener<Void>(){
			@Override
			public void onSuccess(Void mVoid){
				Toast.makeText(CardListActivity.this, "Created Successfully !", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	/**
	 * update/edit data CardDetails
	 * ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
	 * set onSuccessListener yang berisi kode yang akan dijalankan
	 * ketika data berhasil ditambahkan
	 */
	private void updateDataCardDetails(CardModel cardModel){
		mDatabaseReference.child("card").child(cardModel.getKey()).setValue(cardModel).addOnSuccessListener(new OnSuccessListener<Void>(){
			@Override
			public void onSuccess(Void mVoid){
				Toast.makeText(CardListActivity.this, "Updated successfully !", Toast.LENGTH_LONG).show();
			}
		});
	}
	/**
	 * hapus data CardDetails
	 * ini kode yang digunakan untuk menghapus data yang ada di Firebase Realtime Database
	 * set onSuccessListener yang berisi kode yang akan dijalankan
	 * ketika data berhasil dihapus
	 */
	private void deleteCardDetails(CardModel cardModel){
		if(mDatabaseReference != null){
			mDatabaseReference.child("card").child(cardModel.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>(){
				@Override
				public void onSuccess(Void mVoid){
					Toast.makeText(CardListActivity.this,"Deleted successfully !", Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}
