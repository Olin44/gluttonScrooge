package pl.gluttonScrooge.recipe

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Recipe(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Recipe>(Recipes)
    var title by Recipes.title
}

@Serializable
data class RecipeResponse(val id: Long, val title: String)

@Serializable
data class RecipeCreateRequest(val title: String)
