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

