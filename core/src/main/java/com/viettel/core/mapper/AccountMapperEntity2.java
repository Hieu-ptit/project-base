package com.viettel.core.mapper;

import com.viettel.commons.mapper.BeanMapper;
import com.viettel.core.model.entity.Account;
import com.viettel.core.model.request.AccountRequest;
import com.viettel.core.model.request.AccountUpdateRequest;
import com.viettel.core.model.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapperEntity2 extends BeanMapper<AccountUpdateRequest, Account> {
    AccountMapperEntity2 INSTANCE = Mappers.getMapper(AccountMapperEntity2.class);
}
