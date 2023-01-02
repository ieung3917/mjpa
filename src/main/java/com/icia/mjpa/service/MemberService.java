package com.icia.mjpa.service;

import com.icia.mjpa.dto.MemberDTO;
import com.icia.mjpa.entity.MemberEntity;
import com.icia.mjpa.repository.MemberRopository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRopository mrepo;

    public void join(MemberDTO member) {
    
        // 1. dto를 entity로 변환
        MemberEntity entity = MemberEntity.toMemberEntity(member);

        // 2. enitity를 JapRepository의 내장 메소드를 사용해서 insert 실행
        mrepo.save(entity);
    
    }

    public List<MemberDTO> list() {
        // 1. DB에서 entity로 목록을 조회
        List<MemberEntity> memberEntityList = mrepo.findAll();

        List<MemberDTO> memberList = new ArrayList<>();

        // 2. entity를 dto로 변환
        // memberEntityList의 갯수 만큼 반복문을 실행(entity를 dto로 변환)
        for(MemberEntity entity : memberEntityList){
            memberList.add(MemberDTO.toMemberDTO(entity));
        }

        // 3. dto를 return
        return memberList;
    }

    public MemberDTO view(long idx) {

        // Optional<T> : null이 올 수 있는 값을 감싸는 wrapper 클래스
        // T : 데이터타입
        // <> 제너릭 : 클래스 내부  에서 지정하는 것이 아닌 외부에서
        // 사용자에 의해 지정되는 것을 의미
        Optional<MemberEntity> entity = mrepo.findById(idx);
        
        // isPresent() : entity가 존재하면 true, 존재하지 않으면 false
        // get() : 반환된 객체를 사용
        
        if(entity.isPresent()){
            return MemberDTO.toMemberDTO(entity.get());
        } else {
            System.out.println("entity 없음");
            return null;
        }

    }

    public void modify(MemberDTO member) {
        
        // 1. dto를 entity로 변형
        // 2. 기존 entity 변형 메소드가 아니라 idx를 포함한 새로운 메소드 updataToEntity()메소드 생성
        // 3. JpaRepository의 save()메소드 실행

        MemberEntity entity = MemberEntity.updataMemberEntity(member);
        mrepo.save(entity);

        return ;
    }

    public void delete(Long idx) {
        mrepo.deleteById(idx);
    }

    public MemberDTO login(MemberDTO member) {

        // 1. 입력한 아이디(memId)로 회원정보 조회
        // 2. 입력한 아이디와 DB에서 가져온 비밀번호 일치여부 확인

        Optional<MemberEntity> entity = mrepo.findByMemId(member.getMemId());

        if(entity.isPresent()){
            // 해당 아이디 존재할 경우
            System.out.println("아이디 존재 O");
            MemberEntity memberEntity = entity.get();
            
            if(memberEntity.getMemPw().equals(member.getMemPw())){
                // 비밀번호가 일치할 경우
                System.out.println("비밀번호 일치");

                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);

                // dto 반환
                return dto;
            } else {
                // 비밀번호가 일치하지 않을 경우
                System.out.println("비밀번호 불일치");

                return null;
            }
        } else {
            // 아이디가 존재하지 않을경우
            System.out.println("아이디 존재 X");
            return null;
        }
    }
}


