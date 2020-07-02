INSERT INTO PROJECT (ID, NAME)
VALUES ('1', 'project1'),
       ('2', 'project2');

INSERT INTO USER (ID, EMAIL)
VALUES ('1', 'test1@test.com'),
       ('2', 'test2@test.com');

INSERT INTO ORGANIZATION (ID, NAME)
VALUES ('1', 'organization1'),
       ('2', 'organization2');

INSERT INTO LANGUAGE (ID, CODE)
VALUES ('1', 'language1'),
       ('2', 'language2');

INSERT INTO TRANSLATION (ID, VALUE, LANGUAGE_ID)
VALUES ('1', '{
  "translation": "translation"
}', '1'),
       ('2', '{
         "translation": "translation"
       }', '2');

INSERT INTO ORGANIZATION_TRANSLATIONS (ORGANIZATION_ID, TRANSLATIONS_ID)
VALUES ('1', '1'),
       ('2', '2');

INSERT INTO ORGANIZATION_ROLE (ID, ROLE, ORGANIZATION_ID, USER_ID)
VALUES ('1', 'ADMIN', '1', '1'),
       ('2', 'OWNER', '2', '2')
