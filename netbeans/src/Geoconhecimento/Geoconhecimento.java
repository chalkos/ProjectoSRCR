/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Chalkos
 */
public class Geoconhecimento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Prolog conhecimento = new Prolog("..\\pontos.pl");
        
        //System.out.println(conhecimento.getResults("arco(p1,p7)."));
        
        System.out.println(conhecimento.check("arco(p1,p7)."));
    }
    
}
