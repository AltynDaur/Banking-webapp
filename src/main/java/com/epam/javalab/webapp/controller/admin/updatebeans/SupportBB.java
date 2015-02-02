package com.epam.javalab.webapp.controller.admin.updatebeans;

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


    public String onClick(int id) {
        if (conversation == null) {
            return "";
        }
        start();
        someValue = id;
        return "editPasswordPage?faces-redirect=true";
    }

    public String onFinish() {
        end();
        return "usersPage?faces-redirect=true";
    }


    public int getSomeValue() {
        return someValue;
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }
}