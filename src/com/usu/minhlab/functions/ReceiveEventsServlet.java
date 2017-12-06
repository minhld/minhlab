package com.usu.minhlab.functions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;

@WebServlet("/receiveEvents")
public class ReceiveEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TOP_ITEMS = 10;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// receive parameters
		int top = req.getParameter("top") != null ? Integer.parseInt(req.getParameter("top")) : TOP_ITEMS;
		
		// retrieve mongo client
		MongoClient mongo = (MongoClient) req.getServletContext().getAttribute("MONGO_CLIENT");
		EventDAO.setEventDAO(mongo);
		
		// get the list of top events in JSON format
		String jsonResp = EventDAO.getEvents(top);
		
		// print out
		PrintWriter writer = resp.getWriter();
		writer.write(jsonResp);
		writer.flush();
		
		writer.close();
	}
}
