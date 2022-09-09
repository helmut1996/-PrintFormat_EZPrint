package com.example.impresorarp4;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.SystemClock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    public final BluetoothSocket mmSocket;
    public final InputStream mmInStream;
    public final OutputStream mmOutStream;
    private final Handler mHandler;

    public ConnectedThread(BluetoothSocket socket, Handler handler) {
        mmSocket = socket;
        mHandler = handler;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;


// Obtenga los flujos de entrada y salida, usando objetos temporales porque
        // los flujos de miembros son definitivos
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];// almacén de búfer para el flujo
        int bytes;// bytes devueltos desde read()
// Seguir escuchando InputStream hasta que ocurra una excepción
        while (true) {
            try {

            // Leer del InputStream
                bytes = mmInStream.available();
                if(bytes != 0) {
                    buffer = new byte[1024];
                    SystemClock.sleep(100); //pausar y esperar el resto de datos. Ajuste esto dependiendo de su velocidad de envío.
                    bytes = mmInStream.available(); // ¿Cuántos bytes están listos para ser leídos?
                    bytes = mmInStream.read(buffer, 0, bytes); //  registrar cuántos bytes leemos realmente
                    mHandler.obtainMessage(MainActivity.MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget(); // Envía los bytes obtenidos a la actividad de la interfaz de usuario
                }
            } catch (IOException e) {
                e.printStackTrace();

                break;
            }
        }
    }

    /* Llame a esto desde la actividad principal para enviar datos al dispositivo remoto */
    public void write(String input) {
        byte[] bytes = input.getBytes();           //convierte la cadena ingresada en bytes
        try {
            mmOutStream.write(bytes);

        } catch (IOException e) { }
    }

    /* Llame a esto desde la actividad principal para cerrar la conexión*/
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}