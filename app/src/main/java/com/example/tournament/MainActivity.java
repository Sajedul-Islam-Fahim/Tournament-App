package com.example.tournament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText teamA, teamB;
    private Button save,next;
    private List<String> teama,teamb;
    DatabaseReference referenceA,referenceB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamA=findViewById(R.id.editTextTextPersonName);
        teamB=findViewById(R.id.editTextTextPersonName2);
        next=findViewById(R.id.button2);

        teama = new ArrayList<>();
        teamb = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenceA=FirebaseDatabase.getInstance().getReference().child("TeamA");
        referenceB=FirebaseDatabase.getInstance().getReference().child("TeamB");



        save=findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamAname = teamA.getText().toString();
                String teamBname = teamB.getText().toString();
                if (teamAname.isEmpty() || teamBname.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter the player name", Toast.LENGTH_SHORT).show();
                }
                else{

                    teama.add(teamAname);
                    teamb.add(teamBname);
                }

                referenceA.setValue(teama).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Player "+teama.size()+" is stored successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                referenceB.setValue(teamb).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Player "+teamb.size()+" is stored successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teama.size()==20 )
                    if( teamb.size()==20)
                {
                    Intent intent = new Intent(MainActivity.this,RoundTwoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}