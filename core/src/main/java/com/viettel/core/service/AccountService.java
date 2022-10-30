package com.viettel.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viettel.core.model.request.AccountUpdateRequest;

public interface AccountService {

    long nextAccountId();

    void create(AccountUpdateRequest request) throws JsonProcessingException;
}
