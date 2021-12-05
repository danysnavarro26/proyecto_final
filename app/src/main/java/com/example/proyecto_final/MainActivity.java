package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    Fragment fragmentPre,fragmentFrag;

    Button btn_ace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentPre=new PresentacionFragment();
        fragmentFrag=new FragmentosFragment();

        btn_ace=findViewById(R.id.btn_acelerometro);

        btn_ace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AcelerometroActivity.class);
                startActivity(intent);
            }
        });


    }

    public void onClick(View view) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btn_prese:
                transaction.replace(R.id.contenedorFragments, fragmentPre).commit();
                break;
            case R.id.btn_fragmentos:
                transaction.replace(R.id.contenedorFragments, fragmentFrag).commit();
                break;
        }
    }
}