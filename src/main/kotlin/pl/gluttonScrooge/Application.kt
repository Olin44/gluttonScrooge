package pl.gluttonScrooge

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable
import org.slf4j.event.Level
import pl.gluttonScrooge.database.DatabaseFactory
import pl.gluttonScrooge.recipe.registerRecipeRoutes

fun main() {
    DatabaseFactory.init()
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), watchPaths = listOf("classes")) {
        install(Locations)
        install(ContentNegotiation) {
            json()
        }
        install(StatusPages) {
            exception<ParameterConversionException> { cause ->
                call.respond(HttpStatusCode.BadRequest, ErrorMessageResponse(cause.message, call.request.uri, cause.type))
            }
        }
        configureHTTP()
        configureLogging()
        registerRecipeRoutes()
    }.start(wait = true)
}

@Serializable
data class ErrorMessageResponse(val message: String? = "Empty message", val uri: String, val type: String)

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
    }
}

fun Application.configureLogging() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}
