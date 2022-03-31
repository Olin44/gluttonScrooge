package pl.gluttonScrooge.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import java.net.URI
import java.util.*

object DatabaseFactory {

    fun init() {
        Database.connect(hikariDataSource())
    }

    private fun hikariDataSource(): HikariDataSource {
        val config = createCommonHikariConfig()
        if (Objects.equals(System.getenv("ENV"), "DEV")) {
            config.jdbcUrl = System.getenv("DATABASE_URL")
            config.username = System.getenv("DATABASE_USERNAME")
            config.password = System.getenv("DATABASE_PASSWORD")
        } else {
            val dbUri = URI(System.getenv("DATABASE_URL"))
            val username = dbUri.userInfo.split(":").toTypedArray()[0]
            val password = dbUri.userInfo.split(":").toTypedArray()[1]
            val dbUrl = "jdbc:postgresql://" + dbUri.host + ":" + dbUri.port + dbUri.path + "?sslmode=require" + "&user=$username&password=$password"
            config.jdbcUrl = dbUrl
        }
        config.validate()
        return HikariDataSource(config)
    }

    private fun createCommonHikariConfig(): HikariConfig {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        return config
    }
}