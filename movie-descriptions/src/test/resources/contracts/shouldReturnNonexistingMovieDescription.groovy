package contracts

import org.springframework.cloud.contract.spec.Contract

// https://cloud.spring.io/spring-cloud-contract/1.1.x/multi/multi__contract_dsl.html
// https://github.com/spring-cloud-samples/spring-cloud-contract-samples

Contract.make {
    description "should respond with 404 when description not found"
    request {
        method(GET())
        url(regex("/movie-descriptions/123127983"))
    }
    response {
        status 404
    }
}
