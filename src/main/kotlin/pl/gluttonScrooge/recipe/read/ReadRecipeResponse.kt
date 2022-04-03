package pl.gluttonScrooge.recipe.read

import kotlinx.serialization.Serializable

@Serializable
data class ReadRecipeResponse(val id: Long, val title: String)