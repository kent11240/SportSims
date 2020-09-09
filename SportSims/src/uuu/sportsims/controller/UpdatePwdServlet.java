package uuu.sportsims.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.MemberService;

@WebServlet(name = "UpdatePwdServlet", urlPatterns = {"/user/update_pwd.do"})
public class UpdatePwdServlet extends HttpServlet {

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
        //錯誤訊息
        List<String> errors = new ArrayList();
        HttpSession session = request.getSession(); //從request中取得session id對應之session或create new session
        Member member = (Member) session.getAttribute("user");

        request.setCharacterEncoding("UTF-8"); //請求的編碼(request)
        //1.讀取請求中的表單資料
        String id = member.getId();
        String passwordo = request.getParameter("passwordo");
        String password = request.getParameter("password");
        String passwordc = request.getParameter("passwordc");

        if (passwordo == null || (passwordo = passwordo.trim()).length() == 0) {
            errors.add("必須輸入原密碼");
        } else if (!member.getPassword().equals(passwordo)) {
            errors.add("原密碼不正確");
        }
        if (password == null || (password = password.trim()).length() == 0
                || passwordc == null || (passwordc = passwordc.trim()).length() == 0) {
            errors.add("必須輸入新密碼與確認新密碼");
        } else if (!password.equals(passwordc)) {
            errors.add("會員密碼與確認密碼不一致");
        }

        if (errors.size() == 0) {
            //2.呼叫商業邏輯
            try {
                member.setPassword(password);
                MemberService service = new MemberService();
                service.update(member);

                //3.1 修改成功, redirect to member.jsp
                response.sendRedirect(request.getContextPath() + "/user/member.jsp");
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
        }

        //3.2 註冊失敗, forward to member_pwd.jsp
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/member_pwd.jsp");
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
