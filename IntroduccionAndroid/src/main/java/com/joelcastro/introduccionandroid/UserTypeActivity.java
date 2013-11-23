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
        final Bundle extra = this.getIntent().getExtras();



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
                    Intent intent = new Intent(UserTypeActivity.this, CompanyDataActivity.class);
                    intent.putExtra("cif",cif.getText().toString());
                    intent.putExtra("company",true);
                    intent.putExtra("nombreParada",extra.getString("nombreParada"));
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(UserTypeActivity.this, TypeAndQuantityActivity.class);
                    intent.putExtra("cif",cif.getText().toString());
                    intent.putExtra("company",false);
                    intent.putExtra("nombreParada",extra.getString("nombreParada"));
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
                                Intent intent = new  Intent(UserTypeActivity.this, MainActivity.class);
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
