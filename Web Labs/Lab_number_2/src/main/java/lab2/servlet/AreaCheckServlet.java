package lab2.servlet;

import lab2.view.GetTemplate;
import lab2.model.ResultBeans;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "areaCheckServlet", value = "/area-check")
public class AreaCheckServlet extends HttpServlet {

    private List<ResultBeans> resultList;

    @Override
    public void init() {
        resultList = new LinkedList<>();
        getServletContext().setAttribute("resultList", resultList);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final long startExec = System.nanoTime();

        final String ctx = this.getServletContext().getContextPath();

        final String x = request.getParameter("x-select");
        final String y = request.getParameter("y-select");
        final String r = request.getParameter("r-select");

        final double dx;
        final double dy;
        final double dr;
        try {
            dx = Double.parseDouble(x);
            dy = Double.parseDouble(y);
            dr = Double.parseDouble(r);

        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(400);
            return;
        }

        final boolean result = checkArea(dx, dy, dr);

        final long endExec = System.nanoTime();
        final long executionTime = endExec - startExec;
        final LocalDateTime executedAt = LocalDateTime.now();

        final ResultBeans data = new ResultBeans();
        data.setX(dx);
        data.setY(dy);
        data.setR(dr);
        data.setResult(result);
        data.setCalculationTime(executionTime);
        data.setCalculatedAt(executedAt);


        synchronized (resultList) {
            resultList.add(0, data);
        }
        getServletContext().setAttribute("resultList", resultList);
        GetTemplate.renderView(response, ctx, data);
    }

    public boolean checkSquare(double x, double y, double r) {
        return (x <= 0 && y <= 0 && Math.abs(x) <= r && Math.abs(y) <= r / 2);
    }

    public boolean checkCircle(double x, double y, double r) {
        return (x >= 0 && y >= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)));
    }

    public boolean checkTriangle(double x, double y, double r) {
        return (x >= 0 && y <= 0 && x <= r && y >= -(r)/2 && y >= (x / 2) - (r)/2);
    }

    public boolean checkArea(double x, double y, double r) {
        return checkCircle(x, y, r) || checkTriangle(x, y, r) || checkSquare(x, y, r);
    }
}
