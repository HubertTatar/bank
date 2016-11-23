select * from users;
select * from primary_accounts;
select * from savings_accounts;
select * from user_roles;
select * from roles;

insert into roles(role_id, name) values(1, 'USER');
insert into roles(role_id, name) values(2, 'ADMIN');