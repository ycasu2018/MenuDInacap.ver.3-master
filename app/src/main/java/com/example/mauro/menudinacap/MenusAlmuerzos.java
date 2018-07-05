package com.example.mauro.menudinacap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenusAlmuerzos extends AppCompatActivity {
    TextView tvusualm;
    TextView fechaCompleta;
    private AsyncHttpClient cliente;
    ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almuerzo);
        cliente = new AsyncHttpClient();
        tvusualm = (TextView) findViewById(R.id.TextV_UsuAlm);
        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        final String user_id = intent.getStringExtra("user_id");

        tvusualm.setText(user_id + " .- " + usuario);

        Date hoy = new Date();

        //SACAMOS LA FECHA COMPLETA
        fechaCompleta = (TextView) findViewById(R.id.TextV_Fecha);
        SimpleDateFormat formaFecha = new SimpleDateFormat("dd'/'MM'/'yyyy");
        String fechacComplString = formaFecha.format(hoy);
        fechaCompleta.setText(fechacComplString);
    }
}
