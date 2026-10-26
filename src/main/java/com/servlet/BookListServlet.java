package com.servlet;

import com.bean.Book;
import com.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book/list")
public class BookListServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String searchName = request.getParameter("searchName");
        List<Book> bookList;
        if(searchName != null && !searchName.trim().equals("")){
            // 有搜索词 → 走搜索
            bookList = bookDao.searchByName(searchName.trim());
        }else{
            // 空 → 查询全部
            bookList = bookDao.findAll();
        }

        request.setAttribute("bookList", bookList);
        // 重点！转发到 index.jsp，不是 index.html
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}