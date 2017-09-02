/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author diego
 */
public final class Connector {
    
    private Connector(){}
    public static Connector getInstance(){
        if(conInstance == null){
            conInstance = new Connector();
        }
        return conInstance;
    }
    
    private static Connector conInstance;
    
        String login = "root";
//        String url= "jdbc:mysql://localhost:3306/fact_gruas?autoReconnect=true&useSSL=true";
        String url= "jdbc:mysql://localhost:3306/fact_gruas";
//        String password = "205243";
//        String url = "jdbc:mysql://10.20.224.100:3306/fact_gruas";
        String password = "gruas_205243";
    public String getLogin() {
        return login;
    }

    public  String getPassword() {
        return password;
    }

    public  String getUrl() {
        return url;
    }
}

