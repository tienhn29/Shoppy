package com.example.shoppy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppy.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ToiActivity extends AppCompatActivity {

    GoogleSignInOptions gsp;
    GoogleSignInClient gsc;
    Button buttondangnhaplogin,buttondangkylogin;

    TextView txttenthongtinkhachhang,txtemailthongtinkhachhang,txtidthongtinkhachhang;
    Button btndangxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toi);
        Initial();
        ActionButton();
    }

    private void ActionButton() {
        buttondangnhaplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToiActivity.this, LoginActivity.class));
            }
        });

        buttondangkylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToiActivity.this, RegisterActivity.class));
            }
        });

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        finish();
                        startActivity(new Intent(ToiActivity.this,LoginActivity.class));
                    }
                });
            }
        });
    }

    private void Initial() {
        buttondangnhaplogin = findViewById(R.id.buttondangnhaplogin);
        buttondangkylogin = findViewById(R.id.buttondangkylogin);

        txttenthongtinkhachhang = findViewById(R.id.textviewtenthongtinkhachhang);
        txtemailthongtinkhachhang = findViewById(R.id.textviewemailthongtinkhachhang);
        txtidthongtinkhachhang = findViewById(R.id.textviewidthongtinkhachhang);
        btndangxuat = findViewById(R.id.buttondangxuat);

        gsp = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gsp);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null){
            String Name = acct.getDisplayName();
            String Email = acct.getEmail();
            String Id = acct.getId();
            txttenthongtinkhachhang.setText(Name);
            txtemailthongtinkhachhang.setText(Email);
            txtidthongtinkhachhang.setText(Id);
        }
    }
}