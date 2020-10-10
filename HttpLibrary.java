import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpLibrary {

    final static Integer port = 80;

    public static Map<String, String> getFunction(String url, ArrayList<String> listOfHeaders) throws Exception {

        URL url_obj = new URL(url);
      //  System.out.println("------>"+url);
        Map<String, String> resMap = new HashMap<>();
        Socket socket = new Socket(InetAddress.getByName(url_obj.getHost()), port);
        System.out.println("Connection Successful!!!!!\n");
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("GET /" + url_obj.getFile() + " HTTP/1.0");
        printWriter.println("Host: " + url_obj.getHost());

        if (!listOfHeaders.isEmpty())
        {
            for(String x: listOfHeaders)
            {
                printWriter.println(x);
            }
        }

        printWriter.println("");
        printWriter.flush();


        Scanner sc= new Scanner(socket.getInputStream());
        String line;
        StringBuilder res = new StringBuilder();
        boolean isHeader = false;

        while ((sc.hasNext())) {
            line=sc.nextLine();
            res.append(line + "\n");
            if (line.isEmpty()&& !isHeader) {
                String temp= res.toString();
                resMap.put("h", temp);
                isHeader = true;
                res = new StringBuilder();
            }

        }

        printWriter.close();
        socket.close();

        String responseString= res.toString();
        resMap.put("c", responseString);
//        System.out.println("--------------------------------------------");
//        String re = resMap.get("h");
//        String lines1[] = re.split("\\r?\\n");
//        String status = lines1[0];
//        String loc =lines1[1];
//      //  System.out.println("----->"+lines1[1]);
//      //  System.out.println("------>"+hh);
//        String lines2[] = status.split(" ");
//        String lines3[] = loc.split(": ");
//        //System.out.println("------>"+lines2[1]);
//        System.out.println("------>"+lines3[1]);
//        String loc2 = lines3[1].substring(0,lines3[1].length()-1);
//        int status1 = Integer.parseInt(lines2[1]);
//        if (status1>300 ){
//            System.out.println("redirect");
//            getFunction(loc2,listOfHeaders);
//
//        }
//

        return resMap;
    }

    public static Map<String, String> postFunction(String url, String data, ArrayList<String> listOfHeaders)
            throws Exception {

        URL url_obj = new URL(url);
        Map<String, String> resMap = new HashMap<>();
        Socket socket = new Socket(InetAddress.getByName(url_obj.getHost()), port);
        System.out.println("Connection Successful!!!!\n");
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("POST /" + url_obj.getFile() + " HTTP/1.0");
        printWriter.println("Host: " + url_obj.getHost());
        printWriter.println("Content-Length: " + data.length());
        printWriter.println("json :" + data);

        if (!listOfHeaders.isEmpty())
        {
            if (!listOfHeaders.isEmpty())
                listOfHeaders.stream().forEach(x -> printWriter.println(x));
        }
        printWriter.println();
        printWriter.println(data);
        printWriter.println();
        printWriter.flush();


        Scanner sc= new Scanner(socket.getInputStream());
        String line;
        StringBuilder res = new StringBuilder();
        boolean isHeader = false;

        while (sc.hasNext()) {
            line=sc.nextLine();
            res.append(line + "\n");
            if (line.isEmpty() && !isHeader) {
                String temp= res.toString();
                resMap.put("h", temp);
                isHeader = true;
                res = new StringBuilder();
            }
        }

        printWriter.close();
        socket.close();

        String temp=res.toString();
        resMap.put("c",temp);

        return resMap;
    }


    public static void writeToFile(String fname, String data) {
        try {
            File f = new File(fname);
            FileWriter fw;

            if (!f.exists()) {
                f.createNewFile();
                fw = new FileWriter(f.getAbsoluteFile());
            }
            else
            {
                fw = new FileWriter(f.getAbsoluteFile(), true);
            }

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}