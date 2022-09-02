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

