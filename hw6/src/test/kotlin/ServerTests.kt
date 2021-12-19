import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {
    @Test
    fun testUserForm() = withTestApplication(Application::routing) {
        with(handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(
                listOf(
                    "id" to "34",
                    "username" to "Misha",
                    "password" to "misha2000",
                    "role" to "User"
                ).formUrlEncode()
            )
        }) {
            val expected = """
                <!DOCTYPE html>
                <html>
                  <head>
                    <title>HTML forms</title>
                  </head>
                  <body>
                    <div>
                      <div><input type="text" value="34"></div>
                      <div><input type="text" value="Misha"></div>
                      <div><input type="text" value="misha2000"></div>
                      <div><select><option selected="selected">User</option><option>Editor</option><option>Moderator</option><option>Administrator</option></select></div>
                    </div>
                  </body>
                </html>
            """.trimIndent()
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun testDefaultUserForm() {
        withTestApplication(Application::routing) {
            handleRequest(HttpMethod.Get, "/userForm").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val expected = """
                    <!DOCTYPE html>
                    <html>
                      <head>
                        <title>HTML forms</title>
                      </head>
                      <body>
                        <div>
                          <div><input type="text" value="1"></div>
                          <div><input type="text" value="user"></div>
                          <div><input type="text" value="12345678"></div>
                          <div><select><option selected="selected">User</option><option>Editor</option><option>Moderator</option><option>Administrator</option></select></div>
                        </div>
                      </body>
                    </html>
                """.trimIndent()
                assertEquals(expected, response.content)
            }
        }
    }

    @Test
    fun testProductForm() = withTestApplication(Application::routing) {
        with(handleRequest(HttpMethod.Post, "/order") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(
                listOf(
                    "id" to "20",
                    "name" to "slippers",
                    "status" to "Received"
                ).formUrlEncode()
            )
        }) {
            val expected = """
                <!DOCTYPE html>
                <html>
                  <head>
                    <title>HTML forms</title>
                  </head>
                  <body>
                    <div>
                      <div><input type="text" value="20"></div>
                      <div><input type="text" value="slippers"></div>
                      <div><select><option>Delivered</option><option>OnTheWay</option><option>AwaitingPayment</option><option selected="selected">Received</option></select></div>
                    </div>
                  </body>
                </html>
            """.trimIndent()
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun testDefaultProductForm() {
        withTestApplication(Application::routing) {
            handleRequest(HttpMethod.Get, "/orderForm").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val expected = """
                    <!DOCTYPE html>
                    <html>
                      <head>
                        <title>HTML forms</title>
                      </head>
                      <body>
                        <div>
                          <div><input type="text" value="1"></div>
                          <div><input type="text" value="order"></div>
                          <div><select><option selected="selected">Delivered</option><option>OnTheWay</option><option>AwaitingPayment</option><option>Received</option></select></div>
                        </div>
                      </body>
                    </html>
                """.trimIndent()
                assertEquals(expected, response.content)
            }
        }
    }
}