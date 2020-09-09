package uuu.sportsims.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.Lottery;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.BasketballService;
import uuu.sportsims.model.LotteryService;
import uuu.sportsims.model.MemberService;

public class TestLotteryService {

    public static void main(String[] args) {
        try {
            LotteryService service = new LotteryService();
            MemberService mservice = new MemberService();
            BasketballService bservice = new BasketballService();
            Member member = mservice.login("kent11240", "P@22w0rd");
            Lottery lottery = new Lottery();
            lottery.setMember(member);
            lottery.setSelections(3);
            Basketball game[] = {bservice.get(7), bservice.get(8), bservice.get(9)};
            lottery.setGame(game);
            String item[] = {"over", "over", "over"};
            lottery.setItem(item);
            boolean pass[] = {false, false, true};
            lottery.setPass(pass);
            lottery.setStake(100);
            
            service.insert(lottery);
            
            System.out.println(service.getAllByMember(member));

//            Lottery lottery1 = service.getByKeyAndMember(1, member);
//            System.out.println(service.getHighestPrice(lottery1));
//            System.out.println(service.getCurrentPrice(lottery1));
//            Lottery lottery2 = service.getByKeyAndMember(2, member);
//            System.out.println(service.getHighestPrice(lottery2));
//            System.out.println(service.getCurrentPrice(lottery2));
//            Lottery lottery3 = service.getByKeyAndMember(3, member);
//            System.out.println(service.getHighestPrice(lottery3));
//            System.out.println(service.getCurrentPrice(lottery3));
        } catch (SportSimsException ex) {
            Logger.getLogger(TestLotteryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
