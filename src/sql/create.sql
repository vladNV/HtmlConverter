create table client
(
  login varchar(255) not null,
  password varchar(255) not null,
  userId int auto_increment
    primary key
)
;
create table file
(
  fileId int auto_increment
    primary key,
  path varchar(255) not null,
  data_created datetime not null,
  sizeKB int not null,
  file_type varchar(255) not null,
  uploader int null,
  constraint file_client_userId_fk
  foreign key (uploader) references client (userId)
)
;

create index file_client_userId_fk
  on file (uploader)
;

create table mail
(
  MSubject varchar(255) not null,
  Login varchar(255) not null,
  FromEmail varchar(255) not null,
  ToEmail varchar(255) not null,
  MText text null,
  IdMail int auto_increment
    primary key
)
;





