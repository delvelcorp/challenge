--
-- PostgreSQL database dump
--

-- Dumped from database version 10.20
-- Dumped by pg_dump version 10.20

-- Started on 2022-05-08 17:22:22 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2886 (class 1262 OID 16626)
-- Name: db_transactions; Type: DATABASE; Schema: -; Owner: usr_develcorp
--

CREATE DATABASE db_transactions WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_EC.UTF-8' LC_CTYPE = 'es_EC.UTF-8';


ALTER DATABASE db_transactions OWNER TO usr_develcorp;

\connect db_transactions

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 13003)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16705)
-- Name: balance; Type: TABLE; Schema: public; Owner: usr_develcorp
--

CREATE TABLE public.balance (
    id bigint NOT NULL,
    account_id bigint,
    actual_balance numeric(19,2),
    modified_at timestamp without time zone
);


ALTER TABLE public.balance OWNER TO usr_develcorp;

--
-- TOC entry 196 (class 1259 OID 16685)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: usr_develcorp
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO usr_develcorp;

--
-- TOC entry 198 (class 1259 OID 16712)
-- Name: transaction; Type: TABLE; Schema: public; Owner: usr_develcorp
--

CREATE TABLE public.transaction (
    transaction_id bigint NOT NULL,
    account_id bigint,
    balance numeric(19,2),
    date date NOT NULL,
    message character varying(255),
    status boolean NOT NULL,
    transaction_type character varying(15) NOT NULL,
    value numeric(19,2)
);


ALTER TABLE public.transaction OWNER TO usr_develcorp;

--
-- TOC entry 2879 (class 0 OID 16705)
-- Dependencies: 197
-- Data for Name: balance; Type: TABLE DATA; Schema: public; Owner: usr_develcorp
--

INSERT INTO public.balance (id, account_id, actual_balance, modified_at) VALUES (114, 496825, 1.00, '2022-05-08 17:12:16.257');
INSERT INTO public.balance (id, account_id, actual_balance, modified_at) VALUES (116, 585545, 2000.00, '2022-05-08 17:14:57.454');
INSERT INTO public.balance (id, account_id, actual_balance, modified_at) VALUES (110, 478758, 1425.00, '2022-05-08 17:11:45.693');
INSERT INTO public.balance (id, account_id, actual_balance, modified_at) VALUES (108, 225487, 700.00, '2022-05-08 17:11:02.782');
INSERT INTO public.balance (id, account_id, actual_balance, modified_at) VALUES (112, 495878, 151.00, '2022-05-08 17:12:05.955');


--
-- TOC entry 2880 (class 0 OID 16712)
-- Dependencies: 198
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: usr_develcorp
--

INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (109, 225487, 100.00, '2022-05-08', 'Apertura de 100', true, 'Apertura', 100.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (111, 478758, 2000.00, '2022-05-08', 'Apertura de 2000', true, 'Apertura', 2000.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (113, 495878, 1.00, '2022-05-08', 'Apertura de 1', true, 'Apertura', 1.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (115, 496825, 1.00, '2022-05-08', 'Apertura de 1', true, 'Apertura', 1.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (117, 585545, 2000.00, '2022-05-08', 'Apertura de 2000', true, 'Apertura', 2000.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (118, 478758, 1425.00, '2022-05-08', 'Retiro de 575', true, 'Retiro', 575.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (119, 225487, 700.00, '2022-05-08', 'Deposito de 600', true, 'Deposito', 600.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (120, 495878, 151.00, '2022-05-08', 'Deposito de 150', true, 'Deposito', 150.00);
INSERT INTO public.transaction (transaction_id, account_id, balance, date, message, status, transaction_type, value) VALUES (121, 496825, 1.00, '2022-05-08', 'Saldo no disponible', false, 'Retiro', 540.00);


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: usr_develcorp
--

SELECT pg_catalog.setval('public.hibernate_sequence', 121, true);


--
-- TOC entry 2754 (class 2606 OID 16711)
-- Name: balance balance_pkey; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT balance_pkey PRIMARY KEY (id);


--
-- TOC entry 2756 (class 2606 OID 16716)
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id);


-- Completed on 2022-05-08 17:22:23 -05

--
-- PostgreSQL database dump complete
--

