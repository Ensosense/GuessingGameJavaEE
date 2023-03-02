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

        int guess = Integer.parseInt(request.getParameter("guess"));

        String str = game.getAnswer(guess);

        response.getWriter().println("<html><body>" + str);

        for (String s : game.getList()) {
            response.getWriter().println(s + "<br/>");
        }

        response.getWriter().println("<form method=\"post\" action=\"http://localhost:8080/guess\">\n" +
                "    <input name=\"guess\" type=\"text\" size=\"8\" value=\"0\"/>\n" +
                "    <br/>\n" +
                "    <input name=\"submit\" type=\"submit\" value=\"Send\"/>\n" +
                "    <input type=\"reset\">\n" +
                "</form> </body> </html>\n"
        );

        if (str.equals("You win!")) {
            game = new Game();
            session.setAttribute("game", game);
        }
    }
}





