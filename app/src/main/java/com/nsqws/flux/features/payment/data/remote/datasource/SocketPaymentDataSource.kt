package com.nsqws.flux.features.payment.data.remote.datasource

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject
import android.util.Log
import io.socket.engineio.client.transports.WebSocket


class SocketPaymentDataSource @Inject constructor(
    private val baseUrl: String
) {
    private var socket: Socket? = null

    fun startListening(reference: String, onUpdate: (String) -> Unit) {
        try {
            val options = IO.Options.builder()
                .setTransports(arrayOf(WebSocket.NAME))
                .setReconnection(true)
                .setReconnectionAttempts(10)
                .setReconnectionDelay(2000)
                .build()

            socket = IO.socket(baseUrl, options).apply {

                on(Socket.EVENT_CONNECT) {
                    Log.d("SOCKET_DEBUG", "✅ ¡Conectado al servidor!")
                    emit("subscribePayment", reference)
                    Log.d("SOCKET_DEBUG", "Suscripción enviada para: $reference")
                }

                on(Socket.EVENT_CONNECT_ERROR) { args ->
                    Log.e("SOCKET_DEBUG", "❌ Error de conexión: ${args[0]}")
                }

                on("payment_update") { args ->
                    Log.d("SOCKET_DEBUG", "📩 ¡Evento recibido!")
                    if (args.isNotEmpty()) {
                        val data = args[0] as JSONObject
                        val status = data.getString("status")
                        Log.d("SOCKET_DEBUG", "Estado: $status")
                        onUpdate(status)
                    }
                }

                connect()
            }
        } catch (e: Exception) {
            Log.e("SOCKET_DEBUG", "Error en startListening", e)
        }
    }

    fun stopListening() {
        socket?.disconnect()
        socket = null
    }
}