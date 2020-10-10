import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("            *****************************************");
        System.out.println("                              WELCOME                   ");
        System.out.println("            *****************************************\n");

        System.out.println("Enter your httpc command or Type 'exit' to Exit :");
        String request = sc.nextLine();
        System.out.println("\n \t \t");



        while (!request.equalsIgnoreCase("exit")) {
            String[] temp = request.split(" ");
            List<String> tempsplitRequest = Arrays.asList(temp);
            ArrayList<String> splitRequest = new ArrayList<>();
            //int i=0;
            for (String i : tempsplitRequest) {
                splitRequest.add(i);
            }
            if (splitRequest.get(0).equals("httpc")) {
                if (splitRequest.get(1).equalsIgnoreCase("get"))
                    get(splitRequest);
                else if (splitRequest.get(1).equalsIgnoreCase("post"))
                    post(splitRequest);
                else
                    System.out.println("Invalid Input!! Press help to get assistance");


            } else {
                System.out.println("INCORRECT VALUE!!");
            }
            System.out.println("Enter your httpc command or Type 'exit' to Exit :");
            request = sc.nextLine();
        }
        System.out.println("Exiting...........");

        sc.close();

    }


    public static void get(ArrayList<String> splitRequest) throws Exception {

        Map<String, String> resMap = new HashMap<String, String>();
        ArrayList<String> headers = new ArrayList<>();

        int Request_Length = splitRequest.size();
        if (Request_Length == 3) {
            no_parameters(splitRequest, headers, resMap);
        } else {
            with_Parameters(splitRequest, resMap);
        }

    }


    public static void post(ArrayList<String> splitRequest) throws Exception {

        Map<String, String> responseMap = new HashMap<String, String>();
        ArrayList<String> headers = new ArrayList<>();
        with_Parameters(splitRequest, responseMap);
    }



    public static ArrayList<String> getHeaders(List<String> splitHeaders) {

        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < splitHeaders.size(); i++) {
            if (splitHeaders.get(i).equals("-h")) {
                headers.add(splitHeaders.get(i + 1));
                i++;
            }
        }
        return headers;
    }

    public static String getURL(boolean o, List<String> splitRequest) {
        String url = "";
        if (o) {
            int length = splitRequest.size();
            //System.out.println(length);
            url = splitRequest.get(length - 3);
            //System.out.println(url);
        } else {
            int length = splitRequest.size();
            //System.out.println(length);
            url = splitRequest.get(length - 1);
            //System.out.println(url);
        }
        return url;
    }


    public static void check(ArrayList<String> splitRequest) throws Exception {

        Map<String, String> responseMap = new HashMap<String, String>();
        ArrayList<String> headers = new ArrayList<>();

        int Request_Length = splitRequest.size();
        if (Request_Length == 3) {
            no_parameters(splitRequest, headers, responseMap);
        } else {
            with_Parameters(splitRequest, responseMap);
        }
    }


    public static void no_parameters(List<String> splitRequest, ArrayList<String> splitHeaders, Map<String, String> responseMap) throws Exception {

        String url1 = splitRequest.get(splitRequest.size() - 1);
        responseMap = HttpLibrary.getFunction(url1, splitHeaders);

        //System.out.println("testtttt"+ url1);

        //////////////////////////////////////////////////////////
        String re = responseMap.get("h");
        //System.out.println("----->"+re);
        String lines1[] = re.split("\\r?\\n");
        String status = lines1[0];
        String loc =lines1[1];
        String lines2[] = status.split(" ");
        String lines3[] = loc.split(": ");
        String loc2 = lines3[1].substring(0,lines3[1].length()-1);
        int status1 = Integer.parseInt(lines2[1]);
        if (status1 >300 && status1<400){
            System.out.println("Redirecting to the URL : " +lines3[1]);
            responseMap = HttpLibrary.getFunction(lines3[1], splitHeaders);

        }
        //////////////////////////////////////////////////////////



        System.out.println("Content: \n" + responseMap.get("c"));
    }


    public static void with_Parameters(ArrayList<String> splitRequest,  Map<String, String> responseMap) throws Exception {

        ArrayList<String> headers = getHeaders(splitRequest);
        int Request_Length = splitRequest.size();
        String url;
        String first = splitRequest.get(1);
        // System.out.println("------->"+ first);

        if (first.equals("get")) {

            //System.out.println("Testgettttttttt");
            if (splitRequest.contains("-d") && splitRequest.contains("-f")) {
                System.out.println("Invalid");
            } else {

                if (splitRequest.contains("-o")) {
                    String filename = splitRequest.get(splitRequest.size() - 1);
                    url = splitRequest.get(splitRequest.size() - 3);
                    responseMap = HttpLibrary.getFunction(url, headers);

                    //////////////////////////////////////////////////////////////////

                    String re = responseMap.get("h");
                    //System.out.println("----->"+re);
                    String lines1[] = re.split("\\r?\\n");
                    String status = lines1[0];
                    String loc =lines1[1];
                    String lines2[] = status.split(" ");
                    String lines3[] = loc.split(": ");
                    String loc2 = lines3[1].substring(0,lines3[1].length()-1);
                    int status1 = Integer.parseInt(lines2[1]);
                    if (status1 >300 && status1<400){
                        System.out.println("Redirecting to the URL : " +lines3[1]);
                        responseMap = HttpLibrary.getFunction(lines3[1], headers);

                    }


                    //////////////////////////////////////////////////////////////////



                    if (splitRequest.contains("-v")) {
                        System.out.println("HEADERS \n" + responseMap.get("h" +
                                ""));
                        System.out.println("CONTENT \n" + responseMap.get("c"));
                        HttpLibrary.writeToFile(filename, responseMap.get("h" +
                                ""));
                        HttpLibrary.writeToFile(filename, responseMap.get("c"));
                    } else {
                        System.out.println("CONTENT: \n" + responseMap.get("c"));
                        HttpLibrary.writeToFile(filename, responseMap.get("h" +
                                ""));
                        HttpLibrary.writeToFile(filename, responseMap.get("c"));
                    }

                } else {

                    url = splitRequest.get(splitRequest.size() - 1);
                    responseMap = HttpLibrary.getFunction(url, headers);


                    //////////////////////////////////////////////////////////////////
                    String re = responseMap.get("h");
                    //System.out.println("----->"+re);
                    String lines1[] = re.split("\\r?\\n");
                    String status = lines1[0];
                    String loc =lines1[1];
                    String lines2[] = status.split(" ");
                    String lines3[] = loc.split(": ");
                    String loc2 = lines3[1].substring(0,lines3[1].length()-1);
                    int status1 = Integer.parseInt(lines2[1]);
                    if (status1 >300 && status1<400){
                        System.out.println("Redirecting to the URL : " +lines3[1]);
                        responseMap = HttpLibrary.getFunction(lines3[1], headers);

                    }


////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (splitRequest.contains("-v")) {

                        System.out.println("HEADERS: \n" + responseMap.get("h" +
                                ""));

                        System.out.println("CONTENT: \n" + responseMap.get("c"));


                    } else {

                        System.out.println("CONTENT: \n" + responseMap.get("c"));

                    }

                }


            }
        }
        // for Post
        else {
            String data = null;


            if (splitRequest.contains("-d") && splitRequest.contains("-f")) {
                System.out.println("Invalid");
            } else if (splitRequest.contains("-d")) {
                int index = splitRequest.indexOf("-d");
                data = splitRequest.get(index + 1);

            } else if (splitRequest.contains("-f")) {
                InputStream is = new FileInputStream("hello.txt");
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));

                String line = buf.readLine();
                StringBuilder sb = new StringBuilder();

                while (line != null) {
                    sb.append(line).append("\n");
                    line = buf.readLine();
                }


                data = sb.toString();

            }

            if (splitRequest.contains("-o")) {
                String filename = splitRequest.get(splitRequest.size() - 1);
                url = splitRequest.get(splitRequest.size() - 3);

                responseMap = HttpLibrary.postFunction(url, data, headers);
                if (splitRequest.contains("-v")) {
                    System.out.println("HEADERS: \n" + responseMap.get("h" +
                            ""));
                    System.out.println("CONTENTS: \n" + responseMap.get("c" +
                            ""));
                    HttpLibrary.writeToFile(filename, responseMap.get("h" +
                            ""));
                    HttpLibrary.writeToFile(filename, responseMap.get("c" +
                            ""));
                } else {
                    System.out.println("CONTENTS: \n" + responseMap.get("c" +
                            ""));
                    HttpLibrary.writeToFile(filename, responseMap.get("h" +
                            ""));
                    HttpLibrary.writeToFile(filename, responseMap.get("c" +
                            ""));
                }

            } else {

                url = splitRequest.get(splitRequest.size() - 1);
                responseMap = HttpLibrary.postFunction(url, data, headers);
                if (splitRequest.contains("-v")) {

                    System.out.println("HEADERS: \n" + responseMap.get("h" +
                            ""));

                    System.out.println("CONTENT: \n" + responseMap.get("c" +
                            ""));


                } else {

                    System.out.println("CONTENTS: \n" + responseMap.get("c" +
                            ""));

                }

            }
        }
    }
}