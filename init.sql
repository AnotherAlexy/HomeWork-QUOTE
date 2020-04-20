ALTER SYSTEM SET log_statement = 'all';

create user test_user with password 'test_pass';

-- prod schema
CREATE SCHEMA test AUTHORIZATION test_user;
--GRANT USAGE ON SCHEMA test TO test;
GRANT all ON SCHEMA test TO test_user;
GRANT all ON SCHEMA public TO test_user;
--ALTER DATABASE test SET search_path = test;

-- test schema
CREATE SCHEMA test2 AUTHORIZATION test_user;
--GRANT USAGE ON SCHEMA test2 TO test;
GRANT all ON SCHEMA test2 TO test_user;
--ALTER DATABASE test2 SET search_path = test2,test2;