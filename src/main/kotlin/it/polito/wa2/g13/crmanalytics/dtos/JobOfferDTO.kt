package it.polito.wa2.g13.crmanalytics.dtos

enum class JobOfferStatus {
    Created,
    SelectionPhase,
    CandidateProposal,
    Consolidated,
    Done,
    Aborted,
}

/**
 * Example of debezium schema data
 *
 * ```json
 * {
 * 	"schema": {
 * 		"type": "struct",
 * 		"fields": [
 * 			{
 * 				"type": "struct",
 * 				"fields": [
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"default": 0,
 * 						"field": "id"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"field": "description"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"field": "duration"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"field": "status"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "customer_id"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "professional_id"
 * 					}
 * 				],
 * 				"optional": true,
 * 				"name": "dbserver1.public.job_offer.Value",
 * 				"field": "before"
 * 			},
 * 			{
 * 				"type": "struct",
 * 				"fields": [
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"default": 0,
 * 						"field": "id"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"field": "description"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"field": "duration"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"field": "status"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "customer_id"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "professional_id"
 * 					}
 * 				],
 * 				"optional": true,
 * 				"name": "dbserver1.public.job_offer.Value",
 * 				"field": "after"
 * 			},
 * 			{
 * 				"type": "struct",
 * 				"fields": [
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "version"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "connector"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "name"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"field": "ts_ms"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"name": "io.debezium.data.Enum",
 * 						"version": 1,
 * 						"parameters": {
 * 							"allowed": "true,last,false,incremental"
 * 						},
 * 						"default": "false",
 * 						"field": "snapshot"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "db"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": true,
 * 						"field": "sequence"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "ts_us"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "ts_ns"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "schema"
 * 					},
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "table"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "txId"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "lsn"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": true,
 * 						"field": "xmin"
 * 					}
 * 				],
 * 				"optional": false,
 * 				"name": "io.debezium.connector.postgresql.Source",
 * 				"field": "source"
 * 			},
 * 			{
 * 				"type": "struct",
 * 				"fields": [
 * 					{
 * 						"type": "string",
 * 						"optional": false,
 * 						"field": "id"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"field": "total_order"
 * 					},
 * 					{
 * 						"type": "int64",
 * 						"optional": false,
 * 						"field": "data_collection_order"
 * 					}
 * 				],
 * 				"optional": true,
 * 				"name": "event.block",
 * 				"version": 1,
 * 				"field": "transaction"
 * 			},
 * 			{
 * 				"type": "string",
 * 				"optional": false,
 * 				"field": "op"
 * 			},
 * 			{
 * 				"type": "int64",
 * 				"optional": true,
 * 				"field": "ts_ms"
 * 			},
 * 			{
 * 				"type": "int64",
 * 				"optional": true,
 * 				"field": "ts_us"
 * 			},
 * 			{
 * 				"type": "int64",
 * 				"optional": true,
 * 				"field": "ts_ns"
 * 			}
 * 		],
 * 		"optional": false,
 * 		"name": "dbserver1.public.job_offer.Envelope",
 * 		"version": 2
 * 	},
 * 	"payload": {
 * 		"before": null,
 * 		"after": {
 * 			"id": 8,
 * 			"description": "Peppino",
 * 			"duration": 123,
 * 			"status": "Created",
 * 			"customer_id": 10,
 * 			"professional_id": null
 * 		},
 * 		"source": {
 * 			"version": "3.0.1.Final",
 * 			"connector": "postgresql",
 * 			"name": "dbserver1",
 * 			"ts_ms": 1732743221947,
 * 			"snapshot": "false",
 * 			"db": "crm",
 * 			"sequence": "[null,\"36432176\"]",
 * 			"ts_us": 1732743221947789,
 * 			"ts_ns": 1732743221947789000,
 * 			"schema": "public",
 * 			"table": "job_offer",
 * 			"txId": 933,
 * 			"lsn": 36432176,
 * 			"xmin": null
 * 		},
 * 		"transaction": null,
 * 		"op": "c",
 * 		"ts_ms": 1732743222108,
 * 		"ts_us": 1732743222108815,
 * 		"ts_ns": 1732743222108815622
 * 	}
 * }
 * ```
 */
data class JobOfferDTO(
    val id: Long,
    val description: String?,
    val duration: Long,
    val status: JobOfferStatus,
    val customerId: Long,
    val professionalId: Long?,
)
