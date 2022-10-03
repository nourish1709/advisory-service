create table advisor
(
    id         serial primary key,
    email      text not null,
    username   text unique,
    first_name text not null,
    last_name  text not null,
    role       text not null
);

create table applicant
(
    id         serial primary key,
    email      text not null,
    username   text unique,
    first_name text not null,
    last_name  text not null,
    ssn        text not null,
    city       text,
    street     text,
    number     text,
    zip        text,
    apt        text
);

create table phone_number
(
    applicant_id bigint references applicant (id),
    value        text not null,
    type         text not null
);


create table application
(
    id              serial primary key,
    advisor_id      bigint references advisor (id),
    applicant_id    bigint references applicant (id),
    amount_of_money decimal not null,
    status          text    not null,
    created_at      date    not null default now(),
    assigned_at     date,
    resolved_at     date
);