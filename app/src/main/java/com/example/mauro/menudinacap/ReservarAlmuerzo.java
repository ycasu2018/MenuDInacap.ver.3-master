package com.example.mauro.menudinacap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class ReservarAlmuerzo extends AppCompatActivity implements OnClickListener {
    TextView tvusualm;
    TextView fechaCompleta;
    private AsyncHttpClient cliente;
    CardView cv_resAlm;
    TextView tvNomSpi;
    ImageView imgqr;
    String text2Qr;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservalmuerzo);

        sp = (Spinner) findViewById(R.id.spAlmuerzo);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listItems);
        sp.setAdapter(adapter);

        cliente = new AsyncHttpClient();

        tvusualm = (TextView) findViewById(R.id.TextV_UsuReserva);

        cv_resAlm = (CardView) findViewById(R.id.CardV_reservar);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        final String user_id = intent.getStringExtra("user_id");

        tvusualm.setText(user_id + " .- " +usuario);

        Date hoy = new Date();

        //SACAMOS LA FECHA COMPLETA
        fechaCompleta = (TextView) findViewById(R.id.TextV_FechaReserva);
        SimpleDateFormat formaFecha = new SimpleDateFormat("dd'/'MM'/'yyyy");
        String fechacComplString = formaFecha.format(hoy);
        fechaCompleta.setText(fechacComplString);

        tvNomSpi = (TextView) findViewById(R.id.tvSpinner);

        imgqr = findViewById(R.id.imageqr);
        reservarAlmuerzos();


    }

    private void reservarAlmuerzos(){
        cv_resAlm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomPlato = sp.getSelectedItem().toString();
                String separar = " ";
                final String[] numPlato = nomPlato.split(separar);

                final String nomUseer = tvusualm.getText().toString();
                final String[] numUseer = nomUseer.split(separar);

                final String estado = "Pagado";

                Reserva a = new Reserva();

                a.setUser_id(Integer.parseInt(numUseer[0]));
                a.setAlmuerzo_id(Integer.parseInt(numPlato[0]));
                a.setEstado_Reserva(estado);
                agregarReserva(a);


            }
        });
    }

    public void onStart() {
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();


    }

    @Override
    public void onClick(View view) {

    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.0.19/basedatosMenudinacap/ObtAlmu.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

//convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("nomAlmuerzo"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            listItems.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    private void agregarReserva(Reserva a){
        String url = "http://192.168.0.19/basedatosMenudinacap/AgregarReserva.php?";
        String parametros = "user_id="+a.getUser_id()+"&almuerzo_id="+a.getAlmuerzo_id()+"&estadoReserva="+a.getEstado_Reserva();
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    Toast.makeText(ReservarAlmuerzo.this,"Producto Agregado Correctamente!!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

}
