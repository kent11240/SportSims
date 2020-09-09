package uuu.sportsims.test;

import uuu.sportsims.domain.Basketball;

public class TestArray {

    public static void main(String[] args) {
//        Basketball game[] = new Basketball[8];
//        for (int i = 0; i < 10; i++) {
//            game[i] = new Basketball();
//        }
//        double gameInfo[][] = new double[8][3];
//        System.out.println(gameInfo[0].length);
        String data = "123_away";
        String id = data.split("_")[0];
        String item = data.split("_")[1];
        System.out.println("id = " + id);
        System.out.println("item = " + item);
        
    }
}
