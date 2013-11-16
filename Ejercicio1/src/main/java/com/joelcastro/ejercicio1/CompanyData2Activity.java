package com.joelcastro.ejercicio1;

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
import android.widget.CheckBox;
import android.widget.EditText;

public class CompanyData2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companydata2);

        final EditText peso = (EditText) findViewById(R.id.textWeightCData2);
        final EditText cif = (EditText) findViewById(R.id.textCIFCData2);

        final CheckBox cbit = (CheckBox) findViewById(R.id.checkBoxIT);
        final CheckBox cbfg = (CheckBox) findViewById(R.id.checkBoxFridge);
        final CheckBox cboil = (CheckBox) findViewById(R.id.checkBoxOil);


    }
}
