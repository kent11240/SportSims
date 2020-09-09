package uuu.sportsims.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.MemberService;

public class TestMemberService {

    public static void main(String[] args) {
        try {
            MemberService service = new MemberService();
            
//            Member member = service.login("test001", "P@22w0rd");
//            System.out.println(member);

//            Member member = service.login("kent11240", "P@22w0rd");
//            System.out.println(member);

//            Member member = service.get("test001");
//            System.out.println(member);
            
            List<Member> list = service.getAll();
            System.out.println(list);
            
        } catch (SportSimsException ex) {
            Logger.getLogger(TestMemberService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
