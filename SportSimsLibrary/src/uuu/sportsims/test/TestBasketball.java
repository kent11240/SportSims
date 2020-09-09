package uuu.sportsims.test;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.SportSimsException;

public class TestBasketball {

    public static void main(String[] args) {
        try {
            Basketball game = new Basketball();
            game.setGameId(1);
            game.setDateTime(2016, 3, 20, 7, 30);
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

            System.out.println(game);
            System.out.println(game.getDateTimeString());
            System.out.println(new java.sql.Time(game.getDateTime().getTime()));
            System.out.println(game.getStatus().ordinal());
        } catch (SportSimsException ex) {
            Logger.getLogger(TestBasketball.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
