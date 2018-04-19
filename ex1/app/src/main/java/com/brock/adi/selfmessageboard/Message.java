package com.brock.adi.selfmessageboard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("h:mm a dd/MM/yyyy");
    public String timeStamp;
    public String message;

    public Message(String message) {
        this.message = message;
        this.timeStamp = FORMATTER.format(new Date());
    }
}
