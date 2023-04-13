package com.example.sw221103;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOUser {
    private DatabaseReference databaseReference;

    DAOUser(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User1.class.getSimpleName());
    }
    //등록
    public Task<Void> add(User1 user1){
        return databaseReference.push().setValue(user1);
    }
    //조회
    public Query get(){
        return databaseReference;
    }
    //수정
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
}
