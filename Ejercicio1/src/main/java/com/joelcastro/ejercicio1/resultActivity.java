package com.joelcastro.ejercicio1;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class resultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView cif = (TextView) findViewById(R.id.result_CIF);
        final TextView tipo_residuo = (TextView) findViewById(R.id.result_tipo_residuo);
        final TextView result_cost_text = (TextView) findViewById(R.id.result_cost_text);
        final GridLayout grid = (GridLayout) findViewById(R.id.GridResult);


        Bundle extra = this.getIntent().getExtras();
        if(!extra.getBoolean("company"))
        {
            result_cost_text.setVisibility(View.VISIBLE);
            grid.setVisibility(View.VISIBLE);
        }
        cif.setText(extra.getString("cif"));
        int peso = extra.getInt("peso");

        String tipos = "";

        if(extra.getBoolean("ITmat"))
        {
            if(tipos.length()>0)
            {
                tipos = tipos+(" ,Material Informático");
            }
            else
            {
                tipos = tipos+("Material Informático");
            }
        }

        if(extra.getBoolean("Fridge"))
        {
            if(tipos.length()>0)
            {
                tipos = tipos+(", Neveras");
            }
            else
            {
                tipos = tipos+("Neveras");
            }
        }

        if(extra.getBoolean("Oil"))
        {
            if(tipos.length()>0)
            {
                tipos = tipos+(", Aceites usados");
            }
            else
            {
                tipos = tipos+("Aceites usados");
            }
        }

        tipo_residuo.setText(tipos);
        Log.e("tipos", "valor:" + tipos);




    }
}
