package com.brock.adi.selfmessageboard;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FocusedMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_focused_message, container, false);

        final MessageViewModel viewModel = ViewModelProviders.of(this.getActivity()).get(MessageViewModel.class);
        final Message message = viewModel.curPressedMsg.getValue();
        if (message == null){throw new RuntimeException();}
        TextView msgContent = view.findViewById(R.id.tv_msg_content);
        TextView timestamp = view.findViewById(R.id.tv_timestamp);

        msgContent.setText("Message: " + message.message);
        timestamp.setText("Time: " + message.timeStamp);

        view.findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.msgTODelete.setValue(message);
            }
        });

        view.findViewById(R.id.button_share).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.msgTOShare.setValue(message);
            }
        });


        return view;
    }


}
