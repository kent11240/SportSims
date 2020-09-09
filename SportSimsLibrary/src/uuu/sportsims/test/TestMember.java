package uuu.sportsims.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class TestMember {

    public static void main(String[] args) {
        try {
            Member member = new Member();
            
            member.setId("kents");
            member.setPassword("1357924680");
            member.setNickname("Kent");
            member.setEmail("kent1108@gmail.com");
            member.setBirthday(1992, 11, 8);
            member.setMoney(500000);
            member.setStatus(Member.VALID);
            
//            member.setId("中文");
//            member.setPassword("1234");
//            member.setNickname("");
//            member.setEmail("a12345gmail.com");
//            member.setBirthday(2015, 10, 10);
//            member.setMoney(-1000);
            
            System.out.println(member);
            System.out.println(member.getBirthdayString());
        } catch (SportSimsException ex) {
            Logger.getLogger(TestMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
