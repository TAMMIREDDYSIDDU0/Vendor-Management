package com.vendormanagement.rest_demo.monitoring;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
 @Controller
public class DummyHealthCeck implements HealthIndicator {

    @Autowired
    private Environment env;
    @Override
    public Health health(){
        try {
            if(isServiceUp())
                return Health.up().withDetail("Dummy Service" ,"is UP").build();
            else
                return  Health.down().withDetail("Dummy Service", "is DOWN").build();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
       return null;
    }
    private boolean isServiceUp() throws IOException{
        String address =env.getProperty("dummyService.address");
        String port  = env.getProperty("dummyService.port");
        System.out.println("Address: " + address + " Port : "+port);
        return isAdddressReachable(address, Integer.parseInt(port), 3000);
    }
    private boolean isAdddressReachable(String address , int port, int timeout) throws IOException {
        Socket socket = new Socket();
        try{
            socket.connect(new InetSocketAddress(address, port), timeout);
            return true;
        }catch (IOException exception){
            exception.printStackTrace();
            return false;
        }
        finally {
            socket.close();
        }

    }

}
