package com.brock.adi.selfmessageboard;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MessageViewModel extends ViewModel {

    public ArrayList<Message> myMessages;
    public MutableLiveData<Message> curPressedMsg = new MutableLiveData<>();
    public MutableLiveData<Message> msgTODelete = new MutableLiveData<>();


}
