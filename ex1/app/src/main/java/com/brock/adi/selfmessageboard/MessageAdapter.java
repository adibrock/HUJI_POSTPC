package com.brock.adi.selfmessageboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private MessageViewModel viewModel;

    public MessageAdapter(MessageViewModel viewModel) {
        this.viewModel = viewModel;
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
        final Message curMessage = viewModel.myMessages.get(position);
        holder.message.setText(curMessage.message);
        holder.timeStamp.setText(curMessage.timeStamp);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewModel.curPressedMsg.setValue(curMessage);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewModel.myMessages.size();
    }
}
