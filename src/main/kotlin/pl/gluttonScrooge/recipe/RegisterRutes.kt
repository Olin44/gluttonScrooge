package pl.gluttonScrooge.recipe

import io.ktor.application.*
import io.ktor.routing.*

fun Application.registerRecipeRoutes() {
    routing {
        recipes()
    }
}