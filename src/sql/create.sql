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
  file_type varchar(255) not null
)
;

