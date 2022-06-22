package com.example.soft.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soft.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4,editText5, editText6;
    Button submit;

    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        editText1 = (EditText) findViewById(R.id.jobTitle);
        editText2 = (EditText) findViewById(R.id.jobLocation);
        editText3 = (EditText) findViewById(R.id.companyName);
        editText4 = (EditText) findViewById(R.id.jobDescription);
        editText5 = (EditText) findViewById(R.id.contact);
        editText6 = findViewById(R.id.emailID);
        submit = (Button) findViewById(R.id.submit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                post();
            }
        });
    }

    private void post() {

        String jobTitle = editText1.getText().toString();
        String jobLocation = editText2.getText().toString();
        String companyName = editText3.getText().toString();
        String jobDescription = editText4.getText().toString();
        String contactNo = editText5.getText().toString();
        String emailID = editText6.getText().toString();

        if (!TextUtils.isEmpty(jobTitle) &&
                !TextUtils.isEmpty(jobLocation) && !TextUtils.isEmpty(companyName) && !TextUtils.isEmpty(jobLocation) && !TextUtils.isEmpty(jobDescription)&& !TextUtils.isEmpty(emailID)&& !TextUtils.isEmpty(contactNo)) {

            //getting a unique id using push().getKey() method

            String id = db.push().getKey();

            PostFeedData pf = new PostFeedData(id,jobTitle,jobLocation,companyName,jobDescription,contactNo, emailID);

            db.child(id).setValue(pf);
            //displaying a success toast
            Toast.makeText(this, "Postagem enviada com sucesso", Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(this,PostFeedActivity.class);
//            startActivity(intent);
            finish();

        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Por favor, insira os detalhes corretos!", Toast.LENGTH_LONG).show();
        }
    }


}