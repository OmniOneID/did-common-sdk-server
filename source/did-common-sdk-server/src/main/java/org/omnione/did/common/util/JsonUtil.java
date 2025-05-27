package org.omnione.did.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for JSON serialization and deserialization.
 * This class provides methods to serialize objects to JSON strings, sort the keys,
 * and remove all whitespace. It is used in OpenDID for serializing the original text
 * when signing.
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

   /**
     * Serialize an object to a JSON string and sort the keys.
     * This method serializes the object to a JSON string and then sorts the keys in the JSON object.
     * It returns the sorted JSON string.
     * @param obj  the object to serialize
     * @return the sorted JSON string
     * @throws CommonSdkException if an error occurs during serialization and sort
     */
    public static String serializeAndSort(Object obj)  {
        try {
            String jsonString = mapper.writeValueAsString(obj);
            JsonNode node = mapper.readTree(jsonString);
            JsonNode sortedNode = sortJsonNode(node);
            String sortedJsonString = mapper.writeValueAsString(sortedNode);
            return removeEscapeCharactersExceptValues(sortedJsonString);
        } catch (JsonProcessingException e) {
            throw new CommonSdkException(ErrorCode.JSON_SERIALIZE_SORT_FAILED);
        }

    }

    /**
     * Remove all whitespace characters from a JSON string.
     * This method removes all whitespace characters from a JSON string.
     * It returns the JSON string without any whitespace characters.
     * @param jsonString  the JSON string to process
     * @return the JSON string without any whitespace characters
     */
    private static String removeEscapeCharactersExceptValues(String jsonString) {
        StringBuilder result = new StringBuilder();
        boolean inValue = false;

        for (int i = 0; i < jsonString.length(); i++) {
            char currentChar = jsonString.charAt(i);

            if (currentChar == '\"') {
                inValue = !inValue;
            }

            if (currentChar == '\\' && (i + 1) < jsonString.length()) {
                char nextChar = jsonString.charAt(i + 1);
                if (!inValue && (nextChar == '\"' || nextChar == '/')) {
                    continue;
                }
            }
            result.append(currentChar);
        }

        return result.toString();
    }

    /**
     * Sort the keys in a JSON node.
     * This method sorts the keys in a JSON node and returns the sorted node.
     * @param node  the JSON node to sort
     * @return the sorted JSON node
     */
    private static JsonNode sortJsonNode(JsonNode node) {
        if (node.isObject()) {
            ObjectNode sortedNode = mapper.createObjectNode();
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            Map<String, JsonNode> sortedMap = new TreeMap<>();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                sortedMap.put(field.getKey(), sortJsonNode(field.getValue()));
            }
            sortedMap.forEach(sortedNode::set);
            return sortedNode;
        } else if (node.isArray()) {
            ArrayNode sortedArrayNode = mapper.createArrayNode();
            for (JsonNode item : node) {
                sortedArrayNode.add(sortJsonNode(item));
            }
            return sortedArrayNode;
        }
        return node;
    }

    /**
     * Convert any object to a JSON string.
     * This method converts any object to a JSON string and returns the JSON string.
     * @param obj  the object to convert
     * @return the JSON string
     * @throws CommonSdkException if an error occurs during serialization
     */
    public static String serializeToJson(Object obj)  {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CommonSdkException(ErrorCode.JSON_SERIALIZE_FAILED);
        }
    }

    /**
     * Deserialize a JSON string into an object of the specified class type.
     * <p>
     * This method is useful for converting a JSON string into a simple Java object (POJO).
     * However, it does not support complex generic types such as `List<T>` or `Map<K, V>`,
     * as Java erases generic type information at runtime.
     * </p>
     *
     * @param jsonString  the JSON string to deserialize
     * @param clazz  the class type of the
     * @param <T>  the class type of the object to deserialize
     * @return the deserialized object
     * @throws CommonSdkException if an error occurs during deserialization
     */
    public static <T> T deserializeFromJson(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new CommonSdkException(ErrorCode.JSON_DESERIALIZE_FAILED);
        }
    }

    /**
     * Deserialize a JSON string into an object of a complex or generic type.
     * <p>
     * This method supports deserialization into generic types such as `List<T>` and `Map<K, V>`.
     * It maintains type information by using `TypeReference<T>`, preventing type erasure issues.
     * </p>
     *
     * <pre>
     * Example usage:
     * {@code
     * List<String> list = JsonUtil.deserializeFromJson(jsonString, new TypeReference<List<String>>() {});
     * Map<String, Integer> map = JsonUtil.deserializeFromJson(jsonString, new TypeReference<Map<String, Integer>>() {});
     * }
     * </pre>
     *
     * @param jsonString the JSON string to deserialize
     * @param typeReference the TypeReference indicating the target type
     * @param <T> the type of the object to deserialize
     * @return the deserialized object of type T
     * @throws CommonSdkException if an error occurs during deserialization
     */
    public static <T> T deserializeFromJson(String jsonString, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (JsonProcessingException var3) {
            throw new CommonSdkException(ErrorCode.JSON_DESERIALIZE_FAILED);
        }
    }
}