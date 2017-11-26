package lib.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Aaron Fang on 11/26/2017.
 */

/**An interpolating tree map takes a map, and when given an input key that is not in the original key,
 * it uses linear interpolation to estimate a value from the entries that are already in the map.
 * This is used in our shooter.
 */
public class InterpolatingTreeMap {
    private TreeMap<Double, Double> data;

    public InterpolatingTreeMap(TreeMap data){
        this.data = data;
    }

    public double getValue(double key){
        if(data.containsKey(key)){
            return data.get(key);
        }
        else{
            try {
                Map.Entry<Double, Double> upperKey = data.ceilingEntry(key);
                Map.Entry<Double, Double> lowerKey = data.floorEntry(key);

                //find slope between two points
                double m = (upperKey.getValue() - lowerKey.getValue()) / (upperKey.getKey() - lowerKey.getValue());
                return m * (key - upperKey.getKey()) + upperKey.getValue();
            }
            catch(NullPointerException e){
                return 0;
            }
        }
    }
}