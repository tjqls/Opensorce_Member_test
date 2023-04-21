package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public void run() {
        Container.scanner = new Scanner(System.in); // Container 클래스의 scanner를 입력값을 받을 수 있게 선언
        Container.init(); // 이거 Container에서 만들어놓은 init함수 써야하는거 싹다 모아놓은거 사용하겠다고 선언 → 이러면 굳이 한번씩 선언하는걸 따로따로 선언할 필요 없이 여기서 선언만하면 됨

        while (true){ // while문 true 여서 무한히 실행
            System.out.printf("명령어) "); // 명령어 출력
            String cmd = Container.scanner.nextLine(); // 문자열 cmd는 Container의 scanner를 사용하여 입력값을 받겠다 즉 cmd = 입력값

            Container.rq = new Rq(cmd); //Container의 rq는 Rq클래스에 cmd를 매개변수로 집어넣어 사용하겠다



            //DB연결 시작점 이건 그냥 DB연결하기 위한 로직 연결 종료지점까지 그냥 라이브러리이기 때문에 긁어다가 사용하면 된다.
            Connection conn = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("예외 : MySQL 드라이버 로딩 실패");
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

            try {
                conn = DriverManager.getConnection(url, "root", "");

                Container.conn = conn;

                // action 메서드 실행 위에서 try로 실행시켜서 오류가 아닌 연결이 성공했다면 action함수 실행
                action(Container.rq, cmd);

            } catch (SQLException e) {
                System.out.println("예외 : MySQL 드라이버 로딩 실패");
                System.out.println("프로그램을 종료합니다.");
                break;
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //DB 연결 끝


        }
        Container.scanner.close();
    }

    private void action(Rq rq, String cmd){
        // 실질적인 명령어를 확인하여 어떤 로직을 사용하게 할것인지 여기서 판단 → ex) 명령어에 회원가입이라고 치면 memberController에 있는 join함수 실행하겠다.
        if(rq.getUrlPath().equals("회원가입")){
            Container.memberController.join();
        } else if(rq.getUrlPath().equals("로그인")){
            Container.memberController.login();
        } else if(rq.getUrlPath().equals("로그아웃")){
            Container.memberController.lougout();
        } else if(rq.getUrlPath().equals("로그인상태확인")){
            Container.memberController.whoami();
        } else if(rq.getUrlPath().equals("게시물작성")){
            Container.contentController.write();
        } else if(rq.getUrlPath().equals("게시물목록")){
            Container.contentController.showList();
        } else if(rq.getUrlPath().equals("상세목록")){
            Container.contentController.showDetail();
        } else if(rq.getUrlPath().equals("게시물수정")){
            Container.contentController.modify();
        } else if(rq.getUrlPath().equals("게시물삭제")){
            Container.contentController.delete();
        } else if(rq.getUrlPath().equals("개인정보수정")){
            Container.memberController.membermodify();
        }else if(cmd.equals("시스템종료")){
            System.out.println("시스템 종료");
            System.exit(0);
        }else {
            System.out.println("명령어를 확인해주세요.");
        }
        return;
    }

}
