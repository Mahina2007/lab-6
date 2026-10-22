package task14

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val message: String) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun fetchUserData(userId: String): Result<String> {
    if (userId.isBlank()) return Result.Error("Invalid user ID")
    if (userId == "admin") return Result.Success("Administrator Profile")
    return Result.Error("User not found")
}

fun handleResult(result: Result<String>) {
    when (result) {
        is Result.Success -> println("Success: ${result.data}")
        is Result.Error -> println("Error: ${result.message}")
        Result.Loading -> println("Loading data...")
    }
}

fun main() {
    val results = listOf(
        Result.Loading,
        fetchUserData("admin"),
        fetchUserData("unknown")
    )
    results.forEach { handleResult(it) }
}