/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.sportsims.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uuu.sportsims.model.LotteryService;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "PriceCalculateServlet", urlPatterns = {"/price_cal.do"})
public class PriceCalculateServlet extends HttpServlet {

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
        String selectionsStr = request.getParameter("selectionsStr");
        String stakeStr = request.getParameter("stakeStr");
        String passStr = request.getParameter("passStr");
        String oddsStr = request.getParameter("oddsStr");

        response.setContentType("text/plain;charset=UTF-8");

        if (selectionsStr.equals("") || stakeStr.equals("") || passStr.equals("") || oddsStr.equals("")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("0");
            }
            return;
        }

        LotteryService lService = new LotteryService();
        double price = 0;
        int selections = Integer.parseInt(selectionsStr);
        int stake = Integer.parseInt(stakeStr);
        int pass[] = new int[passStr.split("_").length];
        if (passStr.contains("_")) {
            for (int i = 0; i < pass.length; i++) {
                pass[i] = Integer.parseInt(passStr.split("_")[i]);
            }
        } else {
            pass[0] = Integer.parseInt(passStr);
        }

        double odds[] = new double[oddsStr.split("_").length];
        if (oddsStr.contains("_")) {
            for (int i = 0; i < odds.length; i++) {
                odds[i] = Double.parseDouble(oddsStr.split("_")[i]);
            }
        } else {
            odds[0] = Double.parseDouble(oddsStr);
        }

        for (int i = 0; i < pass.length; i++) {
            price += lService.priceCalculate(selections, pass[i], stake, odds);
        }

        DecimalFormat df = new DecimalFormat("#,##0");
        try (PrintWriter out = response.getWriter()) {
            out.println(df.format(price));
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
