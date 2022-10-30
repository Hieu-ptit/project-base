package com.viettel.core.repository;

import com.viettel.core.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, InsertUpdateRepository<Account>, CustomizedAccountRepository {

    @Query(value = "select nextval('account_id_seq')", nativeQuery = true)
    long nextAccountId();

    @Transactional
    @Query(value = """
            INSERT INTO account (id, name, metadata, company, boxes, created_at, updated_at)
            VALUES
            (:#{#account.id}, :#{#account.name}, :metadata, :company, :boxes, :#{#account.createdAt}, :#{#account.updatedAt}) returning *
            """, nativeQuery = true)
    Account create(Account account, String metadata, String boxes, String company);
}
