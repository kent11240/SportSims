package uuu.sportsims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.Lottery;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.BasketballService;
import uuu.sportsims.model.LotteryService;
import uuu.sportsims.model.MemberService;

@WebServlet(name = "BetServlet", urlPatterns = {"/user/bet.do"})
public class BetServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //錯誤訊息
        List<String> errors = new ArrayList();

        //準備Member member
        Member member = (Member) session.getAttribute("user");

        //準備Basketball game[]
        List<Basketball> selectGameList = (ArrayList) session.getAttribute("gameList");
        Basketball game[] = new Basketball[selectGameList.size()];
        for (int i = 0; i < selectGameList.size(); i++) {
            game[i] = selectGameList.get(i);
        }

        //準備String item[]
        List<String> selectItemList = (ArrayList) session.getAttribute("itemList");
        String item[] = new String[selectItemList.size()];
        for (int i = 0; i < selectItemList.size(); i++) {
            item[i] = selectItemList.get(i);
        }

        //準備stake
        String stakeStr = request.getParameter("stake");
        int stake = Integer.parseInt(stakeStr) * 10;

        //準備boolean pass[]
        String passStr[] = new String[8];
        for (int i = 0; i < 8; i++) {
            passStr[i] = request.getParameter("pass" + (i + 1));
        }
        boolean pass[] = new boolean[selectGameList.size()];
        for (int i = 0; i < pass.length; i++) {
            pass[i] = (passStr[i] != null);
        }

        try {
            //存檔前必須回到資料庫再確認所有賽事為可下注狀態
            BasketballService bService = new BasketballService();
            for (int i = 0; i < game.length; i++) {
                if (bService.get(game[i].getGameId()).getStatus() != Basketball.Status.UNSTARTED) {
                    throw new SportSimsException("賽事編號：" + game[i].getGameId() + "，目前無法下注，請回到賽事列表重新選擇");
                }
            }

            Lottery lottery = new Lottery();
            lottery.setMember(member);
            lottery.setSelections(selectGameList.size());
            lottery.setGame(game);
            lottery.setItem(item);
            lottery.setPass(pass);
            lottery.setStake(stake);

            if (member.getMoney() < (stake * lottery.getCombination(pass))) {
                throw new SportSimsException("剩餘金額不足");
            } else {
                member.setMoney(member.getMoney() - (stake * lottery.getCombination(pass)));
            }

            MemberService mService = new MemberService();
            LotteryService lSerivce = new LotteryService();
            mService.update(member);
            lSerivce.insert(lottery);

            session.removeAttribute("gameList");
            session.removeAttribute("itemList");
            response.sendRedirect(request.getContextPath() + "/user/lottery.jsp");
            return;
        } catch (SportSimsException ex) {
            if (ex.getCause() != null) {
                errors.add(ex.getCause().getMessage());
            } else {
                errors.add(ex.getMessage());
            }
        } catch (Exception ex) {
            errors.add(ex.toString());
        }

        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/bet_detail.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
