package com.example.sw221103;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    EditText updateNameEdit, updateContentEdit, password_edit;
    String sKey, sName, sContent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        DAOUser dao = new DAOUser();

        updateNameEdit = findViewById(R.id.update_name_edit);
        updateContentEdit = findViewById(R.id.update_content_edit);
        //password_edit = findViewById(R.id.password_edit);

        getAndSetIntentData();

        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String uName = updateNameEdit.getText().toString();
                String uContent = updateContentEdit.getText().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("user_name", uName);
                hashMap.put("user_content", uContent);

                dao.update(sKey, hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "업데이트 성공", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "업데이트 실패" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        /*
        Button passwordBtn = findViewById(R.id.password_btn);
        passwordBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!password_edit.getText().toString().equals("")) {
                    //writeuser(password_edit.getText().toString());
                    Toast.makeText(UpdateActivity.this, "비밀번호가 일치합니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

*/
    }
    public void getAndSetIntentData(){
        if(getIntent().hasExtra("key") && getIntent().hasExtra("name") &&
        getIntent().hasExtra("content")){
            sKey = getIntent().getStringExtra("key");
            sName = getIntent().getStringExtra("name");
            sContent = getIntent().getStringExtra("content");

            updateNameEdit.setText(sName);
            updateContentEdit.setText(sContent);
        }
    }

    @Override
    //액티비티가 만들어질 때 미리 자동 호출
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi_menu, menu); //menu_main.xml 인플레이션

        return true;
    }
    @Override
    //사용자가 메뉴 아이템을 선택했을 때 호출
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.item_up:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_de:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
