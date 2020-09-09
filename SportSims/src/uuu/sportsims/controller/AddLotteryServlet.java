/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.sportsims.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.BasketballService;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AddLotteryServlet", urlPatterns = {"/add_lottery.do"})
public class AddLotteryServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            List<Basketball> gameList = (ArrayList) session.getAttribute("gameList") != null ? (ArrayList) session.getAttribute("gameList") : new ArrayList();
            List<String> itemList = (ArrayList) session.getAttribute("itemList") != null ? (ArrayList) session.getAttribute("itemList") : new ArrayList();

            if (gameList.size() >= 8) { //最多投注8關
                response.setStatus(501);
                return;
            }

            String data = request.getParameter("data"); //收到前端傳送字串: gameId_item
            int gameId = Integer.parseInt(data.split("_")[0]); //切割字串 by "_"
            String item = data.split("_")[1]; //切割字串 by "_"

            BasketballService bService = new BasketballService();
            Basketball game = bService.get(gameId);
            if (!gameList.contains(game)) {
                gameList.add(game);
                itemList.add(item);
                session.setAttribute("gameList", gameList);
                session.setAttribute("itemList", itemList);
            } else { //重複賽事錯誤
                response.setStatus(500);
                return;
            }

            StringBuffer msg = new StringBuffer("<p><span>").append(game.getAwayName()).append('@').append(game.getHomeName()).append("</span><br/><span>").append(bService.getItemToString(item));
            if (item.equals("away") || item.equals("home")) {
                msg.append(" 主<b>").append(game.getPointSpread()).append("</b>");
            }
            if (item.equals("over") || item.equals("under")) {
                msg.append(" <b>").append(game.getTotal()).append("</b>");
            }
            msg.append(" <b>").append(game.getOddsByItem(item)).append("</b></span></p>");

            response.setContentType("text/plain;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println(msg);
            }
        } catch (SportSimsException ex) {
            Logger.getLogger(AddLotteryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
