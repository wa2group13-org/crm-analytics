package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.dtos.GenericFilterDTO
import it.polito.wa2.g13.crmanalytics.dtos.GenericStatDTO
import it.polito.wa2.g13.crmanalytics.dtos.Operation
import org.springframework.data.mongodb.repository.Aggregation
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

interface GenericStatRepository {
    fun countByValueOp(op: Operation): Mono<Long>

    @Aggregation(
        pipeline = [
            "{ \$match: { 'value.op': ?1 } }",
            "{ \$set : { 'date': { \$toDate: '\$value.tsMs' } } }",
            "{ \$match : { \$expr: { \$gt: ['\$date', { \$toDate: ?#{ #base.toInstant().toEpochMilli() } }] } } }",
            "{ \$group: { _id: { \$dateToString: { date: '\$date', format: ?#{ #group.format() } } }, count: { \$count: {} } } }",
            "{ \$set : { dateFormat: '\$_id' } }",
            "{ \$unset: '_id' }",
        ]
    )
    fun countByFilter(base: ZonedDateTime, op: Operation, group: GenericFilterDTO.Group): Flux<GenericStatDTO>
}