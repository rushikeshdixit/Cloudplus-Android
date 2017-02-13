package com.cloudrail.fileviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by PM on 9/30/2016.
 */
public class Singup extends Activity {

    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()==R.id.Bsignupbutton)
        {
            EditText name=(EditText)findViewById(R.id.TFname);
            EditText email=(EditText)findViewById(R.id.TFemail);
            EditText uname=(EditText)findViewById(R.id.TFuname);
            EditText pass1=(EditText)findViewById(R.id.TFpass1);
            EditText pass2=(EditText)findViewById(R.id.TFpass2);

           String namestr=name.getText().toString();
            String emailstr=email.getText().toString();
            String unamestr=uname.getText().toString();
            String pass1str=pass1.getText().toString();
            String pass2str=pass2.getText().toString();

            //pop up message
            if(!pass1str.equals(pass2str))
            {
                Toast pass= Toast.makeText(Singup.this,"Password don't match", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                //insert data into first wrapper and then to database
                Contact c= new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);

                helper.insertContact(c);

                Toast pass= Toast.makeText(Singup.this,"Registerd Successfully", Toast.LENGTH_SHORT);
                pass.show();

                Intent i=new Intent(Singup.this,MainActivitymain.class);
                startActivity(i);
            }
        }
    }
}
