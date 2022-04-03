package pl.gluttonScrooge.commons.exceptions

data class EntityNotFoundException(val entityNane: String, val entityId: Long,) :
    Exception("$entityNane with id=$entityId don't exist")
