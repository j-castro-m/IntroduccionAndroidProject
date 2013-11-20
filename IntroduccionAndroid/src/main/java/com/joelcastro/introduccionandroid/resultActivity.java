package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        final GridLayout grid2 = (GridLayout) findViewById(R.id.GridResult2);
        final View linea = (View) findViewById(R.id.Linea);

        final Button enviaremail = (Button) findViewById(R.id.button_sendbymail);
        final Button fin_reg = (Button) findViewById(R.id.button_reg_dep);

        final TextView textCoste = (TextView) findViewById(R.id.textDataResult);
        final TextView textPrecio = (TextView) findViewById(R.id.NumberDataResult);
        final TextView textIVa = (TextView) findViewById(R.id.TextIVAResult);
        final TextView textTotal = (TextView) findViewById(R.id.TextTotalResult);

        final Bundle extra = this.getIntent().getExtras();
        String email = new String("");

        double peso = Double.parseDouble(extra.getString("Peso"));
        double precio = peso * 2.5;
        double iva = precio * 0.2;
        final double total = precio + iva;

        textCoste.setText(String.valueOf(peso)+ "Kg. * 2,5€/Kg");
        textPrecio.setText(String.valueOf(precio)+"€");
        textIVa.setText(String.valueOf(iva)+"€");
        textTotal.setText(String.valueOf(total)+"€");


        if(extra.getBoolean("company"))
        {
            result_cost_text.setVisibility(View.VISIBLE);
            grid.setVisibility(View.VISIBLE);
            grid2.setVisibility(View.VISIBLE);
            linea.setVisibility(View.VISIBLE);
            enviaremail.setVisibility(View.VISIBLE);
            email = extra.getString("email");

        }

        final String emailF = email;

        cif.setText(extra.getString("cif"));



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


        enviaremail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", emailF, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Factura");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "El coste de su deposito asciende a "+String.valueOf(total)+"€");
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            }
        });




        fin_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new  Intent(resultActivity.this, MainActivity.class);
                // Indica que la aplicación debe cerrarse
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }
}
