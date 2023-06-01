package com.example.newkudugram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AnaSayfaActivity extends AppCompatActivity {

    Button btn_cikisYap;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        btn_cikisYap = findViewById(R.id.cikisbutton);
        btn_cikisYap.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pd=new ProgressDialog(AnaSayfaActivity.this);
            pd.dismiss();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AnaSayfaActivity.this, GirisActivity.class));
        }
        });
    }
}