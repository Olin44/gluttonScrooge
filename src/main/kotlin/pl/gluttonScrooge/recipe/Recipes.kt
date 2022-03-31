package pl.gluttonScrooge.recipe

import org.jetbrains.exposed.dao.id.LongIdTable

object Recipes : LongIdTable("recipes") {
    val title = varchar("title", 30)
}