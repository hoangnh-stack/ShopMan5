package view;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/** Đặt UTF-8 cho mọi yêu cầu/phản hồi để hiển thị đúng tiếng Việt. */
@WebFilter("/*")
public class CharsetFilter implements Filter {
    @Override public void init(FilterConfig cfg) { }
    @Override public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
    @Override public void destroy() { }
}
