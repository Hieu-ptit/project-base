package com.viettel.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viettel.commons.model.response.Response;
import com.viettel.core.model.request.AccountRequest;
import com.viettel.core.model.request.AccountUpdateRequest;
import com.viettel.core.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Validated
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Response<Void> create(@Valid @RequestBody AccountUpdateRequest request) throws IOException {
        accountService.create(request);
        return Response.ofSucceeded();
    }
}
