package com.github.slideshow;

import java.io.File;
import java.io.IOException;

/**
 * Main class to demonstrate XML to JSON conversion with camel case formatting
 */
public class Main {
    
    public static void main(String[] args) {
        XmlToJsonConverter converter = new XmlToJsonConverter();
        
        // Example 1: Convert XML string to JSON
        System.out.println("=== Example 1: Simple XML to JSON Conversion ===");
        String xmlExample1 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <person>
                    <first_name>John</first_name>
                    <last_name>Doe</last_name>
                    <age>30</age>
                    <email_address>john.doe@example.com</email_address>
                </person>
                """;
        
        try {
            String json1 = converter.convertXmlToJson(xmlExample1);
            System.out.println("Input XML:");
            System.out.println(xmlExample1);
            System.out.println("\nOutput JSON (with camel case):");
            System.out.println(json1);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error converting XML to JSON: " + e.getMessage());
        }
        
        // Example 2: Complex nested XML structure
        System.out.println("=== Example 2: Complex Nested XML Structure ===");
        String xmlExample2 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <employee_record>
                    <employee_id>12345</employee_id>
                    <personal_info>
                        <first_name>Jane</first_name>
                        <last_name>Smith</last_name>
                        <date_of_birth>1990-05-15</date_of_birth>
                    </personal_info>
                    <contact_details>
                        <phone_number>555-0123</phone_number>
                        <email_address>jane.smith@company.com</email_address>
                        <home_address>
                            <street_name>123 Main St</street_name>
                            <city_name>Springfield</city_name>
                            <zip_code>12345</zip_code>
                        </home_address>
                    </contact_details>
                    <department_name>Engineering</department_name>
                    <job_title>Software Developer</job_title>
                </employee_record>
                """;
        
        try {
            String json2 = converter.convertXmlToJson(xmlExample2);
            System.out.println("Input XML:");
            System.out.println(xmlExample2);
            System.out.println("\nOutput JSON (with camel case):");
            System.out.println(json2);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error converting XML to JSON: " + e.getMessage());
        }
        
        // Example 3: XML with array elements
        System.out.println("=== Example 3: XML with Array Elements ===");
        String xmlExample3 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <product_catalog>
                    <catalog_name>Electronics Store</catalog_name>
                    <products>
                        <product>
                            <product_id>1</product_id>
                            <product_name>Laptop</product_name>
                            <unit_price>999.99</unit_price>
                        </product>
                        <product>
                            <product_id>2</product_id>
                            <product_name>Mouse</product_name>
                            <unit_price>19.99</unit_price>
                        </product>
                    </products>
                </product_catalog>
                """;
        
        try {
            String json3 = converter.convertXmlToJson(xmlExample3);
            System.out.println("Input XML:");
            System.out.println(xmlExample3);
            System.out.println("\nOutput JSON (with camel case):");
            System.out.println(json3);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error converting XML to JSON: " + e.getMessage());
        }
        
        // Example 4: Convert from file (if sample.xml exists)
        File sampleXmlFile = new File("sample.xml");
        if (sampleXmlFile.exists()) {
            System.out.println("=== Example 4: Converting from file (sample.xml) ===");
            try {
                String jsonFromFile = converter.convertXmlFileToJson(sampleXmlFile);
                System.out.println("Output JSON:");
                System.out.println(jsonFromFile);
                
                // Save to output file
                File outputFile = new File("output.json");
                converter.saveJsonToFile(jsonFromFile, outputFile);
                System.out.println("\nJSON saved to: " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error converting file: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== Conversion Complete ===");
    }
}
