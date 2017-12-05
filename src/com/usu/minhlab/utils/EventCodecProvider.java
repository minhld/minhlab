package com.usu.minhlab.utils;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class EventCodecProvider implements CodecProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Codec<T> get(Class<T> c, CodecRegistry cr) {
		if (c == Event.class) {
            return (Codec<T>) new EventCodec();
        }
        return null;
	}

}
