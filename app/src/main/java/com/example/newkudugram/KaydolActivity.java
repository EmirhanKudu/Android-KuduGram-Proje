package com.example.newkudugram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class KaydolActivity extends AppCompatActivity {


    EditText edt_kullaniciAdi, edt_Ad, edt_Email, edt_Sifre;

    Button btn_Kaydol;
    TextView txt_GirisSayfasinaGit;

    FirebaseAuth yetki;

    DatabaseReference yol;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        edt_kullaniciAdi=findViewById(R.id.edt_kullaniciAdi);
        edt_Ad=findViewById(R.id.edt_Ad);
        edt_Email=findViewById(R.id.edt_Email);
        edt_Sifre=findViewById(R.id.edt_Sifre);

        btn_Kaydol=findViewById(R.id.btn_Kaydol_activity);

        txt_GirisSayfasinaGit=findViewById(R.id.txt_GirisSayfasina_git);

        yetki=FirebaseAuth.getInstance();

        txt_GirisSayfasinaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KaydolActivity.this, GirisActivity.class));
            }
        });

        btn_Kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd=new ProgressDialog(KaydolActivity.this);
                pd.setMessage("Lütfen bekleyin..");
                pd.show();

                String str_kullaniciAdi=edt_kullaniciAdi.getText().toString();
                String str_Ad=edt_Ad.getText().toString();
                String str_Email=edt_Email.getText().toString();
                String str_Sifre=edt_Sifre.getText().toString();

                if (TextUtils.isEmpty(str_kullaniciAdi)||TextUtils.isEmpty(str_Ad)||TextUtils.isEmpty(str_Email)||TextUtils.isEmpty(str_Sifre))
                {
                    Toast.makeText(KaydolActivity.this, "Lütfen bütün alanları doldurunuz..", Toast.LENGTH_SHORT).show();

                } else if (str_Sifre.length()<6) {
                    Toast.makeText(KaydolActivity.this, "Şifreniz minimum 6 karakter olmalı..", Toast.LENGTH_SHORT).show();

                }
                else {
                    //Yeni kullanıcı kaydetme kodlarını çağır

                    kaydet(str_kullaniciAdi,str_Ad,str_Email,str_Sifre);
                }



            }
        });
    }

    private void kaydet(String kullaniciadi, String ad, String email, String sifre)
    {

        //Yeni kullanıcı kaydetme kodları
        yetki.createUserWithEmailAndPassword(email, sifre)
                .addOnCompleteListener(KaydolActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            pd.dismiss();
                            Intent intent=new Intent(KaydolActivity.this, AnaSayfaActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            pd.dismiss();
                            Toast.makeText(KaydolActivity.this, "Mail adresi daha önce kullanılmış. Lütfen farklı bir email kullanın!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}