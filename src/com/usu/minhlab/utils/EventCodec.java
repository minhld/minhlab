package com.usu.minhlab.utils;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class EventCodec implements Codec<Event> {

    @Override
	public void encode(BsonWriter writer, Event e, EncoderContext ctx) {
    	writer.writeStartDocument();
        writer.writeName("time");
        writer.writeInt64(e.time);
        writer.writeName("type");
        writer.writeString(e.type);
        writer.writeName("info");
        writer.writeString(e.info);
        writer.writeEndDocument();		
    }

	@Override
	public Event decode(BsonReader reader, DecoderContext ctx) {
		try {
			reader.readStartDocument();
			String id = reader.readObjectId().toString();
	        long time = reader.readInt64("time");
	        String type = reader.readString("type");
	        String info = reader.readString("info");
	        reader.readEndDocument();
	        return new Event(time, type, info);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Class<Event> getEncoderClass() {
		return Event.class;
	}
}
