//package com.hospital.booking.crud;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.hospital.booking.R;
//
//
//import java.util.ArrayList;
//
//public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
//
//    ArrayList<Deal> list;
//
//
//    public AdapterClass(ArrayList<Deal> list) {
//        this.list = list;
//
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_appoinement, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        holder.docname.setText(list.get(position).getDocname());
////        holder.email.setText(list.get(position).getEmail());
////        holder.number.setText(list.get(position).getPhonenumber());
////        holder.age.setText(list.get(position).getAge());
//       // holder.image.setBackgroundResource(Integer.parseInt(list.get(position).getImage()));
//
//
//      //  holder.image.setText(list.get(position).getImage());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView docname, docemail, docaddress, docnumber,image;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            docname = itemView.findViewById(R.id.appointment_card_name);
////            docemail = itemView.findViewById(R.id.tvemail);
////            docaddress = itemView.findViewById(R.id.tvnumber);
////            docnumber = itemView.findViewById(R.id.tvage);
//
//
////            String imageUri = "https://firebasestorage.googleapis.com/v0/b/pharmacyinfos.appspot.com/o/Profile%20pic%2FrcwBbyJJUGVb1GNM1wUUPwgkKwJ2.jpg?alt=media&token=8fff0f22-2a7a-43e6-a032-d033eb27344e";
////            ImageView images =itemView.findViewById(R.id.getpicture);
////            Picasso.get().load(imageUri).into(images);
//
//
//
//        }
//
//
//    }
//
//}
