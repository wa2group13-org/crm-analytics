package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Customer
import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.repositories.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
) : CustomerService {
    companion object {
        private val logger = LoggerFactory.getLogger(CustomerServiceImpl::class.java)
    }

    override suspend fun create(dto: DebeziumPayloadDTO<CustomerDTO>): Customer {
        val newCustomer = customerRepository.save(Customer.from(dto)).awaitSingleOrNull()!!
        logger.info("Created ${newCustomer::class.qualifiedName}@${newCustomer.id}")
        return newCustomer
    }

    override suspend fun count(operation: Operation): Long {
        return customerRepository.countByValueOp(operation).awaitSingle()
    }

    override fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return customerRepository
            .countByFilter(filter.base, filter.op, filter.group)
            .asFlow()
    }
}