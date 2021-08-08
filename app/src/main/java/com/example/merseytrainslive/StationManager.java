package com.example.merseytrainslive;

import com.example.merseytrainslive.livedata.ScheduleManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StationManager {
    static Station[] allStations = new Station[66];

    public StationManager() {
        defineRoutes();
    }

    public String getTipLoc(String stationName) {


        allStations[1] = new Station("Southport", "35001", 6, "SOUTHPT" );
        allStations[2] = new Station("Birkdale", "36060", 2, "BKDLE" );
        allStations[3] = new Station("Hillside", "36061", 2, "HILLSID" );
        allStations[4] = new Station("Ainsdale", "36062", 2, "AINSDAL" );
        allStations[5] = new Station("Freshfield", "36063", 2, "FRSHFLD" );
        allStations[6] = new Station("Formby", "36064", 2, "FORMBY" );
        allStations[7] = new Station("Hightown", "36065", 2, "HITN" );
        allStations[8] = new Station("Hall Road", "36066", 2, "HALLRD" );
        allStations[9] = new Station("Blundellsands & Crosby", "36068", 2, "BLNDLAC" );
        allStations[10] = new Station("Water Loo", "36069", 2, "WLOO" );
        allStations[11] = new Station("Seaforth & Litherland", "36070", 2, "SFRTHAL" );
        allStations[12] = new Station("Bootle New Strand", "36071", 2, "BOOTLNS" );
        allStations[13] = new Station("Bootle Oriel Road", "36072", 2, "BOOTLOR" );
        allStations[14] = new Station("Bank Hall", "36075", 2, "BNKHALL" );
        allStations[15] = new Station("Sandhills", "36076", 2, "SANDH" );
        allStations[16] = new Station("Moorfields", "36081", 2, "MORFLDS" );
        allStations[17] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );
        allStations[18] = new Station("Brunswick", "36082", 2, "BNWK" );
        allStations[19] = new Station("St. Michaels", "36084", 2, "STMCHLS");
        allStations[20] = new Station("Aigburth", "36086", 2, "AIGBURT");
        allStations[21] = new Station("Cressington", "36088", 2, "CRSNGTN");
        allStations[22] = new Station("Liverpool South Parkway", "36091", 2, "LVRPSPY");
        allStations[23] = new Station("Hunts Cross", "36702", 3, "HUNTSX");
        allStations[24] = new Station("Kirkby", "36050", 2, "KKBY");
        allStations[25] = new Station("Fazakerley", "36052", 2, "FAZKRLY");
        allStations[26] = new Station("Rice Lane", "36053", 2, "RICELA");
        allStations[27] = new Station("Kirkdale", "36057", 2, "	KRKDALE");
        allStations[28] = new Station("James Street", "38184", 3, "JAMESST");
        allStations[29] = new Station("Ormskirk", "36001", 2, "ORMSKRK");
        allStations[30] = new Station("Aughton Park", "36005", 2, "AGHTNPH");
        allStations[31] = new Station("Town Green", "36011", 2, "TOWNGRN");
        allStations[32] = new Station("Maghull North", "36014", 2, "MAGHNTH");
        allStations[33] = new Station("Maghull", "36013", 2, "MAGHULL");
        allStations[34] = new Station("Old Roan", "36015", 2, "OLDROAN");
        allStations[35] = new Station("Aintree", "36027", 1, "AINTREE");
        allStations[36] = new Station("Walton", "36029", 2, "WALTONM");
        allStations[37] = new Station("Hamilton Square", "38185", 3, "HAMTSQ");
        allStations[38] = new Station("Conway Park", "38157", 2, "BRKNCPK");
        allStations[39] = new Station("Birkenhead Park", "38153", 2, "BRKNHDP");
        allStations[40] = new Station("Birkenhead North", "38151", 3, "BRKNHDN");
        allStations[41] = new Station("Wallasey Village", "38016", 2, "WALASYV");
        allStations[42] = new Station("Wallasey Grove Road", "38018", 2, "WALAGRD");
        allStations[43] = new Station("New Brighton", "38010", 2, "NBTN");
        allStations[44] = new Station("Bidston", "38012", 2, "BDSTON");
        allStations[45] = new Station("LEASOWE", "38009", 2, "LEASOWE");
        allStations[46] = new Station("Moreton", "38008", 2, "MOTO");
        allStations[47] = new Station("Meols", "38006", 2, "MELS");
        allStations[48] = new Station("ManorRoad", "38005", 2, "MNRD");
        allStations[49] = new Station("Hoylake", "38004", 2, "HOYLAKE");
        allStations[50] = new Station("West Kirby", "38001", 2, "WKIRBY");
        allStations[51] = new Station("Birkenhead Central", "38200", 2, "BRKNHDC");
        allStations[52] = new Station("Green Lane", "38202", 2, "GNLN");
        allStations[53] = new Station("Rock Ferry", "38201", 2, "ROCKFRY");
        allStations[53] = new Station("Bebington", "38208", 2, "BEBNGTN");
        allStations[54] = new Station("Port Sunlight", "38210", 2, "PSLT");
        allStations[55] = new Station("Spital", "38214", 2, "SPITAL");
        allStations[56] = new Station("Bromborough Rake", "38216", 2, "BRMBRK");
        allStations[57] = new Station("Bromborough", "38218", 2, "BRMB");
        allStations[58] = new Station("Eastham Rake", "38220", 2, "ESTHRAK");
        allStations[59] = new Station("Hooton", "38302", 2, "HOOTON");
        allStations[60] = new Station("Little Sutton", "38314", 2, "LTLSUTN");
        allStations[61] = new Station("Overpool", "38316", 2, "OPOL");
        allStations[62] = new Station("Ellesmere Port", "38317", 2, "ELSMPRT");
        allStations[63] = new Station("Capenhurst", "38304", 2, "CPNHRST");
        allStations[64] = new Station("Bache", "38306", 2, "BACHE");
        allStations[0] = new Station("Chester", "40320", 2, "CHST");
        allStations[65] =new Station("Orrell Park", "36028", 1, "ORELPKH");



        String tiploc = "not found";


        for (int i = 0; i<allStations.length; i++){


            String searchStation = allStations[i].getStationName();
            if (searchStation.equals(stationName)) {
                tiploc = allStations[i].getTiploc();
            }
        }
        return tiploc;
        //for (Station i : allStations) {
        //    if (i.getStationName() == stationName) {//this i is mad as fuck
        //        returnThis = i.getTiploc();
        //    }
        //}
        //return returnThis;
    }

    private static void addAllStations(Station[] route, Station[] allStations, int currentIndex) {

        //int counter = 0;
        boolean bool = false;
            for( int y= 0; y < route.length ; y++) {

                for( int i = 0; i < allStations.length; i++){
                    if (route[y] != allStations[i]) {
                        //duplicate so dont use
                        bool = true;
                    }
                if(bool = false){
                    allStations[currentIndex] = (route[y]);//this probs works
                }
            }
        }
       // for (Station spr : add) {
        //    if (!Arrays.asList(allStations).contains(spr)) {
       //         currentIndex++;
       //     }
      //  }
       // return allStations;
    }

    public static void defineRoutes() {

        Station[] southPortRoute = new Station[23];
        Station[] ormskirkRoute = new Station[14];
        Station[] kirkbyRoute = new Station[8];
        Station[] newBrightonRoute = new Station[11];
        Station[] westKirbyRoute = new Station[15];
        Station[] chesterRoute = new Station[18];
        Station[] ellesmerePortRoute = new Station[18];

        //#######          southport route          #######
        southPortRoute[22] = new Station("Southport", "35001", 6, "SOUTHPT" );
        southPortRoute[21] = new Station("Birkdale", "36060", 2, "BKDLE" );
        southPortRoute[20] = new Station("Hillside", "36061", 2, "HILLSID" );
        southPortRoute[19] = new Station("Ainsdale", "36062", 2, "AINSDAL" );
        southPortRoute[18] = new Station("Freshfield", "36063", 2, "FRSHFLD" );
        southPortRoute[17] = new Station("Formby", "36064", 2, "FORMBY" );
        southPortRoute[16] = new Station("Hightown", "36065", 2, "HITN" );
        southPortRoute[15] = new Station("Hall Road", "36066", 2, "HALLRD" );
        southPortRoute[14] = new Station("Blundellsands & Crosby", "36068", 2, "BLNDLAC" );
        southPortRoute[13] = new Station("Water Loo", "36069", 2, "WLOO" );
        southPortRoute[12] = new Station("Seaforth & Litherland", "36070", 2, "SFRTHAL" );
        southPortRoute[11] = new Station("Bootle New Strand", "36071", 2, "BOOTLNS" );
        southPortRoute[10] = new Station("Bootle Oriel Road", "36072", 2, "BOOTLOR" );
        southPortRoute[9] = new Station("Bank Hall", "36075", 2, "BNKHALL" );
        southPortRoute[8] = new Station("Sandhills", "36076", 2, "SANDH" );
        southPortRoute[7] = new Station("Moorfields Northern Line", "36081", 2, "MORFLDS" );
        southPortRoute[6] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );
        southPortRoute[5] = new Station("Brunswick", "36082", 2, "BNWK" );
        southPortRoute[4] = new Station("St. Michaels", "36084", 2, "STMCHLS");
        southPortRoute[3] = new Station("Aigburth", "36086", 2, "AIGBURT");
        southPortRoute[2] = new Station("Cressington", "36088", 2, "CRSNGTN");
        southPortRoute[1] = new Station("Liverpool South Parkway", "36091", 2, "LVRPSPY");
        southPortRoute[0] = new Station("Hunts Cross", "36702", 3, "HUNTSX");

        //#######          kirkby route          #######
        kirkbyRoute[7] = new Station("Kirkby", "36050", 2, "KKBY");
        kirkbyRoute[6] = new Station("Fazakerley", "36052", 2, "FAZKRLY");
        kirkbyRoute[5] = new Station("Rice Lane", "36053", 2, "RICELA");
        kirkbyRoute[4] = new Station("Kirkdale", "36057", 2, "	KRKDALE");
        kirkbyRoute[3] = new Station("Sandhills", "36076", 2, "SANDH" );
        kirkbyRoute[2] = new Station("Moorfields Northern Line", "36081", 2, "MORFLDS" );
        kirkbyRoute[1] = new Station("James Street", "38184", 3, "JAMESST");
        kirkbyRoute[0] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );

        //#######          Ormskirk route         #######
        ormskirkRoute[13] = new Station("Ormskirk", "36001", 2, "ORMSKRK");
        ormskirkRoute[12] = new Station("Aughton Park", "36005", 2, "AGHTNPH");
        ormskirkRoute[11] = new Station("Town Green", "36011", 2, "TOWNGRN");
        ormskirkRoute[10] = new Station("Maghull North", "36014", 2, "MAGHNTH");
        ormskirkRoute[9] = new Station("Maghull", "36013", 2, "MAGHULL");
        ormskirkRoute[8] = new Station("Old Roan", "36015", 2, "OLDROAN");
        ormskirkRoute[7] = new Station("Aintree", "36027", 1, "AINTREE");
        ormskirkRoute[6] = new Station("Orrell Park", "36028", 1, "ORELPKH");
        ormskirkRoute[5] = new Station("Walton", "36029", 2, "WALTONM");
        ormskirkRoute[4] = new Station("Kirkdale", "36057", 2, "    KRKDALE");
        ormskirkRoute[3] = new Station("Sandhills", "36076", 2, "SANDH" );
        ormskirkRoute[2] = new Station("Moorfields Northern Line", "36081", 2, "MORFLDS" );
        ormskirkRoute[1] = new Station("James Street", "38184", 3, "JAMESST");
        ormskirkRoute[0] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );

        //#######          Lime street to new brighton         #######
        newBrightonRoute[0] = new Station("Lime Street", "38182", 2, "LVRPLSL" );
        newBrightonRoute[1] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );
        newBrightonRoute[2] = new Station("James Street", "38184", 3, "JAMESST");
        newBrightonRoute[3] = new Station("Hamilton Square", "38185", 3, "HAMTSQ");
        newBrightonRoute[4] = new Station("Conway Park", "38157", 2, "BRKNCPK");
        newBrightonRoute[5] = new Station("Birkenhead Park", "38153", 2, "BRKNHDP");
        newBrightonRoute[6] = new Station("Birkenhead North", "38151", 3, "BRKNHDN");
        newBrightonRoute[7] = new Station("Wallasey Village", "38016", 2, "WALASYV");
        newBrightonRoute[8] = new Station("Wallasey Grove Road", "38018", 2, "WALAGRD");
        newBrightonRoute[9] = new Station("New Brighton", "38010", 2, "NBTN");

        //#######          Lime street to west kirby        #######
        westKirbyRoute[0] = new Station("Lime Street", "38182", 2, "LVRPLSL" );
        westKirbyRoute[1] = new Station("Liverpool Central", "38183", 2, "LVRPLCL" );
        westKirbyRoute[2] = new Station("James Street", "38184", 3, "JAMESST");
        westKirbyRoute[3] = new Station("Hamilton Square", "38185", 3, "HAMTSQ");
        westKirbyRoute[4] = new Station("Conway Park", "38157", 2, "BRKNCPK");
        westKirbyRoute[5] = new Station("Birkenhead Park", "38153", 2, "BRKNHDP");
        westKirbyRoute[6] = new Station("Birkenhead North", "38151", 3, "BRKNHDN");
        westKirbyRoute[7] = new Station("Bidston", "38012", 2, "BDSTON");
        westKirbyRoute[8] = new Station("LEASOWE", "38009", 2, "LEASOWE");
        westKirbyRoute[9] = new Station("Moreton", "38008", 2, "MOTO");
        westKirbyRoute[10] = new Station("Meols", "38006", 2, "MELS");
        westKirbyRoute[11] = new Station("ManorRoad", "38005", 2, "MNRD");
        westKirbyRoute[12] = new Station("Hoylake", "38004", 2, "HOYLAKE");
        westKirbyRoute[13] = new Station("West Kirby", "38001", 2, "WKIRBY");

        //#######         Lime Street to Ellesmere port
        ellesmerePortRoute[0] = new Station("Moorfields", "38181", 1, "MORFLDS" );
        ellesmerePortRoute[1] = new Station("Lime Street", "38182", 1, "LVRPLSL" );
        ellesmerePortRoute[2] = new Station("Liverpool Central", "38183", 1, "LVRPLCL" );
        ellesmerePortRoute[3] = new Station("James Street", "38184", 3, "JAMESST" );
        ellesmerePortRoute[4] = new Station("Hamilton Square", "38185", 2, "HAMTSQ" );
        ellesmerePortRoute[5] = new Station("Birkenhead Central", "38200", 2, "BRKNHDC");
        ellesmerePortRoute[6] = new Station("Green Lane", "38202", 2, "GNLN");
        ellesmerePortRoute[7] = new Station("Rock Ferry", "38201", 2, "ROCKFRY");
        ellesmerePortRoute[8] = new Station("Bebington", "38208", 2, "BEBNGTN");
        ellesmerePortRoute[9] = new Station("Port Sunlight", "38210", 2, "PSLT");
        ellesmerePortRoute[10] = new Station("Spital", "38214", 2, "SPITAL");
        ellesmerePortRoute[11] = new Station("Bromborough Rake", "38216", 2, "BRMBRK");
        ellesmerePortRoute[12] = new Station("Bromborough", "38218", 2, "BRMB");
        ellesmerePortRoute[13] = new Station("Eastham Rake", "38220", 2, "ESTHRAK");
        ellesmerePortRoute[14] = new Station("Hooton", "38302", 2, "HOOTON");
        ellesmerePortRoute[15] = new Station("Little Sutton", "38314", 2, "LTLSUTN");
        ellesmerePortRoute[16] = new Station("Overpool", "38316", 2, "OPOL");
        ellesmerePortRoute[17] = new Station("Ellesmere Port", "38317", 2, "ELSMPRT");

        //#######          Lime Street to Chester
        chesterRoute[0] = new Station("Moorfields", "38181", 1, "MORFLDS" );
        chesterRoute[1] = new Station("Lime Street", "38182", 1, "LVRPLSL" );
        chesterRoute[2] = new Station("Liverpool Central", "38183", 1, "LVRPLCL" );
        chesterRoute[3] = new Station("James Street", "38184", 3, "JAMESST" );
        chesterRoute[4] = new Station("Hamilton Square", "38185", 2, "HAMTSQ" );
        chesterRoute[5] = new Station("Birkenhead Central", "38200", 2, "BRKNHDC");
        chesterRoute[6] = new Station("Green Lane", "38202", 2, "GNLN");
        chesterRoute[7] = new Station("Rock Ferry", "38201", 2, "ROCKFRY");
        chesterRoute[8] = new Station("Bebington", "38208", 2, "BEBNGTN");
        chesterRoute[9] = new Station("Port Sunlight", "38210", 2, "PSLT");
        chesterRoute[10] = new Station("Spital", "38214", 2, "SPITAL");
        chesterRoute[11] = new Station("Bromborough Rake", "38216", 2, "BRMBRK");
        chesterRoute[12] = new Station("Bromborough", "38218", 2, "BRMB");
        chesterRoute[13] = new Station("Eastham Rake", "38220", 2, "ESTHRAK");
        chesterRoute[14] = new Station("Hooton", "38302", 2, "HOOTON");
        chesterRoute[15] = new Station("Capenhurst", "38304", 2, "CPNHRST");
        chesterRoute[16] = new Station("Bache", "38306", 2, "BACHE");
        chesterRoute[17] = new Station("Chester", "40320", 2, "CHST");



        Station[][] allRoutes = {southPortRoute, ormskirkRoute, kirkbyRoute, newBrightonRoute, westKirbyRoute, chesterRoute, ellesmerePortRoute};

        //int currentIndex = 0;
        //for (Station[] i : allRoutes) {
            //addAllStations(i, allStations, currentIndex);
           // currentIndex += i.length - 1;
        //}
    }



    public static ArrayList<String[]> getClosestTrain (String tiploc, int platform, String time) throws IOException { ///finds the train with the
        ScheduleManager schedule = new ScheduleManager();
        ArrayList<String[]> data = schedule.getStationSchedule(tiploc, platform);
        if(time == "0"){ //set to 0 to find current time
            time = new SimpleDateFormat("HHmm", Locale.getDefault()).format(new Date());
        }
        return data;
    }

    //give it route list



}