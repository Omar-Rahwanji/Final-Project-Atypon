package controllers;

import cache_components.Cache;
import models.entities.Entity;
import models.entities.NullEntity;
import models.operations_components.Operations;
import models.operations_components.OperationsUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "servlets.HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String userType = (String) session.getAttribute("userType");
        if(userType.equals("instructor")){
            request.getRequestDispatcher("/WEB-INF/views/instructor_page.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        Cache[] cachedRecords = (Cache[]) session.getAttribute("cachedRecords");
        Operations operations = new Operations();
        Entity record;
        String statement;
        String operation = request.getParameter("operation").split(" ")[0];
        String table = request.getParameter("operation").split(" ")[1];
        int tableIndex = OperationsUtilities.getTableIndex(table);
        request.setAttribute("operationStatus" + table, "Failed Operation!");
        switch (operation) {
            case "Read":
                statement = request.getParameter("readStatement" + table);
                record = operations.readRecord(tableIndex, statement, cachedRecords);
                request.setAttribute("record", record);
                if(record!= NullEntity.getInstance())
                    request.setAttribute("operationStatus" + table,"Successful Operation!");
                break;
            case "Insert":
                statement = request.getParameter("insertStatement" + table);
                if(operations.insertRecord(tableIndex, statement, cachedRecords))
                    request.setAttribute("operationStatus" + table,"Successful Operation!");
                break;
            case "Update":
                statement = request.getParameter("updateStatement" + table);
                if(operations.updateRecord(tableIndex, statement, cachedRecords))
                    request.setAttribute("operationStatus" + table,"Successful Operation!");
                break;
            case "Delete":
                statement = request.getParameter("deleteStatement" + table);
                if(operations.deleteRecord(tableIndex, statement, cachedRecords))
                    request.setAttribute("operationStatus" + table,"Successful Operation!");
                break;
            default:
                request.setAttribute("operationStatus" + table, "Invalid Operation!");
        }
        String userType = (String) session.getAttribute("userType");
        request.getRequestDispatcher("/WEB-INF/views/" + userType + "_page.jsp").forward(request, response);
    }
}
