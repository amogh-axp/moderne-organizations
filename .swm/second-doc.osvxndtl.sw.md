---
title: Second Doc
---
<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="19">

---

This code snippet is a Java class named `OrganizationDataFetcher`. It is responsible for fetching organization data and initializing an `ownership` list. The class uses the `ObjectMapper` to read a JSON file named 'ownership.json' and map it to a list of `OrganizationRepositories`. The class also initializes an `ALL_ORG` object of type `Organization` with specific properties.

```java
@DgsComponent
public class OrganizationDataFetcher {
    List<OrganizationRepositories> ownership;
    Organization ALL_ORG = Organization.newBuilder().id("ALL").name("ALL").commitOptions(List.of(CommitOption.values())).build();

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
