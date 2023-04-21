package org.example.repository;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.Map;

public class MemberRepository {
    public boolean isLoginIdDup(String loginId) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int join(String loginId, String loginPw, String name, String birth, String gender, String e_mail) {
        SecSql sql = new SecSql();
        sql.append("INSERT INTO \'user\'");
        sql.append("SET regDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginId = ?", loginId);
        sql.append(", loginPw = ?", loginPw);
        sql.append(", name = ?", name);
        sql.append(", birth = ?", birth);
        sql.append(", gender = ?", gender);
        sql.append(", e_mail = ?", e_mail);

        int id = DBUtil.insert(Container.conn, sql);

        return id;
    }

    public Member getMemberByLoginId(String loginId) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);

        Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

        if (memberMap.isEmpty()) {
            return null;
        }
        return new Member(memberMap);
    }

    public Member getMemberByLoginPw(String loginPw) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginPw = ?", loginPw);

        Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

        if (memberMap.isEmpty()) {
            return null;
        }
        return new Member(memberMap);
    }
}
