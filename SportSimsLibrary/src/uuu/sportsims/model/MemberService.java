package uuu.sportsims.model;

import java.util.List;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class MemberService {

    private RDBMembersDAO dao = new RDBMembersDAO();

    public Member login(String id, String pwd) throws SportSimsException {
        if ((id == null || (id = id.trim()).length() == 0) || (pwd == null || (pwd = pwd.trim()).length() == 0)) {
            throw new SportSimsException("必須輸入帳號密碼");
        }
        Member member = dao.get(id);
        if (member == null || !(pwd.equals(member.getPassword()))) {
            throw new SportSimsException("帳號或密碼不正確");
        }
        if (member.getStatus() == Member.INVALID) {
            throw new SportSimsException("此帳號已停用，請聯絡客服。");
        }
        return member;
    }

    public void register(Member member) throws SportSimsException {
        dao.insert(member);
    }

    public void update(Member member) throws SportSimsException {
        dao.update(member);
    }

    public void invalid(Member member) throws SportSimsException {
        dao.delete(member);
    }

    public Member get(String id) throws SportSimsException {
        return dao.get(id);
    }

    public List<Member> getAll() throws SportSimsException {
        return dao.getAll();
    }
}
