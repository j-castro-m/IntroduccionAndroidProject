package com.joelcastro.ejercicio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTypeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);

        final Button button = (Button) findViewById(R.id.button_deposito);
        final EditText cif = (EditText) findViewById(R.id.userTypeCIF);
        final RadioButton rbcom = (RadioButton) findViewById(R.id.rbuttonCompany);
        final RadioButton rbper = (RadioButton) findViewById(R.id.rbuttonCiudadano);


        rbcom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cif.setHint("CIF");
            }
        });

        rbper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cif.setHint("NIF");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(((RadioButton) findViewById(R.id.rbuttonCompany)).isChecked())
                {
                    Intent intent = new Intent(UserTypeActivity.this, CompanyData1Activity.class);
                    intent.putExtra("cif",cif.getText().toString());
                    intent.putExtra("company",true);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(UserTypeActivity.this, CompanyData2Activity.class);
                    intent.putExtra("cif",cif.getText().toString());
                    intent.putExtra("company",false);
                    startActivity(intent);
                }

            }
        });

        cif.addTextChangedListener(new TextWatcher() {
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
                if (valid(cif)) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }

            }
        }
        );

    }

    public boolean valid(TextView tv)
    {

        if(tv.getText().toString().length()==0)
        {
            return false;
        }
        else
        {
            Pattern p2 = Pattern.compile("^[0-9]{8}[a-zA-Z]{1}$");
            Matcher m2 = p2.matcher(tv.getText().toString());
            return m2.matches();
        }


    }

}
