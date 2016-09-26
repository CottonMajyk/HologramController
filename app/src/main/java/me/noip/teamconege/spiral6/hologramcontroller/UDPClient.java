package me.noip.teamconege.spiral6.hologramcontroller;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Colton on 9/23/2016.
 */
public class UDPClient extends AsyncTask<String, Void, Void> {
    public Void doInBackground(String... command)
    {
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("192.168.43.76");
            byte[] sendData = new byte[1024];
            sendData = command[0].getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 23459);
            clientSocket.send(sendPacket);
            clientSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
