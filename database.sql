DROP table if EXISTS ar_account CASCADE;
DROP table if EXISTS ar_role CASCADE;
DROP table if EXISTS ar_account_role CASCADE;
DROP table if exists ar_account_data CASCADE;

DROP table if EXISTS ar_user CASCADE;
DROP table if EXISTS ar_provider CASCADE;

DROP table if EXISTS ar_activity CASCADE;
DROP table if EXISTS ar_vacation CASCADE;
drop table if exists ar_av_compatibility CASCADE;

drop table if exists ar_picture cascade;
drop table if exists ar_comments cascade;
drop table if exists ar_commentsupertable cascade;

create table ar_account(
	acc_id 		Serial,
	passhash	text,
	email		varchar(320),
	primary KEY(acc_id)
);

create table ar_role(
	role_id		Serial,
	role_name	text,
	primary KEY(role_id)
);

create table ar_account_role(
	ar_id		Serial,
	acc_id		Integer not null,
	role_id		Integer not null,
	primary KEY(ar_id),
	foreign KEY(acc_id) references ar_account(acc_id),
	foreign KEY(role_id) references ar_role(role_id)
);

create table ar_account_data(
	acc_data_id	Serial,
	first_name  text not null,
	last_name 	text not null,
	phone		Integer,
	address 	text,
	coutry		text,
	primary KEY(acc_data_id)
);


create table ar_user(
	user_id 	Serial,
	acc_id		Integer not null,
	first_name  text not null,
	last_name 	text not null,
	phone		Integer,
	address 	text,
	coutry		text,
	primary KEY(user_id),
	foreign KEY(acc_id) references ar_account(acc_id) on delete cascade
);

create table ar_provider(
	provider_id Serial,
	acc_id		Integer not null,
	name		text not null,
	rating		real,
	primary KEY(provider_id),
	foreign KEY(acc_id) references ar_account(acc_id)
);


create table ar_commentsupertable(
	id 				Serial,
	primary key(id)
);

create table ar_comments(
	comment_id		Serial,
	super_id		integer	not null,
	comment 		text 	not null,
	primary KEY(comment_id),
	foreign KEY(super_id) references ar_commentsupertable(id)
);

create table ar_vacation(
	vacation_id Serial,
	owner_id	Integer not null,
	address 	text,
	zip 		Integer,
	city 		text,
	country		text,
	price 		real not null check (price > 0),
	rating		real,
	best_season	text,
	comment_id	Integer,
	primary KEY(vacation_id),
	foreign KEY(owner_id) references ar_provider(provider_id),
	foreign KEY(comment_id) references ar_commentsupertable(id)
);

create table ar_activity(
	activity_id Serial,
	owner_id	Integer not null,
	price		real	not null check (price > 0),
	rating		real,
	description text,
	category	text,
	needEquip	text,
	amt_people	Integer check (amt_people > 0),
	picture_id	Integer,
	comment_id	Integer,
	primary KEY(activity_id),
	foreign KEY(owner_id) references ar_provider(provider_id),
	foreign KEY(comment_id) references ar_commentsupertable(id)
);

create table ar_av_compatibility(
	vacation_id Integer not null,
	activity_id	Integer not null,
	primary key(vacation_id, activity_id),
	foreign KEY(vacation_id) references ar_vacation(vacation_id) on delete cascade,
	foreign KEY(activity_id) references ar_activity(activity_id) on delete cascade
);



create table ar_picture(
	picture_id		Serial,
	url				text not null UNIQUE,
	primary KEY(picture_id)
);


--INSERT INTO ar_commentsupertable values(DEFAULT);

insert into ar_role(role_name) VALUES('User');
insert into ar_role(role_name) VALUES('Admin');
insert into ar_role(role_name) VALUES('Provider');


