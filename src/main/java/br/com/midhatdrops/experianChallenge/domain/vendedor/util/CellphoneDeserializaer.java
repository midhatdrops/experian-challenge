package br.com.midhatdrops.experianChallenge.domain.vendedor.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CellphoneDeserializaer extends StdDeserializer {
    protected CellphoneDeserializaer(final Class vc) {
        super(vc);
    }

    public CellphoneDeserializaer() {
        this(null);
    }




    @Override
    public Object deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException, JacksonException {
         JsonNode node = jp.getCodec().readTree(jp);
         String s = node.asText();
        return s.replace("-", "").replaceAll("\\s", "").trim();

    }
}
