-- FUNCTION: rest.gettotaldetailcount()

-- DROP FUNCTION rest.gettotaldetailcount();

CREATE OR REPLACE FUNCTION rest.gettotaldetailcount(
)RETURNS integer LANGUAGE 'plpgsql'
COST 100 VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE

/*
======================================================================
SP   이름  : 계정 보안 설정 문자 조합 리스트 ( uvm.GetAccountCheckList )
----------------------------------------------------------------------
작업     성명        일자                       내용
----------------------------------------------------------------------
신규     NVM   2021.03.03   최초작성
======================================================================
*/

    total_count integer;

BEGIN
        select count(*) from rest.t_test_detail into total_count;

        RETURN total_count;
END;

$BODY$;
ALTER FUNCTION rest.gettotaldetailcount()
    OWNER TO postgres;
