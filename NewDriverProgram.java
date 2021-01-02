package Freshworks;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;



public class NewDriverProgram extends NewCreateJson {


    public static void main(String[] args) throws Exception {
        NewCreateJson p=new NewCreateJson();

                String c="";
        int startflag=0;
        String timeLive;
        while(true) {
            Scanner scan=new Scanner(System.in);

            System.out.println("1.create\n2.read\n3.delete\n4.exit");
            if(startflag==0)
            {
			System.out.println("do you want to give path for the file(y/n)");
			if(scan.next().equals("y"))
				{
			  	 System.out.println("Enter the path");
				p.path=scan.next();

				}
			else
				p.path="example.json";

                File fpp = new File(p.path);
                FileWriter frr = new FileWriter(fpp);
                frr.write("");
                frr.close();

            }

            startflag++;
            System.out.println("now enter the option");
            int input=scan.nextInt();

            switch(input)
            {

                case 1:
                {


                    System.out.println("enter the key");
                    String key=scan.next();
                    System.out.println("Do you want to create time to live property for key(y/n)");
                    timeLive=scan.next();
                    if((timeLive.trim()).equals("y"))
                    {
                        System.out.println("Enter the number of second key wants to live(integer value)");
                        int noOfSeconds= scan.nextInt();
                        c=""+"{\""+"TIMETOLIVE\":"+LocalDateTime.now().plusSeconds(noOfSeconds);


                    }

                    System.out.println("press 4 for json object ");
                    int opt=scan.nextInt();
                    System.out.println("dont leave any spaces string will terminate and they enclose the string with double quotes");
                    System.out.println("syntax is {\"Key\":value,\"Key\":value,..}");
                    System.out.println("please type like above systax now ");

                    if(opt==4)
                    {
                        System.out.println("enter the value as jsonObject");
                        String value="";
                        if(timeLive.equals("y"))
      create(key,c+scan.next().replaceAll("\\s","").replaceFirst("\\{",","),opt,p);
                        else
                            create(key,scan.next().replaceAll("\\s", ""),opt,p);


                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the key to get the value as object");
                    read(scan.next(),p);
                    break;
                }
                case 3:
                {
                    System.out.println("enter the key to delete the value as object");
                    delete(scan.next(),p);
                    break;
                }
                case 4:
                {
                    System.out.println("|thank you welcome|");
                    return;
                }

            }



        }


    }

}