package pl.gluttonScrooge.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

suspend fun <T> executeQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }