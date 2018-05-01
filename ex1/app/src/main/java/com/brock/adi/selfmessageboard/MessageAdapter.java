package com.brock.adi.selfmessageboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        TextView timeStamp;

        public MessageViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.item_massage);
            timeStamp = itemView.findViewById(R.id.item_timestamp);
        }
    }

    public MessageAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    private ArrayList<Message> messages;

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message curMessage = messages.get(position);
        holder.message.setText(curMessage.message);
        holder.timeStamp.setText(curMessage.timeStamp);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("adapter", "been here");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
