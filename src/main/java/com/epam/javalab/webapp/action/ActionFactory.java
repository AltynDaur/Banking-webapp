package com.epam.javalab.webapp.action;

import com.epam.javalab.webapp.action.admin.account.*;
import com.epam.javalab.webapp.action.admin.accountType.AccTypeAddAction;
import com.epam.javalab.webapp.action.admin.accountType.AccTypeDeleteAction;
import com.epam.javalab.webapp.action.admin.accountType.AccTypeUpdateAction;
import com.epam.javalab.webapp.action.admin.accountType.AccountTypesListAction;
import com.epam.javalab.webapp.action.admin.exchangeRates.ExchangeRateAddAction;
import com.epam.javalab.webapp.action.admin.exchangeRates.ExchangeRateDeleteAction;
import com.epam.javalab.webapp.action.admin.exchangeRates.ExchangeRateUpdateAction;
import com.epam.javalab.webapp.action.admin.exchangeRates.ExchangeRatesAction;
import com.epam.javalab.webapp.action.admin.user.UserAddAction;
import com.epam.javalab.webapp.action.admin.user.UserDeleteAction;
import com.epam.javalab.webapp.action.admin.user.UserUpdateAction;
import com.epam.javalab.webapp.action.admin.user.UsersListAction;
import com.epam.javalab.webapp.action.client.*;
import com.epam.javalab.webapp.action.admin.*;
import com.epam.javalab.webapp.action.login.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    Map<String,Action> actions = new HashMap<>();
    {
        actions.put("login",new LoginAction());
        actions.put("loginPage", new LoginPageAction());
        actions.put("register", new RegisterAction());
        actions.put("registerPage", new RegisterPageAction());
        actions.put("admin/adminMainPage", new AdminMainPageAction());
        actions.put("client/clientMainPage",new ClientMainPageAction());
        actions.put("admin/users", new UsersListAction());
        actions.put("client/accountsInfo", new AccountsInfoAction());
        actions.put("client/accountsTypeInfo", new AccountsTypeInfoAction());
        actions.put("userInfoChangePage", new UserInfoChangePageAction());
        actions.put("admin/accounts", new AccountsListAction());
        actions.put("admin/accountTypes", new AccountTypesListAction());
        actions.put("changeLocale", new ChangeLocaleAction());
        actions.put("userInfoChange", new UserInfoChangeAction());
        actions.put("admin/updateUser",new UserUpdateAction());
        actions.put("admin/addUser", new UserAddAction());
        actions.put("admin/deleteUser", new UserDeleteAction());
        actions.put("admin/updateAccount", new AccountUpdateAction());
        actions.put("admin/addAccount", new AccountAddAction());
        actions.put("admin/deleteAccount", new AccountDeleteAction());
        actions.put("admin/updateAccType", new AccTypeUpdateAction());
        actions.put("admin/addAccType", new AccTypeAddAction());
        actions.put("admin/deleteAccType", new AccTypeDeleteAction());
        actions.put("logout", new LogOutAction());
        actions.put("admin/accountsPage",new AccountsPageAction());
        actions.put("admin/accountsTypePage", new AccountsTypePageAction());
        actions.put("admin/usersPage", new UsersPageAction());
        actions.put("client/transactionPage", new TransactionPageAction());
        actions.put("client/transaction", new TransactionAction());
        actions.put("client/transactionHistory", new UserTransactionHistoryAction());
        actions.put("admin/transactionHistory", new TransactionHistoryAction());
        actions.put("admin/exchangeRates", new ExchangeRatesAction());
        actions.put("admin/addExchangeRate", new ExchangeRateAddAction());
        actions.put("admin/deleteExchangeRate", new ExchangeRateDeleteAction());
        actions.put("admin/updateExchangeRate", new ExchangeRateUpdateAction());
        actions.put("admin/exchangeRatesPage", new ExchangeRatesPageAction());
        actions.put("client/exchangeRates", new ExchangeRatesListAction());
    }

    public Action getAction(String answerName) {

        Action action = actions.get(answerName);
        return action;
    }
}
