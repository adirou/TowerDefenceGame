package UtilsPackage;

import java.util.Comparator;

/**
 * Created by USER on 6/9/2017.
 */
public class ComparatorWiners implements Comparator<UserDetails> {
    @Override
    public int compare(UserDetails o1, UserDetails o2) {
        try {
            System.out.println("ss");
            return Integer.parseInt(o2.getPointsString())-Integer.parseInt(o1.getPointsString());

        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
