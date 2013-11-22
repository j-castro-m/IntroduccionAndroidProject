package com.joelcastro.introduccionandroid;

/**
 * Created by alu03009 on 21/11/13.
 */
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class OnItemClickListenerListViewItem implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewItem));


        String listItemText = textViewItem.getText().toString();


        String listItemId = textViewItem.getTag().toString();


        Intent intent = new Intent().setClass(context, UserTypeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ID", listItemId);
        intent.putExtra("NAME", listItemText);
        context.startActivity(intent);

    }

}