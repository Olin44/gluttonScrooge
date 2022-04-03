package pl.gluttonScrooge.recipe.read.pagination

import io.ktor.locations.*

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/recipes")
data class RecipesPagingLocation(val limit: Int, val offset: Long)