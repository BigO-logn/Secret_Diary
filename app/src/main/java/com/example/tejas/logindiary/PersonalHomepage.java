package com.example.tejas.logindiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PersonalHomepage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onDestroy() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(PersonalHomepage.this, MainActivity.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_homepage);

        firebaseAuth= FirebaseAuth.getInstance();
    }




    private void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(PersonalHomepage.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.logoutMenu:{
                logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
