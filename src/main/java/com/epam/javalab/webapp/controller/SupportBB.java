package com.epam.javalab.webapp.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named()
@ConversationScoped()
public class SupportBB implements Serializable {
    private static final long serialVersionUID = 1L;
    private int someValue;
    @Inject
    private Conversation conversation;

    // Control start and end of conversation
    public void start() {
        conversation.begin();
    }

    public void end() {
        conversation.end();
    }


    public void onStart(int id) {
        if (conversation == null) {
            return;
        }
        start();
        someValue = id;
    }

    public void onFinish() {
        end();
    }


    public int getSomeValue() {
        return someValue;
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }
}