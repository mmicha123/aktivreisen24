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
    acc_id   Serial,
    passhash text,
    email    varchar(320),
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
    first_name  text    not null,
    last_name   text    not null,
    phone       Bigint,
    address     text,
    country     text,
    PRIMARY KEY (acc_data_id),
    FOREIGN KEY (acc_id) REFERENCES ar_account (acc_id) ON DELETE CASCADE
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
    title       text,
    address     text,
    zip         Integer,
    city        text,
    country     text,
    price       real    not null check (price > 0),
    rating      real,
    best_season text,
    comment_id  Integer,
    picture_url text,
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
    need_equip  text,
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
    url         text    not null,
    PRIMARY KEY (activity_id, url),
    FOREIGN KEY (activity_id) REFERENCES ar_activity (activity_id) on delete cascade
);


--INSERT INTO ar_commentsupertable values(DEFAULT);

insert into ar_role(role_name)
VALUES ('Admin');
insert into ar_role(role_name)
VALUES ('User');
insert into ar_role(role_name)
VALUES ('Provider');

---EXAMPLES
BEGIN;

INSERT INTO ar_account(passhash, email)
VALUES ('AiH2wee4haech', 'MarcelSchwartz@cuvox.de');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (1, (SELECT role_id FROM ar_role WHERE role_name = 'Admin'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (1, 'Marcel', 'Schwartz', 02655526071, 'Buelowstrasse 78 87668 Rieden', 'Deutschland');


INSERT INTO ar_account(passhash, email)
VALUES ('OhGi3ye9', 'SandraMaier@cuvox.de');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (2, (SELECT role_id FROM ar_role WHERE role_name = 'User'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (2, 'Sandra ', 'Maier', 06211974265, 'Scharnweberstrasse 66 68229 Mannheim', 'Deutschland');


INSERT INTO ar_account(passhash, email)
VALUES ('gei4Saiyo5i', 'AndreaMoeller@einrot.com');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (3, (SELECT role_id FROM ar_role WHERE role_name = 'User'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (3, 'Andrea', 'Möller', 06335324843, 'Invalidenstrasse 35 66957 Kröppen', 'Deutschland');


INSERT INTO ar_account(passhash, email)
VALUES ('VeeThaix7', 'AnjaPfeffer@einrot.com');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (4, (SELECT role_id FROM ar_role WHERE role_name = 'Provider'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (4, 'Anja', 'Pfeffer', 06291381810, 'Grosse Praesidenten Str. 98 74259 Widdern', 'Deutschland');


INSERT INTO ar_account(passhash, email)
VALUES ('oThooghu5ae', 'ReneSchmidt@cuvox.de');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (5, (SELECT role_id FROM ar_role WHERE role_name = 'Provider'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (5, 'René', 'Schmidt', 0272462082, 'Bahnhofstrasse 145 3917 Goppenstein', 'Schweiz');


INSERT INTO ar_account(passhash, email)
VALUES ('Lie4tai8', 'DieterSchmitt@cuvox.de');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (6, (SELECT role_id FROM ar_role WHERE role_name = 'Provider'));

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (6, 'Dieter', 'Schmitt', 06201887426, 'Scharnweberstrasse 6669469 Weinheim', 'Deutschland');



INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (4, 'Pierre et Vacances Premium Julia Augusta', '137 Avenue de la Plage', 06190, 'Roquebrune-Cap-Martin',
        'Frankreich', 201, 4.4, 'Sommer',
        'https://photos.pierreetvacances.com/admin/fp2/photos/panopv/480x270/sejour-residence-premium-julia-augusta-roquebrune-cap-martin-RAL_112523_panopv.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 1 WHERE vacation_id = 1;
INSERT INTO ar_comments(super_id, comment) VALUES (1, 'Gut für den Sommerurlaub, gute Lage, aber teuer.');
INSERT INTO ar_comments(super_id, comment) VALUES (1, 'Gute Lage. Die Appartements sind modern und hell eingerichtet. Man fühlt sich wohl!');


INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (5, 'H10 Marina Barcelona | Hotel in Barcelona"s Villa Olímpica', 'Av. del Bogatell 64 68', 08005, 'Barcelona',
        'Spanien', 101, 4.3, 'Sommer',
        'https://pix10.agoda.net/hotelImages/42793/0/b59617ca4f8ddc3c3685255aee7c9065.jpg?s=1024x768');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 2 WHERE vacation_id = 2;
INSERT INTO ar_comments(super_id, comment) VALUES (2, 'Es gab keinen wie versprochenen Wellnessbereich, alles war extra zu bezahlen! Kein Whirlpool keine Sauna nichts! Tut mir Leid aber wenn man es schon vorher so groß anschreibt, dann ist mir echt unverständlich wieso es nicht inkludiert ist. Zahle ja nicht umsonst 50€ mehr als in einem anderen Hotel?!');
INSERT INTO ar_comments(super_id, comment) VALUES (2, 'Sehr freundliche Mitarbeiter, schönes und sauberes Hotelzimmer sowie eine gute Lage! Habe mich sehr wohl gefühlt und würde es weiterempfehlen!');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (4, 'Golden Tulip Porto Gaia Hotel & Spa', 'R. da Bélgica 86', 4404, 'Vila Nova de Gaia', 'Portugal', 60, 3.6,
        'Sommer', 'https://www.hotelbeds.com/giata/00/004315/004315a_hb_a_035.jpg?width=1000');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 3 WHERE vacation_id = 3;
INSERT INTO ar_comments(super_id, comment) VALUES (3, 'Sehr gebrauchtes Hotel, sauber und klasse Personal. Spa Bereich hat teils Renovierung nötig.Für den Preis aber klasse!Danke schön!');
INSERT INTO ar_comments(super_id, comment) VALUES (3, 'Etwas in die Jahre gekommenes Hotel, abseits des öffentlichen Verkehrs. Man muss ein Auto haben um es zu erreichen. Parkplatz i. O');
INSERT INTO ar_comments(super_id, comment) VALUES (3, 'Personal war sehr nett sonst sehr altes,renovationsbedurftiges Hotel mit fehlende sauberkeit. Kein Spa wie auf Bilder,Sauna hat nicht funktioniert. Nicht empfelungswert!');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (5, 'Fontainebleau Miami Beach', '4441 Collins Ave', 33140, 'Miami Beach', 'Vereinigte Staaten', 286, 4.4,
        'Sommer', 'https://media-cdn.tripadvisor.com/media/photo-s/1a/93/af/af/exterior.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 4 WHERE vacation_id = 4;
INSERT INTO ar_comments(super_id, comment) VALUES (4, 'Sehr schönes Hotel in Miami. Die Zimmer waren sehr sauber. Das Personal war sehr freundlich und höflich. Das Hotel besitzt sehr viele Poolbecken und hat eine große Auswahl an Restaurants. ');
INSERT INTO ar_comments(super_id, comment) VALUES (4, 'Es gibt viele Dj´s , die in der Szenen bekannt sind und einen den Tag am Pool entspannter machen. Als Fazit kann gesagt werden, dass es sich lohnt seinen Urlaub in diesem Hotel zu verbringen.');
INSERT INTO ar_comments(super_id, comment) VALUES (4, 'Super schön dort! Und sehr warmes Wasser. Allerdings kosten die liegen Geld Man muss für alles extra zahlen!');
INSERT INTO ar_comments(super_id, comment) VALUES (4, 'Sehr unterhaltsames Hotel. Zimmer sauber und funktional. Service freundlich und zuvorkommend. Nachteil: hohe Parkgebühren bei Langzeitaufenthalt.');


INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (4, 'Hotel Pension Villa Klothilde', 'Skiliftstraße 2', 5700, 'Zell am See', 'Österreich', 119, 4.7, 'Winter',
        'https://media-cdn.holidaycheck.com/w_1280,h_720,c_fill,q_80/ugc/images/ad98de02-59eb-4af4-9a8c-1e991355be5c');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 5 WHERE vacation_id = 5;
INSERT INTO ar_comments(super_id, comment) VALUES (5, 'Ein super schönes Hotel. Sehr sauber. Lecker Frühstück alles frisch zubereitet. Sehr nette Besitzer.');
INSERT INTO ar_comments(super_id, comment) VALUES (5, 'überaus freundlich und zuvorkommen und sehr flexibel. wunderbares frühstück in traumhaftem ambiente. zimmer mit balkon und blick auf den see - riesengross. ausgezeichnete lage.');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (4, 'Chalet Edelweiss', 'Brucker Bundesstraße 9', 5700, 'Zell am See', 'Österreich', 95, 3.8, 'Winter',
        'https://www.finest-holidays.com/objectfotos/Chalet_Edelweiss/720/Chalet_Edelweiss_ChaletEdelweissCourchevel1850_2.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 6 WHERE vacation_id = 6;
INSERT INTO ar_comments(super_id, comment) VALUES (6, 'Die Dame am Empfang hat uns sehr zuvorkommend und freundlich empfangen und uns unser Zimmer gezeigt. Das Frühstück war top, das Zimmer war sehr schön. Man uns die Fahrräder untergestellt. Alles Rundrum super!!!');
INSERT INTO ar_comments(super_id, comment) VALUES (6, 'Zell war gar nicht an unserer Reiseroute aber wegen der guten Erfahrungen letztes Jahr, nahmen wir den Umweg zur Villa Klothilde gerne in Kauf. In allen Belangen erneute Freude hier logiert zu haben. Vielen Dank an das nette Management und das Personal.');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (5, 'AlpenParks Hotel & Apartment Central', 'Brucker Bundesstraße 12', 5700, 'Zell am See', 'Österreich', 131,
        4.1, 'Winter', 'https://media-cdn.tripadvisor.com/media/photo-s/0a/4d/d1/e8/aussenansicht-winter.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 7 WHERE vacation_id = 7;
INSERT INTO ar_comments(super_id, comment) VALUES (7, 'Das waren zwei wunderschöne Übernachtungen. Schon die Abwicklung mit der Buchung war sehr lobenswert und angenehm. Wir hatten mehrfach den Termin verschoben, aber immer wurde freundlich eine Lösung gesucht und gefunden. ');
INSERT INTO ar_comments(super_id, comment) VALUES (7, 'überaus freundlich und zuvorkommen und sehr flexibel. wunderbares frühstück in traumhaftem ambiente. zimmer mit balkon und blick auf den see - riesengross. ausgezeichnete lage.');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (5, 'Hotel Salzburgerhof', 'Auerspergstraße 11', 5700, 'Zell am See', 'Österreich', 342, 4.6, 'Winter, Sommer',
        'https://media-cdn.holidaycheck.com/w_1280,h_720,c_fill,q_80/ugc/images/eb5d84cb-36d7-4ee5-903e-c29052974df5');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 8 WHERE vacation_id = 8;
INSERT INTO ar_comments(super_id, comment) VALUES (8, 'überaus freundlich und zuvorkommen und sehr flexibel. wunderbares frühstück in traumhaftem ambiente. zimmer mit balkon und blick auf den see - riesengross. ausgezeichnete lage.');
INSERT INTO ar_comments(super_id, comment) VALUES (8, 'Einfaches gutes Hotel. Direkt am Meer. Die Zimmer sind einfach gehalten. Es gibt kein Tresor oder Getränke im Zimmer auch keine Minibar. Das Frühstück ist sehr übersichtlich und einfach. ');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (4, 'Krymwood Flats Wynwood', '145 NW 29th St', 33127, 'Miami', 'Vereinigte Staaten', 109, 3.4, 'Sommer',
        'https://media-cdn.tripadvisor.com/media/vr-splice-j/05/e5/07/a6.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 9 WHERE vacation_id = 9;
INSERT INTO ar_comments(super_id, comment) VALUES (9, 'Personal war sehr nett sonst sehr altes,renovationsbedurftiges Hotel mit fehlende sauberkeit. Kein Spa wie auf Bilder,Sauna hat nicht funktioniert. Nicht empfelungswert!');
INSERT INTO ar_comments(super_id, comment) VALUES (9, 'Gut für den Sommerurlaub, gute Lage, aber teuer.');

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (5, 'Hotel Catalonia Barcelona 505', 'Carrer de Muntaner 505', 08022, 'Barcelona', 'Spanien', 108, 4.1,
        'Frühling', 'https://z.cdrst.com/foto/hotel-sf/d77/granderesp/catalonia-barcelona-505-exterior-85f5b93.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_vacation SET comment_id = 10 WHERE vacation_id = 10;
INSERT INTO ar_comments(super_id, comment) VALUES (10, 'Ein super schönes Hotel. Sehr sauber. Lecker Frühstück alles frisch zubereitet. Sehr nette Besitzer.');
INSERT INTO ar_comments(super_id, comment) VALUES (10, 'Gute Lage. Die Appartements sind modern und hell eingerichtet. Man fühlt sich wohl!');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 59, 4.6,
        'mit Tourbegleiter (Raftguide) im separatem Boot, jede Gruppe unter sich im Boot für 3-5 Personen, 2er Kanu oder 1er Sit on Top Boot. Die leichte Wildwassertour bei Lenggries mit der Wildwasserstelle „Isarburg“ ist die interessanteste Isar-Tour die jeder Teilnehmer schafft. Täglich 12 Uhr bei ausreichend Teilnehmerzahl ab € 59,- pPers.',
        'Rafting', 'Badehose', 5);

INSERT INTO ar_pictures(activity_id, url) VALUES (1, 'https://i0.wp.com/www.map-erlebnis.de/wp-content/uploads/2019/05/1111.jpg?fit=1024%2C576&ssl=1');
INSERT INTO ar_pictures(activity_id, url) VALUES (1, 'https://i0.wp.com/www.map-erlebnis.de/wp-content/uploads/2019/01/P1000053-2.jpg?fit=1000%2C750&ssl=1');
INSERT INTO ar_pictures(activity_id, url) VALUES (1, 'https://i2.wp.com/www.map-erlebnis.de/wp-content/uploads/2019/01/ra2.jpg?fit=1024%2C784&ssl=1');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 11 WHERE activity_id = 1;
INSERT INTO ar_comments(super_id, comment) VALUES (11, 'An sich schöne Rafting Tour die interessant gestaltet wurde. Personal an sich nett, nur manche Kommentare waren sehr unangebracht. Ich versteh viel Spaß, aber man kennt sich einfach nicht wirklich. Als Kunde, der dafür bezahlt, möchte ich einfach nicht so angeredet werden.');
INSERT INTO ar_comments(super_id, comment) VALUES (11, 'War ein super Action Tag.  Super Team  die uns durch die Fluten der Saalach führten. Hatten richtig viel Spaß gemacht. Kann man nur weiter Empfehlen.');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 84.50, 4.2,
        'Schraube deine Erwartungen in Sachen Geschwindigkeit und Fahrspaß ruhig in die Höhe: Sie werden trotzdem bei weitem übertroffen. Auch ohne Bootsführerschein musst du auf das Jetski fahren nicht verzichten. Denn mit einem ausgebildeten Instruktor darfst du natürlich auch selbst ans Steuer. Wirf die Turbine an und düse mit dem Jetski übers Wasser!',
        'Jetski', 'Bootführerschein, Badehose', 2);

INSERT INTO ar_pictures(activity_id, url) VALUES (2, 'https://media.gettyimages.com/photos/powerboat-racing-at-high-speed-picture-id110052804?s=2048x2048');
INSERT INTO ar_pictures(activity_id, url) VALUES (2, 'https://media.gettyimages.com/photos/motorboat-picture-id171572077?s=2048x2048');
INSERT INTO ar_pictures(activity_id, url) VALUES (2, 'https://media.gettyimages.com/photos/white-fast-speedboat-picture-id173931262?s=2048x2048');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 12 WHERE activity_id = 2;
INSERT INTO ar_comments(super_id, comment) VALUES (12, 'Wir haben uns ein Jetski über das Wochenende ausgeliehen und hatten super viel spaß damit. Der Kontakt war sehr nett und wir hatten keinerlei Probleme. Gerne wieder !');
INSERT INTO ar_comments(super_id, comment) VALUES (12, 'Super Kontakt, super Service, hat Spaß gemacht! Wird auf jeden Fall wiederholt!');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 84.5, 3.2,
        'Du hast keine Lust auf eine stinknormale Fahrradtour? Dann ist dieses Fahrraderlebnis mit Schifffahrt und Picknick genau das richtige für dich. Leihe dir morgens ein Fahrrad aus und radel direkt den Fluss entlang drauf los. Fahre über Stock, Stein und mit der Schifffahrt auch über das Wasser und erkunde die atemberaubende Landschaft auf zwei Rädern. Breite für die kleine Verschnaufpause zwischendurch die Picknickdecke aus und besorge dir den nötigen Energiekick mit den zahlreichen Köstlichkeiten aus eurem Picknickkorb. Damit hast du genug Power für die weitere Strecke! Die perfekte Abwechslung zu normalen Radtouren! Schwing dich auf dein Fahrräder und trete in die Pedale.',
        'Radtour', ' ', 10);

INSERT INTO ar_pictures(activity_id, url) VALUES (3, 'https://www.radweg-reisen.com/sites/default/files/styles/tour_teaser_800x600/public/media/image/file/die_kurze_weser_radtour_1_1_1.jpg?itok=vMjTU4Zu');
INSERT INTO ar_pictures(activity_id, url) VALUES (3, 'https://www.olbernhau.de/sites/default/files/styles/titelbild_gross/public/uploads/bilder/inhalte/2018-02/xdsc_6112-1519078663.jpg?itok=2XIyMEJP&c=dda1181bf430fb6c549b7ee29da3be6e');
INSERT INTO ar_pictures(activity_id, url) VALUES (3, 'https://www.blick.de/DYNIMG/radfreunde-treffen-sich-zur-olbernhauer-radtour/WFraYGwB_z4sT0diVrz5/40/55/6914055_M650x433C916x611o99x0.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 13 WHERE activity_id = 3;
INSERT INTO ar_comments(super_id, comment) VALUES (13, 'Sehr coole und spannende Stadttour der etwas anderen Art! Danke an unseren Guide, Michael, für die tollen Insights! Auf jeden Fall empfehlenswert!');
INSERT INTO ar_comments(super_id, comment) VALUES (13, 'Danke für die Radtour-Empfehlungen!');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 110, 3.3,
        'Skitouren sind die ideale Alternative für alle, die genug vom Massentourismus haben. Denn auf einer Skitour erwartet dich statt langer Liftschlangen und überfüllter Pisten märchenhafte Winterlandschaften, einsame Täler, verschneite Gipfel, unberührte Hänge und unvergessliche Tiefschneeabfahrten.',
        'Skitour', 'Skiausrüstung', 3);

INSERT INTO ar_pictures(activity_id, url) VALUES(4, 'https://images.unsplash.com/photo-1551698618-1dfe5d97d256?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(4, 'https://images.unsplash.com/photo-1551524559-8af4e6624178?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1226&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(4, 'https://images.unsplash.com/photo-1505873346750-69ce3daeb225?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 14 WHERE activity_id = 4;
INSERT INTO ar_comments(super_id, comment) VALUES (14, 'Berge sind immer schön!');
INSERT INTO ar_comments(super_id, comment) VALUES (14, 'Unterm Strich große Berge. Anstrengend da hoch zu laufen, dann doch lieber wieder Flachland.');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 25, 3.9,
        'Beschreite ganz neue Wege und besuche städtische Attraktionen, atemberaubende Sehenswürdigkeiten, Kultstätten und Szenetreffs. Bei diesen Sightseeing-Touren ist für jeden etwas dabei',
        'Sightseeing', 'Verpflegung', 25);

INSERT INTO ar_pictures(activity_id, url) VALUES(5, 'https://images.unsplash.com/photo-1415125721330-116ba2f81152?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(5, 'https://images.unsplash.com/photo-1536061335097-c267bd2e4ed7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1351&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(5, 'https://images.unsplash.com/photo-1556408001-287a5db9c67e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 15 WHERE activity_id = 5;
INSERT INTO ar_comments(super_id, comment) VALUES (15, 'Mit dem kleinen Bus durch die Altstadt kann ich nicht empfehlen . Meine Frau hatte ein Sitzplatz , von dem aus  konnte man nur zu einer Seite heraus schauen . Die Scheiben waren beschlagen , und mir persönlich war alles zu eng ');
INSERT INTO ar_comments(super_id, comment) VALUES (15, 'Super Touren zum ersten Eindruck der Stadt! Wichtig: Die Tickets müssen am Startpunkt ausgedruckt werden, auch wenn sie online bezogen worden sein sollten. Sonst sind sie NICHT gültig. Dafür sind sie ab dann aber für den ÖPNV als Karte nutzbar');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 59, 4.4,
        'description:		Beim Rennbob fahren, Rodeln und Wok fahren geht im Eiskanal ganz schön die Post ab. Dabei erreichst du beim Rennbob fahren Spitzengeschwindigkeiten von weit über 100 km/h',
        'Rennbob/Rodeln', 'Winterbekleidung', 3);

INSERT INTO ar_pictures(activity_id, url) VALUES(6, 'https://images.unsplash.com/photo-1577651508699-e444bf8e5868?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=642&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(6, 'https://images.unsplash.com/photo-1465220183275-1faa863377e3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1267&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 16 WHERE activity_id = 6;
INSERT INTO ar_comments(super_id, comment) VALUES (16, 'Wir sind hier nach unserem Spaziergang durch den Wald auf ein Getränk sowie Würstchen und Gulaschsuppe eingekehrt. Die Hütte ist sehr gemütlich, der Service freundlich und die Speisen geschmacklich und preislich in Ordnung. Runter gings dann mit der Rodel.');
INSERT INTO ar_comments(super_id, comment) VALUES (16, 'Rodelhütte Geschlossen um 18.30 Uhr bei Vollmond. Rodelbahn hervoragend');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 49, 2.7,
        'Der innovative Tenniskurs "Fast Learning" für Schnelleinsteiger wird dich mit raschen Spiel- und Erfolgserlebnissen begeistern. In nur 10 Trainingseinheiten erlangst du die "Platzreife" und bist damit bestens für ein Tennis-Match gewappnet. Das Wichtige dabei: Du brauchst weder eine Vereinsmitgliedschaft, noch gilt ein spezieller Dresscode. Alles was du mitbringen musst ist die Freude am Spiel. Passend dazu liegt der Fokus der ersten Kurseinheiten auf spielerischen Praxisübungen. Zieh in Zukunft andere Seiten auf und leg beim Erlernen des Tennisspiels den Turbo ein!',
        'Tennis', 'Sportbeleidung', 3);

INSERT INTO ar_pictures(activity_id, url) VALUES(7, 'https://images.unsplash.com/photo-1545151414-8a948e1ea54f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(7, 'https://images.unsplash.com/photo-1544298621-35a764866ff0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=650&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(7, 'https://images.unsplash.com/photo-1544298621-77b4ec9c2903?ixlib=rb-1.2.1&auto=format&fit=crop&w=669&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 17 WHERE activity_id = 7;
INSERT INTO ar_comments(super_id, comment) VALUES (17, 'Gepflegte Anlage mit guten Plätzen, zudem nette Mitglieder und eine schöne Gastronomie 👍');
INSERT INTO ar_comments(super_id, comment) VALUES (17, 'Tolle Tennisanlage mit schöner Gastronomie.');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 99, 4.8,
        'Wenn du gerne auf vier Rädern durch unebenes Gelände düst, dann ist eine Quadtour genau das Richtige für dich! Die wendigen Fahrzeuge können fast jedes Terrain problemlos meistern und lassen dich mit Highspeed durch Wald und Unterholz brettern oder gemütlich die Straße entlang cruisen.',
        'Quad', 'festes Schuhwerk', 5);

INSERT INTO ar_pictures(activity_id, url) VALUES(8, 'https://images.unsplash.com/photo-1489731300081-a03b0ce82303?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(8, 'https://images.unsplash.com/photo-1489731254138-5401fb834d9c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(8, 'https://images.unsplash.com/photo-1504385756874-65fd52ffc770?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 18 WHERE activity_id = 8;
INSERT INTO ar_comments(super_id, comment) VALUES (18, 'Tolles Erlebnis. Cooler Typ, fachkundige Einweisung und eine sehr, sehr schöne Tour durch die reizvolle Landschaft. Empfehlenswert!');
INSERT INTO ar_comments(super_id, comment) VALUES (18, 'Sehr gut alles top aber die Quads sind nicht so toll sehr nette Besitzer');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 220, 4.1,
        'Meditativ, erfrischend und erhebend – Ballonfahren ist pure Magie! Du kletterst in den Korb, spürst das kräftige Fauchen des Gasbrenners und die Vorfreude auf diesen ganz besonderen Ausflug. Der Moment, wenn du vom Boden abhebst, ist einfach beflügelnd! Hoch über der Erde lässt du dich dann vom Wind durch die Lüfte tragen und schwebst buchstäblich im Himmel. Von dort bewunderst du die wundervollen Landschaften unter dir und genießt traumhafte Ausblicke auf glitzernde Seen, bunte Mischwälder und schnuckelige Dörfer!',
        'Heißluftballonfahrt', 'Windfeste Kleidung', 4);

INSERT INTO ar_pictures(activity_id, url) VALUES(9, 'https://images.unsplash.com/photo-1565641385533-5458b10c63b2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1361&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(9, 'https://images.unsplash.com/photo-1545334911-ed690179a8a4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(9, 'https://images.unsplash.com/photo-1560025404-9395caba4f06?ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 19 WHERE activity_id = 9;
INSERT INTO ar_comments(super_id, comment) VALUES (19, 'Großartiges Erlebnis. Mit Herrn Haese kann man sich keinen besseren Piloten wünschen, Sicherheit steht an erster Stelle, wir haben uns jederzeit sehr gut aufgehoben gefühlt. Außerdem merkt man, das man hier zu Gast ist bei jemandem, der seine Begeisterung mit größter Leidenschaft teilt, jede Menge Humor mitbringt und sich viel Zeit lässt um es für alle mitfahrenden ein tolles Erlebnis werden zu lassen. Und er beantwortet alle Fragen, auch die, die er bestimmt schon hunderte Male gestellt bekommen hat, mit viel Charme und Witz. Fazit: Absolut Empfehlenswert!');
INSERT INTO ar_comments(super_id, comment) VALUES (19, 'Wir haben eine wunderschöne Ballonfahrt erlebt. Herr Haese macht auf uns einen kompetenten Eindruck - ferner wurden wir in den Auf- und Abbau des Ballons einbezogen. Der Höhepunkt war die Taufe mit Urkunde! Ein Erlebnis, was man so schnell nicht vergisst!');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 18, 3.9,
        'Du spürst das Leben, wenn du beim Klettern an der Steilwand hängst und dir jeder Zentimeter Höhengewinn alles abverlangt. Körper und Psyche sind nur auf eines fixiert: den nächsten Griff, den nächsten Schritt. Und warum tust du das hier? Weil dort oben die Freiheit wartet',
        'Klettern', ' ', 5);

INSERT INTO ar_pictures(activity_id, url) VALUES(10, 'https://images.unsplash.com/photo-1545212586-f25d3631b77f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1347&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(10, 'https://img1.klettern.de/kl-klettern-ein-grad-schwerer-sarah-klettert-teaser-n-16-12-23-Waldau-Edelrid-Ohm-127-jpg--169FullWidth-385ec5b3-1597245.jpg');
INSERT INTO ar_pictures(activity_id, url) VALUES(10, 'https://img1.klettern.de/kl-klettern-ein-grad-schwerer-sechster-grad-19-04-10-Waldau-mit-Philippe299-copy-jpg--inlineImageCOdc-5f1ec598-1597249.jpg');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 20 WHERE activity_id = 10;
INSERT INTO ar_comments(super_id, comment) VALUES (20, 'Gut zu erreichen mit dem Auto man kann gut Parken. Personal war nett. Preis war okay. Von der Schwierigkeit ist für jeden was dabei. Man kann sich dort Schuhe ausleihen diese sind auch voll okay');
INSERT INTO ar_comments(super_id, comment) VALUES (20, 'Super freundliches Personal. Sehr gute Einweisung und echt tolle Kletterparcour für jedes Niveau. Kommen gerne wieder');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 49, 4.2,
        'Nimm dir die Freiheit neue Welten zu entdecken! Hol tief Luft und tauche ein in faszinierende Unterwasserwelten und gehe den Geheimnissen der Tiefe auf den Grund.',
        'Tauchen', ' ', 2);

INSERT INTO ar_pictures(activity_id, url) VALUES(11, 'https://images.unsplash.com/photo-1539498508910-091b5e859b1d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=666&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(11, 'https://images.unsplash.com/photo-1564381564020-17161124fff1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(11, 'https://images.unsplash.com/photo-1544551763-92ab472cad5d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 21 WHERE activity_id = 11;
INSERT INTO ar_comments(super_id, comment) VALUES (21, 'Ich habe den SSI—-Theorie-Kurs „Science of Diving“ hier gebucht und abgeschlossen. Von der ersten Kontaktaufnahme bis zum Abschluss des Kurses hat alles super geklappt. Die Organisation und Kommunikation war sehr gut. Lothar hat sich viel Zeit genommen um offene Fragen und auch viele andere Themen rund um das Tauchen mit mir zu besprechen. Ich kann eine uneingeschränkte Empfehlung aussprechen. Danke Lothar für den Unterricht.');
INSERT INTO ar_comments(super_id, comment) VALUES (21, 'Super Tauchschule. Kann ich nur weiterempfehlen. Wir haben uns sehr wohl gefühlt. Es wurde alles sehr gut vermittelt und auf einen individuell eingegangen. Das macht Spaß auf mehr. Wir werden sicherlich weiter machen.');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (6, 69, 4.9,
        'Treib deine Wanderlust auf die Spitze, komm auf die Beine und erklimm den Gipfel der Wander-Freude! Ob im Schnee, mit Huskies, auf Pferden, über Weinberge oder Flussbetten – bei diesen Frischluft-Highlights ist die atemberaubende Natur dein stetiger Begleiter.',
        'Wandern', 'Wanderkleidung, Schuhe', 46);

INSERT INTO ar_pictures(activity_id, url) VALUES(12, 'https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(12, 'https://images.unsplash.com/photo-1519607482862-4e767329ce8b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80');
INSERT INTO ar_pictures(activity_id, url) VALUES(12, 'https://images.unsplash.com/19/nomad.JPG?ixlib=rb-1.2.1&auto=format&fit=crop&w=1288&q=80');

INSERT INTO ar_commentsupertable VALUES (DEFAULT);
UPDATE ar_activity SET comment_id = 22 WHERE activity_id = 12;
INSERT INTO ar_comments(super_id, comment) VALUES (22, 'Sehr schöner, abwechslungsreicher Wanderweg. Sehr zu empfehlen');
INSERT INTO ar_comments(super_id, comment) VALUES (22, 'Ein Ort mit viel Natur und sehr ruhig.');

INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 11);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 7);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 5);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 10);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 9);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 11);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 3);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (3, 5);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (3, 3);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (3, 1);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (3, 10);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (4, 1);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (4, 11);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (4, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (4, 3);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (5, 4);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (5, 6);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (5, 12);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (5, 10);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (6, 5);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (6, 3);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (6, 6);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (6, 7);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (7, 12);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (7, 10);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (7, 4);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (7, 6);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (8, 12);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (8, 9);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (8, 4);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (8, 1);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (9, 7);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (9, 8);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (9, 5);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (9, 11);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (10, 5);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (10, 3);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (10, 10);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (10, 2);
COMMIT;

/*
---TESTS----

INSERT INTO ar_account(passhash, email)
VALUES ('password', 'email');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (1, (SELECT role_id FROM ar_role WHERE role_name = 'Admin'));
--INSERT INTO ar_account_role (acc_id, role_id) VALUES (hier id, (SELECT role_id FROM ar_role WHERE role_name = hier rolle));
--ids as many as accounts added..   (1 is the id)
--select between Admin, User, Provider

INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country)
VALUES (1, 'FIRSTNAME', 'LASTNAME', 13777213, 'street num zip and city', 'country');
--ids as many as accounts added..   (1 is the id) like in account_role

INSERT INTO ar_vacation(owner_id, title, address, zip, city, country, price, rating, best_season,
                        picture_url)
VALUES (1, 'TITLE', 'street and num', 44001, 'CITY', 'COUNTRY', 4.5, 1.4, 'BEST SEASON', 'URL PICTURE');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES(1, 4.5, 1.4, 'DESCRIPTION', 'CATEGORY', 'DEEDED EQUIP', 4);

-- id is the account id of the owner
-- price and rating in dot notation
-- only change after VALUES!!

INSERT INTO ar_av_compatibility(vacation_id, activity_id) VALUES (1, 2);
-- ids of vacation and activity can be (1, 1) (1, 2) ... (2, 1)

---END TEST----



INSERT INTO ar_account(passhash, email)
VALUES ('mememadsdaffs', 'memem@meme.de');
INSERT INTO ar_account(passhash, email)
VALUES ('testdadssafads', 'memem@1meme.de');

INSERT INTO ar_account_role (acc_id, role_id)
VALUES (1, (SELECT role_id FROM ar_role WHERE role_name = 'Admin'));
INSERT INTO ar_account_role (acc_id, role_id)
VALUES (2, (SELECT role_id FROM ar_role WHERE role_name = 'Provider'));

SELECT ar_account.acc_id, passhash, email, role_name
FROM ar_account
         JOIN ar_account_role aar on ar_account.acc_id = aar.acc_id
         JOIN ar_role ar on aar.role_id = ar.role_id
WHERE ar_account.acc_id = 2;

SELECT ar_account.acc_id, passhash, email, role_name
FROM ar_account
         JOIN ar_account_role aar on ar_account.acc_id = aar.acc_id
         JOIN ar_role ar on aar.role_id = ar.role_id
WHERE ar_account.passhash = 'testdadssafads'
  and ar_account.email = 'memem@1mem.de';


INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season)
VALUES (2, 'adsadsad', 1337, 'afgswqd', 'meme', 3.50, 2.5, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season)
VALUES (2, 'rwsfdf', 124, 'afgswqd', 'meme', 5.50, 5.0, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season)
VALUES (2, 'hgkgj', 4363, 'afgswqd', 'meme', 8.50, 1.3, 'summer');
INSERT INTO ar_vacation(owner_id, address, zip, city, country, price, rating, best_season)
VALUES (2, 'rzt', 658542, 'afgswqd', 'meme', 1.50, 4.14, 'summer');

INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (2, 3.5, 2.5, 'memeasdadsada', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (2, 3.5, 2.5, 'asfaas', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (2, 3.5, 2.5, 'memeaszt54dadsada', 'test', 'ski', 4);
INSERT INTO ar_activity(owner_id, price, rating, description, category, need_equip, amt_people)
VALUES (2, 3.5, 2.5, 'dskakdasksaksaksda', 'test', 'ski', 4);


INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 1);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (1, 4);

INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 2);
INSERT INTO ar_av_compatibility(vacation_id, activity_id)
VALUES (2, 3);

SELECT *
FROM ar_activity
         INNER JOIN ar_av_compatibility aac on ar_activity.activity_id = aac.activity_id
WHERE aac.vacation_id = 1;

SELECT comment FROM ar_comments WHERE super_id = (SELECT comment_id FROM ar_activity WHERE activity_id = 1);
*/
