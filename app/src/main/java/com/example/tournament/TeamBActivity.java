package com.example.tournament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class TeamBActivity extends AppCompatActivity {
    private EditText nameEt;
    private Button addBtn;
    private List<String> teamB;
    DataSet data;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_b);
        nameEt=findViewById(R.id.nameEt);
        addBtn=findViewById(R.id.addBtn);
        teamB=new ArrayList<>();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                teamB.add(String.valueOf(nameEt));
                nameEt.setText("");
                Toast.makeText(TeamBActivity.this, "Player "+teamB.size()+" added", Toast.LENGTH_SHORT).show();
                if (teamB.size()==20)
                {
                    Toast.makeText(TeamBActivity.this, "TEAMB Squad is full", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(TeamBActivity.this,RoundOneActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}