package com.nile.apiservice.noti.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nile.apiservice.noti.entity.Noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepository extends JpaRepository<Noti, Long>, CustomNotiRepository{
    List<Noti> findByCreatedtBetween(Date startdate, Date enddate);
    List<Noti> findByCreatedtAfter(Date startdate);
    List<Noti> findByCreatedtBefore(Date enddate);

    @Query("from Noti where id = ?1")
    Noti qcGetById(long id);

    @Query("select n from Noti n order by n.createdt DESC")
    List<Noti> qcGetAllNoti();

    @Query("from Noti where id in (?1)")
    List<Noti> qcGetByIds(Set<Long> ids);
    
    @Query("select n.notititle, n.notibody, n.senderusernm, n.createdt from Noti n where n.notibody like %:searchstr% or n.notititle like %:searchstr% order by n.createdt DESC")
    List<Object[]> qcGetNotiByTitleOrBody(@Param("searchstr") String searchstr);

    @Query(
        value = "select n.noti_title, n.noti_body, n.sender_user_nm, n.create_dt from t_notification n where n.noti_body like %:searchstr% or n.noti_title like %:searchstr% order by n.create_dt DESC",
        nativeQuery = true)
    List<Object[]> qnGetNotiByTitleOrBody(@Param("searchstr") String searchstr);

    @Query(value = "select rest.gettotalnoticount()", nativeQuery = true) // ! schema 필수
    int qnprocGetTotalNotiCount();

    @Procedure(procedureName = "rest.gettotalnoticount") // todo: schema 명을 하드코딩하지 않는 방법? ex. {h-schema}
    int procGetTotalNotiCount();

    @Query(value = "select rest.getnotiid(?1)", nativeQuery = true)
    int qnprocGetNotiId(long id);

    @Query(value = "select * from rest.getnotidetailbyqnproc(?1)", nativeQuery = true)
    Map<String, ?> qnprocGetNotiInfo(long id);

    @Procedure(name = "noti.getnotidetailbynamedproc")
    Map<String, ?> nprocGetNotiInfo(long i_noti_id);

    @Query(value = "select * from rest.getnotidetailreturntableset(?1)", nativeQuery = true)
    Map<String, ?> qnprocGetNotiInfoTbl(long i_noti_id);

}
