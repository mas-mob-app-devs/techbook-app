package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;

import org.json.*;

import android.util.Log;
/**
 * Assist the HomeActivity perform network request such as recent forums and my courses
 * This will be instantiated by the private inner class that extends AsyncTask
 */
public class NetInterfacer {
	
	public static String getPath(){
		HttpClient client = new DefaultHttpClient();
	    String responseBody;
	    JSONObject jsonObject = new JSONObject();
        responseBody="If you see this, then something went wrong in the 'try' block";
	    try{
	        HttpPost post = new HttpPost("http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/jruiz30/");
	        jsonObject.put("field1", ".........");
	        jsonObject.put("field2", ".........");

	        StringEntity se = new StringEntity(jsonObject.toString());  

	        post.setEntity(se);
	        post.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        post.setHeader("Content-type", "application/json");
	        Log.e("webservice request","executing");

	        ResponseHandler responseHandler = new BasicResponseHandler();
	        responseBody = client.execute(post, responseHandler);
	        /* 
	         * You can work here on your responseBody
	         * if it's a simple String or XML/JSON response
	         */
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    return responseBody;
	}
//    public static String getPath(String path) {
//       try {
//    	//   URL url = new URL("http://dev.m.gatech.edu/developer/adehn3/api/techbook/" + path);
//           URL url = new URL("http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/");
//           HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//           InputStream in = conn.getInputStream();
//           if (conn.getResponseCode() != HttpURLConnection.HTTP_OK){
//               return null;
//           }
//           byte[] buffer = new byte[1024];
//           in.read(buffer);
//           String out = new String(buffer);
//           System.out.println(out); //debugging
//           return out;
//       } catch (MalformedURLException e) {
//           e.printStackTrace();
//           return null;
//       } catch (IOException e) {
//           e.printStackTrace();
//           return null;
//       }
//    }
//    
//    public static String getJSON(){
//    DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
//    HttpPost httppost = new HttpPost("http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/");
//    // Depends on your web service
//    httppost.setHeader("Content-type", "application/json");
//
//    InputStream inputStream = null;
//    String result = null;
//    try {
//        HttpResponse response = httpclient.execute(httppost);           
//        HttpEntity entity = response.getEntity();
//
//        inputStream = entity.getContent();
//        // json is UTF-8 by default
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        while ((line = reader.readLine()) != null)
//        {
//            sb.append(line + "\n");
//        }
//        result = sb.toString();
//    } catch (Exception e) { 
//        // Oops
//    }
//    finally {
//        try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
//        
//       
//    }
//    return result;
//    } //end getJSON
//    public static Boolean setPath(String path, String value) throws IOException {
//        try {
//            URL url = new URL("http://dev.m.gatech.edu/developer/adehn3/api/techbook/" + path);
//
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.connect();
//
//            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//            out.writeBytes(value);
//            out.flush();
//            out.close();
//            DataInputStream in = new DataInputStream(conn.getInputStream());
//            in.close();
//
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//
//    }

//    public static ArrayList<String> findArtist(String query){
//        try {
//           String url_str = "http://dev.m.gatech.edu/developer/adehn3/api/techbook/recentforums/";
//           URL url = new URL(url_str);
//           HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//           InputStream in = conn.getInputStream();
//           if (conn.getResponseCode() != HttpURLConnection.HTTP_OK){
//               return null;
//           }
//           InputStreamReader isr = new InputStreamReader(in);
//           BufferedReader br = new BufferedReader(isr);
//           String out = "";
//           String outLine;
//           while ((outLine = br.readLine()) != null) {
//               out += outLine;
//           }
//           Reader string_reader = new StringReader(out);
//           Gson gson = new Gson();
//           APIResponse resp = gson.fromJson(string_reader, APIResponse.class);
//           ArrayList<String> result = new ArrayList<String>(resp.results.artistmatches.artists.size());
//          
//           return result;
//        } catch (MalformedURLException e) {
//           e.printStackTrace();
//           return new ArrayList<String>();
//        } catch (IOException e) {
//           e.printStackTrace();
//           return new ArrayList<String>();
//        }
//    }
/*
    public static List<Event> findEvent(){

    }*/
}
