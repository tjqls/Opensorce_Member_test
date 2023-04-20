package org.example.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Member {

    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String loginPw;
    private String name;
    private String birth;
    private String gender;
    private String e_mail;

    public Member(Map<String, Object> memberMap) {
        this.id = (int) memberMap.get("id");
        this.regDate= (String) memberMap.get("regDate");
        this.updateDate= (String) memberMap.get("updateDate");
        this.loginId= (String) memberMap.get("loginId");
        this.loginPw= (String) memberMap.get("loginPw");
        this.name= (String) memberMap.get("name");
        this.birth = (String) memberMap.get("birth");
        this.gender = (String) memberMap.get("gender");
        this.e_mail = (String) memberMap.get("e_mail");
    }
}
