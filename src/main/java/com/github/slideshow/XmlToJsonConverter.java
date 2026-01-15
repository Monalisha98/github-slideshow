package com.github.slideshow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * XmlToJsonConverter - Converts XML to JSON with camel case property names
 */
public class XmlToJsonConverter {
    
    private static final String DELIMITER_PATTERN = "[-_ ]";
    
    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;
    
    public XmlToJsonConverter() {
        this.xmlMapper = new XmlMapper();
        this.jsonMapper = new ObjectMapper();
    }
    
    /**
     * Converts XML string to JSON string with camel case property names
     * 
     * @param xmlContent The XML content as a string
     * @return JSON string with camel case property names
     * @throws IOException if parsing fails
     */
    public String convertXmlToJson(String xmlContent) throws IOException {
        // Parse XML to JsonNode
        JsonNode xmlNode = xmlMapper.readTree(xmlContent);
        
        // Convert property names to camel case
        JsonNode camelCaseNode = convertToCamelCase(xmlNode);
        
        // Convert to pretty-printed JSON string
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(camelCaseNode);
    }
    
    /**
     * Converts XML file to JSON string with camel case property names
     * 
     * @param xmlFile The XML file to convert
     * @return JSON string with camel case property names
     * @throws IOException if file reading or parsing fails
     */
    public String convertXmlFileToJson(File xmlFile) throws IOException {
        // Parse XML file to JsonNode
        JsonNode xmlNode = xmlMapper.readTree(xmlFile);
        
        // Convert property names to camel case
        JsonNode camelCaseNode = convertToCamelCase(xmlNode);
        
        // Convert to pretty-printed JSON string
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(camelCaseNode);
    }
    
    /**
     * Recursively converts all property names in a JsonNode to camel case
     * 
     * @param node The JsonNode to convert
     * @return A new JsonNode with camel case property names
     */
    private JsonNode convertToCamelCase(JsonNode node) {
        if (node.isObject()) {
            ObjectNode camelCaseObject = jsonMapper.createObjectNode();
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String originalKey = field.getKey();
                String camelCaseKey = toCamelCase(originalKey);
                JsonNode value = field.getValue();
                
                // Recursively convert nested nodes
                camelCaseObject.set(camelCaseKey, convertToCamelCase(value));
            }
            
            return camelCaseObject;
        } else if (node.isArray()) {
            ArrayNode camelCaseArray = jsonMapper.createArrayNode();
            
            for (JsonNode element : node) {
                camelCaseArray.add(convertToCamelCase(element));
            }
            
            return camelCaseArray;
        } else {
            // Return primitive values as-is
            return node;
        }
    }
    
    /**
     * Converts a string to camel case format
     * Handles various naming conventions (snake_case, kebab-case, PascalCase, etc.)
     * 
     * @param input The input string
     * @return The camel case version of the input
     */
    private String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        // Split by common delimiters
        String[] parts = input.split(DELIMITER_PATTERN);
        
        if (parts.length == 1) {
            // No delimiters found, check if it's PascalCase
            return Character.toLowerCase(parts[0].charAt(0)) + parts[0].substring(1);
        }
        
        StringBuilder camelCase = new StringBuilder();
        
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty()) {
                continue;
            }
            
            if (i == 0) {
                // First part should start with lowercase
                camelCase.append(Character.toLowerCase(part.charAt(0)));
                if (part.length() > 1) {
                    camelCase.append(part.substring(1).toLowerCase());
                }
            } else {
                // Subsequent parts should be capitalized
                camelCase.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    camelCase.append(part.substring(1).toLowerCase());
                }
            }
        }
        
        return camelCase.toString();
    }
    
    /**
     * Saves JSON output to a file
     * 
     * @param jsonContent The JSON content to save
     * @param outputFile The output file
     * @throws IOException if file writing fails
     */
    public void saveJsonToFile(String jsonContent, File outputFile) throws IOException {
        java.nio.file.Files.writeString(outputFile.toPath(), jsonContent);
    }
}
