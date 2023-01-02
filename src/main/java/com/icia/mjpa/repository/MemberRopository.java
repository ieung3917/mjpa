package com.icia.mjpa.repository;

import com.icia.mjpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRopository extends JpaRepository<MemberEntity, Long> {

    // 아이디(MemId)로 회원정보를 조회
    // select * from member where memId = ?
    Optional<MemberEntity> findByMemId(String memId);
}
