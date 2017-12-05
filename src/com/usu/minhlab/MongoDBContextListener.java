package com.usu.minhlab;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.usu.minhlab.utils.EventCodecProvider;

@WebListener
public class MongoDBContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext ctx = sce.getServletContext();
			CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
	                CodecRegistries.fromProviders(new EventCodecProvider()),
	                MongoClient.getDefaultCodecRegistry());
			MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
			
			// MongoClient mongo = new MongoClient(
			//		ctx.getInitParameter("MONGODB_HOST"), 
			// 		Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
	        MongoClient mongo = new MongoClient(ctx.getInitParameter("MONGODB_HOST"), options);
	        
			System.out.println("MongoClient initialized successfully");
			sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
		} catch (Exception e) {
			throw new RuntimeException("MongoClient init failed");
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		MongoClient mongo = (MongoClient) sce.getServletContext()
				.getAttribute("MONGO_CLIENT");
		mongo.close();
		System.out.println("MongoClient closed successfully");
	}
}
