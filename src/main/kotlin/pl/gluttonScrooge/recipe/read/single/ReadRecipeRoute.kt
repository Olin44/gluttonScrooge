package pl.gluttonScrooge.recipe

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.gluttonScrooge.database.executeQuery
import pl.gluttonScrooge.recipe.common.RecipeIdLocation
import pl.gluttonScrooge.recipe.common.findRecipeById
import pl.gluttonScrooge.recipe.read.ReadRecipeResponse

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.readRecipeRoute() {

    get<RecipeIdLocation> { findRecipeByIdRequest ->
        val id = findRecipeByIdRequest.id
        val recipe = executeQuery { findRecipeById(id) }
        call.respond(ReadRecipeResponse(recipe.id.value, recipe.title))
    }
}

