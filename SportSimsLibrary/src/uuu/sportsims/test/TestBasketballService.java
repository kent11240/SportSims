/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Administrator
 */
public class TestBasketballService {

    public static void main(String[] args) {
        try {
            BasketballService service = new BasketballService();
            LotteryService lservice = new LotteryService();
            MemberService mservice = new MemberService();
            Member member = mservice.login("kent11240", "P@22w0rd");
            Lottery lottery = lservice.getByKeyAndMember(1, member);

            for (int i = 0; i < lottery.getSelections(); i++) {
                System.out.println(service.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]));
            }

//            Basketball game = new Basketball();
//            game.setDateTime(2016, 4, 13, 10, 30);
//            game.setAwayName("曼斐斯灰熊");
//            game.setHomeName("洛杉磯快艇");
//            game.setPointSpread(-8.5);
//            game.setTotal(198.5);
//            game.setAway(1.75);
//            game.setHome(1.75);
//            game.setOver(1.75);
//            game.setUnder(1.75);
//            game.setAwayPk(3.5);
//            game.setHomePk(1.15);
//            game.setAwayQ1(2.15);
//            game.setDrawQ1(13.0);
//            game.setHomeQ1(1.4);
//            game.setOdd(1.75);
//            game.setEven(1.75);
//            game.setAway1to5(6.0);
//            game.setAway6to10(9.75);
//            game.setAway11to15(18.0);
//            game.setAway16to20(25.0);
//            game.setAway21to25(28.0);
//            game.setAway26(30.0);
//            game.setHome1to5(4.25);
//            game.setHome6to10(3.75);
//            game.setHome11to15(4.0);
//            game.setHome16to20(5.25);
//            game.setHome21to25(7.75);
//            game.setHome26(8.25);
//            game.setStatus(Basketball.Status.UNSTARTED);
//            service.insert(game);
//            Basketball game = service.get(4);
//            System.out.println(game);
//            game.setAwayResult(109);
//            game.setHomeResult(102);
//            game.setAwayQ1Result(28);
//            game.setHomeQ1Result(24);
//            game.setStatus(Basketball.Status.PAYOUT);
//            service.update(game);
//            for (Basketball game : service.getAll()) {
//                System.out.println(game);
//            }
//            for (Basketball game : service.getAllByStatus(Basketball.Status.UNSTARTED)) {
//                System.out.println(game);
//            }
        } catch (SportSimsException ex) {
            Logger.getLogger(TestBasketballService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
