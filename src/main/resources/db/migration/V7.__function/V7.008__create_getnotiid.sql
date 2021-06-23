-- FUNCTION: rest.getnotiid(bigint)

-- DROP FUNCTION rest.getnotiid(bigint);

CREATE OR REPLACE FUNCTION rest.getnotiid(
    i_noti_id bigint,
    OUT o_noti_id bigint
)RETURNS bigint LANGUAGE 'plpgsql'
COST 100 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
    select t1.noti_id from rest.t_notification as t1 where noti_id = i_noti_id into o_noti_id;
END;

$BODY$;
ALTER FUNCTION rest.getnotiid(bigint)
    OWNER TO postgres;