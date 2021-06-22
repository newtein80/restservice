-- FUNCTION: rest.getproctablesetoutput()

-- DROP FUNCTION rest.getproctablesetoutput();

CREATE OR REPLACE FUNCTION rest.getproctablesetoutput(
    employeeid integer
)RETURNS table(test_name character varying, test_desc character varying) LANGUAGE 'plpgsql'
COST 100 ROWS 1000 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
        /*
        select * from rest.t_test_detail;
        -- test_id, test_name, test_desc
        */
        select test_name, test_desc from rest.t_test_detail where test_id = employeeid;
END;

$BODY$;
ALTER FUNCTION rest.getproctablesetoutput(integer)
    OWNER TO postgres;