---
title: First Doc - Organizations
---
<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationRepositories.java" line="14">

---

### <SwmToken path="/src/main/java/io/moderne/organizations/OrganizationRepositories.java" pos="14:4:4" line-data="public record OrganizationRepositories(String name, List&lt;OrganizationRepository&gt; repositories,">`OrganizationRepositories`</SwmToken>

This code defines a `public record` named `OrganizationRepositories` that represents a collection of repositories belonging to an organization. It includes properties such as `name`, `repositories`, `dashboard`, `commitOptions`, and `parent`. The `matches` method checks if a given `RepositoryInput` matches any of the repositories in the collection by iterating through the `repositories` list and invoking the `matches` method on each `OrganizationRepository`.

```java
public record OrganizationRepositories(String name, List<OrganizationRepository> repositories,
                                       Dashboard dashboard,
                                       @Nullable List<CommitOption> commitOptions,
                                       @Nullable String parent) {
    boolean matches(RepositoryInput toMatchRepositoryInput) {
        return repositories != null && repositories.stream().anyMatch(repository -> repository.matches(toMatchRepositoryInput));
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationRepository.java" line="8">

---

### <SwmToken path="/src/main/java/io/moderne/organizations/OrganizationRepository.java" pos="6:4:4" line-data="public record OrganizationRepository(String path, String origin, String branch) {">`OrganizationRepository`</SwmToken>

This code snippet initializes an `OrganizationRepository` object with the provided `path`, `origin`, and `branch`. If the `origin`, `path`, and `branch` are all `*`, it throws an `IllegalArgumentException` with a specific error message.

```java
    public OrganizationRepository(String path, String origin, String branch) {
        this.origin = origin;
        this.path = path;
        this.branch = branch;
        if (origin.equals("*") && path.equals("*") && branch.equals("*")) {
            throw new IllegalArgumentException("Cannot have a repository with origin=* and branch=* and path=* as this would match all repositories");
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationRepository.java" line="17">

---

This code snippet is a `matches` method that takes a `RepositoryInput` object as an argument. It uses the `StringUtils.matchesGlob` method to compare the lowercase values of `repositoryInput.getOrigin()`, `repositoryInput.getPath()`, and `repositoryInput.getBranch()` with the lowercase values of `origin`, `path`, and `branch` respectively. It returns a boolean value indicating whether all the comparisons are true.

```java
    public boolean matches(RepositoryInput repositoryInput) {
        return StringUtils.matchesGlob(repositoryInput.getOrigin().toLowerCase(), origin.toLowerCase()) &&
               StringUtils.matchesGlob(repositoryInput.getPath().toLowerCase(), path.toLowerCase()) &&
               StringUtils.matchesGlob(repositoryInput.getBranch().toLowerCase(), branch.toLowerCase());
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationMetricController.java" line="10">

---

### <SwmToken path="/src/main/java/io/moderne/organizations/OrganizationMetricController.java" pos="10:4:4" line-data="public record OrganizationMetricController(PrometheusMeterRegistry meterRegistry){">`OrganizationMetricController`</SwmToken>

This code snippet defines a `OrganizationMetricController` class that has a single method `scrape()`. The method is annotated with `@GetMapping` and maps to the `/metrics/prometheus` endpoint. It returns a `Mono` of type `String` which represents the scraped metrics from the `meterRegistry`. The `scrape()` method calls the `scrape()` function of the `meterRegistry` object with the `TextFormat.CONTENT_TYPE_OPENMETRICS_100` parameter to retrieve the metrics in OpenMetrics format.

```java
public record OrganizationMetricController(PrometheusMeterRegistry meterRegistry){
    @GetMapping("/metrics/prometheus")
    Mono<String> scrape() {
        return Mono.just(meterRegistry.scrape(TextFormat.CONTENT_TYPE_OPENMETRICS_100));
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="32">

---

This code snippet is a `Flux` query method that returns a stream of `Organization` objects based on a given `RepositoryInput`. It filters and maps `Ownership` objects to `Organization` objects and appends a predefined `ALL_ORG` object to the stream.

```
    @DgsQuery
    Flux<Organization> organizations(@InputArgument RepositoryInput repository) {
        return Flux.fromIterable(ownership)
                .filter(org -> org.matches(repository))
                .map(this::mapOrganization)
                .concatWithValues(ALL_ORG); // if you want an "ALL" organization
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="40">

---

This code snippet is a `Flux` query that retrieves all organizations. It converts a collection called `ownership` into a `Flux` stream, maps each element using `mapOrganization` method, and appends an 'ALL_ORG' organization to the end of the stream.

```java
    @DgsQuery
    Flux<Organization> allOrganizations() {
        return Flux.fromIterable(ownership)
                .map(this::mapOrganization)
                .concatWithValues(ALL_ORG); // if you want an "ALL" organization
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="47">

---

This code snippet defines a `DgsQuery` function named `organization` that takes a `String` argument `id`. It returns a `Mono` that emits an `Organization` object. The function filters the `ownership` list using the `id` argument and maps the result to an `Organization` object using the `mapOrganization` method.

```java
    @DgsQuery
    Mono<Organization> organization(@InputArgument String id) {
        return Flux.fromIterable(ownership)
                .filter(org -> org.name().equals(id))
                .next()
                .map(this::mapOrganization);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="55">

---

This code snippet returns a `Flux` of `Organization` objects for a given `User` and `OffsetDateTime`. It first maps a list of `ownership` objects to `Organization` objects using the `mapOrganization` method. Then, it concatenates this `Flux` with a `Flux` containing a single `ALL_ORG` object, which is filtered to always return `true`. This ensures that every user receives the `ALL` organization in addition to their own organizations.

```java
    @DgsQuery
    Flux<Organization> userOrganizations(@InputArgument User user, @InputArgument OffsetDateTime at) {
        // everybody belongs to every organization, and the "default" organization is listed
        // first in the json that this list is based on, so it will be selected by default in the UI
        return Flux.fromIterable(ownership)
                .map(this::mapOrganization)
                .concatWith(
                        Flux.just(ALL_ORG)
                                .filter(__ -> true) // give "ALL" organization to all users
                );
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="67">

---

This code snippet maps an `OrganizationRepositories` object to an `Organization` object by setting the corresponding properties of the `Organization` object. It sets the `id` and `name` properties to the `name` property of the `org` parameter. It sets the `dashboard` property to the `dashboard` property of `org`. It checks if the `commitOptions` property of `org` is null and if so, sets it to a list of all `CommitOption` values; otherwise, it sets it to the `commitOptions` property of `org`. It checks if the `parent` property of `org` is not null and if so, it calls the `getOrganizationByName` function to get the parent organization and sets it as the `_parent` property of the `Organization` object. Finally, it builds and returns the `Organization` object.

```java
    private Organization mapOrganization(OrganizationRepositories org) {
        return Organization.newBuilder()
                .id(org.name())
                .name(org.name())
                .dashboard(org.dashboard())
                .commitOptions(org.commitOptions() == null ?
                        List.of(CommitOption.values()) :
                        org.commitOptions())
                ._parent(org.parent() != null ? getOrganizationByName(org.parent()) : null)
                .build();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/OrganizationDataFetcher.java" line="79">

---

This code snippet is a private method named `getOrganizationByName` that takes in a `String` parameter `name`. It returns an `Organization` object based on the given `name`. If the `name` is equal to "ALL", it returns a predefined `Organization` object named `ALL_ORG`. Otherwise, it uses Java 8 stream to filter through a list of `ownership` objects, finding the first `org` object whose name is equal to the given `name`. It then maps the `org` object to an `Organization` object using the `mapOrganization` method, and returns it. If no matching `org` object is found, it returns `null`.

```java
    private Organization getOrganizationByName(String name) {
        if (name.equals("ALL")) {
            return ALL_ORG;
        }

        return ownership.stream()
                .filter(org -> org.name().equals(name))
                .findFirst()
                .map(this::mapOrganization)
                .orElse(null);
    }
```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBbW9kZXJuZS1vcmdhbml6YXRpb25zJTNBJTNBYW1vZ2gtYXhw" repo-name="moderne-organizations"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
