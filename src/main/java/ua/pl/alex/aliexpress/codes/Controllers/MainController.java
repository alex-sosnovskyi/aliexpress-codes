package ua.pl.alex.aliexpress.codes.Controllers;

import com.google.gson.Gson;
import ua.pl.alex.aliexpress.codes.DTO.CouponDTO;
import ua.pl.alex.aliexpress.codes.service.impl.CouponServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aleksandr on 12.09.2019.
 */

@WebServlet(name = "MainController", urlPatterns = "/service")
public class MainController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String reg = request.getParameter("reg");
        if(null==reg || reg.length()==0 || reg.trim().length()==0 ||reg.isEmpty()){
            request.getRequestDispatcher("/pages/common/error.jsp").forward(request, response);
        }else{
            List<CouponDTO> CouponsByRegion = CouponServiceImpl.getInstance().getAllByCouponesByRegion(reg);
            String json = new Gson().toJson(CouponsByRegion);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//interesting ))
    }
}
