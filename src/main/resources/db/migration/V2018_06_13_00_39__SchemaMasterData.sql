create sequence seq_users;
create table users (
  id         int                    not null primary key default nextval('seq_users'),
  username   character varying(100) not null unique,
  password   character varying(150) not null,
  is_enabled boolean                                     default false
);
insert into users (username, password, is_enabled) values ('admin', 'admin', true);
insert into users (username, password, is_enabled) values ('user', 'user', true);

create sequence seq_nasabah_general;
create table nasabah_general (
  id                int                    not null primary key default nextval('seq_nasabah_general'),
  identitas_nasabah character varying(64)  not null unique,
  nama_lengkap      character varying(100) not null,
  tanggal_lahir     date                   not null,
  nomor_telephon    character varying(15)
);

create sequence seq_nasabah_pribadi;
create table nasabah_pribadi (
  id                       int                    not null primary key default nextval('seq_nasabah_pribadi'),
  nama_ibu_kandung         character varying(100) not null,
  nomor_telephone_orangtua character varying(15)
);

alter table nasabah_general
  add column nasabah_pribadi_id int;
alter table nasabah_general
  add constraint fk_nasabah_pribadi foreign key (nasabah_pribadi_id)
references nasabah_pribadi (id) on update cascade on delete cascade;

insert into nasabah_general (id, identitas_nasabah, nama_lengkap, tanggal_lahir, nomor_telephon) values
  (1, '621234-12344-1234-5343', 'Dimas maryanto', '1993-03-28', '082117355133'),
  (2, '621234-12344-1234-5123', 'Muhamad Yusuf', '1993-10-10', '082117355135');

insert into nasabah_pribadi (id, nama_ibu_kandung, nomor_telephone_orangtua) values
  (1, 'Margakarti', '0817806226');

update nasabah_general
set nasabah_pribadi_id = 1
where id = 1;

create sequence seq_nasabah_dokumen;
create table nasabah_dokumen (
  id                 int                    not null primary key default nextval('seq_nasabah_dokumen'),
  filename           character varying(50)  not null,
  path               character varying(255) not null,
  nasabah_general_id int                    not null
);
alter table nasabah_dokumen
  add constraint fk_nasabah_general foreign key (nasabah_general_id)
references nasabah_general (id) on update cascade on delete cascade;

insert into nasabah_dokumen (filename, path, nasabah_general_id) values
  ('profile.jpg', 'classpath:/images/', 1),
  ('scan-ktp.jpg', 'classpath:/images/', 1),
  ('scan-kk.jpg', 'classpath:/images/', 1);