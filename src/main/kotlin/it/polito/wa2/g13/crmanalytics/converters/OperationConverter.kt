package it.polito.wa2.g13.crmanalytics.converters

import it.polito.wa2.g13.crmanalytics.dtos.Operation
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class OperationConverter: Converter<String, Operation> {
    override fun convert(source: String): Operation? = Operation.from(source)
}