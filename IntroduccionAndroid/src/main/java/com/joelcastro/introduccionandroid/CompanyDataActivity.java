package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companydata);


        final EditText cif = (EditText) findViewById(R.id.textCIFCData1);
        final EditText name = (EditText) findViewById(R.id.textCNameCData1);
        final EditText phone = (EditText) findViewById(R.id.textPhoneCData1);
        final EditText email = (EditText) findViewById(R.id.textEmailCData1);
        final EditText web = (EditText) findViewById(R.id.textWebCData1);


        final Button button_siguiente = (Button) findViewById(R.id.button_siguiente);
        final Button button_dominio = (Button) findViewById(R.id.info_dom);
        final Button button_phone = (Button) findViewById(R.id.buttonPhoneCData1);
        final Button button_email = (Button) findViewById(R.id.buttonEmailCData1);
        final Button button_web = (Button) findViewById(R.id.buttonWebCData1);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sectores_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        final Bundle extra = this.getIntent().getExtras();
        cif.setText(extra.getString("cif"));

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
                Boolean bomail = correctMail(email,button_email);
                Boolean bophone = correctPhone(phone,button_phone);
                Boolean boweb = correctWeb(web,button_web);

                if (valid(cif)&&bomail&&bophone&&boweb&&name.getText().toString().length()>0) {
                    button_siguiente.setEnabled(true);
                } else {
                    button_siguiente.setEnabled(false);
                }

            }
        }
        );

        name.addTextChangedListener(new TextWatcher() {
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
                Boolean bomail = correctMail(email,button_email);
                Boolean bophone = correctPhone(phone,button_phone);
                Boolean boweb = correctWeb(web,button_web);

                if (valid(cif)&&bomail&&bophone&&boweb&&name.getText().toString().length()>0) {
                    button_siguiente.setEnabled(true);
                } else {
                    button_siguiente.setEnabled(false);
                }

            }
        }
        );

        phone.addTextChangedListener(new TextWatcher() {
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
                Boolean bomail = correctMail(email,button_email);
                Boolean bophone = correctPhone(phone,button_phone);
                Boolean boweb = correctWeb(web,button_web);



                if (valid(cif)&&bomail&&bophone&&boweb&&name.getText().toString().length()>0) {
                    button_siguiente.setEnabled(true);
                } else {
                    button_siguiente.setEnabled(false);
                }

            }
        }
        );

        email.addTextChangedListener(new TextWatcher() {
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
                Boolean bomail = correctMail(email,button_email);
                Boolean bophone = correctPhone(phone,button_phone);
                Boolean boweb = correctWeb(web,button_web);



                if (valid(cif)&&bomail&&bophone&&boweb&&name.getText().toString().length()>0) {
                    button_siguiente.setEnabled(true);
                } else {
                    button_siguiente.setEnabled(false);
                }

            }
        }
        );

        web.addTextChangedListener(new TextWatcher() {
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
                Boolean bomail = correctMail(email,button_email);
                Boolean bophone = correctPhone(phone,button_phone);
                Boolean boweb = correctWeb(web,button_web);

                button_dominio.setEnabled(boweb);

                if (valid(cif)&&bomail&&bophone&&boweb&&name.getText().toString().length()>0) {
                    button_siguiente.setEnabled(true);
                } else {
                    button_siguiente.setEnabled(false);
                }

            }
        }
        );

        button_dominio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CompanyDataActivity.this, InfoDomActivity.class);
                intent.putExtra("url", web.getText().toString());
                startActivity(intent);
            }
        });

        button_siguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CompanyDataActivity.this, TypeAndQuantityActivity.class);
                intent.putExtra("company",extra.getBoolean("company"));
                intent.putExtra("email",email.getText().toString());
                startActivity(intent);
            }
        });

        button_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",email.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            }
        });

        button_phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }
        });

        button_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web.getText().toString()));
                startActivity(browserIntent);
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
                Intent intent = new  Intent(CompanyDataActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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


    public boolean correctMail(EditText et, Button b){
        String c = et.getText().toString();
        //asignamos la expresion
        Pattern p = Pattern.compile("^[-a-zA-Z0-9+_.]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}(.[a-zA-Z]{2,4})?$");
        //comparamos con nuestro valor
        Matcher m = p.matcher(c);
        //si el correo es correcto devuelve TRUE o de lo contrario FALSE
        if(m.matches())
        {
            b.setEnabled(true);
            return true;
        }
        else
        {
            b.setEnabled(false);
            return false;
        }
    }

    public boolean correctPhone(EditText et, Button b){
        String c = et.getText().toString();
        //asignamos la expresion
        Pattern p = Pattern.compile("^[0-9]{9}$");
        //comparamos con nuestro valor
        Matcher m = p.matcher(c);
        if(m.matches())
        {
            b.setEnabled(true);
            return true;
        }
        else
        {
            b.setEnabled(false);
            return false;
        }
    }

    public boolean correctWeb(EditText et, Button b){
        String c = et.getText().toString();
        //asignamos la expresion
        Pattern p = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        //comparamos con nuestro valor
        Matcher m = p.matcher(c);
        if(m.matches())
        {
            b.setEnabled(true);
            return true;
        }
        else
        {
            b.setEnabled(false);
            return false;
        }
    }


}
