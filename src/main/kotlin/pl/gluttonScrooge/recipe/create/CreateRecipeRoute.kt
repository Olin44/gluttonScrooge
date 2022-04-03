package pl.gluttonScrooge.recipe.create

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.gluttonScrooge.database.executeQuery
import pl.gluttonScrooge.recipe.common.RecipeRepository

fun Route.createRecipeRoute() {
    route("/recipes") {
        post {
            val recipeCreateRequest = call.receive<RecipeCreateRequest>()
            val createdRecipeRepository = executeQuery { RecipeRepository.new { title = recipeCreateRequest.title } }
            call.respond(
                status = HttpStatusCode.Created,
                CreateRecipeResponse(
                    "Recipe created successfully $createdRecipeRepository",
                    createdRecipeRepository.id.value
                )
            )
        }
    }
}