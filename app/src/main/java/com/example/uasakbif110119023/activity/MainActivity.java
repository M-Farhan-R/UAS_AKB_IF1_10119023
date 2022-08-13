package com.example.uasakbif110119023.activity;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 11/08/2022
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.uasakbif110119023.R;
import com.example.uasakbif110119023.fragment.CatatanFragment;
import com.example.uasakbif110119023.fragment.InfoFragment;
import com.example.uasakbif110119023.fragment.ProfileFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private View decorView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navbar;
    MaterialToolbar toolbar;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if (user == null){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hide StatusBar keep Nav bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if (i == 0){
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        });

        //toggleNavbar
        drawer = findViewById(R.id.drawerLayout);
        navbar = findViewById(R.id.navbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navbar.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.catatan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CatatanFragment()).commit();
                break;
            case R.id.info:
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InfoFragment()).commit();
                break;
            case R.id.logout:
                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Ingin Log Out?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                auth.signOut();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }
}