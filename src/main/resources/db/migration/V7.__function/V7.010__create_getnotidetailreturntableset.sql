-- FUNCTION: rest.getnotidetailreturntableset(bigint)

-- DROP FUNCTION rest.getnotidetailreturntableset(bigint);

CREATE OR REPLACE FUNCTION rest.getnotidetailreturntableset(
    i_noti_id bigint
)RETURNS table(noti_title character varying, noti_body text) LANGUAGE 'plpgsql'
COST 100 ROWS 1000 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
    RETURN QUERY
        EXECUTE 'select
            n.noti_title
            , n.noti_body
            -- , is_unread
            -- , noti_title
            -- , is_hidden
            -- , noti_id
            -- , noti_type
            -- , recipient_user_id
            -- , recipient_user_nm
            -- , sender_user_id
            -- , sender_user_nm
        from rest.t_notification as n
        where noti_id = '||i_noti_id
        ;
END;

$BODY$;
ALTER FUNCTION rest.getnotidetailreturntableset(bigint)
    OWNER TO postgres;