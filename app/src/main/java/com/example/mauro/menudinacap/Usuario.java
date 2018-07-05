package com.example.mauro.menudinacap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class Usuario extends AppCompatActivity {
    TextView tvusuario;
    CardView cv_Almuerzo;
    CardView cv_ReserAlmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvusuario = (TextView) findViewById(R.id.TextV_Usuario);
        cv_Almuerzo = (CardView) findViewById(R.id.CardV_Almuerzo);
        cv_ReserAlmu = (CardView) findViewById(R.id.CardV_ReservAlmu);

        Intent intent = getIntent();
        final String usuario = intent.getStringExtra("usuario");
        final String user_id = intent.getStringExtra("user_id");

        tvusuario.setText(user_id+ " .- " +usuario);

        cv_Almuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Usuario.this, MenusAlmuerzos.class);
                i.putExtra("usuario", usuario);
                i.putExtra("user_id", user_id);
                startActivity(i);
            }
        });

        cv_ReserAlmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Usuario.this, ReservarAlmuerzo.class);
                i.putExtra("usuario", usuario);
                i.putExtra("user_id", user_id);
                startActivity(i);
            }
        });


    }

}
