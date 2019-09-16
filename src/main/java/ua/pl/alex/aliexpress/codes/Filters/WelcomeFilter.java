package ua.pl.alex.aliexpress.codes.Filters;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ua.pl.alex.aliexpress.codes.helper.PropertyHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by Aleksandr on 12.09.2019.
 */
@WebFilter(filterName = "WelcomeFilter", urlPatterns = "/service")
public class WelcomeFilter extends BaseHttpFilter {
    //private final String mainId = "123456789";

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String id = servletRequest.getParameter("id");
        if (null == id || id.length() == 0 || id.isEmpty()) {
            servletRequest.getRequestDispatcher("/pages/common/error.jsp").forward(servletRequest, servletResponse);
        } else {
            String trimedId = id.trim();
            try {
                byte[] data = Base64.decode(trimedId);
                if(null!=data){
                    trimedId = new String(data, "UTF-8");
                }else{
                    servletRequest.getRequestDispatcher("/pages/common/error.jsp").forward(servletRequest, servletResponse);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                servletRequest.getRequestDispatcher("/pages/common/error.jsp").forward(servletRequest, servletResponse);
            }
            if (trimedId.length() > 0) {
                if (!trimedId.equals(PropertyHolder.getInstance().getMainId())) {
                    servletRequest.getRequestDispatcher("/pages/common/error.jsp").forward(servletRequest, servletResponse);
                }else{
                   filterChain.doFilter(servletRequest,servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/pages/common/error.jsp").forward(servletRequest, servletResponse);
            }
        }
    }
}
