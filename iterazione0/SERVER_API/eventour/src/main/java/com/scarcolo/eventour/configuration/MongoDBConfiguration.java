package com.scarcolo.eventour.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;



// TODO: Auto-generated Javadoc
/**
 * The Class MongoDBConfiguration.
 */
@Configuration
public class MongoDBConfiguration {


	/** The database name. */
	//database Name
    private String databaseName = "evenTour";

    /** The user. */
    //host
    private String user = "user_web";
    
    /** The pass. */
    private String pass = "admin";

   
    /**
     * Mongo client.
     *
     * @return the mongo client
     */
    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://" + user + ":" + pass + "@cluster0.0ef9u.mongodb.net/" + databaseName + "?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
    
    /**
     * Mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), databaseName);
    }
}
