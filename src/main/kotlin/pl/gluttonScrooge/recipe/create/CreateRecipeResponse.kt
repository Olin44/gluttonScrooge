package pl.gluttonScrooge.recipe.create

import kotlinx.serialization.Serializable

@Serializable
data class CreateRecipeResponse(val message: String, val createdEntityId: Long)