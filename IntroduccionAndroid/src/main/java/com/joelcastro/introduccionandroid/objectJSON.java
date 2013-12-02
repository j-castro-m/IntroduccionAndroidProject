package com.joelcastro.introduccionandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by alu03009 on 23/11/13.
 */
public class objectJSON extends AsyncTask<String, String, String> {

    private Context contexto;
    private TextView ip;
    private TextView country;
    private TextView city;
    private TextView gps;
    private ProgressDialog dialog;
    private GoogleMap mMap;



    public objectJSON(Context contexto, TextView ip, TextView city, TextView country,  TextView gps, GoogleMap mMap) {
        this.contexto = contexto;
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.gps = gps;
        this.mMap = mMap;
    }

    public String getGps() {
        return gps.getText().toString();
    }

    @Override
    protected String doInBackground(String... string) {
        String domain;
        domain=string[0].split("//")[1];
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://freegeoip.net/json/"+domain);
        try {
            HttpResponse reponse = client.execute(get);
            StatusLine state = reponse.getStatusLine();
            int statusCode = state.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = reponse.getEntity();
                InputStream data = entity.getContent();
                BufferedReader read = new BufferedReader(new InputStreamReader(data));
                String line;
                while ((line = read.readLine()) != null) {
                    builder.append(line);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    protected void onPostExecute (String datos){
        parseJSON(datos);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void parseJSON(String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            String ipDomain=json.getString("ip");
            String countryDomain=json.getString("country_name");
            String cityDomain=json.getString("city");
            String coordinates_lat=json.getString("latitude");
            String coordinates_long=json.getString("longitude");
            String coordinatesDomain=coordinates_lat+","+coordinates_long;

            ip.setText(ipDomain);
            country.setText(countryDomain);
            city.setText(cityDomain);
            gps.setText(coordinatesDomain);
            LatLng usjLatLong = new LatLng(Double.valueOf(coordinates_lat),Double.valueOf(coordinates_long));
            mMap.addMarker(new MarkerOptions()
                    .position(usjLatLong));
            mMap.setMyLocationEnabled(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onPreExecute() {
        this.dialog= new ProgressDialog(contexto);
        this.dialog.setMessage("Cargando información de dominio");
        this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.dialog.incrementProgressBy(5);
        this.dialog.show();
    }
}
