insert into admin_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('ITmanager', '{noop}asdf1234', 'ADMIN', 'ITmanager', 'it@mail.com', 'I am ITmanager.', now(), 'ITmanager', now(), 'ITmanager'),
    ('Patrick', '{noop}asdf1234', 'DEVELOPER', 'Patrick', 'patrick@mail.com', 'I am Patrick.', now(), 'patrick', now(), 'patrick'),
    ('mark', '{noop}asdf1234', 'MANAGER', 'Mark', 'mark@mail.com', 'I am Mark.', now(), 'mark', now(), 'mark'),
    ('susan', '{noop}asdf1234', 'MANAGER,DEVELOPER', 'Susan', 'susan@mail.com', 'I am Susan.', now(), 'susan', now(), 'susan'),
    ('jim', '{noop}asdf1234', 'USER', 'Jim', 'jim@mail.com', 'I am Jim.', now(), 'jim', now(), 'jim')

;