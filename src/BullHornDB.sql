CREATE TABLE BullUser
(
  user_id integer primary key,
  full_name varchar(100),
  email varchar(100) unique,
  user_name  varchar(50) unique,
  user_password varchar(50)
);

CREATE TABLE Post
(
  post_id integer primary key,
  user_id integer references BullUser(user_id),
  post_content varchar(140),
  post_date date
);

ALTER TABLE BullUser ADD JOINDATE DATE;
ALTER TABLE BullUser ADD MOTTO VARCHAR(100);


INSERT INTO BullUser VALUES (1,'Ken Ngo', 'cpngo@uh.edu', 'kenngo2306','123');
INSERT INTO BullUser VALUES (2,'Tran Ngo', 'tran@uh.edu', 'tran2306','123');
INSERT INTO BullUser VALUES (3,'Van Ha', 'van@uh.edu', 'van2306','123');

INSERT INTO Post VALUES (1,1,'This is a post about me. Weather is good today!',TO_DATE('08/27/2015','mm/dd/yyyy'));
truncate table post;

truncate table BullUser;
drop table post;
drop table bulluser;
CREATE SEQUENCE SEQ_BULLUSERID;
CREATE SEQUENCE SEQ_POSTID;
SELECT SEQ_BULLUSERID.NEXTVAL FROM DUAL;