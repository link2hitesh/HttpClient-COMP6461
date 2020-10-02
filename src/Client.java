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
        String get_url=null;
        ArrayList<String> headerList= new ArrayList<String>();

        try{
             if(splitRequest.size()>3)
             {
                 if(splitRequest.contains("-v"))
                 v=true;
                 else if(splitRequest.contains("-h"))
                     h=true;
                 else if(splitRequest.contains("-o"))
                     o=true;
                 else if(splitRequest.contains("-d"))
                     invalid=true;
                 else if(splitRequest.contains("-f"))
                     invalid=true;
             }

             if(invalid)
                 System.out.println("Command Invalid");

             else{

             }
         }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void post(List<String> splitRequest)
    {
        System.out.println("post");
    }

    public static void printHelpMenu()
    {
        System.out.println("help");
    }
}
