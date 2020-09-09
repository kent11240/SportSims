package uuu.sportsims.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.RDBMembersDAO;

public class TestRDBMembersDAO {

    public static void main(String[] args) {
        try {
            RDBMembersDAO dao = new RDBMembersDAO();
            Member member = new Member();

//            member.setId("test001");
//            member.setPassword("P@22w0rd");
//            member.setNickname("測試人員001");
//            member.setEmail("test001@sportsims.com");
//            member.setBirthday(1997, 3, 17);
////            member.setMoney(500000);
////            member.setStatus(Member.ENABLE);
//            dao.insert(member);

//            member.setId("test001");
//            member.setPassword("P@22w0rd");
//            member.setNickname("測試人員001");
//            member.setEmail("test001@sportsims.com");
//            member.setBirthday(1997, 3, 17);
//            member.setMoney(1000000);
//            member.setStatus(Member.ENABLE);
//            dao.update(member);

//            member.setId("test001");
//            member.setPassword("P@22w0rd");
//            member.setNickname("測試人員001");
//            member.setEmail("test001@sportsims.com");
//            member.setBirthday(1997, 3, 17);
//            member.setMoney(1000000);
//            member.setStatus(Member.ENABLE);
//            dao.delete(member);

//            member = dao.get("test001");
//            System.out.println(member);

            List<Member> list = dao.getAll();
            System.out.println(list);

        } catch (SportSimsException ex) {
            Logger.getLogger(TestRDBMembersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
