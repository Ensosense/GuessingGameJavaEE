package se.iths.guessthenumbergame;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/guess")
public class GuessTheNumberServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            game = new Game();
            session.setAttribute("game", game);
        }

        response.setContentType("text/html");

        // getParameter är det som klienten skickar till oss, vi hämtar det och förvarar i en variabel. Med hjälp av request vi efterfrågar parametrarna från klienten, i detta fallet guess
        int guess = Integer.parseInt(request.getParameter("guess"));


        String str = game.getAnswer(guess);

        // Response (ответ) - это объект, который представляет ответ от сервера клиенту
        response.getWriter().println("<html><body>" + str);

        for (String s : game.getList()) {
            response.getWriter().println(s + "<br/>");
        }

        response.getWriter().println("<form method=\"post\" action=\"http://localhost:8080/guess\">\n" +
                "    <input name=\"guess\" type=\"text\" size=\"8\" value=\"\"/>\n" +
                "    <br/>\n" +
                "    <input name=\"submit\" type=\"submit\" value=\"Send\"/>\n" +
                "    <input type=\"reset\">\n" +
                "</form> </body> </html>\n"
        );

        if (str.equals("You win! New game")) {
                game = new Game();
                session.setAttribute("game", game);
            }
        }
    }






