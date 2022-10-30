package com.viettel.core.repository;

import com.viettel.core.model.entity.Account;
import com.viettel.core.model.request.AccountUpdateRequest;

public interface CustomizedAccountRepository {
    int[] create(Account account);
}
