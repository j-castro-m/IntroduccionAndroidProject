package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;
import android.widget.TextView;

public class InfoDomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infodom);


        final Bundle extra = this.getIntent().getExtras();
        final TextView urlView = (TextView) findViewById(R.id.info_dom_url);
        final TextView textIP = (TextView) findViewById(R.id.info_dom_ip);
        final TextView textCity = (TextView) findViewById(R.id.info_dom_city);
        final TextView textCountry = (TextView) findViewById(R.id.info_dom_country);
        final TextView textGps = (TextView) findViewById(R.id.info_dom_gps);

        urlView.setText(extra.getString("url"));

        objectJSON json = new objectJSON(this,textIP,textCity,textCountry,textGps);
        json.execute(extra.getString("url"));


        String gps = json.getGps();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.selection_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_desconectar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.textSalir))
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new  Intent(InfoDomActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();}})
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();}});

                AlertDialog alert = builder.create();
                alert.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
