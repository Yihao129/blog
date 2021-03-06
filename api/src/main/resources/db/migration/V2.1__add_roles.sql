insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', TRUE , TRUE, TRUE, TRUE),
('Manager', '/authors?(/[a-z0-9]+)?(/eager)?,/comments?(/[a-z0-9]+)?(/eager)?,/posts?(/[a-z0-9]+)?(/eager)?', TRUE, TRUE, TRUE, FALSE),
('User', '/authors?(/[a-z0-9]+)?(/eager)?,/comments?(/[a-z0-9]+)?(/eager)?,/posts?(/[a-z0-9]+)?(/eager)?,/files?(/[a-z0-9]+)?(/eager)?', TRUE, TRUE, FALSE, FALSE)
;
commit;
insert into users (name, password, first_name, last_name, email) values
('dwang', '25f9e794323b453885f5181f1b624d0b', 'David', 'Wang', 'dwang@training.ascendingdc.com'),
('rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com'),
('xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com')
;
commit;
insert into users_roles values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;