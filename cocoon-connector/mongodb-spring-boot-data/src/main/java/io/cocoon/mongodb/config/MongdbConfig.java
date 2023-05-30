package io.cocoon.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongdbConfig extends AbstractMongoClientConfiguration{

    @Override
    protected String getDatabaseName() {
        return "spring";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        // Configure additional settings if needed
        builder.applyConnectionString(new ConnectionString("mongodb://localhost:27017"));
    }
}
