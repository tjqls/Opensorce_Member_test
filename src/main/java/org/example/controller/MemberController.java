package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.service.MemberService;

public class MemberController {

    private MemberService memberService;

    public MemberController(){
        memberService = Container.memberService;
    }


    public void join() { // 회원가입 로직 시작
        String loginId;
        String loginPw;
        String loginPwConfirm;
        String name;
        String birth;
        String gender;
        String e_mail;

       while (true) {
           System.out.printf("로그인 아이디 : ");
           loginId = Container.scanner.nextLine().trim();

           if (loginId.length() == 0) {
               System.out.println("로그인 아이디를 입력해주세요.");
               continue;
           }

           boolean isLoginIdDup = memberService.isLoginDup(loginId);

           if (isLoginIdDup) {
               System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);
               continue;
           }
           break;
       }

       //비밀번호 설정
       while (true){
           System.out.printf("로그인 비밀번호 : ");
           loginPw = Container.scanner.nextLine().trim();

           if(loginPw.length() == 0){
               System.out.println("로그인 비밀번호를 입력해주세요.");
               continue;
           }

           //로그인 비밀번호 확인
           boolean loginPwConfirmIssame = true;

           while (true){
               System.out.printf("로그인 비밀번호 확인 : ");
               loginPwConfirm = Container.scanner.nextLine().trim();

               if(loginPwConfirm.length() == 0){
                   System.out.println("로그인 비밀번호를 입력해주세요.");
                   continue;
               }

               if(loginPw.equals(loginPwConfirm) == false){
                   System.out.println("로그인 비밀번호가 일치하지 않습니다.");
                   loginPwConfirmIssame = false;
                   break;
               }
               break;
           }

           if(loginPwConfirmIssame){
               break;
           }
       }

       while (true){
           System.out.printf("이름 : ");
           name = Container.scanner.nextLine().trim();

           if(name.length()==0){
               System.out.println("이름을 입력해주세요.");
               continue;
           }
           break;
       }

       while (true){
           System.out.printf("생년월일 : ");
           birth = Container.scanner.nextLine().trim();

           if(birth.length() == 0 ){
               System.out.println("생년월일을 입력해주세요");
               continue;
           }
           break;
       }

       while (true){
           System.out.println("성별 : ");
           gender = Container.scanner.nextLine().trim();

           if(gender.length()==0){
               System.out.println("성별을 입력해주세요.");
               continue;
           }
           break;
       }

       while (true){
           System.out.println("e-mail : ");
           e_mail = Container.scanner.nextLine().trim();

           if(e_mail.length() == 0){
               System.out.println("e-mail을 입력해주세요");
               continue;
           }
           break;
       }

       int id= memberService.join(loginId, loginPw, name, birth,gender,e_mail );


    } // 회원가입 로직 끝





    public void login() {
        String loginId;
        String loginPw;

        System.out.println("== 로그인 ==");

        System.out.printf("로그인 아이디 : ");
        loginId = Container.scanner.nextLine().trim();

        if(loginId.length()==0){
            System.out.println("아이디를 입력해주세요.");
            return;
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if(member == null){
            System.out.println("입력하신 아이디는 존재하지 않습니다.");
            return;
        }



        while (true){
            System.out.printf("로그인 비밀번호 : ");
            loginPw = Container.scanner.nextLine().trim();

            if(loginPw.length() == 0){
                System.out.println("비밀번호를 입력해주세요.");
                continue;
            }
            if(member.getLoginPw().equals(loginPw) == false) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }

            System.out.printf("\"%s\"님 환영합니다.\n", member.getName());
            Container.session.login(member);

            break;
        }

    }


    public void lougout() {
        Container.session.logout();
        System.out.println("로그아웃 되었습니다.");
    }

    public void whoami() {
        if(Container.session.isLogined() == false){
            System.out.println("로그인상태가 아닙니다.");
        }else{
            System.out.println(Container.session.loginedMemberId.getLoginId());
        }
    }
}
