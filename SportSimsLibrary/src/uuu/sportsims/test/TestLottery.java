package uuu.sportsims.test;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.Lottery;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class TestLottery {

    public static void main(String[] args) {
        try {
            Member member = new Member();
            member.setId("kent11240");
            member.setPassword("1357924680");
            member.setNickname("Kent");
            member.setEmail("kent1108@gmail.com");
            member.setBirthday(1992, 11, 8);
            member.setMoney(500000);
            System.out.println(member + "\n");

            Basketball game = new Basketball();
            game.setGameId(1);
            game.setDateTime(new Date());
            game.setAwayName("洛杉磯快艇");
            game.setHomeName("聖安東尼奧馬刺");
            game.setPointSpread(-8.5);
            game.setTotal(202.5);
            game.setAway(1.75);
            game.setHome(1.75);
            game.setOver(1.75);
            game.setUnder(1.75);
            game.setAwayPk(3.30);
            game.setHomePk(1.18);
            game.setAwayQ1(2.15);
            game.setDrawQ1(14.00);
            game.setHomeQ1(1.40);
            game.setOdd(1.75);
            game.setEven(1.75);
            game.setAway1to5(6.00);
            game.setAway6to10(9.50);
            game.setAway11to15(18.00);
            game.setAway16to20(25.00);
            game.setAway21to25(28.00);
            game.setAway26(30.00);
            game.setHome1to5(4.25);
            game.setHome6to10(3.75);
            game.setHome11to15(4.25);
            game.setHome16to20(5.25);
            game.setHome21to25(7.75);
            game.setHome26(7.75);
            game.setAwayResult(87);
            game.setHomeResult(108);
            game.setAwayQ1Result(21);
            game.setHomeQ1Result(19);
            game.setStatus(Basketball.Status.PAYOUT);
            System.out.println(game + "\n");

            Basketball game2 = new Basketball();
            game2.setGameId(2);
            game2.setDateTime(new Date());
            game2.setAwayName("洛杉磯快艇");
            game2.setHomeName("聖安東尼奧馬刺");
            game2.setPointSpread(-8.5);
            game2.setTotal(202.5);
            game2.setAway(1.75);
            game2.setHome(1.75);
            game2.setOver(1.75);
            game2.setUnder(1.75);
            game2.setAwayPk(3.30);
            game2.setHomePk(1.18);
            game2.setAwayQ1(2.15);
            game2.setDrawQ1(14.00);
            game2.setHomeQ1(1.40);
            game2.setOdd(1.75);
            game2.setEven(1.75);
            game2.setAway1to5(6.00);
            game2.setAway6to10(9.50);
            game2.setAway11to15(18.00);
            game2.setAway16to20(25.00);
            game2.setAway21to25(28.00);
            game2.setAway26(30.00);
            game2.setHome1to5(4.25);
            game2.setHome6to10(3.75);
            game2.setHome11to15(4.25);
            game2.setHome16to20(5.25);
            game2.setHome21to25(7.75);
            game2.setHome26(7.75);
            game2.setAwayResult(87);
            game2.setHomeResult(108);
            game2.setAwayQ1Result(21);
            game2.setHomeQ1Result(19);
            game2.setStatus(Basketball.Status.PAYOUT);
            System.out.println(game + "\n");

            Lottery lottery = new Lottery();
            lottery.setLotteryId(1);
            lottery.setMember(member);
//            lottery.setDateTime(new Date());
            lottery.setSelections(2);
            Basketball[] gameArray = {game, game2};
            lottery.setGame(gameArray);
            String[] itemArray = {"AwayQ1", "AwayQ1"};
            lottery.setItem(itemArray);
            boolean[] passArray = {true, true};
            lottery.setPass(passArray);
            lottery.setStake(1000);
            lottery.setPrice(2150);
            lottery.setStatus(Lottery.Status.WIN);

            System.out.println(lottery);
        } catch (SportSimsException ex) {
            Logger.getLogger(TestBasketball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
