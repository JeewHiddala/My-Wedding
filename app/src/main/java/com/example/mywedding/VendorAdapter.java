package com.example.mywedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VendorAdapter extends ArrayAdapter<VendorModel> {

    private Context context;
    private int resource;
    List<VendorModel> vendors;

    //build a constructor
    VendorAdapter(Context context, int resource, List<VendorModel> vendors){
        super(context,resource,vendors);
        this.context = context;
        this.resource = resource;
        this.vendors = vendors;
    }

    //get view in array
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //convert single vendor xml design as java by using Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView vendorName = row.findViewById(R.id.vendorName);
        TextView vendorStatus = row.findViewById(R.id.vendorStatus);
        ImageView imageView = row.findViewById(R.id.complete_tick);

        //vendors [obj1,obj2,obj3] -> array list
        VendorModel vendor = vendors.get(position);
        vendorName.setText(vendor.getVendorname());
        vendorStatus.setText(vendor.getStatus());
        imageView.setVisibility(row.INVISIBLE); // complete image

        String status = vendor.getStatus();
        String completed = "Completed";

        if(status.equals(completed)){
            imageView.setVisibility(View.VISIBLE);
        }

        return row;
    }
}
