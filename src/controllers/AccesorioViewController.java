package controllers;

import javax.swing.JFrame;

import Services.AccesoriosService;
import View.AccesorioView;

public class AccesorioViewController {
    private AccesoriosService as;
    private AccesorioView av; 


    public AccesorioViewController(AccesorioView av){
        this.av = av;
        this.as = new AccesoriosService();
        
    }
}
