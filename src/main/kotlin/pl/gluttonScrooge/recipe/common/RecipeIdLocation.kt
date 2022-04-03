package pl.gluttonScrooge.recipe.common

import io.ktor.locations.*

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/recipes/{id}")
data class RecipeIdLocation(val id: Long)