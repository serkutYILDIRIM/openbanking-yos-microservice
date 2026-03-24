package com.openbanking.yos.domain.account.service;

import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;

public interface AccountService {

    AccountListResponse getAccounts(String xAspspCode, String xTppCode, String rizaNo);

    AccountResponse getAccount(String hspRef, String xAspspCode, String xTppCode, String rizaNo);
}

