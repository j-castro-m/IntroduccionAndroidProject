package com.joelcastro.ejercicio1;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioButton;

public class UserTypeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);

        final Button button = (Button) findViewById(R.id.button_deposito);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(((RadioButton) findViewById(R.id.rbuttonCompany)).isChecked())
                {
                    Intent intent = new Intent(UserTypeActivity.this, CompanyData1Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(UserTypeActivity.this, resultActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

}
