package uuu.sportsims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.LotteryService;
import uuu.sportsims.model.MemberService;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"})
public class LoginServlet extends HttpServlet {

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

        List<String> errors = new ArrayList();//錯誤訊息
        HttpSession session = request.getSession(); //從request中取得session id對應之session或create new session

        //1.讀取請求中的表單資料
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("check_code");

        if (id == null || (id = id.trim()).length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (password == null || (password = password.trim()).length() == 0) {
            errors.add("必須輸入密碼");
        }
        if (checkCode == null || (checkCode = checkCode.trim()).length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            //檢查驗證碼
            String checkCodeC = (String) session.getAttribute("LoginImageCheckCodeServlet");
            if (!checkCode.equalsIgnoreCase(checkCodeC)) {
                errors.add("驗證碼不正確");
            } else {
                session.removeAttribute("LoginImageCheckCodeServlet");
            }
        }

        if (errors != null && errors.size() == 0) {
            //2.呼叫商業邏輯
            MemberService service = new MemberService();
            try {
                Member member = service.login(id, password);
                if (member != null) {
                    //3.1 登入成功, forward to index.jsp
                    session.setAttribute("user", member);
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return;
                }
            } catch (SportSimsException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, "無法登入", ex);
                if (ex.getCause() != null) {
                    this.log("無法登入", ex);
                    errors.add("無法登入，請聯絡管理員");
                } else {
                    errors.add(ex.getMessage());
                }
            }
        }

        //3.2 登入失敗, forward to /login.jsp
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
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
