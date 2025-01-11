package it.polito.wa2.g13.crmanalytics.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.annotation.Retryable
import org.springframework.transaction.annotation.Transactional

/**
 * Enables mongo [Retryable] and [Transactional] query.
 */
//@Configuration
//@EnableRetry
//@EnableReactiveMongoRepositories("it.polito.wa2.g13.crmanalytics.repositories")
//class MongoConfig : AbstractReactiveMongoConfiguration() {
//    @Bean
//    fun transactionManager(factory: ReactiveMongoDatabaseFactory): ReactiveMongoTransactionManager {
//        return ReactiveMongoTransactionManager(factory)
//    }
//
//    override fun getDatabaseName(): String {
//        TODO("Not yet implemented")
//    }
//}