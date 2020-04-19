package com.manav.mycontentprovider.testpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.manav.mycontentprovider.ContactBean;
import com.manav.mycontentprovider.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView rv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv2 = findViewById(R.id.rv2);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(RecyclerView.VERTICAL);
        rv2.setLayoutManager(llm2);

        fetchContactDetails();
    }

    private void fetchContactDetails(){

        List<ContactBean> beanList = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projections = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selArgs = null;
        String sortOrder = null;

        Cursor cursor = contentResolver.query(uri,projections,selection,selArgs,sortOrder);

        while(cursor.moveToNext()){


            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            System.out.println(name + " : "+ number);

            beanList.add(new ContactBean(number,name));

        }

        beanLIstAdapter bla = new beanLIstAdapter(beanList,this);

        rv2.setAdapter(bla);

        bla.notifyDataSetChanged();


    }
}
