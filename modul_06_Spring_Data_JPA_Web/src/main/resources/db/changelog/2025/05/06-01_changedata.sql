-- liquibase formatted sql


-- changeset VTB209274:1746525055308-5
INSERT INTO public.users(
    id, username)
VALUES (1, 'Федя');

INSERT INTO public.pproduct(
    id, balance, product_type, "product-list_id", user_id, account)
VALUES (1, 100, 1, 1, 1, '40702810...');

INSERT INTO public.pproduct(
    id, balance, product_type, "product-list_id", user_id, account)
VALUES (2, 100, 1, 1, 1, '40702840...');