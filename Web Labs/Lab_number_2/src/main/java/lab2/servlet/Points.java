package lab2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lab2.model.ResultBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "points", value = "/Points")
public class Points extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<ResultBeans> resultList;
        if (session.getAttribute("resultList") != null) {
            resultList = (List<ResultBeans>) session.getAttribute("resultList");
        } else {
            resultList = new ArrayList<>();
        }

        resultList.addAll((List<ResultBeans>) getServletContext().getAttribute("resultList"));

        session.setAttribute("resultList", resultList);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(resultList);
        resp.getWriter().write(json);
    }
}