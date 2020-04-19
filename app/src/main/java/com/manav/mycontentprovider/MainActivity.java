package com.manav.mycontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.manav.mycontentprovider.testpackage.Main2Activity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView contactlistRecycler;
    ListView contactlistView;
    ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        contactlistRecycler = findViewById(R.id.contactList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        contactlistRecycler.setLayoutManager(llm);

        contactlistView = findViewById(R.id.contactlist);

        fetchContactInfo();


    }


 /*   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);

        contactlistRecycler = view.findViewById(R.id.contactList);

        fetchContactInfo();

        return view;
    }
*/

        @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void fetchContactInfo(){

        List<ContactBean> contactList = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = null;
        String[] selectionArgs = null;
        String[] projections = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String sortOrder = null;

        Cursor cursor = contentResolver.query(uri,projections,selection,selectionArgs,sortOrder);

        while(cursor.moveToNext()){

            String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            System.out.println("name : "+ displayName +","+" number : "+ number);
            contactList.add(new ContactBean(number,displayName));

        }

        System.out.println("setting adapter");

        contactListAdapter = new ContactListAdapter(contactList);

        contactlistRecycler.setAdapter(contactListAdapter);

        contactListAdapter.notifyDataSetChanged();


      //  contactlistView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactList));

    }

    public void onButton(View v){

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

            startActivity(intent);

    }
}
