package com.manav.mycontentprovider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.listVewHolder> {

    private List<ContactBean> contactBeanList;

    public ContactListAdapter(List<ContactBean> contactBeanList) {
        this.contactBeanList = contactBeanList;
    }

    @NonNull
    @Override
    public listVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listexample,parent,false);

        System.out.println("in create view hilder");

        return new listVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listVewHolder holder, int position) {

        String name = contactBeanList.get(position).getName();
        String number = contactBeanList.get(position).getNumber();
        holder.setListData(name,number);

    }

    @Override
    public int getItemCount() {
        return contactBeanList.size();
    }

    public class listVewHolder extends RecyclerView.ViewHolder{

        private TextView displayName , number;


        public listVewHolder(@NonNull View itemView) {
            super(itemView);

            displayName = itemView.findViewById(R.id.listDisplayName);
            number = itemView.findViewById(R.id.listNumber);

        }

        public void setListData(String displayName , String number){

            this.displayName.setText(displayName);
            this.number.setText(number);

        }


    }
}
