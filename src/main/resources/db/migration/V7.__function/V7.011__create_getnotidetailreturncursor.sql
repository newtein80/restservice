-- FUNCTION: rest.getnotidetailreturncursor(bigint)

-- DROP FUNCTION rest.getnotidetailreturncursor(bigint);

CREATE OR REPLACE FUNCTION rest.getnotidetailreturncursor(
    i_noti_id bigint,
    OUT v_ref_cursor refcursor)
    RETURNS refcursor
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE PARALLEL UNSAFE

AS $BODY$
BEGIN

    OPEN v_ref_cursor FOR
        select
            noti_id
            , noti_title
            , noti_body
            , noti_href
            , is_unread
            , is_hidden
            , noti_type
            , noti_detail
            , recipient_user_id
            , recipient_user_nm
            , sender_user_id
            , sender_user_nm
            , create_dt
        from rest.t_notification
        where noti_id > i_noti_id
        ;

END;

$BODY$;

ALTER FUNCTION rest.getnotidetailreturncursor(bigint)
    OWNER TO postgres;