package pl.gluttonScrooge.recipe

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.recipes() {

    get<RecipesPaging> { recipesPaging ->
        val limit = recipesPaging.limit
        val offset = recipesPaging.offset
        call.respond(dbQuery {
            Recipe.all().limit(limit, offset = offset)
                .map { RecipeResponse(it.id.value, it.title) }
        })
    }

    route("/recipes") {
        get("{id}") {
            val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Id must be valid long")
            val recipe = dbQuery { Recipe[id] }
            call.respond(RecipeResponse(recipe.id.value, recipe.title))
        }
        post {
            val recipeCreateRequest = call.receive<RecipeCreateRequest>()
            val createdRecipe = dbQuery{ Recipe.new { title = recipeCreateRequest.title }}
            call.respondText("Recipe created successfully $createdRecipe", status = HttpStatusCode.Created)
        }
        delete("{id}") {

        }
    }
}

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }

@Location("/recipes")
data class RecipesPaging(val limit: Int, val offset: Long)