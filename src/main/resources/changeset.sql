--liquibase formatted sql

--document: https://docs.liquibase.com/concepts/changelogs/sql-format.html

--changeset tranu:1-1
CREATE TABLE IF NOT EXISTS public.test1
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid()
)
--rollback DROP TABLE

--changeset tranu:1-2
CREATE TABLE IF NOT EXISTS public.test2
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid()
)
--rollback DROP TABLE
