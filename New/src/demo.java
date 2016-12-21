


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class demo 
{
	static String dbstore="";
	public static void dispaly(String result, String which) {
	    if(result!=null){
	    	result=result.replaceAll("lang=en", "");
	    		String fword;
	    		 String ts;
	    		 String dump=result;
	    		 //dump=dump.replaceAll("[^a-zA-Z0-9.,;\\s+]", "");
	    		 if(result.contains("[[")||result.contains("{{"))
	    			 
		    		{	
		    		 dump=dump.replaceAll("[^a-zA-Z0-9.\\[\\]|,;}\\s+]", "").trim();
		    		// System.out.println("DUMP "+dump);
		    		 	if(dump.startsWith("[["))
		    		 	{
		    		 	int i=result.indexOf("[[");
		    			int j=result.indexOf("]]");
		    			fword=result.substring(i,j);
		    			int k=fword.indexOf('|');
		    			fword=fword.substring(k+1);
		    			//System.out.println("First Word "+fword);
		    			//System.out.println("FIND "+result);
		    			result=result.substring(j+2);
		    		//	System.out.println("FIND "+result);
		    			result=fword.concat(result);
		    			result=	result.replace('|', ' ');
		    		//	result=result.replaceAll("\\s{2}", " ");
		    			
		    		 	}
		    		 	else
		    			{
		    		 		System.out.println("Esle called "+result);
		    		 		dump=dump.replaceAll("[^a-zA-Z0-9.\\[\\]|,;}\\s+]", "").trim();
		    		 		String arr[]=dump.split(" ");
		    		 		for(int aa=0;aa<arr.length;aa++)
		    		 		{
		    		 			
		    		 			if(arr[aa].contains("[[")&&arr[aa].contains("|"))
		    		 			{
		    		 				//System.out.println(arr[aa]);
		    		 				String min=arr[aa];
		    		 				//System.out.println("MINNN "+min);
		    		 				int x=min.indexOf("[[");
		    		 				int y=min.indexOf("|");
		    		 				 min=min.substring(y+1);
		    		 				//System.out.println(min);
		    		 				result=result.replace(arr[aa], min);
		    		 			}
		    		 		
		    		 		}
		    		 	result=	result.replace('|', ' ');
		    		 	result=result.replaceAll("\\s{2}", " ");
		    		 	 }
		    		}
	    		 
	    		 
	    	

		      
	    	   if(result.contains("}}"))
	    			   {
	    		   int resultindex=result.indexOf("}}");
			   
			    	 result=result.substring(resultindex);
	    			   }
	    	
	    	 result = result.replaceAll("[^a-zA-Z0-9.,;\\s+]", "");
	    	 result=result.replaceAll(";",".\n");
	     
	    
	       int newline=0;
	       for(int k=0;k<result.length();k++)
	       {
	    	   if(result.charAt(k)=='\n')
	    	   {
	    		   newline++;
	    	   }
	       }
	   
	    
	       for(int l=0;l<newline;l++)
	       {	
	    	   int nn=result.indexOf("\n");
	    	
	    	   String res=result.substring(0,nn);
	    	   
	    	   result=result.substring(nn+1);
	    	
	       }
	       int nn=result.indexOf("\n");
 	 
	       
 	   result=result.substring(nn+1);
 	  result=result.replaceAll("\\s{2}", " ");
 	System.out.println(which+"\n"+result);
	       dbstore+=which+"\n"+result;
	      }
	
	       
else {
			System.out.println("Enter correct word");
	}
	}	
	
	public static void main(String[] args) throws MalformedURLException {
		
		String s1="";
		 String line,jsd="",result = null;
		 System.out.println("Enter word");
		 Scanner in=new Scanner(System.in);
		 s1=in.next();
		
		URL url = new URL("https://en.wiktionary.org/w/api.php?action=query&prop=revisions&titles="+s1+"&rvprop=content&format=json&utf8=");
		  
		try  {
		
			URLConnection urlConnection = url.openConnection();
			
		      // wrap the urlconnection in a bufferedreader
		      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		     
		      
		      

		      // read from the urlconnection via the bufferedreader
		      while ((line = bufferedReader.readLine()) != null)
		      {
		    	 jsd +=line;

		      }
		      
//		     
		       if(jsd.contains("===Noun==="))
			    {
			    	int i1=jsd.indexOf("===Noun===");
				      String r1=jsd.substring(i1);
				      System.out.println(r1);
				      int i3=r1.indexOf("#");
					   System.out.println(i3);
					  String out=r1.substring(i3);
					  System.out.println(out);
					//  System.out.println(out);
					  if(out.contains("===")){
					  int i2=out.indexOf("===");
				     System.out.println(i2);
				      String r2=out.substring(i2);//error
				      String main=r1.substring(i3, i3+i2);
				//   System.out.println(main);
				   int i4=main.indexOf(".");
				 //  System.out.println(main.substring(0, i4+1));
				   result=main.substring(0, i4+1);
					  }
					  else{
						   int i4=out.indexOf(".");
							 //  System.out.println(main.substring(0, i4+1));
							   result=out.substring(0, i4+1);
					  }
				   dispaly(result,"Noun");
			    }
		           if(jsd.contains("===Adjective==="))
			    {
			    	int i1=jsd.indexOf("===Adjective===");
				      String r1=jsd.substring(i1);
				      int i3=r1.indexOf("#");
					  
					  String out=r1.substring(i3);
				
					  int i2=out.indexOf("===");
				  //    System.out.println(i2);
				      String r2=out.substring(i2);
				      String main=r1.substring(i3, i3+i2);
				  
				   int i4=main.indexOf(".");
				 //  System.out.println(i4);
				 //  System.out.println(main.substring(0, i4+1));
				   result=main.substring(0, i4+1);
				   dispaly(result,"Adjective");
			    }
		        if(jsd.contains("===Interjection==="))
			    {
			    	int i1=jsd.indexOf("===Interjection===");
				      String r1=jsd.substring(i1);
				      int i3=r1.indexOf("|");
					   
					  String out=r1.substring(i3);
					//  System.out.println(out);
					  int i2=out.indexOf("===");
				    //  System.out.println(i2);
				      String r2=out.substring(i2);
				      String main=r1.substring(i3, i3+i2);
				//   System.out.println(main);
				   int i4=main.indexOf(".");
				//   System.out.println(main.substring(0, i4+1));
				   result=main.substring(0, i4+1);
				   dispaly(result,"Interjection");
				  
			    }
		      
		  
		       
		       if(result!=null){
		    	   System.out.println("Connect");
		    	   try {

						//**** Connect to MongoDB ****//
						// Since 2.10.0, uses MongoClient
						MongoClient mongo = new MongoClient("localhost", 27017);

						//**** Get database ****//
						// if database doesn't exists, MongoDB will create it for you
						DB db = mongo.getDB("Wik");

						//**** Get collection / table from 'testdb' ****//*
						// if collection doesn't exists, MongoDB will create it for you
						DBCollection table = db.getCollection("WORD");

						//**** Insert ****//*
						// create a document to store key and value
						BasicDBObject document = new BasicDBObject();
						document.put(s1, dbstore);
						
						table.insert(document);


					    } catch (UnknownHostException e) {
						e.printStackTrace();
					    } catch (MongoException e) {
						e.printStackTrace();
					    }
		    	   
		       }
		       else
		       {
		    	   System.out.println("Not");
		       }
		   
		      
		      
		      
		     
		    
		    
		}
		    
		     
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
