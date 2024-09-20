package github.sagubr.entities;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class Address {

    @NonNull
    private List<String> urls;

    public Address(List<String> urls) {
        this.urls = urls;
    }

    public @NonNull List<String> getUrl() {
        return urls;
    }
}

