package com.icia.mjpa.dto;

import com.icia.mjpa.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberDTO {

    private Long idx;
    private String memId;
    private String memPw;
    private String memName;

    public static MemberDTO toMemberDTO(MemberEntity entity) {
        // member 객체 생성
        MemberDTO member = new MemberDTO();

        // entity에 있는 Idx, memId, memPw, memName을 member로 변형
        member.setIdx(entity.getIdx());
        member.setMemId(entity.getMemId());
        member.setMemPw(entity.getMemPw());
        member.setMemName(entity.getMemName());

        return member;
    }


}
