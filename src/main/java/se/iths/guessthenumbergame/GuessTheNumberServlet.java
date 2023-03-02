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
        //int rand = random.nextInt(1, 100);

        // Integer secretNumber = (Integer) session.getAttribute("secretNumber");
        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            // secretNumber = Integer.valueOf(rand);
            game = new Game();
            // session.setAttribute("secretNumber", secretNumber);
            session.setAttribute("game", game);
        }

        response.setContentType("text/html");

        int guess = Integer.parseInt(request.getParameter("guess"));

        String str = game.getAnswer(guess);

        response.getWriter().println("<html><body>" + str);

        for (String s: game.getList()) {
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

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


    }

    // Generate a random number if it doesn't exist in the session
       /* if (session.getAttribute("secretNumber") == null) {
            int secretNumber = random.nextInt(1 + 100); // generates a random number between 1 and 100
            session.setAttribute("secretNumber", secretNumber);
        }

        // Get the user's guess from the request parameter
        Integer guess = Integer.parseInt(request.getParameter("guess"));


        // Compare the user's guess with the secret number
        String message;
        if (guess < secretNumber) {
            message = "Your guess is too low. Try again!";
        } else if (guess > secretNumber) {
            message = "Your guess is too high. Try again!";
        } else {
            message = "Congratulations! You guessed the secret number!";
            session.removeAttribute("secretNumber"); // remove the secret number from the session*/
}





