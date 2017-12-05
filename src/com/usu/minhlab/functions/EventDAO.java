package com.usu.minhlab.functions;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.usu.minhlab.utils.Event;
import com.usu.minhlab.utils.JSonUtils;

public class EventDAO {
	static MongoClient mongo; 
	static MongoCollection<Document> eventTable;
	
	public static void setEventDAO(MongoClient mongo) {
		EventDAO.mongo = mongo;
		
		// create db name cam-event
		MongoDatabase db = EventDAO.mongo.getDatabase("camevent");
		
		// create table events
		eventTable = db.getCollection("events");
	}
	
	public static String getEvents(int top) {
		List<Event> events = new ArrayList<>();
		
		// set up query and find top items
		Document findQuery = new Document();
		findQuery.append("_id", -1);
		FindIterable<Event> eventList = eventTable.find(Event.class).sort(findQuery).limit(top);
		MongoCursor<Event> curs = eventList.iterator();
		
		return JSonUtils.print(curs);
	}
}
