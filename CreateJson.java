import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class CreateJson {
	
	
	
/* update*/
	public static void update(String key)throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("example.json"));
   	  
		  
		 String line=br.readLine().trim();
		 br.close();
		 String valueFromFile=",";
		 
		 if((valueFromFile=(isKeyExist(line,key)))!=null)
		 {
			
			 //System.out.println(valueFromFile);
line=line.replaceAll("\\{\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}\\}","");	
line=line.replaceAll(",\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}", "");
line=line.replaceAll("\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\},","");
//System.out.println("\\{\\{\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}\\}");

//C:\\Users\\LENOVO\\eclipse-workspace\\JsonWrite\\
File f=new File("example.json");
FileWriter fp=new FileWriter(f);
fp.write(line);
fp.close();
		
		
	}
		 }
	
public static void  delete(String key) throws Exception
	{
		  BufferedReader br=new BufferedReader(new FileReader("example.json"));
     	  
		  
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
				 update(key);
			 }
			 else
				 System.out.println("key doesnt exist");
/*
System.out.println(valueFromFile);
line=line.replaceAll("\\{\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}\\}","");	
line=line.replaceAll(",\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}", "");
line=line.replaceAll("\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\},","");
  System.out.println("\\{\\{\""+key+"\""+":\\"+valueFromFile.substring(0,valueFromFile.length()-1)+"\\}\\}");

	File f=new File("C:\\Users\\LENOVO\\eclipse-workspace\\JsonWrite\\example.json");
	FileWriter fp=new FileWriter(f);
	fp.write(line);
	fp.close();
	System.out.println("corresponding value for the key is delete successfully");
			 }
			 else
				 System.out.println("Key does not exist");
*/
		 }

		  
  		  	
	
	
	public static void read(String key) throws IOException
	{
		
		  BufferedReader br=new BufferedReader(new FileReader("example.json"));
			     	  
		 String line=br.readLine();
		 String valueFromFile="";
		 if((valueFromFile=(isKeyExist(line,key)))!=null)
		 {
			 if(valueFromFile.contains("TIMETOLIVE"))
			 {
				 if((LocalDateTime.now().toString()).compareTo(valueFromFile.substring(14,42))>=0)
				 {
					 System.out.println("TIME-TO_LIVE for the key got expired no such key exist found");
				    update(key);
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
					//System.out.println(keyappend);
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

	public static void  create(String key,Object value,int valueType)  throws IOException {
		
		 File fpp=new File("example.json");
        FileWriter frr=new FileWriter(fpp);
       frr.write("");
       frr.close();
      
		
	  BufferedReader br=new BufferedReader(new FileReader("example.json"));
		      //System.out.println("create"+path);   
	 String line=br.readLine();
			  if(line==null)
			  {
				  //C:\\Users\\LENOVO\\eclipse-workspace\\JsonWrite\\
					File f=new File("example.json");
					 
					FileWriter fp=new FileWriter(f);	
					  if(valueType==1||valueType==4)
						  fp.write("{"+"\""+key+"\""+":"+value+"}");
					  else
						  if(valueType==2)
							  fp.write("{"+"\""+key+"\""+":"+"\""+value+"\""+"}");
						
					 
					  fp.close();
					
			  }
			  else
			  {
			   if(isKeyExist(line,key)==null) {
			  br.close();
			  
//			  System.out.println(line.substring(0,line.length()-1));
			  //C:\\Users\\LENOVO\\eclipse-workspace\\JsonWrite\\
			  
			  File f=new File("example.json");
			  FileWriter fp=new FileWriter(f);	
			  String ans="";
			  if(valueType==1||valueType==4)
			  {
				  ans=line.substring(0,line.length()-1)+","+"\""+key+"\""+":"+value+"}";
			  }
			  else {
				  ans=line.substring(0,line.length()-1)+","+"\""+key+"\""+":"+"\""+value+"\""+"}";
			  	}
			  fp.write(ans);
			  fp.close();
	//		  System.out.println(line.substring(0,line.length()-1)+","+"\""+key+"\""+":"+"\""+value+"\""+"}");
 }
			   else
			   {
				   
				   System.out.println("oops key already exist");
			   }
			
			 }
			  
		
		
	
	}
}
