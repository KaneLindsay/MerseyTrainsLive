import net.ser1.stomp.*;
import java.util.Map;
import org.json.*;

class Consumer {
    private net.ser1.stomp.Client client;
    private String server = "datafeeds.networkrail.co.uk";
    private int port = 61618;
    private String vhost = "datafeeds.networkrail.co.uk";
    private String destination = "/topic/TRAIN_MVT_TOC_DAILY"; //There are more options other than /queue/...
    private String login = "isaac12ward@outlook.com";
    private String passcode = "P4ssword!";


    private void consume(TrainManager manager) {
        while (true) {
            try {
                client = new Client(server, port, login, passcode, vhost);
                client.subscribe(destination, new Listener() {
                    /**
                     * This method is the overridden callback on receiving messages.
                     * @ It is event-driven. You don't call it in your code.
                     * @ It prints the message body on console.
                     * @ There are other callback functions provided by this library.
                     */
                    public void message(Map headers, String message) {


                        JSONArray messageList = new JSONArray(message);    //Creates a JSON array of the received messages
                        for (int i = 0; i < messageList.length(); i++) {
                            JSONObject messageObject = messageList.getJSONObject(i);    //Creates creates a JSONObject from the current message
                            JSONObject header = messageObject.getJSONObject("header");  //Splits messageObject into header and body JSONObjects
                            JSONObject body = messageObject.getJSONObject("body");
                            Integer msgType = Integer.parseInt(header.getString("msg_type"));   //Searches for "msg_type" in header and converts to an Integer
                            //manager.printMessage(header.getString("msg_type"));
                            if (msgType== 0003) {                                               //If message is a train movement
                                manager.printMessage("movement");
                                String[] messageArray = new String[5];
                                messageArray[0] = body.getString("train_service_code");     //Service Code (8-digit code)
                                messageArray[1] = body.getString("train_id");               //Train ID (10-character code)
                                messageArray[2] = body.getString("event_type");             //Event type (ARRIVAL, DEPARTURE)
                                messageArray[3] = body.getString("timetable_variation");    //How much time to add to timetable (minutes)
                                messageArray[4] = body.getString("loc_stanox");             //STANOX code for the location of the event (5-digit code)
                                manager.moveService(messageArray);

                            } else if (msgType == 0001) {                                       //If message is a train activation
                                manager.printMessage("activate");
                                String[] messageArray = new String[4];
                                messageArray[0] = body.getString("train_service_code");     //Service Code (8-digit code)
                                messageArray[1] = body.getString("train_id");               //Train ID (10-character code)
                                messageArray[2] = body.getString("tp_origin_timestamp");    //YYYY-MM-DD
                                messageArray[3] = body.getString("originStanox");
                                manager.activateService(messageArray);

                            } else if (msgType == 0002) {                                       //If message is a train cancellation
                                String[] messageArray = new String[2];
                                messageArray[0] = body.getString("train_service_code");     //Service Code (8-digit code)
                                messageArray[1] = body.getString("train_id");               //Train ID (10-character code)
                                manager.cancelService(messageArray);
                            }
                        }
                    }
                });
                client.addErrorListener(new Listener() {
                    public void message(Map header, String body) {
                        System.out.printf("Exception handled, reconnecting...\nDetail:\n%s\n", body);
                        //after connected, disconnect on error
                        try {
                            client.disconnect();
                        } catch (Exception e) {
                        }
                    }
                });
                while (true) {
                    //after connected, reconnect on connection lost
                    if (!client.isSockConnected()) {
                        break;
                    }
                    Thread.sleep(2000); //check interval must be short enough
                }
            } catch (Exception e) {
                //when initializing connection, reconnect on exception
                System.out.printf("Exception handled, reconnecting...\nDetail:\n%s\n", e);
                try {
                    Thread.sleep(5000);
                } catch (Exception es) {
                }
            }
        }
    }

    public Consumer(TrainManager manager) {
        consume(manager);
    }

    public static void main(String[] args) {
        TrainManager manager = new TrainManager();
        Consumer c = new Consumer(manager);
        c.consume(manager);
    }
}
