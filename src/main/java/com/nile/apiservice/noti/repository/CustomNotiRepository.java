package com.nile.apiservice.noti.repository;

import java.util.List;

import com.nile.apiservice.noti.dto.NotiInfoResultSet;
import com.nile.apiservice.noti.entity.Noti;

public interface CustomNotiRepository {
    List<NotiInfoResultSet> emspqGetNotiInfo(Long noti_id);
    List<Noti> emspqGetNotiInfoCursor(Long noti_id);
    List<Noti> querydslFindByNotititleInCustom(String noti_title);
}
