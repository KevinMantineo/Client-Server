/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainserver;

/**
 *
 * @author kevin
 */
public class MainServer {
    public static void main(String[] args) {
        Server mioServer = new Server(1234);
        mioServer.attendi();
    }
}
