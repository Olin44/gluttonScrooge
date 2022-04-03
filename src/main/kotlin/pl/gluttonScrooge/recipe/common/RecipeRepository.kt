package pl.gluttonScrooge.recipe.common

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RecipeRepository(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RecipeRepository>(Recipes)
    var title by Recipes.title
}
