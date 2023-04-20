package org.example;

import org.example.controller.ContentController;
import org.example.controller.MemberController;
import org.example.repository.ContentRepository;
import org.example.repository.MemberRepository;
import org.example.service.ContentService;
import org.example.service.MemberService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container { // 쓸 것들을 선언해놓음 Container 클래스 안에
    // Container 클래스
    public static ContentRepository contentRepository;
    public static MemberRepository memberRepository;

    public static ContentService contentService;
    public static MemberService memberService;

    public static ContentController contentController;
    public static MemberController memberController;

    public static Scanner scanner;
    public static Session session;
    public static Connection conn;
    public static Rq rq;

    public static void init(){ //App에서 다 쓰는 것들을 모아놓음 나중에 App클래스에다 선언만 하면 싹다 사용 가능 하나의 컨테이너 안에 여러 요소들을 집어넣음
        contentRepository = new ContentRepository();
        memberRepository = new MemberRepository();

        contentService = new ContentService();
        memberService = new MemberService();

        contentController = new ContentController();
        memberController = new MemberController();

        scanner = new Scanner(System.in);
        session = new Session();
    }

}
