---
title: Third Doc
---
<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationRepository.java" line="6">

---

This code snippet defines a `OrganizationRepository` record class with three properties: `path`, `origin`, and `branch`. It also includes a constructor that initializes these properties. Additionally, there is a `matches` method that takes a `repositoryInput` parameter and returns a boolean value based on whether the `origin`, `path`, and `branch` properties match the corresponding properties of the `repositoryInput` object. The `matches` method uses the `StringUtils.matchesGlob` method to perform case-insensitive matching using glob patterns.

```java
public record OrganizationRepository(String path, String origin, String branch) {

    public OrganizationRepository(String path, String origin, String branch) {
        this.origin = origin;
        this.path = path;
        this.branch = branch;
        if (origin.equals("*") && path.equals("*") && branch.equals("*")) {
            throw new IllegalArgumentException("Cannot have a repository with origin=* and branch=* and path=* as this would match all repositories");
        }
    }

    public boolean matches(RepositoryInput repositoryInput) {
        return StringUtils.matchesGlob(repositoryInput.getOrigin().toLowerCase(), origin.toLowerCase()) &&
               StringUtils.matchesGlob(repositoryInput.getPath().toLowerCase(), path.toLowerCase()) &&
               StringUtils.matchesGlob(repositoryInput.getBranch().toLowerCase(), branch.toLowerCase());
    }
}
```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBbW9kZXJuZS1vcmdhbml6YXRpb25zJTNBJTNBYW1vZ2gtYXhw" repo-name="moderne-organizations"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
