-- FUNCTION: rest.getproccursoroutput(integer)

-- DROP FUNCTION rest.getproccursoroutput(integer);

CREATE OR REPLACE FUNCTION rest.getproccursoroutput(
    employeeid integer,
    OUT v_ref_cursor refcursor)
    RETURNS refcursor
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE PARALLEL UNSAFE

AS $BODY$
BEGIN

    OPEN v_ref_cursor FOR
        select test_name, test_desc from rest.t_test_detail where test_id > employeeid;

END;

$BODY$;

ALTER FUNCTION rest.getproccursoroutput(integer)
    OWNER TO postgres;