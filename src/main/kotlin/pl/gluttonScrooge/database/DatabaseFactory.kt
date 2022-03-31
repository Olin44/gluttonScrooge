package pl.gluttonScrooge.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import java.net.URI
import java.util.*

object DatabaseFactory {

    fun init() {
        val credentials = createCredentials()
        Database.connect(hikariDataSource(credentials))
        val flyway = Flyway.configure().dataSource(credentials.dbUrl, credentials.userName, credentials.password).load()
        flyway.migrate()
    }

    private fun hikariDataSource(credentials: Credentials): HikariDataSource {
        val config = createCommonHikariConfig()
        config.jdbcUrl = credentials.dbUrl
        config.username = credentials.userName
        config.password = credentials.password
        config.validate()
        return HikariDataSource(config)
    }

    private fun createCredentials(): Credentials {
        return if (Objects.equals(System.getenv("ENV"), "DEV")) {
            val jdbcUrl = System.getenv("DATABASE_URL")
            val username = System.getenv("DATABASE_USERNAME")
            val password = System.getenv("DATABASE_PASSWORD")
            Credentials(jdbcUrl, username, password)
        } else {
            val dbUri = URI(System.getenv("DATABASE_URL"))
            val username = dbUri.userInfo.split(":").toTypedArray()[0]
            val password = dbUri.userInfo.split(":").toTypedArray()[1]
            val dbUrl =
                "jdbc:postgresql://" + dbUri.host + ":" + dbUri.port + dbUri.path + "?sslmode=require" + "&user=$username&password=$password"
            Credentials(dbUrl, username, password)
        }
    }

    private fun createCommonHikariConfig(): HikariConfig {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        return config
    }

    data class Credentials(val dbUrl: String, val userName: String, val password: String)
}