# XML to JSON Converter with Camel Case

This Java application converts XML documents to JSON format with automatic conversion of property names to camel case.

## Features

- ✅ Converts XML to JSON format
- ✅ Automatically converts property names to camel case (snake_case, kebab-case, PascalCase → camelCase)
- ✅ Handles nested XML structures
- ✅ Supports arrays/lists in XML
- ✅ Pretty-printed JSON output
- ✅ File and string input support

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean compile
```

## Running the Application

### Run with Maven:

```bash
mvn exec:java -Dexec.mainClass="com.github.slideshow.Main"
```

### Or compile and run directly:

```bash
mvn clean package
java -jar target/xml-to-json-converter-1.0.0.jar
```

## Usage Examples

### Example 1: Convert XML String to JSON

```java
XmlToJsonConverter converter = new XmlToJsonConverter();

String xml = """
    <?xml version="1.0" encoding="UTF-8"?>
    <person>
        <first_name>John</first_name>
        <last_name>Doe</last_name>
        <age>30</age>
    </person>
    """;

String json = converter.convertXmlToJson(xml);
System.out.println(json);
```

**Output:**
```json
{
  "firstName" : "John",
  "lastName" : "Doe",
  "age" : 30
}
```

### Example 2: Convert XML File to JSON

```java
XmlToJsonConverter converter = new XmlToJsonConverter();

File xmlFile = new File("sample.xml");
String json = converter.convertXmlFileToJson(xmlFile);

// Save to file
File outputFile = new File("output.json");
converter.saveJsonToFile(json, outputFile);
```

### Example 3: Complex Nested Structure

**Input XML:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<employee_record>
    <employee_id>12345</employee_id>
    <personal_info>
        <first_name>Jane</first_name>
        <last_name>Smith</last_name>
    </personal_info>
    <department_name>Engineering</department_name>
</employee_record>
```

**Output JSON:**
```json
{
  "employeeId" : "12345",
  "personalInfo" : {
    "firstName" : "Jane",
    "lastName" : "Smith"
  },
  "departmentName" : "Engineering"
}
```

## Naming Convention Conversion

The converter automatically handles various naming conventions:

| Input Format | Example | Output (Camel Case) |
|-------------|---------|---------------------|
| snake_case | `first_name` | `firstName` |
| kebab-case | `first-name` | `firstName` |
| PascalCase | `FirstName` | `firstName` |
| space separated | `first name` | `firstName` |

## Testing the Application

Run the main class to see multiple conversion examples:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.github.slideshow.Main"
```

This will demonstrate:
1. Simple XML to JSON conversion
2. Complex nested structures
3. XML with arrays
4. File-based conversion (using sample.xml)

## Project Structure

```
.
├── pom.xml                          # Maven configuration
├── sample.xml                       # Sample XML input file
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── github/
│                   └── slideshow/
│                       ├── XmlToJsonConverter.java  # Main converter class
│                       └── Main.java                # Demo application
└── XML_TO_JSON_README.md           # This file
```

## Dependencies

- **Jackson Databind** (2.17.0): JSON processing
- **Jackson Dataformat XML** (2.17.0): XML parsing

## API Reference

### XmlToJsonConverter

#### Methods:

- `String convertXmlToJson(String xmlContent)` - Converts XML string to JSON string
- `String convertXmlFileToJson(File xmlFile)` - Converts XML file to JSON string
- `void saveJsonToFile(String jsonContent, File outputFile)` - Saves JSON to a file

## License

This project is part of the GitHub Learning Lab slideshow repository.
