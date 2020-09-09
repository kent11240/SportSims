package uuu.sportsims.model;

import java.util.List;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.SportSimsException;

public class BasketballService {

    private RDBBasketballsDAO dao = new RDBBasketballsDAO();

    public void insert(Basketball game) throws SportSimsException {
        dao.insert(game);
    }

    public void update(Basketball game) throws SportSimsException {
        dao.update(game);
    }

    public Basketball get(int gameId) throws SportSimsException {
        return dao.get(gameId);
    }

    public List<Basketball> getAll() throws SportSimsException {
        return dao.getAll();
    }

    public List<Basketball> getAllByStatus(Basketball.Status status) throws SportSimsException {
        return dao.getAllByStatus(status);
    }

    public int checkGamePass(Basketball game, String item) throws SportSimsException {
        if (game.getAwayResult() == 0 || game.getHomeResult() == 0 || game.getAwayQ1Result() == 0 || game.getHomeQ1Result() == 0) {
            return 2;
        }
        switch (item) {
            case "away":
                if ((double) game.getAwayResult() > ((double) game.getHomeResult() + game.getPointSpread())) {
                    return 1;
                }
                return 0;
            case "home":
                if ((double) game.getAwayResult() < ((double) game.getHomeResult() + game.getPointSpread())) {
                    return 1;
                }
                return 0;
            case "over":
                if ((double) game.getAwayResult() + (double) game.getHomeResult() > game.getTotal()) {
                    return 1;
                }
                return 0;
            case "under":
                if ((double) game.getAwayResult() + (double) game.getHomeResult() < game.getTotal()) {
                    return 1;
                }
                return 0;
            case "awayPk":
                if (game.getAwayResult() > game.getHomeResult()) {
                    return 1;
                }
                return 0;
            case "homePk":
                if (game.getAwayResult() < game.getHomeResult()) {
                    return 1;
                }
                return 0;
            case "awayQ1":
                if (game.getAwayQ1Result() > game.getHomeQ1Result()) {
                    return 1;
                }
                return 0;
            case "drawQ1":
                if (game.getAwayQ1Result() == game.getHomeQ1Result()) {
                    return 1;
                }
                return 0;
            case "homeQ1":
                if (game.getAwayQ1Result() < game.getHomeQ1Result()) {
                    return 1;
                }
                return 0;
            case "odd":
                if ((game.getAwayResult() + game.getHomeResult()) % 2 == 1) {
                    return 1;
                }
                return 0;
            case "even":
                if ((game.getAwayResult() + game.getHomeResult()) % 2 == 0) {
                    return 1;
                }
                return 0;
            case "away1to5":
                if ((game.getAwayResult() - game.getHomeResult()) >= 1 & (game.getAwayResult() - game.getHomeResult()) <= 5) {
                    return 1;
                }
                return 0;
            case "away6to10":
                if ((game.getAwayResult() - game.getHomeResult()) >= 6 & (game.getAwayResult() - game.getHomeResult()) <= 10) {
                    return 1;
                }
                return 0;
            case "away11to15":
                if ((game.getAwayResult() - game.getHomeResult()) >= 11 & (game.getAwayResult() - game.getHomeResult()) <= 15) {
                    return 1;
                }
                return 0;
            case "away16to20":
                if ((game.getAwayResult() - game.getHomeResult()) >= 16 & (game.getAwayResult() - game.getHomeResult()) <= 20) {
                    return 1;
                }
                return 0;
            case "away21to25":
                if ((game.getAwayResult() - game.getHomeResult()) >= 21 & (game.getAwayResult() - game.getHomeResult()) <= 25) {
                    return 1;
                }
                return 0;
            case "away26":
                if ((game.getAwayResult() - game.getHomeResult()) >= 26) {
                    return 1;
                }
                return 0;
            case "home1to5":
                if ((game.getHomeResult() - game.getAwayResult()) >= 1 & (game.getHomeResult() - game.getAwayResult()) <= 5) {
                    return 1;
                }
                return 0;
            case "home6to10":
                if ((game.getHomeResult() - game.getAwayResult()) >= 6 & (game.getHomeResult() - game.getAwayResult()) <= 10) {
                    return 1;
                }
                return 0;
            case "home11to15":
                if ((game.getHomeResult() - game.getAwayResult()) >= 11 & (game.getHomeResult() - game.getAwayResult()) <= 15) {
                    return 1;
                }
                return 0;
            case "home16to20":
                if ((game.getHomeResult() - game.getAwayResult()) >= 16 & (game.getHomeResult() - game.getAwayResult()) <= 20) {
                    return 1;
                }
                return 0;
            case "home21to25":
                if ((game.getHomeResult() - game.getAwayResult()) >= 21 & (game.getHomeResult() - game.getAwayResult()) <= 25) {
                    return 1;
                }
                return 0;
            case "home26":
                if ((game.getHomeResult() - game.getAwayResult()) >= 26) {
                    return 1;
                }
                return 0;
            default:
                throw new SportSimsException("賠率項目名稱錯誤！");
        }
    }
    
    public String getItemToString(String item) throws SportSimsException {
        switch (item) {
            case "away":
                return "客讓分";
            case "home":
                return "主讓分";
            case "over":
                return "大分";
            case "under":
                return "小分";
            case "awayPk":
                return "客不讓分";
            case "homePk":
                return "主不讓分";
            case "awayQ1":
                return "客第一節";
            case "drawQ1":
                return "和第一節";
            case "homeQ1":
                return "主第一節";
            case "odd":
                return "單";
            case "even":
                return "雙";
            case "away1to5":
                return "客 1-5";
            case "away6to10":
                return "客 6-10";
            case "away11to15":
                return "客 11-15";
            case "away16to20":
                return "客 16-20";
            case "away21to25":
                return "客 21-25";
            case "away26":
                return "客 26+";
            case "home1to5":
                return "主 1-5";
            case "home6to10":
                return "主 6-10";
            case "home11to15":
                return "主 11-15";
            case "home16to20":
                return "主 16-20";
            case "home21to25":
                return "主 21-25";
            case "home26":
                return "主 26+";
            default:
                throw new SportSimsException("賠率項目名稱錯誤！");
        }
    }
}
