package com.brock.adi.selfmessageboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String KEY_JSON = "jasonKey";
    ArrayList<Message> myMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myMessages  = loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MessageAdapter messageAdapter = new MessageAdapter(myMessages);
        final RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(messageAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        final EditText edit = findViewById(R.id.editText);

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fromUser = edit.getText().toString();
                if (fromUser.isEmpty()) {return;}
                myMessages.add(new Message(fromUser));
                messageAdapter.notifyItemInserted(myMessages.size() - 1);
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                edit.setText("");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData(myMessages);
    }

    private ArrayList<Message> loadData(){
        String json = getSP().getString(KEY_JSON, null);
        Log.d("activity", json);
        if (json == null){
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
        return new Gson().fromJson(json, listType);
    }

    private void saveData(ArrayList<Message> messages){
        String dataJson = new Gson().toJson(messages);
        Log.d("activity", dataJson);
        getSP().edit().putString(KEY_JSON, dataJson).apply();
    }

    private SharedPreferences getSP(){
        return this.getSharedPreferences("com.brock.adi.selfmessageboard", MODE_PRIVATE);
    }
}
