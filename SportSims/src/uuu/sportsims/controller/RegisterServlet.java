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
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.MemberService;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {

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

        request.setCharacterEncoding("UTF-8"); //請求的編碼(request)
        //1.讀取請求中的表單資料
        String id = request.getParameter("id");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String passwordc = request.getParameter("passwordc");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String checkCode = request.getParameter("check_code");

        if (id == null || (id = id.trim()).length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (nickname == null || (nickname = nickname.trim()).length() == 0) {
            errors.add("必須輸入暱稱");
        }
        if (password == null || (password = password.trim()).length() == 0
                || passwordc == null || (passwordc = passwordc.trim()).length() == 0) {
            errors.add("必須輸入密碼與確認密碼");
        } else if (!password.equals(passwordc)) {
            errors.add("會員密碼與確認密碼不一致");
        }
        if (email == null || (email = email.trim()).length() == 0) {
            errors.add("必須輸入電子郵件");
        }
        if (birthday == null || (birthday = birthday.trim()).length() == 0) {
            errors.add("必須輸入生日");
        }
        if (checkCode == null || (checkCode = checkCode.trim()).length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            //檢查驗證碼
            String checkCodeC = (String) session.getAttribute("RegisterImageCheckCodeServlet");
            if (!checkCode.equalsIgnoreCase(checkCodeC)) {
                errors.add("驗證碼不正確");
            } else {
                session.removeAttribute("RegisterImageCheckCodeServlet");
            }
        }

        if (errors.size() == 0) {
            //2.呼叫商業邏輯
            try {
                Member member = new Member();
                member.setId(id);
                member.setNickname(nickname);
                member.setPassword(password);
                member.setEmail(email);
                member.setBirthday(birthday);

                MemberService service = new MemberService();
                service.register(member);

                //3.1 註冊成功, redirect to register_done.jsp
                response.sendRedirect(request.getContextPath() + "/register_done.jsp");
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

        //3.2 註冊失敗, forward to register.jsp
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
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
