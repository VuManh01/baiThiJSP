package Controller;

import Dao.PlayerDAO;
import Model.Indexer;
import Model.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    PlayerDAO dao = new PlayerDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deletePlayer(id);
            response.sendRedirect("player");

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Player p = dao.getPlayerById(id);
            request.setAttribute("editPlayer", p);
            List<Player> list = dao.getAllPlayers();
            request.setAttribute("players", list);

            List<Indexer> indexers = dao.getAllIndexers();
            request.setAttribute("indexers", indexers);

            RequestDispatcher rd = request.getRequestDispatcher("Show.jsp");
            rd.forward(request, response);

        } else {
            List<Player> list = dao.getAllPlayers();
            request.setAttribute("players", list);

            List<Indexer> indexers = dao.getAllIndexers();
            request.setAttribute("indexers", indexers);

            RequestDispatcher rd = request.getRequestDispatcher("Show.jsp");
            rd.forward(request, response);
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String indexIdStr = request.getParameter("index_id");
        String valueStr = request.getParameter("value");

        if (name == null || name.isEmpty() ||
                age == null || age.isEmpty() ||
                indexIdStr == null || indexIdStr.isEmpty() ||
                valueStr == null || valueStr.isEmpty()) {

            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            List<Player> list = dao.getAllPlayers();
            request.setAttribute("players", list);
            RequestDispatcher rd = request.getRequestDispatcher("Show.jsp");
            rd.forward(request, response);
            return;
        }

        int indexId = Integer.parseInt(indexIdStr);
        float value = Float.parseFloat(valueStr);

        Player p = new Player();
        p.setName(name);
        p.setFullName(name);
        p.setAge(age);
        p.setIndexId(indexId);
        p.setValue(value);

        String message;
        if (idStr != null && !idStr.isEmpty()) {
            // Cập nhật
            int id = Integer.parseInt(idStr);
            p.setId(id);
            dao.updatePlayer(p);
            message = "Cập nhật cầu thủ thành công!";
        } else {
            // Thêm mới
            dao.insertPlayer(p);
            message = "Thêm cầu thủ thành công!";
        }

        List<Player> list = dao.getAllPlayers();
        request.setAttribute("players", list);
        request.setAttribute("success", message);

        List<Indexer> indexers = dao.getAllIndexers();
        request.setAttribute("indexers", indexers);

        RequestDispatcher rd = request.getRequestDispatcher("Show.jsp");
        rd.forward(request, response);

    }
}

