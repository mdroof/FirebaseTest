package org.michaelroof.android.firebasetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);


        Firebase ref = new Firebase(BuildConfig.UNIQUE_FIREBASE_ROOT_URL).child("test");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String data = dataSnapshot.getValue(String.class);

                tv_test = (TextView) findViewById(R.id.tv_test);
                tv_test.setText(data);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void clickMe(View view) {
        Firebase ref = new Firebase(BuildConfig.UNIQUE_FIREBASE_ROOT_URL);
        //String userEnteredName = mEditTextListName.getText().toString();
        ref.child("test").setValue("Firebase is awesome!");
    }

/*
    @Override
    public void onContentChanged() {
        Firebase ref = new Firebase(BuildConfig.UNIQUE_FIREBASE_ROOT_URL).child("test");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String data = dataSnapshot.getValue(String.class);

                tv_test = (TextView)findViewById(R.id.tv_test);
                tv_test.setText(data.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        super.onContentChanged();
    }*/
}
