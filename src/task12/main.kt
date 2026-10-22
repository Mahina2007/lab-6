package task12

sealed interface AppError {
    val code: Int
}

data class NetworkError(override val code: Int, val endpoint: String) : AppError
data class DatabaseError(override val code: Int, val query: String) : AppError
data object UnknownError : AppError {
    override val code: Int = 500
}

fun formatError(error: AppError): String = when (error) {
    is NetworkError -> "Network failure at ${error.endpoint} (code ${error.code})"
    is DatabaseError -> "Database error in query '${error.query}' (code ${error.code})"
    UnknownError -> "An unknown error occurred (code ${error.code})"
}

fun main() {
    val errors: List<AppError> = listOf(
        NetworkError(404, "/api/users"),
        DatabaseError(1064, "SELECT * FROM"),
        UnknownError
    )
    errors.forEach { println(formatError(it)) }
}