package pl.gluttonScrooge

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import pl.gluttonScrooge.database.DatabaseFactory
import pl.gluttonScrooge.recipe.registerRecipeRoutes

fun main() {
    DatabaseFactory.init()
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), watchPaths = listOf("classes")) {
        configureRouting()
        install(Locations)
        install(ContentNegotiation) {
            json()
        }
        configureHTTP()
        registerRecipeRoutes()
    }.start(wait = true)
}

private fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
    }
}
