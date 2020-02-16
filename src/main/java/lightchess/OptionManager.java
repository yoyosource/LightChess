package lightchess;

import java.util.HashMap;

public class OptionManager {

    private static HashMap<String, Boolean> options = new HashMap<String, Boolean>();

    public static void load(){
        options.put("limitfps", true);
    }
    public static void save(){

    }
    public static boolean getoption(String option){
        return options.get(option);
    }
    public static void setOption(String option, Boolean value){
        if(options.containsKey(option))
            options.remove(option);
        options.put(option, value);
    }

}
