package com.example.tournament;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoundTwoActivity extends AppCompatActivity {
        private ListView teamA,teamB;
        private List<String> teama,teamb,oneteam;
        DatabaseReference referenceA,referenceB,reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_two);
        teamA=findViewById(R.id.teamA);
        teamB=findViewById(R.id.teamB);
        teama=new ArrayList<>();
        teamb=new ArrayList<>();
        oneteam=new ArrayList<>();
       final ArrayAdapter<String> teamAadapter = new ArrayAdapter<>(RoundTwoActivity.this, android.R.layout.simple_list_item_1,teama);
        final ArrayAdapter<String> teamBadapter = new ArrayAdapter<>(RoundTwoActivity.this, android.R.layout.simple_list_item_1,teamb);
        teamA.setAdapter(teamAadapter);
        teamB.setAdapter(teamBadapter);

        referenceA= FirebaseDatabase.getInstance().getReference();
        referenceA.child("TeamA").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String t1 = snapshot.getValue(String.class);
                teama.add(t1);
                teamAadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                teamAadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceB= FirebaseDatabase.getInstance().getReference();
        referenceB.child("TeamB").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String t2 = snapshot.getValue(String.class);
                teamb.add(t2);
                teamBadapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                teamBadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        teamA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = teamA.getItemAtPosition(position).toString();
                oneteam.add((name));
            }
        });
        teamB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = teamB.getItemAtPosition(position).toString();
                oneteam.add((name));
            }
        });


        reference=FirebaseDatabase.getInstance().getReference().child("OneTeam");
        reference.setValue(oneteam).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RoundTwoActivity.this, "Player "+oneteam.size()+" is stored successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}