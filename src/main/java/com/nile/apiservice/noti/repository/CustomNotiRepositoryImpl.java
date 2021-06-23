package com.nile.apiservice.noti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.nile.apiservice.noti.dto.NotiInfoResultSet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomNotiRepositoryImpl implements CustomNotiRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<NotiInfoResultSet> emspqGetNotiInfo(Long noti_id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("noti.getnotidetailreturntablesetbynamedproc");
        storedProcedureQuery.setParameter("i_noti_id", noti_id);
        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }
    
}
