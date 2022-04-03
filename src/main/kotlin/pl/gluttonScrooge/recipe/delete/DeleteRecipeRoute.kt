package pl.gluttonScrooge.recipe.delete

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.gluttonScrooge.database.executeQuery
import pl.gluttonScrooge.recipe.common.RecipeIdLocation
import pl.gluttonScrooge.recipe.common.findRecipeById

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.deleteRecipeRoute() {

    delete<RecipeIdLocation> { deleteRecipeByIdRequest ->
        val recipe = executeQuery { findRecipeById(deleteRecipeByIdRequest.id) }
        executeQuery { recipe.delete() }
        call.respond(DeletedResponse("Recipe removed successfully"))
    }
}