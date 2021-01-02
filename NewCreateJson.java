package Freshworks;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class NewCreateJson {

String path;

    /* update*/
    public static void update(String key,NewCreateJson p)throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader(p.path));
        String line=br.readLine().trim();
        br.close();
        String valueFromFile=",";

        if((valueFromFile=(isKeyExist(line,key)))!=null)
        {

            line=line.replaceAll("\\{\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}\\}","");
            line=line.replaceAll(",\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}", "");
            line=line.replaceAll("\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\},","");

            File f=new File(p.path);
            FileWriter fp=new FileWriter(f);
            fp.write(line);
            fp.close();


        }
    }

    public static void  delete(String key,NewCreateJson p) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(p.path));


        String line=br.readLine().trim();
        br.close();
        String valueFromFile=",";

        if((valueFromFile=(isKeyExist(line,key)))!=null)
        {

            if(valueFromFile.contains("TIMETOLIVE"))
            {
                if((LocalDateTime.now().toString()).compareTo(valueFromFile.substring(14,42))>=0)
                {
                    System.out.println("TIME-TO_LIVE for the key got expired no such key exist ");
                }
                else
                    System.out.println("Value deleted successfully");
            }
            else
                System.out.println("Value deleted successfully");


            update(key,p);
        }
        else
            System.out.println("key doesnt exist");
    }





    public static void read(String key,NewCreateJson p) throws IOException
    {

        BufferedReader br=new BufferedReader(new FileReader(p.path));

        String line=br.readLine();
        br.close();
        String valueFromFile="";
        if((valueFromFile=(isKeyExist(line,key)))!=null)
        {
            if(valueFromFile.contains("TIMETOLIVE"))
            {
                if((LocalDateTime.now().toString()).compareTo(valueFromFile.substring(14,42))>=0)
                {
                    System.out.println("TIME-TO_LIVE for the key got expired no such key exist found");
                    update(key,p);
                }
                else
                    System.out.println(valueFromFile);

            }
            else
                System.out.println(valueFromFile);
        }
        else
            System.out.println("Key does not exist");


    }



    public  static String isKeyExist(String line,String key)
    {

        String keyappend="";
        String valueFromFile="";
        for(int i=0;i<line.length()-1;i++)
        {
            if(line.charAt(i)!='"'&&line.charAt(i)!='{'&&line.charAt(i)!=':' &&line.charAt(i)!=',')
                keyappend=keyappend+line.charAt(i);

            if(line.charAt(i)==':'&&line.charAt(i+1)=='{')
            {
                if(keyappend.equals(key)) {
                    i++;

                    while(line.charAt(i)!='}')
                    {
                        valueFromFile=valueFromFile+line.charAt(i);
                        i++;
                    }
                    valueFromFile=valueFromFile+line.charAt(i);
                    return valueFromFile;
                }
                else
                {
                    keyappend="";
                    while(line.charAt(i)!='}')
                    {
                        i++;
                    }

                }

            }
        }

        return null;


    }

    public static void  create(String key,Object value,int valueType,NewCreateJson p)  throws IOException {



        BufferedReader br=new BufferedReader(new FileReader(p.path));
        String line=br.readLine();
        br.close();

        if(line==null)
        {

            File f=new File(p.path);
            FileWriter fp=new FileWriter(f);
            if(valueType==4)
                fp.write("{"+"\""+key+"\""+":"+value+"}");

            fp.close();

        }
        else
        {
            if(isKeyExist(line,key)==null) {

                File f=new File(p.path);
                FileWriter fp=new FileWriter(f);

                if(valueType==4)
                    fp.write(line.substring(0,line.length()-1)+","+"\""+key+"\""+":"+value+"}");

                fp.close();
            }
            else
            {

                System.out.println("oops key already exist");
            }

        }




    }
}