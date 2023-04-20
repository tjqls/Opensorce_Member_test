package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.service.MemberService;

public class MemberController {

    private MemberService memberService; // private로 MemberService 형태의 변수 memberService 선언

    public MemberController(){ // memberService는 Container의 memberService와 같다
        memberService = Container.memberService;
    }


    public void join() { // 회원가입 로직 시작 loginId, loginPw,loginPwConfirm 등등 바로 밑에 선언된것들은 나중에 데이터베이스에 column이 되는 것들
        String loginId;
        String loginPw;
        String loginPwConfirm;
        String name;
        String birth;
        String gender;
        String e_mail;

       while (true) { // while문 true니까 무한히 실행
           System.out.printf("로그인 아이디 : "); // 로그인 아이디로 사용할 아이디 입력해달라
           loginId = Container.scanner.nextLine().trim(); // 사용할 아이디 입력받는다.

           if (loginId.length() == 0) { // 조건문 (loginId의 길이가 0일때 → 길이가 0이면 작성자체를 안했다.)
               System.out.println("로그인 아이디를 입력해주세요."); // 작성자체를 안했기때문에 로그인 아이디 쓸거를 입력해달라고 출력
               continue; // 이 조건이 0이 아니게 될때까지 즉 아이디를 입력할때까지 계속 돌린다.
           }

           boolean isLoginIdDup = memberService.isLoginDup(loginId);
           // boolean은 참/거짓을 판명하는 기본형 isLoginIdDup는 나중에 memberService로 접근
           // → MemberRepository로 한번더 접근해서 입력받은 로그인 아이디를 매개변수로 넘긴다
           // → MemberRepository에서 다시 데이터베이스 접근해서 아이디 중복인지 확인

           if (isLoginIdDup) { // 요것이 이제 아이디 중복확인인경우 이미사용중인 아이디라고 해준다.
               System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);
               continue; //중복 아이디가 아닌걸 입력받을때까지 무한루프
           }
           break; // 조건 달성하면 if(isLoginIdDup)이 로직을 빠져나와서 밑에 로직으로 이동
       }

       //비밀번호 설정
       while (true){
           System.out.printf("로그인 비밀번호 : "); // 로그인에 사용할 비밀번호 입력해달라
           loginPw = Container.scanner.nextLine().trim(); // 비밀번호 입력받음

           if(loginPw.length() == 0){ // 비밀번호 입력값의 길이가 0일때 → 그냥 작성자체를 안했을때
               System.out.println("로그인 비밀번호를 입력해주세요."); // 입력해주세요 출력
               continue;  // 입력할때까지 무한루프
           }

           //로그인 비밀번호 확인
           boolean loginPwConfirmIssame = true;
           // 참 거짓 판별형 boolean으로 loginPwConfirmIssame이 true일때
           // loginPwConfirm 이것이 이제 비밀번호 재확인에서 입력값 받는 변수가 됩니다.


           while (true){
               System.out.printf("로그인 비밀번호 확인 : "); // 확인 들어가것습니다 따라란 따라라 쿵짝짝 쿵짝짝
               loginPwConfirm = Container.scanner.nextLine().trim();
               // 비밀번호 재확인 하려고 다시 비밀번호 입력해달라 이번에 다시 입력하는것을 loginPwConfirm라는 변수에 담것다.

               if(loginPwConfirm.length() == 0){ //비밀번호 재확인에 암것도 안썻을때
                   System.out.println("로그인 비밀번호를 입력해주세요."); // 입력해달라
                   continue; // 입력할때까지 무한루프
               }

               if(loginPw.equals(loginPwConfirm) == false){
                   // 조건문(아까 입력한 비밀번호와 비밀번호 재확인에 입력한 값이랑 같지가 않을때)
                   System.out.println("로그인 비밀번호가 일치하지 않습니다."); // 로그인 비밀번호가 일치하지 않는다는 문구 출력
                   loginPwConfirmIssame = false; //loginPwConfirm 요 같지 않는 Is것이 same이 == false 거짓이다.
                   break; // 요기서 먼추고 while문을 다시 시작합니다
               }
               break; // 위에 로직을 만족시키고
           }

           if(loginPwConfirmIssame){ // 밑에 여기로 내려와서 loginPwConfirmIssame = true가 되었을때 비밀번호 재확인 로직이 끝남
               break;
           }
       }

       while (true){ // while문
           System.out.printf("이름 : "); // "이름 :" 출력
           name = Container.scanner.nextLine().trim(); // 입력값을 name에 담는다.

           if(name.length()==0){ // 입력받은 문자열 name이 길이가 0일때 즉 입력자체를 안했을때
               System.out.println("이름을 입력해주세요."); // 이름을 입력해달라는 문구 출력
               continue; // 입력할때까지 무한루프
           }
           break; // name.length() !=0 (길이가 0이 아닐때 = 이름을 입력했을때) 여기 로직 끝
       }

       while (true){ // 이름 입력받는 로직이랑 같음
           System.out.printf("생년월일 : ");
           birth = Container.scanner.nextLine().trim();

           if(birth.length() == 0 ){
               System.out.println("생년월일을 입력해주세요");
               continue;
           }
           break;
       }

       while (true){ // 이름 입력받는 로직이랑 같음
           System.out.println("성별 : ");
           gender = Container.scanner.nextLine().trim();

           if(gender.length()==0){
               System.out.println("성별을 입력해주세요.");
               continue;
           }
           break;
       }

       while (true){ // 이름 입력받는 로직이랑 같음
           System.out.println("e-mail : ");
           e_mail = Container.scanner.nextLine().trim();

           if(e_mail.length() == 0){
               System.out.println("e-mail을 입력해주세요");
               continue;
           }
           break;
       }

       int id= memberService.join(loginId, loginPw, name, birth,gender,e_mail );
        // int형 id 는 memberService의 join에 위에서 입력받은 loginId, loginPw, name, birth,gender,e_mail들을 담겠다
        // 입력받은 값들을 담아서 memberService로 넘기고 다시 memberService에서 MemberRepository의 join에 넘겨서
        // 데이터베이스에 데이터를 저장합니다. (MemberRepository의 join은 sql.append로 INSERT INTO해서 데이터베이스에 저장하는 로직입니다.)

    } // 회원가입 로직 끝




    // 로그인 로직 시작

 /*break: 만나는 즉시 반복문 전체 탈출
 continue: 만나면 해당 반복부분 탈출 후 다음반복실행*/

    public void login() {
        String loginId; // 문자열 loginId선언
        String loginPw; // 문자열 loginPw선언

        System.out.println("== 로그인 =="); // 로그인 시작 문구

        System.out.printf("로그인 아이디 : "); //로그인 아이디를 입력해달라
        loginId = Container.scanner.nextLine().trim(); // 로그인 아이디를 입력받은것을 loginId에 담을거에요

        if(loginId.length()==0){ // 조건문 loginId의 길이가 0이면 즉 아이디 자체를 입력안했을때
            System.out.println("아이디를 입력해주세요."); // 아이디를 입력해달라는 문구 출력
            return; // 반환 → 다시 아이디 쓰는 창 나옵니다.
        }

        Member member = memberService.getMemberByLoginId(loginId);
        // 여기는 dto안에 있는 Member의 형태로 만들어진 객체 member = memberService의 getMemberByLoginId로직에
        // 입력받은 loginId를 넣어서 확인하겠다는것 입니다.
        // memberService.getMemberByLoginId(loginId)이게 → memberRepository.getMemberByLoginId(loginId)로 넘어가고
        // memberRepository.getMemberByLoginId(loginId) 이 로직은 입력받은 loginId를 데이터베이스에 가져가 맞는 Id가 있는지 찾아봅니다

        if(member == null){ // 찾아봤는데 null값이다? 데이터베이스에 존재하지 않기때문에
            System.out.println("입력하신 아이디는 존재하지 않습니다."); // 입력하신 아이디는 존재하지 않습니다라는 문구 출력
            return; // 반환 → 다시 아이디 쓰는 창 나옵니다.
        }


        // 이번엔 비밀번호 로직
        while (true){
            System.out.printf("로그인 비밀번호 : "); //비밀번호를 입력해달라
            loginPw = Container.scanner.nextLine().trim(); // 비밀번호를 입력받은 것을 loginPw에 담는다.

            if(loginPw.length() == 0){ //이것도 그냥 아예 안썼을때
                System.out.println("비밀번호를 입력해주세요."); // 입력해달라는 문구 출력
                continue; // 다시 로그인 비밀번호 쓰세요
            }
            if(member.getLoginPw().equals(loginPw) == false) {
            // 지금 입력한 로그인 비밀번호가 데이터베이스에 저장되어있는 비밀번호가 아닐때 위에서 id검색했는데 맞다 그럼 비밀번호 이제 대조해보는 겁니다.
            // 근데 대조해봤는데 false일때
            // dto.Member가보면 private로 loginPw를 선언해놨는데 맨 위에 보면 import lombok.Data;가 있는데
            // 이건 get, set을 굳이 만들지 않아도 바로 접근할수 있게 약간 오픈같은거? 비슷하게 해놔서
            // Member에 getLoginPw를 안만들어놔도 바로 get해서 데이터를 가져올 수 있습니다.
            // https://binit.tistory.com/21 여기에 설명 잘 나와있습니다 시간나면 한번 구경하세요
                System.out.println("비밀번호가 일치하지 않습니다."); // 일치하지 않는다고 문구 출력
                continue; // 다시 로그인로직으로 갑니다.
            }

            System.out.printf("\"%s\"님 환영합니다.\n", member.getName()); // 위에 로직을 무사히 다 통과했을시 이름나오면서 환영합니다. ex) 서빈님 환영합니다.
            Container.session.login(member);
            // 이건 이제 session에 login로직이 있습니다.
            // 가서 보면 이 사용자의 고유 id를 가져와서 로그인 되는겁니다.
            // session을 보면 loginedMemberId가 -1일때는 로그인이 아닌상태고 1부터는 로그인 상태입니다.
            // 그래서 나중에 로그아웃 할때는 고유id값을 -1로 바꿔버리고 loginedMember에 null값을 넣는거에요
            break;
        }

    }


    public void lougout() { // 로그아웃 로직 시작
        Container.session.logout();
        // 위에서 말한 session에서 logout로직을 보면 고유id(loginedMemberId)를 -1로 만들고
        // loginedMember를 null값으로 만들면서 logout 상태로 전환합니다.
        System.out.println("로그아웃 되었습니다."); // 로그아웃 되었다고 문구 출력
    }

    public void whoami() { // 사용자가 로그인중인가 확인하는 로직
        if(Container.session.isLogined() == false){
        // 이것도 session에 가보면 isLogined로직이 있는데 isLogined로직에는 loginedMemberId가 -1이 아니다 라고 되어있습니다.
        // 결국 loginedMemberId가 -1이다? 로그인 상태X / loginedMemberId가 정수다? 로그인 상태0
            System.out.println("로그인상태가 아닙니다."); // 조건이 loginedMemberId가 -1이라고 되어있으므로 로그인 상태가 아니라고 출력
        }else{ // 그 외에 -1이 아닌 정수일때 로그인한 사용자ID를 출력합니다.
            System.out.println(Container.session.loginedMember.getLoginId());
        }
    }
}
