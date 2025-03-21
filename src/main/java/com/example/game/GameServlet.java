package com.example.game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");

        try {
            if ("start".equals(action)) {
                int min = Integer.parseInt(request.getParameter("min"));
                int max = Integer.parseInt(request.getParameter("max"));

                if (min >= max) {
                    response.sendRedirect("error.jsp");
                    return;
                }

                int guess = (min + max) / 2;

                session.setAttribute("min", min);
                session.setAttribute("max", max);
                session.setAttribute("guess", guess);

                response.sendRedirect("game.jsp");
            } else if ("respond".equals(action)) {
                String result = request.getParameter("result");

                Integer min = (Integer) session.getAttribute("min");
                Integer max = (Integer) session.getAttribute("max");
                Integer guess = (Integer) session.getAttribute("guess");

                if (min == null || max == null || guess == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }

                switch (result) {
                    case "greater":
                        min = guess + 1;
                        break;
                    case "less":
                        max = guess - 1;
                        break;
                    case "equal":
                        response.sendRedirect("win.jsp");
                        return;
                }

                if (min > max) {
                    response.sendRedirect("cheat.jsp");
                    return;
                }

                guess = (min + max) / 2;

                session.setAttribute("min", min);
                session.setAttribute("max", max);
                session.setAttribute("guess", guess);

                response.sendRedirect("game.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
