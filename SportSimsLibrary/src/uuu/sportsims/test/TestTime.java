/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.sportsims.test;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TestTime {

    public static void main(String[] args) {
        Date d1 = new Date(50000000);
        Date d2 = new Date(40000000);
        Date d3 = new Date(d2.getTime() - d1.getTime());
        Date d4 = new Date(10000000);
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3.getTime());
        System.out.println("d4 = " + d4);
    }
}
