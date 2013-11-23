package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TypeAndQuantityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeandquantity);


        final EditText peso = (EditText) findViewById(R.id.textWeightCData2);
        final EditText cif = (EditText) findViewById(R.id.textCIFCData2);

        final Button button = (Button) findViewById(R.id.button_deposite);

        final CheckBox cbit = (CheckBox) findViewById(R.id.checkBoxIT);
        final CheckBox cbfg = (CheckBox) findViewById(R.id.checkBoxFridge);
        final CheckBox cboil = (CheckBox) findViewById(R.id.checkBoxOil);

        final Bundle extra = this.getIntent().getExtras();
        cif.setText(extra.getString("cif"));

        cbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkMarkers(cbit,cbfg,cboil,button,peso);
            }
        });

        cbfg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkMarkers(cbit,cbfg,cboil,button,peso);
            }
        });

        cboil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkMarkers(cbit,cbfg,cboil,button,peso);
            }
        });


        peso.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkMarkers(cbit,cbfg,cboil,button,peso);

            }
        }
        );


         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = new Intent(TypeAndQuantityActivity.this, resultActivity.class);
                 intent.putExtra("cif",cif.getText().toString());
                 intent.putExtra("ITmat",cbit.isChecked());
                 intent.putExtra("Fridge",cbfg.isChecked());
                 intent.putExtra("Oil",cboil.isChecked());
                 intent.putExtra("Peso",peso.getText().toString());
                 intent.putExtra("company",extra.getBoolean("company"));
                 intent.putExtra("email",extra.getString("email"));
                 intent.putExtra("nombreParada",extra.getString("nombreParada"));
                 startActivity(intent);
             }
         });

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
                                Intent intent = new  Intent(TypeAndQuantityActivity.this, MainActivity.class);
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

    public void checkMarkers(CheckBox cbit, CheckBox cbfg, CheckBox cboil, Button button, EditText et){

        if(cbit.isChecked()||cbfg.isChecked()||cboil.isChecked())
        {
            et.setEnabled(true);
        }
        else
        {
            et.setEnabled(false);
        }

        if(et.isEnabled()&&(et.getText().toString().length()>0))
        {
            button.setEnabled(true);
        }
        else
        {
            button.setEnabled(false);
        }
    }
}
