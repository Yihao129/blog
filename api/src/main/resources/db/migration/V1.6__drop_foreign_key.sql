alter table post
drop constraint FK_post_author;

alter table comment
drop constraint FK_comment_post;

