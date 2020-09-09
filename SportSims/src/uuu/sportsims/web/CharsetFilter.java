package uuu.sportsims.web;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "CharsetFilter", urlPatterns = {"*.jsp", "*.do"}, initParams = {
    @WebInitParam(name = "charset", value = "UTF-8")}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ERROR})
public class CharsetFilter implements Filter {

    private FilterConfig filterConfig;
    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String charset = filterConfig.getInitParameter("charset");
        if (charset != null) {
            this.charset = charset;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(charset);
        request.getParameterNames();//鎖住請求編碼設定, 忽略所有後來的encoding設定

        response.setCharacterEncoding(charset);
        response.getWriter(); //鎖住回應編碼設定, 忽略所有後來的encoding設定

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
