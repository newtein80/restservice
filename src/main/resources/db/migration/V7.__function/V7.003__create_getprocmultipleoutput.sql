-- FUNCTION: rest.getprocmultipleoutput()

-- DROP FUNCTION rest.getprocmultipleoutput();

CREATE OR REPLACE FUNCTION rest.getprocmultipleoutput(
    employeeid integer,
    OUT test_name character varying,
    OUT test_desc character varying
)RETURNS record LANGUAGE 'plpgsql'
COST 100 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
        /*
        select * from rest.t_test_detail;
        -- test_id, test_name, test_desc
        */
        select test_name, test_desc from rest.t_test_detail where test_id = employeeid into test_name, test_desc;
END;

$BODY$;
ALTER FUNCTION rest.getprocmultipleoutput(integer)
    OWNER TO postgres;