package com.yashchitre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/SavedServlet")
public class SavedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("GET");
    }
}
