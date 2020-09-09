package uuu.sportsims.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.SportSimsException;
import uuu.sportsims.model.BasketballService;

public class TestSet {

    public static void main(String[] args) {
        try {
            BasketballService service = new BasketballService();
//            Set<Basketball> set = new HashSet<Basketball>();
//            set.add(service.get(1));
//            set.add(service.get(2));
//            set.add(service.get(3));
//            set.add(service.get(4));
//            set.add(service.get(1));
//            System.out.println("set = " + set.size());            

            List<Basketball> list = new ArrayList();
            list.add(service.get(1));
            list.add(service.get(2));
            list.add(service.get(3));
            System.out.println(list.get(0));
            System.out.println(list.contains(service.get(1)));
        } catch (SportSimsException ex) {
            Logger.getLogger(TestSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
