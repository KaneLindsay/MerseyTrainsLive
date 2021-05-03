package com.example.merseytrainslive.livedata;

import org.json.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.util.Vector;
import org.apache.commons.io.FileUtils;

public class ScheduleManager {
    public Vector<Station> stations;

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("G:/MerseyRail API testing/merseyRailFullDaily.gz");
        Path target = Paths.get("G:/MerseyRail API testing/merseyRailFullDaily");

        URL url = new URL("https://datafeeds.networkrail.co.uk/ntrod/CifFileAuthenticate?type=CIF_HE_TOC_FULL_DAILY&day=toc-full");
        //downloadFile(url, "G:/MerseyRail API testing/merseyRailFullDaily.gz");
        if (Files.notExists(source)) {
            System.err.printf("The path %s doesn't exist!", source);
            return;
        }

        try {
            decompressGzip(source,target);
        } catch (IOException e) {
            e.printStackTrace();
        }

        readJSONFile(target);
    }

    public void initializeStations() {
        //stations.add(new com.example.merseytrainslive.livedata.Station("name", "stanox", platforms, "tiploc"));
    }

    public static void readJSONFile(Path fileLocation) {
        try (Scanner myReader = new Scanner(fileLocation.toFile())) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                JSONObject o = new JSONObject(line);
                //System.out.println(o);
                try {
                    JSONObject schedule = o.getJSONObject("JsonScheduleV1");

                    JSONObject scheduleSegment = schedule.getJSONObject("schedule_segment");
                    JSONArray a = new JSONArray(scheduleSegment.getJSONArray("schedule_location"));

                    Vector<String[]> locationVector = new Vector<>();
                    for (int i = 0; i < a.length(); i++) {

                        JSONObject scheduleLocation = a.getJSONObject(i);
                        String[] locationInfo = new String[4];

                        String recordIdentity = scheduleLocation.getString("location_type");
                        locationInfo[0] = schedule.getString("CIF_train_uid");
                        //System.out.println("train_uid");
                        if (recordIdentity.equals("LO")) {
                            locationInfo[1] = scheduleLocation.getString("tiploc_code");
                            //locationInfo[2] = scheduleLocation.getString("public_arrival");
                            locationInfo[3] = scheduleLocation.getString("public_departure");
                        } else if (recordIdentity.equals("LT")) {
                            locationInfo[1] = scheduleLocation.getString("tiploc_code");
                            locationInfo[2] = scheduleLocation.getString("public_arrival");
                            //locationInfo[3] = scheduleLocation.getString("public_departure");
                        } else if (recordIdentity.equals("LI")){
                            locationInfo[1] = scheduleLocation.getString("tiploc_code");
                            locationInfo[2] = scheduleLocation.getString("public_arrival");
                            locationInfo[3] = scheduleLocation.getString("public_departure");
                        }

                        locationVector.add(locationInfo);   //locationVector stores all arrays of location infos
                    }

                    //locationVector needs to be searched through, adding times of arrival and departure to the station's column (idk how you're doing it)
                    //Format for locationVector is: [[train_uid, tiploc_code, public_arrival, public departure], [etc..]]
                    //For origin stations public_arrival is null, for termination stations public_departure is null.
                    //System.out.println("o");

                    for (int i = 0; i < locationVector.size(); i++) {   //Prints the shit
                        for (int j = 0; j < locationVector.get(i).length; j++) {
                            System.out.print(locationVector.get(i)[j] + "  ");
                        }
                        System.out.println();
                    }

                } catch (JSONException e) {
                    //Ignore I guess lol
                }

            }
            //JSONObject o = new JSONObject(fis.toString());
        } catch (IOException | JSONException e) {
            System.err.printf("IOException : %s", e);
        }
    }

    public static void decompressGzip(Path source, Path target) throws IOException {
        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile())); FileOutputStream fos = new FileOutputStream(target.toFile())) {
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }

    public static void downloadFile(URL url, String fileName) throws IOException{
        FileUtils.copyURLToFile(url, new File(fileName));
    }

}

