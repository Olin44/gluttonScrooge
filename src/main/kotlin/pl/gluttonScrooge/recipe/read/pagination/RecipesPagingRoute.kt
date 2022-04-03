package pl.gluttonScrooge.recipe.read.pagination

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.gluttonScrooge.database.executeQuery
import pl.gluttonScrooge.recipe.common.RecipeRepository
import pl.gluttonScrooge.recipe.read.ReadRecipeResponse

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.recipePaginationRoute() {
    get<RecipesPagingLocation> { recipesPaging ->
        val limit = recipesPaging.limit
        val offset = recipesPaging.offset
        call.respond(executeQuery {
            RecipeRepository.all().limit(limit, offset = offset)
                .map { ReadRecipeResponse(it.id.value, it.title) }
        })
    }
}