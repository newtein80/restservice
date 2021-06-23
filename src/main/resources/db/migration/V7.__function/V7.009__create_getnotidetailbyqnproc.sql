-- FUNCTION: rest.getnotidetailbyqnproc(bigint)

-- DROP FUNCTION rest.getnotidetailbyqnproc(bigint);

CREATE OR REPLACE FUNCTION rest.getnotidetailbyqnproc(
    i_noti_id bigint,
    OUT o_noti_title character varying,
    OUT o_noti_body character varying
)RETURNS record LANGUAGE 'plpgsql'
COST 100 VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
        select
            noti_title
            , noti_body
            -- , is_unread
            -- , noti_title
            -- , is_hidden
            -- , noti_id
            -- , noti_type
            -- , recipient_user_id
            -- , recipient_user_nm
            -- , sender_user_id
            -- , sender_user_nm
        into
            o_noti_title
            , o_noti_body
        from rest.t_notification
        where noti_id = i_noti_id
        ;
END;

$BODY$;
ALTER FUNCTION rest.getnotidetailbyqnproc(bigint)
    OWNER TO postgres;