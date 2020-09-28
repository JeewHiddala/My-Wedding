package com.example.mywedding;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mywedding.Database.DBHelper;

import java.util.List;

public class GuestAdapter extends ArrayAdapter<Guest> {

    private Context context;
    private int resource;
    List<Guest> guests;
    DBHelper dhbelper;


    GuestAdapter(Context context, int resource, List<Guest> guests){ // assgning above mentioned variables to local variables
        super(context,resource,guests);
        this.context = context;
        this.resource = resource;
        this.guests = guests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {  //preview of a single list item

        LayoutInflater inflater = LayoutInflater.from(context);   //creating a inflater object
        View row = inflater.inflate(resource,parent,false); //convert the singleguest.xml file into java and save it inside the view object


        //link xml file by include references
        TextView title = row.findViewById(R.id.guestName);
        TextView description = row.findViewById(R.id.description);

        ImageView imageView = row.findViewById(R.id.tick);

        final ImageButton delete = row.findViewById(R.id.delbtn);
        ImageButton edit = row.findViewById(R.id.editbtn);


        //status visibility

        final Guest guest = guests.get(position);
        title.setText(guest.getGuestName());
        description.setText(guest.getNotes());
        imageView.setVisibility(row.INVISIBLE);


       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context , viewGuest.class);
                intent.putExtra("id",String.valueOf(guest.getId()));
                context.startActivity(intent);

            }
        }); */


       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             DBHelper  dbhelper = new DBHelper(context);
             dbhelper.deleteGuest(guest.getId());

             Intent myintent = new Intent(context,allGuests.class);
             context.startActivity(myintent);

           }
       });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context ,viewGuest.class);
                intent.putExtra("id",String.valueOf(guest.getId()));
                context.startActivity(intent);
            }
        });



        //status
       /* if(guest.getStatus() > 0){
            imageView.setVisibility(View.VISIBLE);
        } */
/*
        String status = guest.getStatus();
        String sent = "Sent";

        if(status.equals(sent)){
            imageView.setVisibility(View.VISIBLE);
        }
*/



        String status = String.valueOf(guest.getStatus());
        String sent = "Sent";

        if(status.equals(sent)){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;



    }
}
