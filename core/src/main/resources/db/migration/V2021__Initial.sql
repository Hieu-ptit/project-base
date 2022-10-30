create table account
(
    id         serial8     not null
        constraint account_pkey
            primary key,
    name       varchar(20) not null,
    metadata   jsonb,
    company    jsonb,
    boxes      jsonb,
    created_at timestamptz default CURRENT_TIMESTAMP,
    updated_at timestamptz default CURRENT_TIMESTAMP
);

create table company
(
    id   serial8     not null
        constraint company_pkey
            primary key,
    name varchar(20) not null
);