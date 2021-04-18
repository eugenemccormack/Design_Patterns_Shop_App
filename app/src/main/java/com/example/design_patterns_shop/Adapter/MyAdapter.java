package com.example.design_patterns_shop.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.model.StoreItemsModel;
import com.example.design_patterns_shop.views.ViewItem;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    public static final String MESSAGE_KEY1 = "text";
    public static final String MESSAGE_KEY2 = "position";
    public static final String MESSAGE_KEY3 = "category";
    public static final String MESSAGE_KEY4 = "price";
    public static final String MESSAGE_KEY5 = "stock";

    private List<StoreItemsModel> values;




    // Provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtView;

        TextView textViewTitle;
        TextView textViewManufacturer;
        TextView textViewCategory;
        TextView textViewPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewManufacturer = (TextView) itemView.findViewById(R.id.manufacturer);
            textViewCategory= (TextView) itemView.findViewById(R.id.category);
            textViewPrice = (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            StoreItemsModel name = values.get(position);

            // before test with starting new activity, you can try make a Toast to display name and position
            Intent intent = new Intent(view.getContext(), ViewItem.class);
            intent.putExtra(MESSAGE_KEY1, name.getTitle());
            intent.putExtra(MESSAGE_KEY2, name.getManufacturer());
            intent.putExtra(MESSAGE_KEY3, name.getCategory());
            intent.putExtra(MESSAGE_KEY4, name.getPrice());
            //intent.putExtra(MESSAGE_KEY5, name.getStock());
            view.getContext().startActivity(intent);
            //start activity from another activity, here we are in MyAdapter class,
            // need to call start from the activity within that view holder}}
        }
    }

    // Provide the data set to the Adapter
    public MyAdapter(List<StoreItemsModel>list){

        this.values = list;


    }

    //@NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_view, parent, false);
        MyViewHolder viewHolder= new MyViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        StoreItemsModel data = values.get(position);

        holder.textViewTitle.setText(data.getTitle());
        holder.textViewManufacturer.setText(data.getManufacturer());
        holder.textViewCategory.setText(data.getCategory());
        holder.textViewPrice.setText(String.valueOf(data.getPrice()));


    }

    @Override
    public int getItemCount() {

        return values.size();
    }

    public void filterList(ArrayList<StoreItemsModel> filteredList){

        values = filteredList;

        notifyDataSetChanged();

    }

}
