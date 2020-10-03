import java.util.*;

public class Client {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        System.out.println("            *****************************************");
        System.out.println("            *****************WELCOME*****************");
        System.out.println("            *****************************************\n");


        System.out.println("Enter your httpc command OR Type 'help' for any Help \n                         OR Type 'exit' to Exit");

        String request= sc.nextLine();

        while(!request.equalsIgnoreCase("exit"))
        {
          String[] temp= request.split(" ");
          List<String> splitRequest= Arrays.asList(temp);

          if(splitRequest.get(0).equals("httpc")){

                    if(splitRequest.get(1).equalsIgnoreCase("get"))
                        get(splitRequest);
                    else if(splitRequest.get(1).equalsIgnoreCase("post"))
                        post(splitRequest);
                    else
                        System.out.println("Invalid Input!! Press help to get assistance");
          }
          else if (request.equalsIgnoreCase("help"))
          {
              printHelpMenu();
          }
          else{
              System.out.println("INCORRECT VALUE!!");
          }

            System.out.println("Enter another command:");
              request=sc.nextLine();

        }

        System.out.println("Exiting...........");


    }

    public static void get(List<String> splitRequest)
    {
        boolean v=false, h=false, o=false, invalid=false;
        Map<String, String> responseMap;
        String file=null;
        String url=null;
        List<String> headers= getHeaders(splitRequest);
        //System.out.println(headers);

        try{
             if(splitRequest.size()>3)
             {
                 if(splitRequest.contains("-v"))
                 v=true;
                 if(splitRequest.contains("-h"))
                     h=true;
                 if(splitRequest.contains("-o"))
                 {
                     o = true;
                     file= splitRequest.get(splitRequest.size()-1);
                 }
                 if(splitRequest.contains("-d") || splitRequest.contains("-f"))
                     invalid=true;
             }

            //System.out.println("v="+v+" h="+h+" o="+o+" invalid=" +invalid );
             if(invalid)
                 System.out.println("Command Invalid");
             else
                 {
                    url=getURL(o,splitRequest);
                     System.out.println(url);
                }
         }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void post(List<String> splitRequest)
    {
        boolean v=false, h=false, o=false, invalid=false;
        Map<String, String> responseMap;
        String file=null;
        String post_url=null;
        String data = null;
    }

    public static void printHelpMenu()
    {
        System.out.println("help");
    }


   public static List<String> getHeaders(List<String> splitHeaders)
   {
       List<String> Headers= new ArrayList<String>();
       for(int i=0; i<splitHeaders.size(); i++)
       {
           if(splitHeaders.get(i).equals("-h"))
           {
               Headers.add(splitHeaders.get(i+1));
               i++;
           }
       }
       return Headers;
   }
   public static String getURL(boolean o, List<String>splitRequest)
   {
       String url="";
       if (o)
       {
           int length = splitRequest.size();
          //System.out.println(length);
           url = splitRequest.get(length - 3);
           //System.out.println(url);
       }

       else {
           int length = splitRequest.size();
           //System.out.println(length);
           url = splitRequest.get(length - 1);
           //System.out.println(url);
       }
       return url;
   }

}
