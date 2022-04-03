package pl.gluttonScrooge.recipe.create

import kotlinx.serialization.Serializable

@Serializable
data class RecipeCreateRequest(val title: String)