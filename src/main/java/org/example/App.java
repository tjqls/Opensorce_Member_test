package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public void run() {
        Container.scanner = new Scanner(System.in);
        Container.init();

        while (true){
            System.out.printf("명령어) ");
            String cmd = Container.scanner.nextLine();

            Container.rq = new Rq(cmd);



            //DB연결 시작점
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
                conn = DriverManager.getConnection(url, "3306", "");

                Container.conn = conn;

                // action 메서드 실행
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
        } else if(cmd.equals("시스템종료")){
            System.out.println("시스템 종료");
            System.exit(0);
        }else {
            System.out.println("명령어를 확인해주세요.");
        }
        return;
    }

}
