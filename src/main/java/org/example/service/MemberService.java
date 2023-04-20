package org.example.service;

import org.example.dto.Member;

import static org.example.Container.memberRepository;

public class MemberService {
    public int join(String loginId, String loginPw, String name, String birth, String gender, String e_mail) {
        return memberRepository.join(loginId, loginPw, name, birth, gender, e_mail);
    }

    public boolean isLoginDup(String loginId) {
        return memberRepository.isLoginIdDup(loginId);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.getMemberByLoginId(loginId);
    }
}
