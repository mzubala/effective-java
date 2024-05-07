package contracts

import org.springframework.cloud.contract.spec.Contract

// https://cloud.spring.io/spring-cloud-contract/1.1.x/multi/multi__contract_dsl.html
// https://github.com/spring-cloud-samples/spring-cloud-contract-samples

Contract.make {
    description "should respond with a movie description when description exists"
    request {
        method(GET())
        url("/movie-descriptions/1")
    }
    response {
        body(
                description: $(regex('.*'))
        )
        status 200
        headers {
            contentType(applicationJson())
        }
    }
}
