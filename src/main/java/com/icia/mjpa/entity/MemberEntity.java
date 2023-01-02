package com.icia.mjpa.entity;

import com.icia.mjpa.dto.MemberDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "member_table")
@Entity
public class MemberEntity {

    /*
     *   [*] Entity : 실제 DB테이블과 매핑되는 클래스
     *                  데이터 베이스 테이블과 칼럼을 지정한다
     *
     *    [*] DTO : 데이터 교환이 이루어질 수 있도록 하는 객체
     * */
    @Id // 테이블 primary key 지정
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // sequence지정
    private Long idx;

    @Column(unique = true) // unique 제약조건 생성
    private String memId;

    @Column
    private String memPw;

    @Column
    private String memName;

    // DTO를 Entity로 변환하는 메소드
    // MemberEntity : 데이터 타입
    // toMemberEntity : 메소드 이름
    // 매개변수 : MemberDTO

    public static MemberEntity toMemberEntity(MemberDTO member) {
        // 회원가입 전용
        
        // MemberEntity 객체 생성
        MemberEntity entity = new MemberEntity();

        // DTO에 있는 memId, memPw, memName을 Entity로 변형
        entity.setMemId(member.getMemId());
        entity.setMemPw(member.getMemPw());
        entity.setMemName(member.getMemName());

        return entity;

    }
    
    public static MemberEntity updataMemberEntity(MemberDTO member) {
        // 회원수정 전용
        
        // MemberEntity 객체 생성
        MemberEntity entity = new MemberEntity();

        // DTO에 있는 memId, memPw, memName을 Entity로 변형
        entity.setIdx(member.getIdx());
        entity.setMemId(member.getMemId());
        entity.setMemPw(member.getMemPw());
        entity.setMemName(member.getMemName());

        return entity;

    }
}
