package github.sagubr.entities;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Address {

    @NonNull
    private String url;

    public Address( String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}

