--
-- PostgreSQL database dump
--

-- Dumped from database version 10.20
-- Dumped by pg_dump version 10.20

-- Started on 2022-05-08 17:20:52 -05

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
-- TOC entry 2890 (class 1262 OID 16625)
-- Name: db_accounts; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE db_accounts WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_EC.UTF-8' LC_CTYPE = 'es_EC.UTF-8';


ALTER DATABASE db_accounts OWNER TO postgres;

\connect db_accounts

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
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 16719)
-- Name: account; Type: TABLE; Schema: public; Owner: usr_develcorp
--

CREATE TABLE public.account (
    account_id bigint NOT NULL,
    customer_id bigint,
    initial_balance numeric(19,2),
    status boolean NOT NULL,
    account_type_account_type_id integer
);


ALTER TABLE public.account OWNER TO usr_develcorp;

--
-- TOC entry 196 (class 1259 OID 16666)
-- Name: account_type; Type: TABLE; Schema: public; Owner: usr_develcorp
--

CREATE TABLE public.account_type (
    account_type_id integer NOT NULL,
    description character varying(50) NOT NULL
);


ALTER TABLE public.account_type OWNER TO usr_develcorp;

--
-- TOC entry 197 (class 1259 OID 16671)
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
-- TOC entry 198 (class 1259 OID 16678)
-- Name: seq; Type: SEQUENCE; Schema: public; Owner: usr_develcorp
--

CREATE SEQUENCE public.seq
    START WITH 100000
    INCREMENT BY 100
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq OWNER TO usr_develcorp;

--
-- TOC entry 2884 (class 0 OID 16719)
-- Dependencies: 199
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: usr_develcorp
--

INSERT INTO public.account (account_id, customer_id, initial_balance, status, account_type_account_type_id) VALUES (478758, 1, 2000.00, true, 1);
INSERT INTO public.account (account_id, customer_id, initial_balance, status, account_type_account_type_id) VALUES (495878, 3, 1.00, true, 1);
INSERT INTO public.account (account_id, customer_id, initial_balance, status, account_type_account_type_id) VALUES (496825, 4, 1.00, true, 1);
INSERT INTO public.account (account_id, customer_id, initial_balance, status, account_type_account_type_id) VALUES (585545, 1, 2000.00, true, 1);
INSERT INTO public.account (account_id, customer_id, initial_balance, status, account_type_account_type_id) VALUES (225487, 1, 1000.00, false, 2);


--
-- TOC entry 2881 (class 0 OID 16666)
-- Dependencies: 196
-- Data for Name: account_type; Type: TABLE DATA; Schema: public; Owner: usr_develcorp
--

INSERT INTO public.account_type (account_type_id, description) VALUES (1, 'Ahorros');
INSERT INTO public.account_type (account_type_id, description) VALUES (2, 'Corriente');


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 197
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: usr_develcorp
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 198
-- Name: seq; Type: SEQUENCE SET; Schema: public; Owner: usr_develcorp
--

SELECT pg_catalog.setval('public.seq', 100100, true);


--
-- TOC entry 2758 (class 2606 OID 16723)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 2756 (class 2606 OID 16670)
-- Name: account_type account_type_pkey; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.account_type
    ADD CONSTRAINT account_type_pkey PRIMARY KEY (account_type_id);


--
-- TOC entry 2759 (class 2606 OID 16724)
-- Name: account fkg7dlmy7fkme3dm51gsnuhht2g; Type: FK CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkg7dlmy7fkme3dm51gsnuhht2g FOREIGN KEY (account_type_account_type_id) REFERENCES public.account_type(account_type_id);


-- Completed on 2022-05-08 17:20:54 -05

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 10.20
-- Dumped by pg_dump version 10.20

-- Started on 2022-05-08 17:21:21 -05

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
-- TOC entry 2881 (class 1262 OID 16624)
-- Name: db_customers; Type: DATABASE; Schema: -; Owner: usr_develcorp
--

CREATE DATABASE db_customers WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_EC.UTF-8' LC_CTYPE = 'es_EC.UTF-8';


ALTER DATABASE db_customers OWNER TO usr_develcorp;

\connect db_customers

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
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16702)
-- Name: customer; Type: TABLE; Schema: public; Owner: usr_develcorp
--

CREATE TABLE public.customer (
    person_id bigint NOT NULL,
    address character varying(100) NOT NULL,
    age integer NOT NULL,
    gender character varying(15) NOT NULL,
    identification character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    phone character varying(10) NOT NULL,
    password character varying(12) NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.customer OWNER TO usr_develcorp;

--
-- TOC entry 196 (class 1259 OID 16640)
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
-- TOC entry 2875 (class 0 OID 16702)
-- Dependencies: 197
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: usr_develcorp
--

INSERT INTO public.customer (person_id, address, age, gender, identification, name, phone, password, status) VALUES (1, 'Otavalo sn y principal ', 43, 'Masculino', '0194827364', 'Jose Lema', '098254785', '1234', true);
INSERT INTO public.customer (person_id, address, age, gender, identification, name, phone, password, status) VALUES (2, 'Amazonas y NNUU', 32, 'Femenino', '0194837267', 'Marianela Montalvo', '097548965', '5678', true);
INSERT INTO public.customer (person_id, address, age, gender, identification, name, phone, password, status) VALUES (3, '13 junio y Equinoccial', 35, 'Masculino', '0194857398', 'Juan Osorio ', '098874587', '1245', true);


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: usr_develcorp
--

SELECT pg_catalog.setval('public.hibernate_sequence', 30, true);


--
-- TOC entry 2750 (class 2606 OID 16709)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (person_id);


--
-- TOC entry 2752 (class 2606 OID 16718)
-- Name: customer uk_rhpv87q3057rohwm2cawtfr3e; Type: CONSTRAINT; Schema: public; Owner: usr_develcorp
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_rhpv87q3057rohwm2cawtfr3e UNIQUE (identification);


-- Completed on 2022-05-08 17:21:22 -05

--
-- PostgreSQL database dump complete
--

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

