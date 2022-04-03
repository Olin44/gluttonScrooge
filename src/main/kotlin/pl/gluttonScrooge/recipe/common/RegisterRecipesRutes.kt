package pl.gluttonScrooge.recipe

import io.ktor.application.*
import io.ktor.routing.*
import pl.gluttonScrooge.recipe.create.createRecipeRoute
import pl.gluttonScrooge.recipe.delete.deleteRecipeRoute
import pl.gluttonScrooge.recipe.read.pagination.recipePaginationRoute

fun Application.registerRecipeRoutes() {
    routing {
        createRecipeRoute()
        recipePaginationRoute()
        getRecipeRoute()
        deleteRecipeRoute()
    }
}