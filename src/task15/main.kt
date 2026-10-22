package task15

enum class Priority { LOW, MEDIUM, HIGH, URGENT }

sealed interface Ticket {
    val id: String
    val priority: Priority
}

data class TechnicalIssue(
    override val id: String,
    override val priority: Priority,
    val systemModule: String
) : Ticket

data class BillingQuestion(
    override val id: String,
    override val priority: Priority,
    val invoiceNumber: String
) : Ticket

data object GeneralInquiry : Ticket {
    override val id: String = "GEN-000"
    override val priority: Priority = Priority.LOW
}

fun processTicket(ticket: Ticket): String = when (ticket) {
    is TechnicalIssue -> "Handling technical problem in '${ticket.systemModule}' (Priority: ${ticket.priority})"
    is BillingQuestion -> "Processing billing inquiry for invoice ${ticket.invoiceNumber} (Priority: ${ticket.priority})"
    GeneralInquiry -> "Answering general support question (Priority: ${ticket.priority})"
}

fun main() {
    val supportQueue: List<Ticket> = listOf(
        TechnicalIssue("TICK-101", Priority.HIGH, "Database"),
        BillingQuestion("TICK-102", Priority.URGENT, "INV-8832"),
        GeneralInquiry,
        TechnicalIssue("TICK-103", Priority.LOW, "UI Layout")
    )

    // Сортировка тикетов по приоритету от URGENT к LOW
    val sortedQueue = supportQueue.sortedByDescending { it.priority }

    sortedQueue.forEach { ticket ->
        println("[${ticket.id}] -> ${processTicket(ticket)}")
    }
}