package uuu.sportsims.model;

import java.util.List;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.Lottery;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class LotteryService {

    private RDBLotteriesDAO dao = new RDBLotteriesDAO();

    public void insert(Lottery lottery) throws SportSimsException {
        dao.insert(lottery);
    }

    public void update(Lottery newLottery, Member member) throws SportSimsException {
        Lottery oldLottery = dao.getByKeyAndMember(newLottery.getLotteryId(), member);
        if (oldLottery == null) {
            throw new SportSimsException("該彩券不存在或不屬於該客戶不得更改。");
        }
        if (oldLottery.getStatus() != Lottery.Status.NOTPAYOUT) {
            throw new SportSimsException("該彩券已開獎或作廢不得進行更改。");
        } else if (newLottery.getStatus() == Lottery.Status.INVALID) {
            if (newLottery.getDateTime().getTime() - oldLottery.getDateTime().getTime() > 5 * 60 * 1000) { //購買時間五分鐘才可以作廢
                throw new SportSimsException("該彩券購買時間已超過五分鐘，不得作廢。");
            }
        }
        dao.update(newLottery);
    }

    //表列彩券用
    public List<Lottery> getAllByMember(Member member) throws SportSimsException {
        return dao.getAllByMember(member);
    }

    //查看彩券明細用
    public Lottery getByKeyAndMember(int lotteryId, Member member) throws SportSimsException {
        Lottery lottery = dao.getByKeyAndMember(lotteryId, member);
        double gameInfo[][] = dao.getGameInfoByKeyAndMember(lotteryId, member);

        if (lottery != null && gameInfo != null) {
            //Call BasketballService 將gameId陣列轉換為Basketball陣列存入lottery中
            BasketballService service = new BasketballService();
            Basketball game[] = new Basketball[lottery.getSelections()];
            for (int i = 0; i < game.length; i++) {
                game[i] = service.get((int) gameInfo[i][0]);
                if (lottery.getItem()[i].equals("away") || lottery.getItem()[i].equals("home")) {
                    game[i].setPointSpread(gameInfo[i][1]);
                } else if (lottery.getItem()[i].equals("over") || lottery.getItem()[i].equals("under")) {
                    game[i].setTotal(gameInfo[i][1]);
                }
                game[i].setOddsByItem(lottery.getItem()[i], gameInfo[i][2]);
            }
            lottery.setGame(game);
        }

        return lottery;
    }

    public double getHighestPrice(Lottery lottery) throws SportSimsException {
        double price = 0;
        int selections = lottery.getSelections();
        int stake = lottery.getStake();

        double odds[] = new double[selections];
        for (int i = 0; i < selections; i++) {
            odds[i] = lottery.getGame()[i].getOddsByItem(lottery.getItem()[i]);
        }

        for (int i = 0; i < selections; i++) {
            if (lottery.getPass()[i]) {
                price += priceCalculate(selections, i + 1, stake, odds);
            }
        }

        return price;
    }

    public double getCurrentPrice(Lottery lottery) throws SportSimsException {
        double price = 0;
        int selections = lottery.getSelections();
        int stake = lottery.getStake();
        BasketballService bService = new BasketballService();

        double odds[] = new double[selections];
        for (int i = 0; i < selections; i++) {
            if (bService.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]) == 2) {
                return -1;
            } else if (bService.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]) == 1) {
                odds[i] = lottery.getGame()[i].getOddsByItem(lottery.getItem()[i]);
            } else {
                odds[i] = 0;
            }
        }

        for (int i = 0; i < selections; i++) {
            if (lottery.getPass()[i]) {
                price += priceCalculate(selections, i + 1, stake, odds);
            }
        }

        return price;
    }

    public double priceCalculate(int selections, int pass, int stake, double[] odds) {
        double price = 0;

        switch (pass) {
            case 1:
                for (int i = 0; i < selections; i++) {
                    price = price + Math.round(odds[i] * stake);
                }
                break;
            case 2:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        price = price + Math.round(odds[i] * odds[j] * stake);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            price = price + Math.round(odds[i] * odds[j] * odds[k] * stake);
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            for (int l = k + 1; l < selections; l++) {
                                price = price + Math.round(odds[i] * odds[j] * odds[k] * odds[l] * stake);
                            }
                        }
                    }
                }
                break;
            case 5:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            for (int l = k + 1; l < selections; l++) {
                                for (int m = l + 1; m < selections; m++) {
                                    price = price + Math.round(odds[i] * odds[j] * odds[k] * odds[l] * odds[m] * stake);
                                }
                            }
                        }
                    }
                }
                break;
            case 6:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            for (int l = k + 1; l < selections; l++) {
                                for (int m = l + 1; m < selections; m++) {
                                    for (int n = m + 1; n < selections; n++) {
                                        price = price + Math.round(odds[i] * odds[j] * odds[k] * odds[l]
                                                * odds[m] * odds[n] * stake);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 7:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            for (int l = k + 1; l < selections; l++) {
                                for (int m = l + 1; m < selections; m++) {
                                    for (int n = m + 1; n < selections; n++) {
                                        for (int o = n + 1; o < selections; o++) {
                                            price = price + Math.round(odds[i] * odds[j] * odds[k] * odds[l]
                                                    * odds[m] * odds[n] * odds[o] * stake);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 8:
                for (int i = 0; i < selections; i++) {
                    for (int j = i + 1; j < selections; j++) {
                        for (int k = j + 1; k < selections; k++) {
                            for (int l = k + 1; l < selections; l++) {
                                for (int m = l + 1; m < selections; m++) {
                                    for (int n = m + 1; n < selections; n++) {
                                        for (int o = n + 1; o < selections; o++) {
                                            for (int p = o + 1; p < selections; p++) {
                                                price = price + Math.round(odds[i] * odds[j] * odds[k] * odds[l]
                                                        * odds[m] * odds[n] * odds[o] * odds[p] * stake);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }

        return price;
    }

    //兌獎
    public void claimLottery(Member member, List<Lottery> list) throws SportSimsException {
        MemberService mService = new MemberService();
        for (Lottery lottery : list) {
            if (lottery.getStatus() == Lottery.Status.NOTPAYOUT) { //檢查尚未派彩之彩券
                lottery = getByKeyAndMember(lottery.getLotteryId(), member); //取得明細
                if (getCurrentPrice(lottery) > 0) { //CurrentPrice > 0, 有中獎
                    lottery.setPrice((int) getCurrentPrice(lottery));
                    lottery.setStatus(Lottery.Status.WIN);
                    member.setMoney(member.getMoney() + (int) getCurrentPrice(lottery));
                    update(lottery, member);
                    mService.update(member);
                } else if (getCurrentPrice(lottery) == 0) { //CurrentPrice == 0, 沒中獎
                    lottery.setStatus(Lottery.Status.LOSE);
                    update(lottery, member);
                }
            }
        }
    }
}
