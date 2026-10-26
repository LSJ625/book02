package com.servlet;

import com.bean.Book;
import com.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookAdd")
public class BookAddServlet extends HttpServlet {
    private BookDao bookdao = new BookDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bookname = request.getParameter("bookname");
        String author = request.getParameter("author");
        String status = request.getParameter("status");

        Book book = new Book();
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setStatus(status);

        int rows = bookdao.addBook(book);
        if(rows > 0){
            response.sendRedirect(request.getContextPath()+"/book/list");
        }else{
            response.getWriter().write("新增失败！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
