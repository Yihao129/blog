alter table post
ADD CONSTRAINT FK_post_author
foreign key (author_id) references author(id) on delete cascade;

alter table comment
ADD CONSTRAINT FK_comment_post
foreign key (post_id) references post(id)  on delete cascade;