package com.github.knextsunj.cms.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

public class PairDeserializer extends JsonDeserializer<Pair> {
    @Override
    public Pair deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        p.nextValue();
        String left= p.getValueAsString();
        p.nextValue();
        String right= p.getValueAsString();
        return Pair.of(left, right);
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        Pair<String, String> pair = Pair.of("28","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXNzaSIsImV4cCI6MTY5ODgzNjA5MX0.OqtGRAhufjapPFWap06XHnRRDgo-6yDePdsg6dpr3Qs");
//
//        SimpleModule pairModule = new SimpleModule();
//        pairModule.addSerializer(Pair.class, new PairSerializer());
//        pairModule.addDeserializer(Pair.class, new PairDeserializer());
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.registerModule(pairModule);
//
//        String json = mapper.writeValueAsString(pair);
//        System.out.println(json);
//
//        System.out.println(mapper.readValue(json, MutablePair.class));
//    }
}
