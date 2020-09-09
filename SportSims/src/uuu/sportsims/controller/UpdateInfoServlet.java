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

@WebServlet(name = "UpdateInfoServlet", urlPatterns = {"/user/update_info.do"})
public class UpdateInfoServlet extends HttpServlet {

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
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");

        if (password == null || (password = password.trim()).length() == 0) {
            errors.add("必須輸入原密碼");
        } else if (!member.getPassword().equals(password)) {
            errors.add("原密碼不正確");
        }
        if (nickname == null || (nickname = nickname.trim()).length() == 0) {
            errors.add("必須輸入暱稱");
        }
        if (email == null || (email = email.trim()).length() == 0) {
            errors.add("必須輸入電子郵件");
        }
        if (birthday == null || (birthday = birthday.trim()).length() == 0) {
            errors.add("必須輸入生日");
        }
        if (errors.size() == 0) {
            //2.呼叫商業邏輯
            try {
                member.setNickname(nickname);
                member.setEmail(email);
                member.setBirthday(birthday);
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

        //3.2 註冊失敗, forward to member_info.jsp
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/member_info.jsp");
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
