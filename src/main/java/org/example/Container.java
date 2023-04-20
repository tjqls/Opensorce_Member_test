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

public class Container {
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

    public static void init(){
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
