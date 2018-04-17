package com.brock.adi.selfmessageboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_in_card);
        }
    }

    private ArrayList<String> messages = new ArrayList<>();

    public void addMessage(String message){
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String curMessage = messages.get(position);
        holder.textView.setText(curMessage);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
