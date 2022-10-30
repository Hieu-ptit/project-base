package com.viettel.core.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AccountRequest {

    private String name;

    private String address;
}
