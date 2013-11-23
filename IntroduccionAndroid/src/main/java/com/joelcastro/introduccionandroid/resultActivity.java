package com.joelcastro.introduccionandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class resultActivity extends Activity {
    private int mYear;
    private int mMonth;
    private int mDay;
    private TextView date;
    private Button edit;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);


        final TextView cif = (TextView) findViewById(R.id.result_CIF);
        final TextView tipo_residuo = (TextView) findViewById(R.id.result_tipo_residuo);
        final TextView result_cost_text = (TextView) findViewById(R.id.result_cost_text);
        final GridLayout grid = (GridLayout) findViewById(R.id.GridResult);
        final GridLayout grid2 = (GridLayout) findViewById(R.id.GridResult2);
        final View linea = findViewById(R.id.Linea);

        final Button enviaremail = (Button) findViewById(R.id.button_sendbymail);
        final Button fin_reg = (Button) findViewById(R.id.button_reg_dep);

        final TextView textCoste = (TextView) findViewById(R.id.textDataResult);
        final TextView textPrecio = (TextView) findViewById(R.id.NumberDataResult);
        final TextView textIVa = (TextView) findViewById(R.id.TextIVAResult);
        final TextView textTotal = (TextView) findViewById(R.id.TextTotalResult);
        final TextView textPlace = (TextView) findViewById(R.id.result_Place);

        final Bundle extra = this.getIntent().getExtras();
        String email = new String("");



        double peso = Double.parseDouble(extra.getString("Peso"));
        double precio = peso * 2.5;
        double iva = precio * 0.2;
        final double total = precio + iva;
        textPlace.setText(extra.getString("nombreParada"));
        textCoste.setText(String.valueOf(peso)+ getString(R.string.kg)+" * 2,5"+getString(R.string.currency)+"/"+getString(R.string.kg));
        textPrecio.setText(String.valueOf(precio)+getString(R.string.currency));
        textIVa.setText(String.valueOf(iva)+getString(R.string.currency));
        textTotal.setText(String.valueOf(total)+getString(R.string.currency));


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
                tipos = tipos+(" ,"+getString(R.string.ITMaterial));
            }
            else
            {
                tipos = tipos+(getString(R.string.ITMaterial));
            }
        }

        if(extra.getBoolean("Fridge"))
        {
            if(tipos.length()>0)
            {
                tipos = tipos+(", "+getString(R.string.Fridge));
            }
            else
            {
                tipos = tipos+(getString(R.string.Fridge));
            }
        }

        if(extra.getBoolean("Oil"))
        {
            if(tipos.length()>0)
            {
                tipos = tipos+(", "+getString(R.string.Oil));
            }
            else
            {
                tipos = tipos+(getString(R.string.Oil));
            }
        }

        tipo_residuo.setText(tipos);
        Log.e("tipos", "valor:" + tipos);


        enviaremail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", emailF, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.asunto_email_result));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.body_email_result)+String.valueOf(total)+"€");
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            }
        });




        fin_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.end_toast);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent = new  Intent(resultActivity.this, MainActivity.class);
                // Indica que la aplicación debe cerrarse
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });



        date = (TextView) findViewById(R.id.textDateCData);
        edit = (Button) findViewById(R.id.buttonDate);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar calendario= Calendar.getInstance();
        mYear=calendario.get(Calendar.YEAR);
        mMonth=calendario.get(Calendar.MONTH);
        mDay=calendario.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int month, int day){
            mYear=year;
            mMonth=month;
            mDay=day;
            updateDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear,mMonth,mDay);
        }
        return null;
    }


    private void updateDisplay(){
        date.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result, menu);
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
                                Intent intent = new  Intent(resultActivity.this, MainActivity.class);
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
}
