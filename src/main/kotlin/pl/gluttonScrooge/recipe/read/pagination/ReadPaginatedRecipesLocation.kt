package pl.gluttonScrooge.recipe.read.pagination

import io.ktor.locations.*

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/recipes")
data class ReadPaginatedRecipesLocation(val limit: Int, val offset: Long)