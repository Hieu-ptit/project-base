package com.viettel.core.service.impl;

import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Generics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.commons.util.Json;
import com.viettel.core.mapper.AccountMapperEntity2;
import com.viettel.core.mapper.CompanyMapperEntity2;
import com.viettel.core.mapper.RewardBoxMapperEntity;
import com.viettel.core.model.BoxInvitationMetadata;
import com.viettel.core.model.RewardBox;
import com.viettel.core.model.RewardMetadata;
import com.viettel.core.model.entity.Company;
import com.viettel.core.model.request.AccountRequest;
import com.viettel.core.model.request.AccountUpdateRequest;
import com.viettel.core.repository.AccountRepository;
import com.viettel.core.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private static final JsonWriter.WriteObject<List<RewardBox>> dataWriter = Json.findWriter(Generics.makeParameterizedType(List.class, RewardBox.class));

    final ObjectMapper mapper = new ObjectMapper();
    @Override
    @Transactional
    public long nextAccountId() {
        return repository.nextAccountId();
    }

    @Override
    public void create(AccountUpdateRequest request) throws JsonProcessingException {
        var boxInvitationMetadata = new BoxInvitationMetadata().setBoxId(request.getBoxId()).setName(request.getNameBox());
        var account = (AccountMapperEntity2.INSTANCE.map(request).setMetadata(boxInvitationMetadata)).setId(nextAccountId())
                .setCreatedAt(OffsetDateTime.now()).setUpdatedAt(OffsetDateTime.now());
        List<RewardBox> boxes = RewardBoxMapperEntity.INSTANCE.mapList(request.getBoxes());
        var company = CompanyMapperEntity2.INSTANCE.map(request.getCompany());
        repository.create(account, mapper.writeValueAsString(account.getMetadata()), Json.encodeToString(boxes, dataWriter),
                mapper.writeValueAsString(company));
    }
}
