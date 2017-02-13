package com.cloudrail.fileviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



//This is the class to be launched first
public class MainActivitymain extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //For Login activity
    public void onButtonClick(View v)
    {
    if(v.getId()== R.id.Blogin)
    {
        EditText a =(EditText)findViewById(R.id.TFusername);
        String str=a.getText().toString();

        EditText b =(EditText)findViewById(R.id.TFpassword);
        String pass=b.getText().toString();

        String password=helper.searchPass(str);

        if(pass.equals(password)) {
            Intent i = new Intent(MainActivitymain.this, FileViewer.class);
            i.putExtra("Username", str);
            startActivity(i);
        }
        else
        {
            Toast temp= Toast.makeText(MainActivitymain.this,"Username-Password mis-match", Toast.LENGTH_SHORT);
            temp.show();
        }
        }

       //if user clicks on Sign up Button
     if(v.getId()==R.id.Bsignup)
     {
         Intent i=new Intent(MainActivitymain.this,Singup.class);
         startActivity(i);
     }
    }
}
