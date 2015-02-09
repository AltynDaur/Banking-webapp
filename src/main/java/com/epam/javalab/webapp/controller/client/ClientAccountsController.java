package com.epam.javalab.webapp.controller.client;

import com.epam.javalab.webapp.controller.SupportBB;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ClientAccountsController {

    @Inject
    private SupportBB supportBB;

    public String startTransaction(int id) {
        supportBB.onStart(id);
        return "transactionPage";
    }
}
