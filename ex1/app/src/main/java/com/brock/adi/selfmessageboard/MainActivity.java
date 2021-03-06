package com.brock.adi.selfmessageboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String KEY_JSON = "jasonKey";
    MessageViewModel viewModel;
    final MessagesListFragment listFragment = new MessagesListFragment();
    final FocusedMessageFragment msgFragment = new FocusedMessageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        viewModel.myMessages  = loadData();


        viewModel.msgTOAdd.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {
                if (message == null){return;}
                viewModel.myMessages.add(message);
            }
        });

        viewModel.curPressedMsg.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {
                if (message == null) {return;}
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, msgFragment).commit();
            }
        });

        viewModel.msgTODelete.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {
                if(message == null) {return;}
                viewModel.myMessages.remove(message);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();

            }
        });

        viewModel.msgTOShare.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {
                if (message == null){return;}
                String shareBody = message.message;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_message)));
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFragment).commit();

    }

    @Override
    public void onBackPressed() {
        if (msgFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData(viewModel.myMessages);
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
