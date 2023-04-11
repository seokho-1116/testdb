create table MEMBER(
    member_id int(10) NOT NULL AUTO_INCREMENT,
    email varchar(50),
    name varchar(30),
    nickname varchar(30),
    role varchar(10),
    profile_file_id int(15),
    primary key (member_id)
);