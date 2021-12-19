import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import kotlinx.serialization.Serializable
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

@Serializable
class User(
    val id: Long,
    val username: String,
    val password: String,
    val role: Role
)

@Serializable
class Order(
    val id: Long,
    val name: String,
    val status: Status
)

enum class Status {
    Delivered,
    OnTheWay,
    AwaitingPayment,
    Received,
    Rejected
}

enum class Role {
    User,
    Editor,
    Moderator,
    Admin,
}

inline fun <reified T : Any> HTML.form(obj: T) {
    head {
        title { +"HTML forms" }
    }
    body {
        div {
            T::class
                .memberProperties
                .map {
                    div {
                        val v = it.call(obj)
                        if (it.returnType.jvmErasure.java.isEnum) {
                            select {
                                it.returnType.jvmErasure.java.enumConstants.forEach {
                                    option {
                                        if (it.toString() == v.toString()) selected = true
                                        text(it.toString())
                                    }
                                }
                            }
                        } else {
                            textInput {
                                value = v.toString()
                            }
                        }
                    }
                }
        }
    }
}

fun HTML.orderDefaultForm() = form(Order(1, "order", Status.Delivered))

fun HTML.userDefaultForm() = form(User(1, "user", "12345678", Role.User))

fun Application.routing() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/orderForm") {
            call.respondHtml(HttpStatusCode.OK, HTML::orderDefaultForm)
        }
        post("/order") {
            val parameters = call.receiveParameters()
            val id = parameters["id"]?.toLong() ?: 0
            val name = parameters["name"] ?: "Unknown"
            val status =
                when (parameters["status"]) {
                    "Delivered" -> Status.Delivered
                    "OnTheWay" -> Status.OnTheWay
                    "AwaitingPayment" -> Status.AwaitingPayment
                    "Received" -> Status.Received
                    else -> Status.Rejected
                }

            fun HTML.productForm() = form(Order(id, name, status))
            call.respondHtml(HttpStatusCode.OK, HTML::productForm)
        }
        get("/userForm") {
            call.respondHtml(HttpStatusCode.OK, HTML::userDefaultForm)
        }
        post("/user") {
            val parameters = call.receiveParameters()
            val id = parameters["id"]?.toLong() ?: 0
            val nickname = parameters["username"] ?: "Unknown"
            val password = parameters["password"] ?: "Unknown"
            val status =
                when (parameters["status"]) {
                    "Moderator" -> Role.Moderator
                    "Admin" -> Role.Admin
                    "Editor" -> Role.Editor
                    else -> Role.User
                }

            fun HTML.userForm() = form(User(id, nickname, password, status))
            call.respondHtml(HttpStatusCode.OK, HTML::userForm)
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing()
    }.start(wait = true)
}