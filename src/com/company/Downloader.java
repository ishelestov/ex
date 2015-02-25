package com.company;

/**
 * Created by vanya on 20.02.15.
 */
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Downloader {
    public void load (String urlIN){

        try {
            URL url = new URL(urlIN);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));// get all page

            String inputLine;
            String fileLink = null;
            String fileName = null;
            while ((inputLine = in.readLine()) != null) {// read lines one by one

                if (findName(inputLine)!=null){
                    fileName = findName(inputLine);
                    System.out.print("Name: " + fileName + "; ");
                }
                if (findLink(inputLine)!=null){
                    fileLink = findLink(inputLine);
                    System.out.println("URL: http://www.ex.ua"+fileLink);
                }
                if (fileLink!=null&&fileName!=null){
                    if (path!=null){
                        loadFile("http://www.ex.ua"+fileLink,path+fileName);// savefiles to your directory
                    }else {
                        loadFile("http://www.ex.ua"+fileLink,fileName);// savefiles to current directory
                    }
                }

//                System.out.println(fileName);

            }


            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    private String findLink(String inputLine){
        if (inputLine.contains("<a href='/load/")){
            String[] splitString = (inputLine.split("'"));
            for (String string : splitString) {
                if (string.contains("/load/")&&!string.contains("?"))   {
//                    System.out.println(string);
                    return string;
                }
            }
        }
        return null;
    }
    private String findName(String inputLine){
        if (inputLine.contains("<a href='/get/")){
//            System.out.println(inputLine);
            String[] splitString = (inputLine.split("'"));
            for (int i=0;i<splitString.length;i++) {
                if (splitString[i].contains("title="))   {
//                    System.out.println(splitString[i+1]);
                    return splitString[i+1];
                }
            }
        }
        return null;
    }

    private void loadFile(String fileURL, String filename){

        try {
            URL url=new URL(fileURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            float filesize = connection.getContentLength();
            float totalDataRead=0;
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
            byte[] data = new byte[1024];
            int i=0;
            while((i=in.read(data,0,1024))>=0) {
                totalDataRead+=i;
                bout.write(data,0,i);
                float percent=(totalDataRead*100)/filesize;
                System.out.println(percent);
//                current.setValue((int)percent);
            }
            bout.close();
            in.close();
            fos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
