package com.joelcastro.introduccionandroid;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.buttonEnterMain);
        final EditText tusuario = (EditText) findViewById(R.id.textUserMain);
        final EditText tpass = (EditText) findViewById(R.id.textPassMain);

        final SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String user = prefs.getString("usuario", "");
        String pass = prefs.getString("pass", "");

        tusuario.setText(user);
        tpass.setText(pass);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if((tusuario.getText().toString().equals("user"))&&(tpass.getText().toString().equals("pass")))
                {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("usuario", tusuario.getText().toString());
                    editor.putString("pass", tpass.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, SelectionPlaceActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.bad_login);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    tusuario.setText("");
                    tpass.setText("");
                }



            }
        });


        tusuario.addTextChangedListener(new TextWatcher() {
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
                    if((tpass.getText().length()>0)&&(tusuario.getText().length()>0))
                    {
                        button.setEnabled(true);
                    }
                    else
                    {
                        button.setEnabled(false);
                    }

                }
            }
            );


        tpass.addTextChangedListener(new TextWatcher() {
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
                    if((tpass.getText().length()>0)&&(tusuario.getText().length()>0))
                    {
                        button.setEnabled(true);
                    }
                    else
                    {
                        button.setEnabled(false);
                    }

                }
            }
            );



        }
};
