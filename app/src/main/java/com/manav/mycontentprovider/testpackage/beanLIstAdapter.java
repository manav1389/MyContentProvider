package com.manav.mycontentprovider.testpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manav.mycontentprovider.ContactBean;
import com.manav.mycontentprovider.R;

import java.util.List;

public class beanLIstAdapter extends RecyclerView.Adapter<beanLIstAdapter.beanViewHolder> {

    List<ContactBean> itemList;
    Context context;

    public beanLIstAdapter(List<ContactBean> itemList,Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public beanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listexample,parent,false);

        return new beanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull beanViewHolder holder, int position) {

        holder.setListData(itemList.get(position).getName(), itemList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class beanViewHolder extends RecyclerView.ViewHolder{

        TextView name , number;

        public beanViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.listDisplayName);
            number = itemView.findViewById(R.id.listNumber);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setListData(String name , String number){

            this.name.setText(name.toString());
            this.number.setText(number.toString());

        }

    }
}
