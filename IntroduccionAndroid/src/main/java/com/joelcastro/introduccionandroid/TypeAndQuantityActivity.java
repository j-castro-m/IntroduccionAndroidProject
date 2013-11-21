package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TypeAndQuantityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                 startActivity(intent);
             }
         });

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
