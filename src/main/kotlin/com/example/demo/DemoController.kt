package com.example.demo

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {
    private val datum = DemoObject(5, "foo")
    @GetMapping
    fun index() = MappingJacksonValue(datum).apply {
        serializationView = Views.Public::class.java
    }

    @JsonView(Views.Public::class)
    @GetMapping("/annotated")
    fun annotated() = datum
}

object Views {
    open class Public

    open class Private : Public()
}

data class DemoObject(
    @JsonView(Views.Private::class)
    val id: Int,
    @JsonView(Views.Public::class)
    val name: String
)
