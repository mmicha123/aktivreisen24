DROP TABLE if EXISTS ar_account CASCADE;
DROP TABLE if EXISTS ar_role CASCADE;
DROP TABLE if EXISTS ar_account_role CASCADE;
DROP TABLE if EXISTS ar_account_data CASCADE;

DROP TABLE if EXISTS ar_user CASCADE;
DROP TABLE if EXISTS ar_provider CASCADE;

DROP TABLE if EXISTS ar_activity CASCADE;
DROP TABLE if EXISTS ar_vacation CASCADE;
DROP TABLE if EXISTS ar_av_compatibility CASCADE;

DROP TABLE if EXISTS ar_picture CASCADE;
DROP TABLE if EXISTS ar_comments CASCADE;
DROP TABLE if EXISTS ar_commentsupertable CASCADE;

CREATE TABLE ar_account
(
    acc_id      Serial,
    acc_data_id Integer,
    passhash    text,
    email       varchar(320),
    PRIMARY KEY (acc_id),
    foreign KEY (acc_data_id) references ar_account_data (acc_data_id)
);

CREATE TABLE ar_role
(
    role_id   Serial,
    role_name text,
    PRIMARY KEY (role_id)
);

CREATE TABLE ar_account_role
(
    ar_id   Serial,
    acc_id  Integer not null,
    role_id Integer not null,
    PRIMARY KEY (ar_id),
    foreign KEY (acc_id) references ar_account (acc_id),
    foreign KEY (role_id) references ar_role (role_id)
);

CREATE TABLE ar_account_data
(
    acc_data_id Serial,
    first_name  text not null,
    last_name   text not null,
    phone       Integer,
    address     text,
    coutry      text,
    PRIMARY KEY (acc_data_id)
);


CREATE TABLE ar_user
(
    user_id    Serial,
    acc_id     Integer not null,
    first_name text    not null,
    last_name  text    not null,
    phone      Integer,
    address    text,
    coutry     text,
    PRIMARY KEY (user_id),
    foreign KEY (acc_id) references ar_account (acc_id) on delete cascade
);

CREATE TABLE ar_provider
(
    provider_id Serial,
    acc_id      Integer not null,
    name        text    not null,
    rating      real,
    PRIMARY KEY (provider_id),
    foreign KEY (acc_id) references ar_account (acc_id)
);


CREATE TABLE ar_commentsupertable
(
    id Serial,
    primary key (id)
);

CREATE TABLE ar_comments
(
    comment_id Serial,
    super_id   integer not null,
    comment    text    not null,
    PRIMARY KEY (comment_id),
    foreign KEY (super_id) references ar_commentsupertable (id)
);

CREATE TABLE ar_vacation
(
    vacation_id Serial,
    owner_id    Integer not null,
    address     text,
    zip         Integer,
    city        text,
    country     text,
    price       real    not null check (price > 0),
    rating      real,
    best_season text,
    comment_id  Integer,
    PRIMARY KEY (vacation_id),
    foreign KEY (owner_id) references ar_provider (provider_id),
    foreign KEY (comment_id) references ar_commentsupertable (id)
);

CREATE TABLE ar_activity
(
    activity_id Serial,
    owner_id    Integer not null,
    price       real    not null check (price > 0),
    rating      real,
    description text,
    category    text,
    needEquip   text,
    amt_people  Integer check (amt_people > 0),
    picture_id  Integer,
    comment_id  Integer,
    PRIMARY KEY (activity_id),
    foreign KEY (owner_id) references ar_provider (provider_id),
    foreign KEY (comment_id) references ar_commentsupertable (id)
);

CREATE TABLE ar_av_compatibility
(
    vacation_id Integer not null,
    activity_id Integer not null,
    PRIMARY key (vacation_id, activity_id),
    foreign KEY (vacation_id) references ar_vacation (vacation_id) on delete cascade,
    foreign KEY (activity_id) references ar_activity (activity_id) on delete cascade
);



CREATE TABLE ar_picture
(
    picture_id Serial,
    url        text not null UNIQUE,
    PRIMARY KEY (picture_id)
);


--INSERT INTO ar_commentsupertable values(DEFAULT);

insert into ar_role(role_name)
VALUES ('Admin');
insert into ar_role(role_name)
VALUES ('User');
insert into ar_role(role_name)
VALUES ('Provider');


