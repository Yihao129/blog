
alter table post
ADD CONSTRAINT pk_post
primary key(id);

alter table author
ADD CONSTRAINT pk_author
primary key(id);

alter table comment
ADD CONSTRAINT pk_comment
primary key(id);


alter table post
ADD CONSTRAINT FK_post_author
foreign key (author_id) references author(id);

alter table comment
ADD CONSTRAINT FK_comment_post
foreign key (post_id) references post(id);