---
title: Fourth doc
---
<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="24">

---

This code snippet reads the content of a JSON file named `ownership.json` and maps it to a Java object using an `ObjectMapper` instance provided as a parameter to the constructor of the `OrganizationDataFetcher` class.

```java
    public OrganizationDataFetcher(ObjectMapper mapper) throws IOException {
        this.ownership = mapper.readValue(
                getClass().getResourceAsStream("/ownership.json"),
                new TypeReference<>() {
                }
        );
    }
```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBbW9kZXJuZS1vcmdhbml6YXRpb25zJTNBJTNBYW1vZ2gtYXhw" repo-name="moderne-organizations"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
