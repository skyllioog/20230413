package com.example.sw221103;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test extends AppCompatActivity {
    String uid;
    String date;
    String order_date;
    String password;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        EditText name_edit = findViewById(R.id.name_edit);
        //EditText content_edit = findViewById(R.id.content_edit);
        //EditText password_edit = findViewById(R.id.password_edit);
        Button addBtn = findViewById(R.id.add_btn);

        DAOUser dao = new DAOUser();

        uid = user.getUid();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_edit.getText().toString();
                //String content = content_edit.getText().toString();
                //String password = password_edit.getText().toString();

                //User1 user1 = new User1(uid, "", name, content, date, order_date, password);
                User1 user1 = new User1(uid, "", name, date, order_date, password);


                dao.add(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "실패" + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

                Date time = new Date();

                String today = dateFormat.format(time);
                String order_today = dateFormat2.format(time);

                user1.setUser_date(today);
                user1.setUser_order_date(order_today);


            }
        });
        Button listBtn = findViewById(R.id.list_btn);
        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Test.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
