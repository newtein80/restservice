-- FUNCTION: rest.getprocsingleoutput()

-- DROP FUNCTION rest.getprocsingleoutput();

CREATE OR REPLACE FUNCTION rest.getprocsingleoutput(
    employeeid integer,
    OUT empage integer
)RETURNS integer LANGUAGE 'plpgsql'
COST 100 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
        select test_id from rest.t_test_detail where test_id = employeeid into empage;
END;

$BODY$;
ALTER FUNCTION rest.getprocsingleoutput(integer)
    OWNER TO postgres;