package com.nile.apiservice.noti.repository;

import java.util.List;

import com.nile.apiservice.noti.dto.NotiInfoResultSet;

public interface CustomNotiRepository {
    List<NotiInfoResultSet> emspqGetNotiInfo(Long noti_id);
}
