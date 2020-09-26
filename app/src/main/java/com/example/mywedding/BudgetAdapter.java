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

import com.example.mywedding.Models.BudgetModel;

import java.util.List;

public class BudgetAdapter extends ArrayAdapter<BudgetModel> {
    Context context;
    int resource;
    List<BudgetModel> budget;

    BudgetAdapter(Context context, int resource, List<BudgetModel> budget){
        super(context, resource, budget);
        this.context = context;
        this.resource = resource;
        this.budget = budget;
    }

    @NonNull
    @Override  //create list items in the list
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater layoutInflater = LayoutInflater.from(context); //converting design to java
        View row = layoutInflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView description = row.findViewById(R.id.description);
        ImageView imageView = row.findViewById(R.id.done);

        BudgetModel budgets = budget.get(position); //creating objects from the list

        //setting values of the list
        title.setText(budgets.getBudgetName());
        description.setText(budgets.getStatus());
        imageView.setVisibility(row.INVISIBLE); //Completed image

        String status = budgets.getStatus();
        String paid = "Paid";

        if(status.equals(paid)){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
