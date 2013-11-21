package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SelectionPlaceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionplace);


        ItemListParada[] ObjectItemData = new ItemListParada[4];

        ObjectItemData[0] = new ItemListParada(1, "San Jose - Las Fuentes",getString(R.string.urlimagen));
        ObjectItemData[1] = new ItemListParada(2, "Cogullada",getString(R.string.urlimagen));
        ObjectItemData[2] = new ItemListParada(4, "Universidad - Delicias",getString(R.string.urlimagen));
        ObjectItemData[3] = new ItemListParada(3, "Valdespartera",getString(R.string.urlimagen));



        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.place_item_layout, ObjectItemData);
        ListView listViewItems = (ListView) findViewById(R.id.listView);
        listViewItems.setAdapter(adapter);
        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selection_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
