package lab2.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "controllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {
    double[] rArray = {1, 2, 3, 4, 5};

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String forwardPath = getServletContext().getContextPath();
        String x = request.getParameter("hidden-x");
        String y = request.getParameter("hidden-y");
        String r = request.getParameter("hidden-r");
        if (x != null && y != null && r != null && validateCoordinates(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(r))) {
            forwardPath = this.getServletContext().getContextPath() + "/area-check?x-select="
                    + request.getParameter("hidden-x") + "&y-select="
                    + request.getParameter("hidden-y") + "&r-select="
                    + request.getParameter("hidden-r");
        }
        response.sendRedirect(forwardPath);
    }
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String forwardPath = getServletContext().getContextPath();
        response.sendRedirect(forwardPath);
    }

    private boolean validateCoordinates(double x, double y, double r) {
        boolean validX = -2 <= x && x <= 2;
        boolean validY = -5 <= y && y <= 3;
        boolean validR = false;
        for (double v : rArray) {
            if (v == r) {
                validR = true;
                break;
            }
        }
        return validX && validY && validR;
    }
}
