package com.brock.adi.selfmessageboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MessageAdapter messageAdapter = new MessageAdapter();
        final RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(messageAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        final EditText edit = findViewById(R.id.editText);

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String message = edit.getText().toString();
                messageAdapter.addMessage(message);
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                edit.setText("");
            }
        });

    }
}
