package com.lizyaver.lizyaverorg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ContactsRecViewAdapter extends RecyclerView.Adapter<ContactsRecViewAdapter.ViewHolder> {


    private ArrayList<Contact> contacts = new ArrayList<>();
    private Context context;

    public ContactsRecViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.txtusername.setText(contacts.get(position).getName());
//            holder.txtTitle.setText(contacts.get(position).getEmail());
            holder.txt_content_details.setText(contacts.get(position).getContent_details());

            //trying to set on click listener to appear parking spot page
            holder.parent.setOnClickListener(view ->{
                    Toast.makeText(context, contacts.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
                    //trying to call the parking to the homepage after clicking the recycle view
                Intent intent = new Intent(view.getContext(), ParkActivity.class);
                context.startActivity(intent);
            });


        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).getImageURL())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_content_details, txtusername, txtTitle;
        private CardView parent;
        private ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtusername = itemView.findViewById(R.id.txtusername);
            parent = itemView.findViewById(R.id.parent);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txt_content_details = itemView.findViewById(R.id.txt_content_details);
            image = itemView.findViewById(R.id.image);
        }
    }
}

   