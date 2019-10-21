package com.busbadajoz.Utils;

import android.util.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitsParser {

    public static Pair<String, Units> parseDistance(String rawData){
        /*
            The data from the web gives us a value of meters. We will evaluate if we need to set it
            to meters or km.
         */

        int distance = Integer.parseInt(rawData);

        if (distance < 1000) {
            return new Pair<String, Units>(rawData, Integer.parseInt(rawData) == 1 ? Units.METER : Units.METERS);
        } else {
            float km = Float.parseFloat(rawData) / 1e3f;
            return new Pair<String, Units>(String.format("%.2f",(km)), Units.KILOMETER);
        }
    }

    public static Pair<String, Units> parseTime(String rawData){
        /*
            The data from the web gives us a value of meters. We will evaluate if we need to set it
            to meters or km.
         */

        if (rawData.equals("Próximo.")){
            //Special case when the bus is very close.
            return new Pair<String, Units>("1", Units.MINUTE);
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(rawData);
        int time;

        if (matcher.find()){
            time = Integer.parseInt(matcher.group());
        } else {
            return new Pair<String, Units>("-1", Units.MINUTE);
        }

        //Get the hours from the minutes (just hours).
        if (time < 60) {
            return new Pair<String, Units>(String.valueOf(time), time == 1 ? Units.MINUTE : Units.MINUTES);
        } else {
            // If the time left is more that an hour, we omit the minutes.
            int hours = (Integer) (time / 60);
            return new Pair<String, Units>(String.valueOf(hours), hours == 1 ? Units.HOUR : Units.HOURS);
        }
    }

    public static String parseLine(String rawData) {
        //OK, this is not a unit but we need to parse it anyways.

        Pattern pattern = Pattern.compile("LÍNEA");
        Matcher matcher = pattern.matcher(rawData);

        if (matcher.find()) {
            return rawData.split(" ")[1];
        } else {
            //Some lines don't have the name LINEAS in front, I don't know why.
            return rawData;
        }
    }
}
