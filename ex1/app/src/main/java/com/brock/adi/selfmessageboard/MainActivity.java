package com.brock.adi.selfmessageboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageAdapter messageAdapter = new MessageAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(messageAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        messageAdapter.addMessage("hello");
        messageAdapter.addMessage("world");
        messageAdapter.addMessage("i");
        messageAdapter.addMessage("love");
        messageAdapter.addMessage("you");
        messageAdapter.addMessage("my");
        messageAdapter.addMessage("beautiful");
        messageAdapter.addMessage("cookie");
        messageAdapter.addMessage("who");
        messageAdapter.addMessage("is");
        messageAdapter.addMessage("my");
        messageAdapter.addMessage("amazing");
        messageAdapter.addMessage("husband");
        messageAdapter.addMessage("in");
        messageAdapter.addMessage("the");
        messageAdapter.addMessage("whole");
        messageAdapter.addMessage("wide");
        messageAdapter.addMessage("world");


        // View.OnClickListener



    }
}
