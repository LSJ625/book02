package com.servlet;

import com.bean.Book;
import com.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookToUpdate")
public class BookToUpdateServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取要修改的图书id
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        // 查询这本书
        Book book = bookDao.findById(id);
        // 传给jsp页面
        request.setAttribute("book",book);
        // 转发到你的 updateBook.jsp
        request.getRequestDispatcher("/updateBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
