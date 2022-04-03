package pl.gluttonScrooge.recipe.common

import pl.gluttonScrooge.commons.exceptions.EntityNotFoundException

fun findRecipeById(id: Long) =
    RecipeRepository.findById(id) ?: throw EntityNotFoundException("Recipe", id)
