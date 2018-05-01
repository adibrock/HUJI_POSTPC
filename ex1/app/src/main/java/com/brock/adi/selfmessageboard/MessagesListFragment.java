package com.brock.adi.selfmessageboard;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MessagesListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);


        final MessageViewModel viewModel = ViewModelProviders.of(this.getActivity()).get(MessageViewModel.class);
        final MessageAdapter messageAdapter = new MessageAdapter(viewModel.myMessages);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(messageAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        final EditText edit = view.findViewById(R.id.editText);

        view.findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fromUser = edit.getText().toString();
                if (fromUser.isEmpty()) {return;}
                viewModel.msgTOAdd.setValue(new Message(fromUser));
                messageAdapter.notifyItemInserted(viewModel.myMessages.size() - 1);
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                edit.setText("");
            }
        });


        return view;
    }
}
