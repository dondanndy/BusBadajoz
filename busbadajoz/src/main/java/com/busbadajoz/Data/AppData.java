package com.busbadajoz.Data;

import com.busbadajoz.models.data.StopMapModel;

import java.util.HashMap;

/*
    In this file we are going to define a map with all the stops available, in a model that carries
    their names, codes, and lines buses that go through them. To get each line stop there's an array
    for each bus: the first element contains the line, the second the code for the next stop of that
    line and the third the direction of the line.

    That way, to follow a line we take the first stop defined in an array of first stops and we go
    to the stop that has that code. Then, we look at the bus array containing the line number we are
    following, which has the code to the next sop until we reach code -1, end of the line.
 */

public class AppData {
    private HashMap<String, String[]> first_stops = new HashMap<>();

    private HashMap<String, StopMapModel> stop_maps;

    public AppData(){
        this.stop_maps = new HashMap<>();
        fill_first_map(this.first_stops);
        fill_stops_map(this.stop_maps);
    }

    public HashMap<String, StopMapModel> getMap(){
        return this.stop_maps;
    }

    private void fill_first_map(HashMap<String, String[]> map){
        map.put("2", new String[]{"200", "7"});
        map.put("3", new String[]{"143", "71"});
        map.put("4", new String[]{"141", "176"});
        map.put("5", new String[]{"100", "186"});
        map.put("5N", new String[]{"219", "100"});
        map.put("6", new String[]{"1817", "1760"});
        map.put("6N", new String[]{"26", "131"});
        map.put("7", new String[]{"113", "91"});
        map.put("7N", new String[]{"100", "174"});
        map.put("9", new String[]{"136", "71"});
        map.put("9N", new String[]{"200", "77"});
        map.put("11", new String[]{"150", "1780"});
        map.put("12", new String[]{"115", "1782"});
        map.put("13", new String[]{"150", "1766"});
        map.put("18", new String[]{"230", "71"});
        map.put("18N", new String[]{"230", "113"});
        map.put("CM", new String[]{"211", "1790"});
        map.put("CA", new String[]{"205", "235"});
        map.put("C1", new String[]{"71", "187"});
        map.put("C2", new String[]{"70", "188"});
        map.put("S1", new String[]{"202", "1787"});
        map.put("S2", new String[]{"211", "191"});
        map.put("21", new String[]{"9", "174"});
        map.put("M1", new String[]{"101", "29"});
        map.put("M3", new String[]{"1749", "1747"});
        map.put("M4", new String[]{"248", "1814"});
    }

    private void fill_stops_map(HashMap<String, StopMapModel> map){
        /*
            In this method we fill the map with the data we have obtained previously in the company
            website. Maybe it's not the most efficient way but it's still fast enough even in older
            phones (always <0.1sec in my testing).
         */
        StopMapModel stop = new StopMapModel("Lady Smith  23", new String [][]{{"18", "234", "1"},{"12", "234", "2"},{"18N", "234", "1"}}, new float[]{(float) 38.889041, (float) -6.900652});
        map.put("230", stop);

        stop = new StopMapModel("Lady Smith  7", new String [][]{{"18", "172", "1"},{"12", "172", "2"},{"18N", "174", "1"}}, new float[]{(float) 38.889683, (float) -6.904467});
        map.put("234", stop);

        stop = new StopMapModel("Avda. José Caldito Ruiz", new String [][]{{"18", "173", "1"},{"12", "173", "2"}}, new float[]{(float) 38.880537, (float) -6.945366});
        map.put("172", stop);

        stop = new StopMapModel("C/ José Caldito Ruiz  3", new String [][]{{"18", "174", "1"},{"12", "174", "2"}}, new float[]{(float) 38.879866, (float) -6.948882});
        map.put("173", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano", new String [][]{{"18", "175", "1"},{"21", "175", "2"},{"12", "175", "2"},{"7N", "175", "2"},{"18N", "175", "1"}}, new float[]{(float) 38.878423, (float) -6.950556});
        map.put("174", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano 87", new String [][]{{"18", "176", "1"},{"21", "176", "2"},{"12", "176", "2"},{"7N", "176", "2"},{"18N", "176", "1"}}, new float[]{(float) 38.878665, (float) -6.953997});
        map.put("175", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  67", new String [][]{{"4", "177", "2"},{"18", "177", "1"},{"C1", "177", "2"},{"21", "177", "2"},{"12", "177", "2"},{"7N", "177", "2"},{"18N", "177", "1"}}, new float[]{(float) 38.878947, (float) -6.956224});
        map.put("176", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  35", new String [][]{{"4", "178", "2"},{"18", "178", "1"},{"C1", "178", "2"},{"21", "178", "2"},{"12", "178", "2"},{"7N", "178", "2"},{"18N", "178", "1"}}, new float[]{(float) 38.879199, (float) -6.958875});
        map.put("177", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  9", new String [][]{{"4", "197", "2"},{"18", "197", "1"},{"C1", "179", "2"},{"21", "197", "2"},{"12", "197", "2"},{"7N", "197", "2"},{"18N", "197", "1"}}, new float[]{(float) 38.879160, (float) -6.961473});
        map.put("178", stop);

        stop = new StopMapModel("Ronda del Pilar  57", new String [][]{{"4", "198", "2"},{"7", "198", "1"},{"18", "198", "1"},{"21", "198", "2"},{"12", "198", "2"},{"CA", "198", "1"},{"M3", "198", "1"},{"M4", "198", "2"},{"7N", "198", "2"},{"18N", "198", "1"}}, new float[]{(float) 38.876284, (float) -6.966831});
        map.put("197", stop);

        stop = new StopMapModel("Ronda del Pilar  29", new String [][]{{"4", "199", "2"},{"7", "199", "1"},{"18", "199", "1"},{"21", "199", "2"},{"12", "199", "2"},{"CA", "1828", "1"},{"M3", "199", "1"},{"M4", "199", "2"},{"7N", "199", "2"},{"18N", "199", "1"}}, new float[]{(float) 38.875583, (float) -6.969416});
        map.put("198", stop);

        stop = new StopMapModel("Ronda del Pilar", new String [][]{{"4", "158", "2"},{"7", "202", "1"},{"18", "201", "1"},{"21", "201", "2"},{"12", "158", "2"},{"M3", "201", "1"},{"M4", "201", "2"},{"7N", "202", "2"},{"18N", "202", "1"}}, new float[]{(float) 38.875261, (float) -6.971206});
        map.put("199", stop);

        stop = new StopMapModel("Ronda del Pilar  4", new String [][]{{"2", "212", "1"},{"5", "202", "2"},{"18", "202", "1"},{"21", "212", "2"},{"CA", "202", "2"},{"M3", "212", "1"},{"M4", "-1", "-1"}}, new float[]{(float) 38.875217, (float) -6.973151});
        map.put("201", stop);

        stop = new StopMapModel("Plaza de La Libertad", new String [][]{{"3", "207", "1"},{"5", "207", "2"},{"6", "203", "1"},{"7", "207", "1"},{"9", "207", "1"},{"18", "203", "1"},{"S1", "207", "1"},{"S2", "-1", "-1"},{"11", "203", "1"},{"13", "203", "1"},{"CA", "203", "2"},{"CM", "-1", "-1"},{"5N", "207", "1"},{"6N", "203", "2"},{"7N", "207", "2"},{"18N", "207", "1"},{"9N", "207", "1"}}, new float[]{(float) 38.875516, (float) -6.974570});
        map.put("202", stop);

        stop = new StopMapModel("Av. Santiago Ramón Y Cajal  11", new String [][]{{"6", "204", "1"},{"18", "204", "1"},{"11", "204", "1"},{"13", "204", "1"},{"CA", "1831", "2"},{"6N", "204", "2"}}, new float[]{(float) 38.876997, (float) -6.976973});
        map.put("203", stop);

        stop = new StopMapModel("Plaza Reyes Católicos  7", new String [][]{{"6", "1748", "1"},{"18", "18", "1"},{"11", "1748", "1"},{"13", "53", "1"},{"CA", "-1", "-1"},{"6N", "18", "2"}}, new float[]{(float) 38.880489, (float) -6.975189});
        map.put("204", stop);

        stop = new StopMapModel("Rotonda Ntra. Sra. de Botoa", new String [][]{{"18", "54", "1"},{"C2", "238", "1"},{"11", "205", "2"},{"13", "52", "2"},{"6N", "54", "2"}}, new float[]{(float) 38.886437, (float) -6.975600});
        map.put("18", stop);

        stop = new StopMapModel("Av. Carolina Coronado  4", new String [][]{{"2", "55", "1"},{"18", "55", "1"},{"21", "55", "2"},{"6N", "55", "2"}}, new float[]{(float) 38.885818, (float) -6.980111});
        map.put("54", stop);

        stop = new StopMapModel("Av. Carolina Coronado  16", new String [][]{{"2", "56", "1"},{"18", "26", "1"},{"21", "56", "2"},{"6N", "-1", "-1"}}, new float[]{(float) 38.887696, (float) -6.981797});
        map.put("55", stop);

        stop = new StopMapModel("Av. Carolina Coronado EST. FF.CC.", new String [][]{{"2", "49", "2"},{"18", "28", "1"},{"C1", "28", "2"},{"21", "49", "1"},{"6N", "49", "1"}}, new float[]{(float) 38.890253, (float) -6.982364});
        map.put("26", stop);

        stop = new StopMapModel("C/ Valladolid  4", new String [][]{{"18", "44", "1"},{"C1", "43", "2"}}, new float[]{(float) 38.888177, (float) -6.983162});
        map.put("28", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  22", new String [][]{{"18", "45", "1"},{"M1", "45", "2"}}, new float[]{(float) 38.887437, (float) -6.985552});
        map.put("44", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  4", new String [][]{{"18", "64", "1"},{"M1", "216", "2"}}, new float[]{(float) 38.885020, (float) -6.984919});
        map.put("45", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona", new String [][]{{"3", "65", "1"},{"18", "65", "1"},{"7N", "89", "1"}}, new float[]{(float) 38.883968, (float) -6.985939});
        map.put("64", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona  32", new String [][]{{"3", "66", "1"},{"18", "66", "1"},{"7N", "66", "2"}}, new float[]{(float) 38.883604, (float) -6.989864});
        map.put("65", stop);

        stop = new StopMapModel("Av. de Elvas  57", new String [][]{{"3", "67", "1"},{"18", "67", "1"},{"7N", "114", "2"}}, new float[]{(float) 38.883221, (float) -6.994348});
        map.put("66", stop);

        stop = new StopMapModel("Av. de Elvas  9", new String [][]{{"3", "86", "1"},{"9", "86", "1"},{"18", "86", "1"},{"9N", "74", "1"}}, new float[]{(float) 38.882681, (float) -6.999851});
        map.put("67", stop);

        stop = new StopMapModel("Av. de La Biología", new String [][]{{"3", "81", "1"},{"9", "74", "1"},{"18", "74", "1"}}, new float[]{(float) 38.882358, (float) -7.008176});
        map.put("86", stop);

        stop = new StopMapModel("Av. de Elvas  7", new String [][]{{"9", "75", "1"},{"18", "75", "1"},{"9N", "75", "1"}}, new float[]{(float) 38.882052, (float) -7.015659});
        map.put("74", stop);

        stop = new StopMapModel("Av. de Elvas  6", new String [][]{{"9", "76", "1"},{"18", "76", "1"},{"9N", "76", "1"}}, new float[]{(float) 38.882388, (float) -7.018558});
        map.put("75", stop);

        stop = new StopMapModel("Av. de Elvas  (El Faro)", new String [][]{{"9", "80", "1"},{"18", "80", "1"},{"9N", "77", "1"}}, new float[]{(float) 38.882888, (float) -7.022436});
        map.put("76", stop);

        stop = new StopMapModel("Av. de Elvas  13", new String [][]{{"9", "81", "1"},{"18", "81", "1"},{"9N", "114", "2"}}, new float[]{(float) 38.881662, (float) -7.013783});
        map.put("80", stop);

        stop = new StopMapModel("Av. de La Física", new String [][]{{"3", "82", "1"},{"9", "82", "1"},{"18", "82", "1"}}, new float[]{(float) 38.884699, (float) -7.010806});
        map.put("81", stop);

        stop = new StopMapModel("Av. de La Investigación 1", new String [][]{{"3", "83", "1"},{"9", "83", "1"},{"18", "83", "1"}}, new float[]{(float) 38.885465, (float) -7.008644});
        map.put("82", stop);

        stop = new StopMapModel("Av. de La Investigación 2", new String [][]{{"3", "84", "1"},{"9", "84", "1"},{"18", "84", "1"}}, new float[]{(float) 38.885729, (float) -7.006573});
        map.put("83", stop);

        stop = new StopMapModel("Av. de La Investigación 3", new String [][]{{"3", "71", "1"},{"9", "71", "1"},{"18", "71", "1"}}, new float[]{(float) 38.886107, (float) -7.003497});
        map.put("84", stop);

        stop = new StopMapModel("Facultad de Medicina", new String [][]{{"3", "36", "2"},{"9", "36", "2"},{"18", "36", "2"},{"C1", "72", "1"}}, new float[]{(float) 38.886430, (float) -7.000465});
        map.put("71", stop);

        stop = new StopMapModel("C/ Federico Mayor Zaragoza", new String [][]{{"3", "143", "2"},{"9", "136", "2"},{"18", "230", "2"}}, new float[]{(float) 38.886518, (float) -6.998433});
        map.put("36", stop);

        stop = new StopMapModel("C/ Federico Mayor Zaragoza  181", new String [][]{{"3", "87", "2"},{"9", "114", "2"},{"18", "87", "2"}}, new float[]{(float) 38.884018, (float) -6.998083});
        map.put("37", stop);

        stop = new StopMapModel("Av. de Elvas  53", new String [][]{{"3", "88", "2"},{"18", "88", "2"},{"7N", "88", "1"}}, new float[]{(float) 38.882938, (float) -6.995024});
        map.put("87", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona", new String [][]{{"3", "65", "1"},{"18", "65", "1"},{"7N", "89", "1"}}, new float[]{(float) 38.883521, (float) -6.989440});
        map.put("88", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona  20", new String [][]{{"3", "227", "2"},{"18", "46", "2"},{"7N", "227", "1"}}, new float[]{(float) 38.883834, (float) -6.986299});
        map.put("89", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  2", new String [][]{{"18", "47", "2"},{"M1", "43", "1"}}, new float[]{(float) 38.884849, (float) -6.984734});
        map.put("46", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  23", new String [][]{{"18", "48", "2"}}, new float[]{(float) 38.887454, (float) -6.985473});
        map.put("47", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  3", new String [][]{{"18", "27", "2"},{"C2", "56", "1"}}, new float[]{(float) 38.888358, (float) -6.984333});
        map.put("48", stop);

        stop = new StopMapModel("Av. Carolina Coronado  318", new String [][]{{"18", "49", "2"}}, new float[]{(float) 38.888609, (float) -6.982694});
        map.put("27", stop);

        stop = new StopMapModel("Av. Carolina Coronado  52", new String [][]{{"2", "51", "2"},{"18", "51", "2"},{"21", "51", "1"},{"6N", "51", "1"}}, new float[]{(float) 38.887640, (float) -6.981943});
        map.put("49", stop);

        stop = new StopMapModel("Av. Carolina Coronado  74", new String [][]{{"2", "52", "2"},{"6", "19", "2"},{"18", "19", "2"},{"21", "52", "1"},{"6N", "19", "1"}}, new float[]{(float) 38.885823, (float) -6.980387});
        map.put("51", stop);

        stop = new StopMapModel("C/ Ntra. Sra. de Bótoa  20", new String [][]{{"6", "205", "2"},{"18", "205", "2"},{"6N", "205", "1"}}, new float[]{(float) 38.885396, (float) -6.977383});
        map.put("19", stop);

        stop = new StopMapModel("Av. Santiago Ramón Y Cajal  9", new String [][]{{"6", "206", "2"},{"18", "206", "2"},{"11", "206", "2"},{"13", "206", "2"},{"CA", "1821", "1"},{"6N", "206", "1"}}, new float[]{(float) 38.880056, (float) -6.976227});
        map.put("205", stop);

        stop = new StopMapModel("Av. Santiago Ramón Y Cajal  4", new String [][]{{"6", "211", "2"},{"18", "211", "2"},{"11", "211", "2"},{"13", "211", "2"},{"6N", "211", "1"}}, new float[]{(float) 38.876940, (float) -6.976973});
        map.put("206", stop);

        stop = new StopMapModel("Plaza La Libertad (Tubasa)", new String [][]{{"3", "248", "2"},{"5", "248", "1"},{"6", "248", "2"},{"7", "248", "2"},{"9", "248", "2"},{"18", "212", "2"},{"S2", "212", "1"},{"11", "248", "2"},{"13", "248", "2"},{"CM", "248", "1"},{"5N", "248", "2"},{"6N", "212", "1"},{"7N", "248", "1"},{"18N", "248", "2"}}, new float[]{(float) 38.875414, (float) -6.974404});
        map.put("211", stop);

        stop = new StopMapModel("Av. de Huelva  10", new String [][]{{"2", "213", "1"},{"18", "248", "2"},{"21", "213", "2"},{"S2", "236", "1"},{"M3", "213", "1"},{"M4", "248", "2"},{"6N", "213", "1"}}, new float[]{(float) 38.873730, (float) -6.975032});
        map.put("212", stop);

        stop = new StopMapModel("C/ Enrique Segura Otaño  3", new String [][]{{"2", "157", "2"},{"3", "236", "2"},{"5", "236", "1"},{"6", "236", "2"},{"7", "156", "2"},{"9", "236", "2"},{"18", "156", "2"},{"21", "156", "1"},{"11", "236", "2"},{"13", "236", "2"},{"M3", "156", "2"},{"M4", "156", "1"},{"CM", "236", "1"},{"5N", "236", "2"},{"7N", "156", "1"},{"18N", "156", "2"}}, new float[]{(float) 38.873182, (float) -6.974466});
        map.put("248", stop);

        stop = new StopMapModel("C/ Enrique Segura Otaño  17", new String [][]{{"7", "161", "2"},{"18", "161", "2"},{"21", "161", "1"},{"12", "161", "1"},{"M3", "161", "2"},{"M4", "161", "1"},{"7N", "161", "1"},{"18N", "161", "2"}}, new float[]{(float) 38.873039, (float) -6.971897});
        map.put("156", stop);

        stop = new StopMapModel("Av. de Pardaleras  10", new String [][]{{"7", "162", "2"},{"18", "162", "2"},{"21", "162", "1"},{"12", "162", "1"},{"M3", "162", "2"},{"M4", "162", "1"},{"7N", "162", "1"},{"18N", "162", "2"}}, new float[]{(float) 38.873309, (float) -6.970020});
        map.put("161", stop);

        stop = new StopMapModel("Av. de Pardaleras  46", new String [][]{{"7", "163", "2"},{"18", "163", "2"},{"21", "163", "1"},{"12", "163", "1"},{"M3", "163", "2"},{"M4", "163", "1"},{"7N", "163", "1"},{"18N", "163", "2"}}, new float[]{(float) 38.874210, (float) -6.966700});
        map.put("162", stop);

        stop = new StopMapModel("Av. de Pardaleras", new String [][]{{"7", "164", "2"},{"18", "164", "2"},{"21", "164", "1"},{"12", "164", "1"},{"M3", "194", "2"},{"M4", "194", "1"},{"7N", "164", "1"},{"18N", "164", "2"}}, new float[]{(float) 38.874762, (float) -6.964221});
        map.put("163", stop);

        stop = new StopMapModel("Ronda del Pilar  93", new String [][]{{"7", "1754", "2"},{"18", "165", "2"},{"21", "165", "1"},{"12", "165", "1"},{"7N", "165", "1"},{"18N", "165", "2"}}, new float[]{(float) 38.877147, (float) -6.965054});
        map.put("164", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  18", new String [][]{{"18", "166", "2"},{"C2", "166", "1"},{"21", "166", "1"},{"12", "166", "1"},{"7N", "166", "1"},{"18N", "166", "2"}}, new float[]{(float) 38.879177, (float) -6.961171});
        map.put("165", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  48", new String [][]{{"18", "167", "2"},{"C2", "167", "1"},{"21", "167", "1"},{"12", "167", "1"},{"7N", "167", "1"},{"18N", "167", "2"}}, new float[]{(float) 38.879113, (float) -6.958911});
        map.put("166", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  65", new String [][]{{"18", "168", "2"},{"C2", "180", "1"},{"21", "168", "1"},{"12", "168", "1"},{"7N", "168", "1"},{"18N", "168", "2"}}, new float[]{(float) 38.878919, (float) -6.956484});
        map.put("167", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  116", new String [][]{{"18", "169", "2"},{"21", "169", "1"},{"12", "169", "1"},{"7N", "169", "1"},{"18N", "169", "2"}}, new float[]{(float) 38.878524, (float) -6.953373});
        map.put("168", stop);

        stop = new StopMapModel("Av. Ricardo Carapeto Zambrano  50", new String [][]{{"18", "170", "2"},{"21", "174", "1"},{"12", "170", "1"},{"7N", "174", "1"},{"18N", "233", "2"}}, new float[]{(float) 38.878280, (float) -6.950290});
        map.put("169", stop);

        stop = new StopMapModel("C/ José Caldito Ruiz  1", new String [][]{{"18", "171", "2"},{"12", "171", "1"}}, new float[]{(float) 38.879867, (float) -6.948357});
        map.put("170", stop);

        stop = new StopMapModel("C/ José Caldito Ruiz 2", new String [][]{{"18", "231", "2"},{"12", "232", "1"}}, new float[]{(float) 38.880439, (float) -6.945323});
        map.put("171", stop);

        stop = new StopMapModel("La Pilara", new String [][]{{"18", "232", "2"},{"M3", "-1", "-1"}}, new float[]{(float) 38.880033, (float) -6.933817});
        map.put("231", stop);

        stop = new StopMapModel("IES Nuestra Señora de Botoa", new String [][]{{"18", "233", "2"},{"12", "233", "1"}}, new float[]{(float) 38.886965, (float) -6.925433});
        map.put("232", stop);

        stop = new StopMapModel("Av. de La Independencia  14", new String [][]{{"18", "-1", "-1"},{"12", "1805", "1"},{"18N", "-1", "-1"}}, new float[]{(float) 38.888310, (float) -6.903364});
        map.put("233", stop);

        stop = new StopMapModel("C/ Pedro de Valdivia  3", new String [][]{{"2", "201", "1"},{"CA", "1824", "1"}}, new float[]{(float) 38.875672, (float) -6.972396});
        map.put("200", stop);

        stop = new StopMapModel("Av. Villanueva  24", new String [][]{{"2", "224", "1"},{"21", "224", "2"},{"M1", "224", "1"},{"M3", "121", "1"},{"6N", "121", "1"}}, new float[]{(float) 38.871647, (float) -6.977598});
        map.put("213", stop);

        stop = new StopMapModel("Av. Antonio Masa Campos 55", new String [][]{{"2", "225", "1"},{"21", "225", "2"},{"M1", "225", "1"}}, new float[]{(float) 38.871594, (float) -6.979215});
        map.put("224", stop);

        stop = new StopMapModel("Av. Antonio Masa Campos  7", new String [][]{{"2", "226", "1"},{"3", "226", "1"},{"21", "226", "2"},{"M1", "226", "1"},{"7N", "226", "2"}}, new float[]{(float) 38.874501, (float) -6.981098});
        map.put("225", stop);

        stop = new StopMapModel("Av. Antonio Masa Campos 1", new String [][]{{"2", "53", "1"},{"3", "64", "1"},{"21", "53", "2"},{"M1", "46", "1"},{"7N", "64", "2"}}, new float[]{(float) 38.877052, (float) -6.980761});
        map.put("226", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona  88", new String [][]{{"2", "54", "1"},{"21", "54", "2"},{"13", "1748", "1"}}, new float[]{(float) 38.884411, (float) -6.983627});
        map.put("53", stop);

        stop = new StopMapModel("Av. Carolina Coronado", new String [][]{{"2", "57", "1"},{"C2", "57", "1"},{"21", "57", "2"}}, new float[]{(float) 38.890181, (float) -6.982144});
        map.put("56", stop);

        stop = new StopMapModel("Argüello Carvajal Teologo Siglo XVII  23", new String [][]{{"2", "58", "1"},{"C2", "58", "1"},{"21", "58", "2"}}, new float[]{(float) 38.888560, (float) -6.979591});
        map.put("57", stop);

        stop = new StopMapModel("Cardenal Cisneros  48", new String [][]{{"2", "59", "1"},{"C2", "59", "1"},{"21", "59", "2"}}, new float[]{(float) 38.887730, (float) -6.977863});
        map.put("58", stop);

        stop = new StopMapModel("Av. Padre Tacoronte", new String [][]{{"2", "60", "1"},{"C2", "62", "1"},{"21", "62", "2"}}, new float[]{(float) 38.889914, (float) -6.976103});
        map.put("59", stop);

        stop = new StopMapModel("Av. del Sol  5", new String [][]{{"2", "61", "1"}}, new float[]{(float) 38.892629, (float) -6.976660});
        map.put("60", stop);

        stop = new StopMapModel("Av. del Sol  70b", new String [][]{{"2", "63", "1"}}, new float[]{(float) 38.892594, (float) -6.979068});
        map.put("61", stop);

        stop = new StopMapModel("Av. Padre Tacoronte  24", new String [][]{{"2", "3", "1"},{"C2", "11", "1"},{"21", "8", "2"}}, new float[]{(float) 38.893024, (float) -6.981217});
        map.put("63", stop);

        stop = new StopMapModel("Complejo Campomayor", new String [][]{{"2", "200", "2"}}, new float[]{(float) 38.896753, (float) -6.984665});
        map.put("3", stop);

        stop = new StopMapModel("C/ Nevero Cuatro", new String [][]{{"2", "5", "1"}}, new float[]{(float) 38.897560, (float) -6.986517});
        map.put("4", stop);

        stop = new StopMapModel("Av. Antonio Nevado González  36", new String [][]{{"2", "6", "1"}}, new float[]{(float) 38.898650, (float) -6.988864});
        map.put("5", stop);

        stop = new StopMapModel("Av. Antonio Nevado González  15", new String [][]{{"2", "7", "1"}}, new float[]{(float) 38.900331, (float) -6.991608});
        map.put("6", stop);

        stop = new StopMapModel("Av. Antonio Nevado González", new String [][]{{"2", "8", "2"}}, new float[]{(float) 38.903345, (float) -6.996509});
        map.put("7", stop);

        stop = new StopMapModel("C/ de Viriato  6", new String [][]{{"2", "9", "2"},{"21", "-1", "-1"}}, new float[]{(float) 38.895599, (float) -6.981233});
        map.put("8", stop);

        stop = new StopMapModel("C/ Perca", new String [][]{{"2", "10", "2"},{"21", "10", "1"}}, new float[]{(float) 38.896707, (float) -6.981829});
        map.put("9", stop);

        stop = new StopMapModel("C/ Gurugú  139", new String [][]{{"2", "21", "2"},{"21", "21", "1"}}, new float[]{(float) 38.895681, (float) -6.978767});
        map.put("10", stop);

        stop = new StopMapModel("Av. Padre Tacoronte  51", new String [][]{{"2", "22", "2"},{"6", "22", "2"},{"C1", "22", "2"},{"21", "22", "1"},{"M1", "22", "1"}}, new float[]{(float) 38.892916, (float) -6.981201});
        map.put("21", stop);

        stop = new StopMapModel("Av. Padre Tacoronte  35", new String [][]{{"2", "23", "2"},{"6", "23", "2"},{"C1", "23", "2"},{"21", "23", "1"},{"M1", "23", "1"}}, new float[]{(float) 38.891783, (float) -6.979212});
        map.put("22", stop);

        stop = new StopMapModel("Av. Padre Tacoronte  5", new String [][]{{"2", "24", "2"},{"6", "24", "2"},{"C1", "24", "2"},{"21", "24", "1"},{"M1", "24", "1"}}, new float[]{(float) 38.890109, (float) -6.976438});
        map.put("23", stop);

        stop = new StopMapModel("Cardenal Cisneros  21", new String [][]{{"2", "25", "2"},{"6", "51", "2"},{"C1", "25", "2"},{"21", "25", "1"},{"M1", "25", "1"}}, new float[]{(float) 38.887659, (float) -6.978175});
        map.put("24", stop);

        stop = new StopMapModel("Argüello Carvajal Teologo Siglo XVII  28", new String [][]{{"2", "26", "2"},{"C1", "26", "2"},{"21", "26", "1"},{"M1", "29", "1"}}, new float[]{(float) 38.888690, (float) -6.979542});
        map.put("25", stop);

        stop = new StopMapModel("Av. Adolfo Díaz Ambrona  87", new String [][]{{"2", "227", "2"},{"21", "227", "1"},{"13", "237", "2"}}, new float[]{(float) 38.884517, (float) -6.983727});
        map.put("52", stop);

        stop = new StopMapModel("Av. Santa Marina  40", new String [][]{{"2", "228", "2"},{"3", "210", "2"},{"21", "228", "1"},{"7N", "210", "1"}}, new float[]{(float) 38.877236, (float) -6.980159});
        map.put("227", stop);

        stop = new StopMapModel("Av. Santa Marina  19", new String [][]{{"2", "229", "2"},{"21", "229", "1"},{"9N", "229", "2"}}, new float[]{(float) 38.874613, (float) -6.978205});
        map.put("228", stop);

        stop = new StopMapModel("Av. Santa Marina  11", new String [][]{{"2", "248", "2"},{"21", "248", "1"},{"9N", "217", "2"}}, new float[]{(float) 38.873777, (float) -6.976385});
        map.put("229", stop);

        stop = new StopMapModel("Av. de Europa  3", new String [][]{{"2", "-1", "-1"},{"3", "202", "1"},{"5", "201", "2"},{"9", "202", "1"},{"CM", "202", "2"},{"5N", "202", "1"}}, new float[]{(float) 38.874271, (float) -6.972692});
        map.put("157", stop);

        stop = new StopMapModel("C Pedro Balas Lopez", new String [][]{{"3", "145", "1"}}, new float[]{(float) 38.866948, (float) -6.968057});
        map.put("143", stop);

        stop = new StopMapModel("C/ La Violeta  8", new String [][]{{"3", "148", "1"},{"C2", "151", "2"}}, new float[]{(float) 38.865677, (float) -6.971621});
        map.put("145", stop);

        stop = new StopMapModel("Av. Damián Téllez Lafuente  9", new String [][]{{"3", "160", "1"},{"6", "242", "1"},{"9", "160", "1"},{"11", "242", "1"},{"13", "242", "1"},{"12", "160", "1"},{"M1", "160", "1"},{"CM", "160", "2"},{"6N", "242", "2"}}, new float[]{(float) 38.868194, (float) -6.973009});
        map.put("148", stop);

        stop = new StopMapModel("Av. de Fernando Calzadilla  25", new String [][]{{"3", "157", "1"},{"5", "157", "2"},{"9", "157", "1"},{"S2", "217", "2"},{"12", "156", "1"},{"M1", "213", "1"},{"CM", "157", "2"},{"5N", "157", "1"}}, new float[]{(float) 38.869767, (float) -6.972974});
        map.put("160", stop);

        stop = new StopMapModel("Av. de Cristobal Colón  10", new String [][]{{"3", "1", "1"},{"5", "1", "2"},{"7", "1", "1"},{"9", "1", "1"},{"S1", "1", "1"},{"5N", "1", "1"},{"7N", "1", "2"},{"18N", "1", "1"},{"9N", "1", "1"}}, new float[]{(float) 38.875846, (float) -6.977402});
        map.put("207", stop);

        stop = new StopMapModel("Av. de Cristobal Colón  27", new String [][]{{"3", "225", "1"},{"5", "124", "2"},{"7", "124", "1"},{"9", "124", "1"},{"S1", "124", "1"},{"5N", "124", "1"},{"7N", "225", "2"},{"18N", "124", "1"},{"9N", "124", "1"}}, new float[]{(float) 38.873696, (float) -6.980181});
        map.put("1", stop);

        stop = new StopMapModel("Av. de Cristobal Colón  11", new String [][]{{"3", "211", "2"},{"5", "211", "1"},{"7", "211", "2"},{"9", "211", "2"},{"S1", "-1", "-1"},{"5N", "211", "2"},{"7N", "211", "1"},{"18N", "211", "2"}}, new float[]{(float) 38.875427, (float) -6.977506});
        map.put("210", stop);

        stop = new StopMapModel("Av. de Fernando Calzadilla  16", new String [][]{{"3", "149", "2"},{"4", "149", "2"},{"5", "243", "1"},{"6", "149", "2"},{"9", "149", "2"},{"S2", "243", "1"},{"11", "149", "2"},{"13", "149", "2"},{"12", "149", "2"},{"M1", "149", "2"},{"CM", "149", "1"},{"5N", "243", "2"}}, new float[]{(float) 38.869780, (float) -6.973076});
        map.put("236", stop);

        stop = new StopMapModel("Av. Damián Téllez Lafuente  11", new String [][]{{"3", "150", "2"},{"4", "155", "2"},{"6", "153", "2"},{"9", "153", "2"},{"11", "-1", "-1"},{"13", "-1", "-1"},{"12", "155", "2"},{"M1", "153", "2"},{"CM", "150", "1"}}, new float[]{(float) 38.867921, (float) -6.973194});
        map.put("149", stop);

        stop = new StopMapModel("Estacion Autobuses", new String [][]{{"3", "153", "2"},{"11", "148", "1"},{"13", "148", "1"},{"CM", "153", "1"}}, new float[]{(float) 38.866723, (float) -6.974287});
        map.put("150", stop);

        stop = new StopMapModel("Av. Damián Téllez Lafuente  30", new String [][]{{"3", "141", "2"},{"6", "126", "2"},{"9", "126", "2"},{"C1", "141", "1"},{"S1", "141", "1"},{"M1", "258", "2"},{"CM", "126", "1"}}, new float[]{(float) 38.864525, (float) -6.972846});
        map.put("153", stop);

        stop = new StopMapModel("Av. Vicente Marcelo Nessi  36", new String [][]{{"3", "142", "2"},{"4", "218", "1"},{"C1", "218", "1"},{"S1", "218", "1"}}, new float[]{(float) 38.862032, (float) -6.969163});
        map.put("141", stop);

        stop = new StopMapModel("C/ Retama  18", new String [][]{{"3", "-1", "-1"}}, new float[]{(float) 38.863041, (float) -6.967279});
        map.put("142", stop);

        stop = new StopMapModel("Av. Vicente Marcelo Nessi  52", new String [][]{{"4", "219", "1"},{"C1", "219", "1"},{"S1", "219", "1"}}, new float[]{(float) 38.864003, (float) -6.962420});
        map.put("218", stop);

        stop = new StopMapModel("Av. Luis de Góngora  8", new String [][]{{"4", "220", "1"},{"C1", "220", "1"},{"S1", "220", "1"},{"5N", "220", "1"}}, new float[]{(float) 38.866498, (float) -6.961165});
        map.put("219", stop);

        stop = new StopMapModel("Av. Luis de Góngora  6", new String [][]{{"4", "190", "1"},{"C1", "189", "1"},{"S1", "1786", "1"},{"5N", "190", "1"}}, new float[]{(float) 38.867459, (float) -6.958621});
        map.put("220", stop);

        stop = new StopMapModel("Av. Antonio Hernández Gil  18", new String [][]{{"4", "191", "1"},{"5", "191", "2"},{"C2", "221", "2"},{"5N", "191", "1"}}, new float[]{(float) 38.871599, (float) -6.956232});
        map.put("190", stop);

        stop = new StopMapModel("Ctra de Sevilla", new String [][]{{"4", "193", "1"},{"5", "246", "2"},{"S2", "246", "2"},{"5N", "246", "1"}}, new float[]{(float) 38.872362, (float) -6.959694});
        map.put("191", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  53", new String [][]{{"4", "194", "1"}}, new float[]{(float) 38.874280, (float) -6.961800});
        map.put("193", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  27", new String [][]{{"4", "1752", "1"},{"M3", "1752", "2"},{"M4", "1752", "1"}}, new float[]{(float) 38.876911, (float) -6.961268});
        map.put("194", stop);

        stop = new StopMapModel("C/ Corte de Peleas  40", new String [][]{{"4", "196", "1"},{"7", "1750", "2"},{"M3", "1750", "2"},{"M4", "1750", "1"}}, new float[]{(float) 38.876681, (float) -6.958525});
        map.put("1752", stop);

        stop = new StopMapModel("C/ Isidro Pacense  11", new String [][]{{"4", "176", "1"},{"C1", "176", "2"}}, new float[]{(float) 38.877340, (float) -6.955676});
        map.put("196", stop);

        stop = new StopMapModel("Av. de Europa  10", new String [][]{{"4", "236", "2"},{"12", "236", "2"}}, new float[]{(float) 38.873826, (float) -6.972807});
        map.put("158", stop);

        stop = new StopMapModel("C/ Antonio de Chaves  2", new String [][]{{"4", "154", "2"},{"12", "258", "2"}}, new float[]{(float) 38.864727, (float) -6.973205});
        map.put("155", stop);

        stop = new StopMapModel("Plaza Hermanos Mediero Encinas  15", new String [][]{{"4", "-1", "-1"},{"12", "260", "1"},{"M1", "260", "1"},{"M3", "126", "1"}}, new float[]{(float) 38.864386, (float) -6.976960});
        map.put("154", stop);

        stop = new StopMapModel("C/ Tilo  18", new String [][]{{"5", "101", "1"},{"M1", "101", "2"},{"5N", "101", "2"}}, new float[]{(float) 38.856455, (float) -6.989933});
        map.put("100", stop);

        stop = new StopMapModel("C/ Tilo", new String [][]{{"5", "122", "1"},{"M1", "116", "1"},{"5N", "122", "2"}}, new float[]{(float) 38.857438, (float) -6.988715});
        map.put("101", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  4", new String [][]{{"5", "123", "1"},{"C2", "123", "2"},{"12", "123", "2"},{"M1", "97", "2"},{"5N", "123", "2"},{"6N", "123", "1"}}, new float[]{(float) 38.866240, (float) -6.984723});
        map.put("122", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  18", new String [][]{{"5", "113", "1"},{"C2", "113", "2"},{"12", "-1", "-1"},{"5N", "113", "2"},{"6N", "113", "1"}}, new float[]{(float) 38.868280, (float) -6.987192});
        map.put("123", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  6", new String [][]{{"5", "106", "1"},{"7", "110", "2"},{"9", "67", "1"},{"C2", "68", "2"},{"5N", "110", "2"},{"6N", "114", "1"},{"7N", "87", "1"},{"18N", "110", "2"},{"9N", "67", "1"}}, new float[]{(float) 38.870686, (float) -6.988879});
        map.put("113", stop);

        stop = new StopMapModel("C/ República Dominicana  20", new String [][]{{"5", "125", "1"}}, new float[]{(float) 38.874049, (float) -6.987635});
        map.put("106", stop);

        stop = new StopMapModel("Av. del Perú  5", new String [][]{{"5", "2", "1"},{"7", "2", "2"},{"9", "2", "2"},{"S1", "2", "2"},{"5N", "2", "2"},{"18N", "2", "2"},{"9N", "2", "2"}}, new float[]{(float) 38.873382, (float) -6.982148});
        map.put("125", stop);

        stop = new StopMapModel("Av. de Cristobal Colón  15", new String [][]{{"5", "210", "1"},{"7", "210", "2"},{"9", "210", "2"},{"S1", "210", "2"},{"5N", "210", "2"},{"18N", "210", "2"},{"9N", "228", "2"}}, new float[]{(float) 38.873579, (float) -6.980034});
        map.put("2", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  10", new String [][]{{"5", "244", "1"},{"S2", "244", "1"},{"5N", "244", "2"}}, new float[]{(float) 38.869092, (float) -6.971270});
        map.put("243", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  66", new String [][]{{"5", "245", "1"},{"S2", "245", "1"},{"5N", "245", "2"}}, new float[]{(float) 38.870429, (float) -6.967054});
        map.put("244", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  56", new String [][]{{"5", "192", "1"},{"S2", "192", "1"},{"5N", "192", "2"}}, new float[]{(float) 38.871540, (float) -6.963990});
        map.put("245", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  96", new String [][]{{"5", "189", "1"},{"S2", "191", "1"},{"5N", "221", "2"}}, new float[]{(float) 38.872358, (float) -6.959987});
        map.put("192", stop);

        stop = new StopMapModel("Av. Antonio Hernández Gil  22", new String [][]{{"5", "186", "1"},{"C1", "187", "1"}}, new float[]{(float) 38.870794, (float) -6.955589});
        map.put("189", stop);

        stop = new StopMapModel("Av. Antonio Hernández Gil  56", new String [][]{{"5", "190", "2"}}, new float[]{(float) 38.869469, (float) -6.953637});
        map.put("186", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  55", new String [][]{{"5", "240", "2"},{"S2", "240", "2"},{"5N", "240", "1"}}, new float[]{(float) 38.871683, (float) -6.963945});
        map.put("246", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  41", new String [][]{{"5", "241", "2"},{"S2", "241", "2"},{"5N", "241", "1"}}, new float[]{(float) 38.870878, (float) -6.966333});
        map.put("240", stop);

        stop = new StopMapModel("Av. Juan Sebastián Elcano  17", new String [][]{{"5", "160", "2"},{"S2", "160", "2"},{"5N", "160", "1"}}, new float[]{(float) 38.869304, (float) -6.970921});
        map.put("241", stop);

        stop = new StopMapModel("Av. del Perú  2", new String [][]{{"5", "107", "2"},{"7", "108", "1"},{"9", "111", "1"},{"S1", "111", "1"},{"5N", "108", "1"},{"18N", "108", "1"},{"9N", "111", "1"}}, new float[]{(float) 38.873448, (float) -6.982245});
        map.put("124", stop);

        stop = new StopMapModel("C/ República Dominicana  10", new String [][]{{"5", "105", "2"}}, new float[]{(float) 38.874918, (float) -6.985078});
        map.put("107", stop);

        stop = new StopMapModel("C/ República Dominicana  22", new String [][]{{"5", "114", "2"}}, new float[]{(float) 38.874080, (float) -6.987670});
        map.put("105", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  7", new String [][]{{"5", "115", "2"},{"7", "96", "1"},{"9", "102", "2"},{"C1", "115", "1"},{"5N", "115", "1"},{"6N", "115", "1"},{"7N", "-1", "-1"},{"18N", "113", "1"},{"9N", "102", "2"}}, new float[]{(float) 38.870580, (float) -6.989023});
        map.put("114", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  21", new String [][]{{"5", "97", "2"},{"C1", "116", "1"},{"12", "116", "1"},{"5N", "97", "1"},{"6N", "116", "1"}}, new float[]{(float) 38.868160, (float) -6.987424});
        map.put("115", stop);

        stop = new StopMapModel("C/ Godofredo Ortega Y Muñoz  31", new String [][]{{"5", "98", "2"},{"M1", "98", "2"},{"5N", "98", "1"}}, new float[]{(float) 38.865521, (float) -6.987978});
        map.put("97", stop);

        stop = new StopMapModel("C/ Arturo Barea  7", new String [][]{{"5", "99", "2"},{"M1", "99", "2"},{"5N", "99", "1"}}, new float[]{(float) 38.863119, (float) -6.986146});
        map.put("98", stop);

        stop = new StopMapModel("C/ Arce  4", new String [][]{{"5", "-1", "-1"},{"M1", "-1", "-1"},{"5N", "100", "1"}}, new float[]{(float) 38.858398, (float) -6.989387});
        map.put("99", stop);

        stop = new StopMapModel("C/ Parque de Las Cañadas  51", new String [][]{{"6", "1818", "1"}}, new float[]{(float) 38.841759, (float) -6.981618});
        map.put("1817", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  32", new String [][]{{"6", "1819", "1"}}, new float[]{(float) 38.844550, (float) -6.977499});
        map.put("1818", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  FC", new String [][]{{"6", "1820", "1"}}, new float[]{(float) 38.844604, (float) -6.974156});
        map.put("1819", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  SP", new String [][]{{"6", "131", "1"}}, new float[]{(float) 38.844897, (float) -6.970069});
        map.put("1820", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  40", new String [][]{{"6", "138", "1"},{"9", "138", "1"},{"M3", "138", "2"},{"6N", "138", "2"}}, new float[]{(float) 38.845333, (float) -6.968445});
        map.put("131", stop);

        stop = new StopMapModel("Ctra Valverde 1", new String [][]{{"6", "139", "1"},{"9", "139", "1"},{"M3", "139", "2"},{"CM", "139", "2"},{"6N", "139", "2"}}, new float[]{(float) 38.849509, (float) -6.966909});
        map.put("138", stop);

        stop = new StopMapModel("Ctra Valverde 2", new String [][]{{"6", "140", "1"},{"9", "140", "1"},{"M3", "140", "2"},{"CM", "140", "2"},{"6N", "140", "2"}}, new float[]{(float) 38.854116, (float) -6.967875});
        map.put("139", stop);

        stop = new StopMapModel("Ctra Valverde 3", new String [][]{{"6", "147", "1"},{"9", "147", "1"},{"M3", "258", "2"},{"CM", "147", "2"},{"6N", "147", "2"}}, new float[]{(float) 38.859809, (float) -6.971038});
        map.put("140", stop);

        stop = new StopMapModel("Av. Damián Téllez Lafuente  27", new String [][]{{"6", "148", "1"},{"9", "148", "1"},{"S1", "151", "2"},{"12", "148", "1"},{"M1", "148", "1"},{"CM", "148", "2"},{"6N", "148", "2"}}, new float[]{(float) 38.864882, (float) -6.972590});
        map.put("147", stop);

        stop = new StopMapModel("Av. Juan Pereda Pila  29", new String [][]{{"6", "216", "1"},{"11", "216", "1"},{"13", "216", "1"},{"6N", "216", "2"}}, new float[]{(float) 38.869322, (float) -6.974298});
        map.put("242", stop);

        stop = new StopMapModel("Av. Villanueva  9", new String [][]{{"6", "217", "1"},{"11", "217", "1"},{"13", "217", "1"},{"M1", "236", "2"},{"M3", "248", "2"},{"6N", "217", "2"}}, new float[]{(float) 38.871045, (float) -6.978046});
        map.put("216", stop);

        stop = new StopMapModel("Av. de Huelva  5", new String [][]{{"6", "202", "1"},{"S2", "202", "2"},{"11", "202", "1"},{"13", "202", "1"},{"6N", "202", "2"},{"9N", "-1", "-1"}}, new float[]{(float) 38.873487, (float) -6.974836});
        map.put("217", stop);

        stop = new StopMapModel("Av. de Adolfo Suárez", new String [][]{{"6", "14", "1"},{"C1", "20", "2"},{"11", "14", "1"},{"13", "14", "1"}}, new float[]{(float) 38.885997, (float) -6.974531});
        map.put("1748", stop);

        stop = new StopMapModel("C/ Nuestra Señora de Bótoa 1A", new String [][]{{"6", "20", "1"},{"11", "15", "1"},{"13", "15", "1"}}, new float[]{(float) 38.895065, (float) -6.969890});
        map.put("14", stop);

        stop = new StopMapModel("Camino Sta. Engracia  8", new String [][]{{"6", "12", "1"},{"C1", "12", "2"}}, new float[]{(float) 38.891495, (float) -6.972089});
        map.put("20", stop);

        stop = new StopMapModel("C/ Gurugú  257", new String [][]{{"6", "1760", "1"},{"C1", "1760", "2"}}, new float[]{(float) 38.895912, (float) -6.974790});
        map.put("12", stop);

        stop = new StopMapModel("C/ Gurugú  145", new String [][]{{"6", "21", "2"},{"C1", "21", "2"}}, new float[]{(float) 38.895751, (float) -6.978427});
        map.put("1760", stop);

        stop = new StopMapModel("Ctra Valverde 1A", new String [][]{{"6", "127", "2"},{"9", "127", "2"},{"M3", "127", "1"},{"CM", "127", "1"},{"6N", "127", "1"}}, new float[]{(float) 38.858658, (float) -6.970422});
        map.put("126", stop);

        stop = new StopMapModel("Ctra Valverde 2A", new String [][]{{"6", "128", "2"},{"9", "128", "2"},{"M3", "128", "1"},{"CM", "128", "1"},{"6N", "128", "1"}}, new float[]{(float) 38.852789, (float) -6.967755});
        map.put("127", stop);

        stop = new StopMapModel("Ctra Valverde 3A", new String [][]{{"6", "1747", "2"},{"9", "132", "2"},{"M3", "1747", "1"},{"CM", "1790", "1"},{"6N", "131", "1"}}, new float[]{(float) 38.848891, (float) -6.967081});
        map.put("128", stop);

        stop = new StopMapModel("C/ Pantano de Puerto Peña", new String [][]{{"6", "129", "2"},{"M3", "129", "2"}}, new float[]{(float) 38.839657, (float) -6.963916});
        map.put("1747", stop);

        stop = new StopMapModel("C/ Pantano de Puerto Peña  12", new String [][]{{"6", "1815", "2"},{"M3", "131", "2"}}, new float[]{(float) 38.839285, (float) -6.967266});
        map.put("129", stop);

        stop = new StopMapModel("C/ Pantano de Puerto Peña  33", new String [][]{{"6", "1816", "2"}}, new float[]{(float) 38.839691, (float) -6.973572});
        map.put("1815", stop);

        stop = new StopMapModel("C/ Pantano de Puerto Peña  63", new String [][]{{"6", "-1", "-1"}}, new float[]{(float) 38.839904, (float) -6.979784});
        map.put("1816", stop);

        stop = new StopMapModel("Corte de Peleas 177", new String [][]{{"7", "184", "1"},{"M4", "184", "2"}}, new float[]{(float) 38.873118, (float) -6.947967});
        map.put("183", stop);

        stop = new StopMapModel("Corte de Peleas 155", new String [][]{{"7", "185", "1"},{"C1", "185", "2"},{"M4", "185", "2"}}, new float[]{(float) 38.873807, (float) -6.950019});
        map.put("184", stop);

        stop = new StopMapModel("Corte de Peleas  136-138", new String [][]{{"7", "1749", "1"},{"C1", "1749", "2"},{"M4", "1749", "2"}}, new float[]{(float) 38.874873, (float) -6.953143});
        map.put("185", stop);

        stop = new StopMapModel("C/ Corte de Peleas  84", new String [][]{{"7", "1751", "1"},{"C1", "196", "2"},{"M3", "1751", "1"},{"M4", "1751", "2"}}, new float[]{(float) 38.875801, (float) -6.955857});
        map.put("1749", stop);

        stop = new StopMapModel("C/ Corte de Peleas  41", new String [][]{{"7", "1753", "1"},{"M3", "1791", "1"},{"M4", "1791", "2"}}, new float[]{(float) 38.876773, (float) -6.958600});
        map.put("1751", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  7", new String [][]{{"7", "197", "1"}}, new float[]{(float) 38.878285, (float) -6.961863});
        map.put("1753", stop);

        stop = new StopMapModel("Av. del Perú  21", new String [][]{{"7", "114", "1"},{"5N", "114", "1"},{"18N", "114", "1"}}, new float[]{(float) 38.873059, (float) -6.986541});
        map.put("108", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda", new String [][]{{"7", "95", "2"}}, new float[]{(float) 38.868596, (float) -6.989495});
        map.put("96", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda  34", new String [][]{{"7", "93", "1"}}, new float[]{(float) 38.867238, (float) -6.992159});
        map.put("1755", stop);

        stop = new StopMapModel("C/ Segovia", new String [][]{{"7", "1756", "1"}}, new float[]{(float) 38.865991, (float) -6.996250});
        map.put("93", stop);

        stop = new StopMapModel("C/José Miguel Sánchez Hueso  57", new String [][]{{"7", "91", "1"}}, new float[]{(float) 38.863044, (float) -7.003967});
        map.put("1756", stop);

        stop = new StopMapModel("C/José Miguel Sánchez Hueso", new String [][]{{"7", "183", "2"}}, new float[]{(float) 38.862353, (float) -7.004454});
        map.put("91", stop);

        stop = new StopMapModel("C/ Segovia", new String [][]{{"7", "1756", "1"}}, new float[]{(float) 38.865792, (float) -6.996185});
        map.put("92", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda", new String [][]{{"7", "95", "2"}}, new float[]{(float) 38.867190, (float) -6.991967});
        map.put("94", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda  147", new String [][]{{"7", "113", "2"}}, new float[]{(float) 38.868576, (float) -6.989232});
        map.put("95", stop);

        stop = new StopMapModel("Av. del Perú  24", new String [][]{{"7", "125", "2"},{"5N", "125", "2"},{"18N", "125", "2"}}, new float[]{(float) 38.873026, (float) -6.986568});
        map.put("110", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  8", new String [][]{{"7", "1752", "2"}}, new float[]{(float) 38.878243, (float) -6.961936});
        map.put("1754", stop);

        stop = new StopMapModel("C/ Corte de Peleas  88", new String [][]{{"7", "181", "2"},{"C2", "181", "1"},{"M3", "231", "2"},{"M4", "181", "1"}}, new float[]{(float) 38.875641, (float) -6.955488});
        map.put("1750", stop);

        stop = new StopMapModel("C/ Corte de Peleas  120", new String [][]{{"7", "182", "2"},{"C2", "182", "1"},{"M4", "182", "1"}}, new float[]{(float) 38.874899, (float) -6.953365});
        map.put("181", stop);

        stop = new StopMapModel("C/ Corte de Peleas  158", new String [][]{{"7", "-1", "-1"},{"C2", "188", "1"},{"M4", "1808", "1"}}, new float[]{(float) 38.873803, (float) -6.950100});
        map.put("182", stop);

        stop = new StopMapModel("C/ Pantano de La Serena  25", new String [][]{{"9", "137", "1"}}, new float[]{(float) 38.849352, (float) -6.975718});
        map.put("136", stop);

        stop = new StopMapModel("C/ Pantano de La Serena  46", new String [][]{{"9", "131", "1"}}, new float[]{(float) 38.848309, (float) -6.970778});
        map.put("137", stop);

        stop = new StopMapModel("Av. Tomás Romero de Castilla  7", new String [][]{{"9", "112", "1"},{"S1", "118", "1"},{"9N", "112", "1"}}, new float[]{(float) 38.872081, (float) -6.983534});
        map.put("111", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda  10", new String [][]{{"9", "113", "1"},{"9N", "113", "1"}}, new float[]{(float) 38.870442, (float) -6.985162});
        map.put("112", stop);

        stop = new StopMapModel("Av. Jose María Alcaraz Y Alenda  11", new String [][]{{"9", "103", "2"},{"9N", "103", "2"}}, new float[]{(float) 38.870274, (float) -6.985196});
        map.put("102", stop);

        stop = new StopMapModel("Av. Tomás Romero de Castilla  12", new String [][]{{"9", "125", "2"},{"S1", "125", "2"},{"9N", "125", "2"}}, new float[]{(float) 38.871912, (float) -6.983318});
        map.put("103", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  29", new String [][]{{"9", "133", "2"}}, new float[]{(float) 38.845030, (float) -6.969788});
        map.put("132", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  30", new String [][]{{"9", "134", "2"}}, new float[]{(float) 38.844660, (float) -6.974860});
        map.put("133", stop);

        stop = new StopMapModel("Av. de Las Vaguadas  31", new String [][]{{"9", "135", "2"}}, new float[]{(float) 38.844604, (float) -6.977765});
        map.put("134", stop);

        stop = new StopMapModel("C/ Parque de Las Cañadas", new String [][]{{"9", "-1", "-1"}}, new float[]{(float) 38.849204, (float) -6.978441});
        map.put("135", stop);

        stop = new StopMapModel("Av. de Elvas  8", new String [][]{{"", "-1", "-1"}}, new float[]{(float) 38.882039, (float) -7.007443});
        map.put("1789", stop);

        stop = new StopMapModel("Av. del Parque Científico Y Tecnológico", new String [][]{{"C1", "-1", "-1"}}, new float[]{(float) 38.887858, (float) -6.999824});
        map.put("34", stop);

        stop = new StopMapModel("Av. Javier Blanco Palenciano", new String [][]{{"C2", "1758", "1"}}, new float[]{(float) 38.886487, (float) -7.000291});
        map.put("70", stop);

        stop = new StopMapModel("Cementerio Nuevo", new String [][]{{"CM", "138", "2"}}, new float[]{(float) 38.784264, (float) -6.994085});
        map.put("1790", stop);

        stop = new StopMapModel("C/ Nuestra Señora de Bótoa 2", new String [][]{{"11", "1768", "1"},{"13", "1768", "1"}}, new float[]{(float) 38.900002, (float) -6.968367});
        map.put("15", stop);

        stop = new StopMapModel("Ctra. Cáceres", new String [][]{{"11", "1764", "1"},{"13", "1761", "1"}}, new float[]{(float) 38.917207, (float) -6.959592});
        map.put("1768", stop);

        stop = new StopMapModel("C/ Afrodita 1 (Gevora)", new String [][]{{"11", "1763", "1"},{"13", "1763", "2"}}, new float[]{(float) 38.922529, (float) -6.939793});
        map.put("1764", stop);

        stop = new StopMapModel("C/ Ns Guadalupe  7  (Gevora)", new String [][]{{"11", "1777", "1"},{"13", "1767", "2"}}, new float[]{(float) 38.919616, (float) -6.943359});
        map.put("1763", stop);

        stop = new StopMapModel("Ctra. EX-209  (Frente Mercoguadiana)", new String [][]{{"11", "1771", "1"}}, new float[]{(float) 38.916773, (float) -6.926950});
        map.put("1777", stop);

        stop = new StopMapModel("Sagrajas (IDA)", new String [][]{{"11", "1778", "1"}}, new float[]{(float) 38.921575, (float) -6.902159});
        map.put("1771", stop);

        stop = new StopMapModel("Finca Sagrajas  14", new String [][]{{"11", "1770", "1"}}, new float[]{(float) 38.927082, (float) -6.881497});
        map.put("1778", stop);

        stop = new StopMapModel("Plaza Mayor (Novelda) IDA", new String [][]{{"11", "1769", "1"}}, new float[]{(float) 38.938406, (float) -6.833185});
        map.put("1770", stop);

        stop = new StopMapModel("C/ Canal  2   (Alcazaba)", new String [][]{{"11", "1780", "1"}}, new float[]{(float) 38.952484, (float) -6.740917});
        map.put("1769", stop);

        stop = new StopMapModel("Plaza Mayor  5 (Novelda)", new String [][]{{"11", "1779", "2"}}, new float[]{(float) 38.938499, (float) -6.833196});
        map.put("1780", stop);

        stop = new StopMapModel("Sagrajas  (Vuelta)", new String [][]{{"11", "1776", "2"}}, new float[]{(float) 38.921636, (float) -6.902145});
        map.put("1779", stop);

        stop = new StopMapModel("Ctra. EX-209 (Mercoguadiana)", new String [][]{{"11", "1761", "2"}}, new float[]{(float) 38.916782, (float) -6.928275});
        map.put("1776", stop);

        stop = new StopMapModel("Ns. Guadalupe (Gevora)", new String [][]{{"11", "1762", "2"},{"13", "1762", "1"}}, new float[]{(float) 38.919357, (float) -6.943869});
        map.put("1761", stop);

        stop = new StopMapModel("C/ Afrodita 2 (Gevora)", new String [][]{{"11", "1767", "2"},{"13", "1765", "1"}}, new float[]{(float) 38.922546, (float) -6.939653});
        map.put("1762", stop);

        stop = new StopMapModel("Ctra. Cáceres", new String [][]{{"11", "16", "2"},{"13", "16", "2"}}, new float[]{(float) 38.917504, (float) -6.959450});
        map.put("1767", stop);

        stop = new StopMapModel("C/ Nuestra Señora de Bótoa 1", new String [][]{{"11", "17", "2"},{"13", "17", "2"}}, new float[]{(float) 38.900022, (float) -6.968606});
        map.put("16", stop);

        stop = new StopMapModel("C/ Nuestra Señora de Bótoa 2A", new String [][]{{"11", "18", "2"},{"13", "18", "2"}}, new float[]{(float) 38.895843, (float) -6.970044});
        map.put("17", stop);

        stop = new StopMapModel("Av. Sinforiano Madroñero  5", new String [][]{{"C1", "117", "1"},{"12", "254", "1"},{"M1", "254", "1"},{"6N", "126", "1"}}, new float[]{(float) 38.865675, (float) -6.984004});
        map.put("116", stop);

        stop = new StopMapModel("Paseo Condes de Barcelona  4", new String [][]{{"12", "255", "1"},{"M1", "255", "1"},{"M3", "255", "1"}}, new float[]{(float) 38.866603, (float) -6.980717});
        map.put("254", stop);

        stop = new StopMapModel("Paseo Condes de Barcelona  16", new String [][]{{"12", "154", "1"},{"M1", "154", "1"},{"M3", "154", "1"}}, new float[]{(float) 38.865609, (float) -6.978872});
        map.put("255", stop);

        stop = new StopMapModel("Av. Damián Téllez Lafuente  48", new String [][]{{"12", "147", "1"},{"M1", "147", "1"}}, new float[]{(float) 38.862981, (float) -6.972295});
        map.put("260", stop);

        stop = new StopMapModel("Av. de La Independencia  24", new String [][]{{"12", "1806", "1"}}, new float[]{(float) 38.888682, (float) -6.900197});
        map.put("1805", stop);

        stop = new StopMapModel("Av. de La Independencia", new String [][]{{"12", "1772", "1"}}, new float[]{(float) 38.889275, (float) -6.896818});
        map.put("1806", stop);

        stop = new StopMapModel("Urb. Golf Guadiana  (IDA)", new String [][]{{"12", "1773", "1"}}, new float[]{(float) 38.887768, (float) -6.882909});
        map.put("1772", stop);

        stop = new StopMapModel("C/ K (Villafranco del Guadiana) IDA", new String [][]{{"12", "1774", "1"}}, new float[]{(float) 38.885124, (float) -6.861117});
        map.put("1773", stop);

        stop = new StopMapModel("Plaza Conquistadores (Balboa) IDA", new String [][]{{"12", "1782", "1"}}, new float[]{(float) 38.905194, (float) -6.816888});
        map.put("1774", stop);

        stop = new StopMapModel("C/ K (Villafranco del Guadiana) Vuelta", new String [][]{{"12", "1781", "2"}}, new float[]{(float) 38.885133, (float) -6.861042});
        map.put("1782", stop);

        stop = new StopMapModel("Urbanizacion Golf Guadiana  1A", new String [][]{{"12", "1807", "2"}}, new float[]{(float) 38.887713, (float) -6.882768});
        map.put("1781", stop);

        stop = new StopMapModel("Av. de La Independencia  2Q", new String [][]{{"12", "230", "2"}}, new float[]{(float) 38.889212, (float) -6.897027});
        map.put("1807", stop);

        stop = new StopMapModel("Paseo Condes de Barcelona  19", new String [][]{{"12", "257", "2"},{"M1", "257", "2"},{"M3", "257", "2"}}, new float[]{(float) 38.865433, (float) -6.977552});
        map.put("258", stop);

        stop = new StopMapModel("Paseo Condes de Barcelona  9", new String [][]{{"12", "256", "2"},{"M1", "256", "2"},{"M3", "117", "2"}}, new float[]{(float) 38.866586, (float) -6.979709});
        map.put("257", stop);

        stop = new StopMapModel("C/ Jesús Rincón Jiménez  82", new String [][]{{"12", "122", "2"},{"M1", "122", "2"}}, new float[]{(float) 38.865659, (float) -6.980760});
        map.put("256", stop);

        stop = new StopMapModel("C/ Sra de Guadalupe (Valdebotoa)", new String [][]{{"13", "1766", "1"}}, new float[]{(float) 38.964439, (float) -6.924101});
        map.put("1765", stop);

        stop = new StopMapModel("C/ de La Guarda (Valdebotoa)", new String [][]{{"13", "1764", "2"}}, new float[]{(float) 38.967728, (float) -6.924624});
        map.put("1766", stop);

        stop = new StopMapModel("Av. Santa Marina  28", new String [][]{{"13", "205", "2"}}, new float[]{(float) 38.878295, (float) -6.979961});
        map.put("237", stop);

        stop = new StopMapModel("C/ Vicente Delgado Algaba  100", new String [][]{{"C1", "153", "1"},{"S1", "153", "1"}}, new float[]{(float) 38.865942, (float) -6.975085});
        map.put("118", stop);

        stop = new StopMapModel("Urb. Los Montitos 1", new String [][]{{"S1", "1787", "1"}}, new float[]{(float) 38.863545, (float) -6.944733});
        map.put("1786", stop);

        stop = new StopMapModel("Urb. Los Montitos 2", new String [][]{{"S1", "1788", "2"}}, new float[]{(float) 38.861676, (float) -6.940257});
        map.put("1787", stop);

        stop = new StopMapModel("Urb. Los Montitos 3", new String [][]{{"S1", "221", "2"}}, new float[]{(float) 38.863610, (float) -6.944505});
        map.put("1788", stop);

        stop = new StopMapModel("Av. Luis de Góngora 1", new String [][]{{"C2", "222", "2"},{"S1", "222", "2"},{"5N", "222", "2"}}, new float[]{(float) 38.867624, (float) -6.958747});
        map.put("221", stop);

        stop = new StopMapModel("Av. Luis de Góngora 2", new String [][]{{"C2", "223", "2"},{"S1", "223", "2"},{"5N", "-1", "-1"}}, new float[]{(float) 38.866651, (float) -6.961248});
        map.put("222", stop);

        stop = new StopMapModel("Av. Salvador Allende", new String [][]{{"C2", "146", "2"},{"S1", "146", "2"}}, new float[]{(float) 38.863325, (float) -6.962646});
        map.put("223", stop);

        stop = new StopMapModel("Av. Vicente Marcelo Nessi  35", new String [][]{{"C2", "144", "2"},{"S1", "147", "2"}}, new float[]{(float) 38.862183, (float) -6.969127});
        map.put("146", stop);

        stop = new StopMapModel("C/ Jacobo Rodríguez Pereira  38", new String [][]{{"C2", "121", "2"},{"S1", "103", "2"}}, new float[]{(float) 38.867617, (float) -6.974992});
        map.put("151", stop);

        stop = new StopMapModel("Av. Padre Tacoronte 14", new String [][]{{"C2", "63", "1"},{"21", "63", "2"}}, new float[]{(float) 38.891676, (float) -6.978783});
        map.put("62", stop);

        stop = new StopMapModel("Av. María Auxiliadora  51", new String [][]{{"C2", "122", "2"},{"M3", "254", "1"},{"6N", "122", "1"}}, new float[]{(float) 38.867453, (float) -6.981127});
        map.put("121", stop);

        stop = new StopMapModel("Av. Elvas  51", new String [][]{{"9N", "80", "2"}}, new float[]{(float) 38.883179, (float) -7.025720});
        map.put("77", stop);

        stop = new StopMapModel("Parque Castelar", new String [][]{{"CA", "1822", "1"}}, new float[]{(float) 38.878552, (float) -6.976651});
        map.put("1821", stop);

        stop = new StopMapModel("Plaza Santo Domingo", new String [][]{{"CA", "1823", "1"}}, new float[]{(float) 38.877111, (float) -6.975535});
        map.put("1822", stop);

        stop = new StopMapModel("Alonso de Celada", new String [][]{{"CA", "200", "1"}}, new float[]{(float) 38.876042, (float) -6.973711});
        map.put("1823", stop);

        stop = new StopMapModel("Plaza Minayo", new String [][]{{"CA", "1825", "1"}}, new float[]{(float) 38.876480, (float) -6.971855});
        map.put("1824", stop);

        stop = new StopMapModel("Plaza España", new String [][]{{"CA", "1826", "1"}}, new float[]{(float) 38.878017, (float) -6.970047});
        map.put("1825", stop);

        stop = new StopMapModel("Plaza Cervantes", new String [][]{{"CA", "1827", "1"}}, new float[]{(float) 38.877800, (float) -6.967108});
        map.put("1826", stop);

        stop = new StopMapModel("Pza. 18 de Diciembre", new String [][]{{"CA", "197", "1"}}, new float[]{(float) 38.877700, (float) -6.964511});
        map.put("1827", stop);

        stop = new StopMapModel("C Zurbarán", new String [][]{{"CA", "235", "1"}}, new float[]{(float) 38.876067, (float) -6.970589});
        map.put("1828", stop);

        stop = new StopMapModel("Plaza España  11", new String [][]{{"CA", "1830", "2"}}, new float[]{(float) 38.878143, (float) -6.970556});
        map.put("235", stop);

        stop = new StopMapModel("Pedro de Valdivia 1", new String [][]{{"CA", "201", "2"}}, new float[]{(float) 38.875624, (float) -6.972494});
        map.put("1830", stop);

        stop = new StopMapModel("Puerta Palmas", new String [][]{{"CA", "1832", "2"}}, new float[]{(float) 38.880189, (float) -6.975036});
        map.put("1831", stop);

        stop = new StopMapModel("Joaquin Costa", new String [][]{{"CA", "1833", "2"}}, new float[]{(float) 38.881638, (float) -6.973974});
        map.put("1832", stop);

        stop = new StopMapModel("Plz San Jose", new String [][]{{"CA", "1834", "2"}}, new float[]{(float) 38.881821, (float) -6.969044});
        map.put("1833", stop);

        stop = new StopMapModel("San Pedro De Alcantara", new String [][]{{"CA", "1835", "2"}}, new float[]{(float) 38.881182, (float) -6.970010});
        map.put("1834", stop);

        stop = new StopMapModel("Plaza La Soledad", new String [][]{{"CA", "1836", "2"}}, new float[]{(float) 38.880731, (float) -6.970707});
        map.put("1835", stop);

        stop = new StopMapModel("Jose Lanot", new String [][]{{"CA", "1837", "2"}}, new float[]{(float) 38.881212, (float) -6.971598});
        map.put("1836", stop);

        stop = new StopMapModel("Joaquín Costa 1", new String [][]{{"CA", "204", "2"}}, new float[]{(float) 38.882089, (float) -6.973738});
        map.put("1837", stop);

        stop = new StopMapModel("Av. de La Medicina", new String [][]{{"C1", "73", "1"}}, new float[]{(float) 38.885105, (float) -6.999768});
        map.put("72", stop);

        stop = new StopMapModel("Av. de La Medicina 3", new String [][]{{"C1", "114", "1"}}, new float[]{(float) 38.882870, (float) -7.000431});
        map.put("73", stop);

        stop = new StopMapModel("Av. María Auxiliadora  45", new String [][]{{"C1", "118", "1"},{"M3", "216", "2"}}, new float[]{(float) 38.867911, (float) -6.980555});
        map.put("117", stop);

        stop = new StopMapModel("Luis Andreu Fernandez de Molina", new String [][]{{"C1", "184", "2"}}, new float[]{(float) 38.870585, (float) -6.953518});
        map.put("187", stop);

        stop = new StopMapModel("C/ Alfonso XIII  70", new String [][]{{"C1", "239", "2"}}, new float[]{(float) 38.881905, (float) -6.961479});
        map.put("179", stop);

        stop = new StopMapModel("C/ Suárez de Figueroa  18", new String [][]{{"C1", "1748", "2"}}, new float[]{(float) 38.883830, (float) -6.971522});
        map.put("239", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  24", new String [][]{{"C1", "30", "2"},{"M1", "21", "1"}}, new float[]{(float) 38.888123, (float) -6.985626});
        map.put("43", stop);

        stop = new StopMapModel("Pedro de Alvarado", new String [][]{{"C1", "31", "2"}}, new float[]{(float) 38.888345, (float) -6.987781});
        map.put("30", stop);

        stop = new StopMapModel("Av. Miguel de Fabra  19", new String [][]{{"C1", "32", "2"}}, new float[]{(float) 38.889904, (float) -6.990339});
        map.put("31", stop);

        stop = new StopMapModel("Av. Castillo Puebla de Alcocer  6", new String [][]{{"C1", "33", "2"}}, new float[]{(float) 38.888281, (float) -6.992568});
        map.put("32", stop);

        stop = new StopMapModel("Av. Castillo Puebla de Alcocer  63", new String [][]{{"C1", "1757", "2"}}, new float[]{(float) 38.887953, (float) -6.996366});
        map.put("33", stop);

        stop = new StopMapModel("Av. Castillo Puebla de Alcocer  18", new String [][]{{"C1", "34", "2"}}, new float[]{(float) 38.887964, (float) -6.998109});
        map.put("1757", stop);

        stop = new StopMapModel("Av. Innovación", new String [][]{{"C2", "38", "1"}}, new float[]{(float) 38.887801, (float) -6.999219});
        map.put("1758", stop);

        stop = new StopMapModel("Av. Castillo Puebla de Alcocer  24", new String [][]{{"C2", "39", "1"}}, new float[]{(float) 38.887857, (float) -6.996936});
        map.put("38", stop);

        stop = new StopMapModel("Av. Castillo Puebla de Alcocer  2", new String [][]{{"C2", "40", "1"}}, new float[]{(float) 38.888215, (float) -6.992417});
        map.put("39", stop);

        stop = new StopMapModel("Av. Miguel de Fabra  14", new String [][]{{"C2", "42", "1"}}, new float[]{(float) 38.889806, (float) -6.990521});
        map.put("40", stop);

        stop = new StopMapModel("Pedro de Alvarado", new String [][]{{"C2", "29", "1"}}, new float[]{(float) 38.888593, (float) -6.987128});
        map.put("42", stop);

        stop = new StopMapModel("Av. Augusto Vázquez  9", new String [][]{{"C2", "48", "1"},{"M1", "44", "2"}}, new float[]{(float) 38.887830, (float) -6.985454});
        map.put("29", stop);

        stop = new StopMapModel("C/ Gurugú  126", new String [][]{{"C2", "1759", "1"}}, new float[]{(float) 38.895641, (float) -6.978382});
        map.put("11", stop);

        stop = new StopMapModel("C/ Gurugú", new String [][]{{"C2", "13", "1"}}, new float[]{(float) 38.895897, (float) -6.975015});
        map.put("1759", stop);

        stop = new StopMapModel("Camino Sta. Engracia  7", new String [][]{{"C2", "18", "1"}}, new float[]{(float) 38.891371, (float) -6.972176});
        map.put("13", stop);

        stop = new StopMapModel("Ronda Cincunvalación Reina Sofia", new String [][]{{"C2", "165", "1"}}, new float[]{(float) 38.883717, (float) -6.971595});
        map.put("238", stop);

        stop = new StopMapModel("C/ Isidro Pacense  20", new String [][]{{"C2", "1750", "1"}}, new float[]{(float) 38.877403, (float) -6.955826});
        map.put("180", stop);

        stop = new StopMapModel("C/ Luis Andreu Fernandez de Molina", new String [][]{{"C2", "190", "2"}}, new float[]{(float) 38.870656, (float) -6.953588});
        map.put("188", stop);

        stop = new StopMapModel("C/ Campanilla  17", new String [][]{{"C2", "145", "2"}}, new float[]{(float) 38.864104, (float) -6.968576});
        map.put("144", stop);

        stop = new StopMapModel("Av. de La Medicina 1", new String [][]{{"C2", "69", "2"}}, new float[]{(float) 38.883143, (float) -7.000136});
        map.put("68", stop);

        stop = new StopMapModel("Av. de La Medicina 2", new String [][]{{"C2", "-1", "-1"}}, new float[]{(float) 38.885169, (float) -6.999755});
        map.put("69", stop);

        stop = new StopMapModel("C/ Santo Cristo de La Paz  14", new String [][]{{"M3", "197", "1"},{"M4", "197", "2"}}, new float[]{(float) 38.877019, (float) -6.961319});
        map.put("1791", stop);

        stop = new StopMapModel("C/ Corte de Peleas 1", new String [][]{{"M4", "1809", "1"}}, new float[]{(float) 38.872993, (float) -6.947780});
        map.put("1808", stop);

        stop = new StopMapModel("C/ Corte de Peleas 2", new String [][]{{"M4", "1811", "1"}}, new float[]{(float) 38.871045, (float) -6.942081});
        map.put("1809", stop);

        stop = new StopMapModel("C/ Corte de Peleas 3", new String [][]{{"M4", "1813", "1"}}, new float[]{(float) 38.868173, (float) -6.933729});
        map.put("1811", stop);

        stop = new StopMapModel("C/ Corte de Peleas 4", new String [][]{{"M4", "1814", "1"}}, new float[]{(float) 38.823703, (float) -6.830353});
        map.put("1813", stop);

        stop = new StopMapModel("C/ Corte de Peleas 5", new String [][]{{"M4", "1812", "2"}}, new float[]{(float) 38.819728, (float) -6.820648});
        map.put("1814", stop);

        stop = new StopMapModel("C/ Corte de Peleas 6", new String [][]{{"M4", "1810", "2"}}, new float[]{(float) 38.868340, (float) -6.933854});
        map.put("1812", stop);

        stop = new StopMapModel("C/ Corte de Peleas 7", new String [][]{{"M4", "183", "2"}}, new float[]{(float) 38.87236, (float) -6.941916});
        map.put("1810", stop);
        
    }
}
