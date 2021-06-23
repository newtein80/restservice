package com.nile.apiservice.noti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.nile.apiservice.noti.dto.NotiInfoResultSet;
import com.nile.apiservice.noti.entity.Noti;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomNotiRepositoryImpl implements CustomNotiRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<NotiInfoResultSet> emspqGetNotiInfo(Long noti_id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("noti.getnotidetailreturntablesetbynamedproc");
        storedProcedureQuery.setParameter("i_noti_id", noti_id);
        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Noti> emspqGetNotiInfoCursor(Long noti_id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("noti.getnotidetailreturncursorbynamedproc");
        storedProcedureQuery.setParameter(2, noti_id);
        // storedProcedureQuery.setParameter(2, noti_id); // * 위의 NamedStoredProcedure 선언된 곳의 param 순서에 맞게 set 하여도 된다.
        storedProcedureQuery.execute();
        // todo: 아래의 경고 문구 해결 방법?
        return storedProcedureQuery.getResultList();
    }
    
}
