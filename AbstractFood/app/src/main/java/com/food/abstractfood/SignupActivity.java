package com.food.abstractfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    private Button cancel;
    private Button signup;
    private ImageButton addImage;
    private Uri imageUri;
    private Bitmap imageBitmap=null;
    EditText pass;
    EditText verifyPass;
    EditText username;
    EditText email;
    DBmanager dBmanager;
    private static Firebase database;
    private static final String url ="https://abstractfoods.firebaseio.com";
    private static final String foodpath = "food";
    private static final String userpath = "user";
    private ArrayList<User> tempUsers;
    private final int id=1;
    private int r;
    private int res=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        dBmanager=new DBmanager();

        database=new Firebase(url);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        verifyPass = (EditText) findViewById(R.id.verifyPasswordtxt);
        pass = (EditText) findViewById(R.id.passwordtxt);
        username=(EditText) findViewById(R.id.username_signup);
        email=(EditText) findViewById(R.id.email_signup);
        pass.setTypeface(Typeface.DEFAULT);
        verifyPass.setTypeface(Typeface.DEFAULT);


        addImage=(ImageButton)findViewById(R.id.addprofilepicture);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(goToGallery,id);
            }
        });



        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Must contain: 8 letters, one uppercase and a number", Toast.LENGTH_LONG).show();
            }
        });

        cancel = (Button) findViewById(R.id.cancel_signup);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signup = (Button) findViewById(R.id.signup);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==id)
        {
            imageUri=data.getData();
            try
            {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
            } catch (IOException e) {}
            addImage.setImageURI(imageUri);
        }
    }

    public void stuff(View v){

        if(pass.getText().toString().equals("")||verifyPass.getText().toString().equals("")||username.getText().toString().equals("")||email.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(),"Some fields are incomplete",Toast.LENGTH_LONG).show();
        }

        else
        {
            if (!(verifyPass.getText().toString().equals(pass.getText().toString())))
            {
                Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
            } else
            {
                User user = new User();
                user.setPassword(pass.getText().toString());
                user.setUsername(username.getText().toString());
                user.setEmail(email.getText().toString());
                user.setImage(imageBitmap);
                //int r=checkDuplicates(user);

                if(r==1)
                {
                    Toast.makeText(getBaseContext(),"Username already exists", Toast.LENGTH_LONG).show();
                }
                else  //if the username is already taken
                {
                    dBmanager.putUser(user);
                    startLogin();


                }


            }
        }
    }




    private void startLogin(){
        Intent next=new Intent(this,MainActivity.class);
        startActivity(next);

    }

    //This function checks if the username already exists in the database
    private void checkDuplicates()
    {

        database.child(userpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u=shot.getValue(User.class);
                    //System.err.println(u.getUsername());
                    if(u.getUsername().equals(username.getText().toString()))
                    {

                        System.out.println("THEY ARE EQUAL!!!!!!");
                        Toast.makeText(getBaseContext(),"Username already exists",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                    {
                        User user = new User();
                        user.setPassword(pass.getText().toString());
                        user.setUsername(username.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setImage(null);
                        user.setSanswer(null);
                        user.setSquestion(null);
                        database.child(userpath).child(user.getUsername()).setValue(user);
                        startLogin();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


        /*int i;
        for(i=0;i<tempUsers.size();i++)
        {
            System.out.println(tempUsers.get(i).getUsername());
            if(tempUsers.get(i).getUsername()==user.getUsername())
            {
                return 1;
            }
        }
        return 0;*/
    }

}