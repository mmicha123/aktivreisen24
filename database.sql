DROP TABLE if EXISTS ar_account CASCADE;
DROP TABLE if EXISTS ar_role CASCADE;
DROP TABLE if EXISTS ar_account_role CASCADE;
DROP TABLE if EXISTS ar_account_data CASCADE;


DROP TABLE if EXISTS ar_activity CASCADE;
DROP TABLE if EXISTS ar_vacation CASCADE;
DROP TABLE if EXISTS ar_av_compatibility CASCADE;

DROP TABLE if EXISTS ar_pictures CASCADE;
DROP TABLE if EXISTS ar_comments CASCADE;
DROP TABLE if EXISTS ar_commentsupertable CASCADE;


CREATE TABLE ar_account
(
    acc_id      Serial,
    passhash    text,
    email       varchar(320),
    PRIMARY KEY (acc_id)
);

CREATE TABLE ar_role
(
    role_id   Serial,
    role_name text,
    PRIMARY KEY (role_id)
);

CREATE TABLE ar_account_role
(
    acc_id  Integer not null,
    role_id Integer not null,
    PRIMARY KEY (acc_id, role_id),
    foreign KEY (acc_id) references ar_account (acc_id) ON DELETE CASCADE,
    foreign KEY (role_id) references ar_role (role_id)
);

CREATE TABLE ar_account_data
(
    acc_data_id Serial,
    acc_id      Integer not null,
    first_name  text not null,
    last_name   text not null,
    phone       Integer,
    address     text,
    country     text,
    PRIMARY KEY (acc_data_id),
    FOREIGN KEY (acc_id) REFERENCES ar_account(acc_id) ON DELETE CASCADE
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
    foreign KEY (owner_id) references ar_account (acc_id),
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
    need_equip   text,
    amt_people  Integer check (amt_people > 0),
    comment_id  Integer,
    PRIMARY KEY (activity_id),
    foreign KEY (owner_id) references ar_account (acc_id),
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



CREATE TABLE ar_pictures
(
    activity_id Integer not null,
    url         text not null UNIQUE,
    PRIMARY KEY (activity_id, url),
    FOREIGN KEY (activity_id) REFERENCES ar_activity(activity_id) on delete cascade
);


--INSERT INTO ar_commentsupertable values(DEFAULT);

insert into ar_role(role_name)
VALUES ('Admin');
insert into ar_role(role_name)
VALUES ('User');
insert into ar_role(role_name)
VALUES ('Provider');

INSERT INTO ar_account(passhash, email) VALUES('mememadsdaffs', 'memem@meme.de');
INSERT INTO ar_account(passhash, email) VALUES('testdadssafads', 'memem@1meme.de');

INSERT INTO ar_account_role (acc_id, role_id) VALUES (1, (SELECT role_id FROM ar_role WHERE role_name = 'Admin'));
INSERT INTO ar_account_role (acc_id, role_id) VALUES (2, (SELECT role_id FROM ar_role WHERE role_name = 'Provider'));

SELECT ar_account.acc_id, passhash, email, role_name FROM ar_account
    JOIN ar_account_role aar on ar_account.acc_id = aar.acc_id
    JOIN ar_role ar on aar.role_id = ar.role_id WHERE ar_account.acc_id = 2;

SELECT ar_account.acc_id, passhash, email, role_name FROM ar_account
    JOIN ar_account_role aar on ar_account.acc_id = aar.acc_id
    JOIN ar_role ar on aar.role_id = ar.role_id WHERE ar_account.passhash = 'testdadssafads' and ar_account.email = 'memem@1mem.de';


INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season) VALUES (2, 'adsadsad', 1337, 'afgswqd', 'meme', 3.50, 2.5, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season) VALUES (2, 'rwsfdf', 124, 'afgswqd', 'meme', 5.50, 5.0, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season) VALUES (2, 'hgkgj', 4363, 'afgswqd', 'meme', 8.50, 1.3, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season) VALUES (2, 'rzt', 658542, 'afgswqd', 'meme', 1.50, 4.14, 'summer');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people) VALUES (2, 3.5, 2.5, 'memeasdadsada', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people) VALUES (2, 3.5, 2.5, 'asfaas', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people) VALUES (2, 3.5, 2.5, 'memeaszt54dadsada', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people) VALUES (2, 3.5, 2.5, 'dskakdasksaksaksda', 'test', 'ski', 4);


INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (1, 1);
INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (1, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (1, 4);

INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (2, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (2, 3);

SELECT * FROM ar_activity INNER JOIN ar_av_compatibility aac on ar_activity.activity_id = aac.activity_id WHERE aac.vacation_id = 1;