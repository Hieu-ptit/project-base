package com.viettel.core.repository;

import com.dslplatform.json.JsonWriter;
import com.viettel.commons.util.Json;
import com.viettel.core.model.BoxInvitationMetadata;
import com.viettel.core.model.RewardMetadata;
import com.viettel.core.model.entity.Account;
import com.viettel.core.model.entity.Company;
import com.viettel.core.model.request.AccountUpdateRequest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountBatchInsertSetter implements BatchPreparedStatementSetter {

    private final Account account;

    private static final JsonWriter.WriteObject<BoxInvitationMetadata> REWARD_METADATA_WRITE_OBJECT = Json.findWriter(BoxInvitationMetadata.class);

    private static final JsonWriter.WriteObject<Company> COMPANY_WRITE_OBJECT = Json.findWriter(Company.class);


    public AccountBatchInsertSetter(Account account) {
        this.account = account;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        ps.setLong(1, account.getId());
        ps.setString(2, account.getName());
        ps.setObject(3, Json.encodeToString(account.getMetadata(), REWARD_METADATA_WRITE_OBJECT));
        ps.setObject(4, account.getCreatedAt());
        ps.setObject(5, account.getUpdatedAt());
    }

    @Override
    public int getBatchSize() {
        return 1;
    }
}
