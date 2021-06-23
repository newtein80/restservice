package com.nile.apiservice.noti.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.nile.apiservice.noti.entity.Noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}
