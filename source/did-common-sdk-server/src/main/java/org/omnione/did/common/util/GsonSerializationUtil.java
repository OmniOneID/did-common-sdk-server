/*
 * Copyright 2025 OmniOne.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omnione.did.common.util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.omnione.did.zkp.datamodel.schema.AttributeDef;
import org.omnione.did.zkp.datamodel.schema.CredentialSchema;

import java.io.IOException;

/**
 * Utility class for custom Gson serialization/deserialization
 */
public class GsonSerializationUtil {

    private GsonSerializationUtil() {
        throw new IllegalStateException("Utility class");
    }

    /** Gson with custom serializer/deserializer for AttributeDef.ATTR_TYPE */
    public static Gson createGsonWithAttributeTypeSerializer() {
        return new GsonBuilder()
                .registerTypeAdapter(AttributeDef.ATTR_TYPE.class, new AttributeTypeAdapter())
                .create();
    }

    /**
     * TypeAdapter for ATTR_TYPE: serialize using getValue(), parse
     * case-insensitively
     */
    private static class AttributeTypeAdapter extends TypeAdapter<AttributeDef.ATTR_TYPE> {
        @Override
        public void write(JsonWriter out, AttributeDef.ATTR_TYPE value) throws IOException {
            out.value(value != null ? value.getValue() : null);
        }

        @Override
        public AttributeDef.ATTR_TYPE read(JsonReader in) throws IOException {
            String value = in.peek() == JsonToken.NULL ? null : in.nextString().toUpperCase();
            return value != null ? AttributeDef.ATTR_TYPE.valueOf(value) : null;
        }
    }

    /** Parse JSON string to CredentialSchema with flexible ATTR_TYPE values */
    public static CredentialSchema parseCredentialSchema(String json) {
        return createGsonWithAttributeTypeSerializer().fromJson(json, CredentialSchema.class);
    }

}
