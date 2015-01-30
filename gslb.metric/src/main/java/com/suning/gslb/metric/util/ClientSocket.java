package com.suning.gslb.metric.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClientSocket {
    
    private String connectionName;
    private ConnectionState connectionState = ConnectionState.DISCONNECTED;
    private static Map<String,ClientSocket> cache = Collections.synchronizedMap(new HashMap<String,ClientSocket>());
    private Socket socket;
    private InputStream inputStream;
    private String host_ip;
    private int port;
    
    private  ClientSocket(String host_ip,int port){
        this.connectionName = getConnectionName(host_ip,port);
        this.host_ip = host_ip;
        this.port = port;
    }

    private static String getConnectionName(String ip, int port) {
        return ip+"@"+port;
    }
    
    public static ClientSocket getClientSocket(String ip,int port){
        final String key = getConnectionName(ip, port);
        ClientSocket cs = cache.get(key);
        if(cs == null){
            ClientSocket client = new ClientSocket(ip, port);
            cache.put(key, client);
        }
        return cs;
    }
    
    public InputStream getInputStream(){
        try {
            socket = new Socket(this.host_ip, this.port);
            inputStream = socket.getInputStream();
            if(socket.isConnected()) 
                connectionState = ConnectionState.CONNECTED;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return inputStream;
    }
    
    public enum ConnectionState {
        DISCONNECTED, CONNECTED
    }
    boolean isConnected(){
        return connectionState.equals(ConnectionState.CONNECTED);
    }
}
