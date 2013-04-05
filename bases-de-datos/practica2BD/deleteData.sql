BEGIN

    FOR T in (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'ALTER TABLE '||T.table_name||' DISABLE ALL CONSTRAINTS';
    END LOOP;

    FOR T in (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'TRUNCATE TABLE '||T.table_name;
    END LOOP;

    FOR T in (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'ALTER TABLE '||T.table_name||' ENABLE ALL CONSTRAINTS';
    END LOOP;
END;