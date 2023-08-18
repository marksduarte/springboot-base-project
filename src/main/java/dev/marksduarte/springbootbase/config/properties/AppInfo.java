package dev.marksduarte.springbootbase.config.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "info")
public class AppInfo {
    private String name;
    private String description;
    private String version;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
