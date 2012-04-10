/*
 *  Copyright 2012 user. mwenja07@gmail.com
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package test;

import core.DevHuru;
import core.SimpleParser;
import core.XmlNode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.microedition.io.HttpConnection;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author user mwenja07@gmail.com
 * Created : Apr 9, 2012 10:03:12 PM
 * File : DevHuruTest.java
 *
 * Description:
 *
 */
public class DevHuruTest {
    DevHuru midlet;
    String testURL;
    
    public DevHuruTest(DevHuru midlet){
        this.midlet=midlet;
        testURL="http://localhost/test.php";
        try{
            System.out.println("Test Send Simple Get 3ml Request: "+(testSend3mlSimpleGetRequest()?"Success":"Failure"));
            System.out.println("Test Send Simple Post 3ml Request: "+(testSend3mlSimplePostRequest()?"Success":"Failure"));
            System.out.println("Test Send Simple Post 3ml Request: "+(testSend3mlGetWithParamRequest()?"Success":"Failure"));
            System.out.println("Test Send Simple Post 3ml Request: "+(testSend3mlPostWithParamRequest()?"Success":"Failure"));
        }
        catch(Exception e){
            midlet.handleException(e);
        }   
    }
    
    private boolean testSend3mlSimpleGetRequest() throws XmlPullParserException, IOException{
        boolean result=false;
        String actualthreeml="<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><label>Summer dont know me no more</label><button action=\"http://localhost/test2.xml\">Fire Coming Out Of Monkey's Head</button></threeml>";
        ByteArrayInputStream bais=new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser=new SimpleParser(bais);
        XmlNode actual3ml=simpleParser.readXML(new KXmlParser());
        XmlNode threeml=midlet.send3mlRequest(testURL, HttpConnection.GET, "");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }
    
    private boolean testSend3mlSimplePostRequest() throws XmlPullParserException, IOException{
        boolean result=false;
        String actualthreeml="<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><label>Summer dont know me no more</label><button action=\"http://localhost/test2.xml\">Fire Coming Out Of Monkey's Head</button></threeml>";
        ByteArrayInputStream bais=new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser=new SimpleParser(bais);
        XmlNode actual3ml=simpleParser.readXML(new KXmlParser());
        XmlNode threeml=midlet.send3mlRequest(testURL, HttpConnection.POST, "");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }
    
    private boolean testSend3mlGetWithParamRequest() throws XmlPullParserException, IOException{
        boolean result=false;
        String actualthreeml="<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><id>0</id><name>kesha blow me</name></threeml>";
        ByteArrayInputStream bais=new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser=new SimpleParser(bais);
        XmlNode actual3ml=simpleParser.readXML(new KXmlParser());
        XmlNode threeml=midlet.send3mlRequest(testURL, HttpConnection.GET, "id=0&name=kesha+blow+me");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }
    
    private boolean testSend3mlPostWithParamRequest() throws XmlPullParserException, IOException{
        boolean result=false;
        String actualthreeml="<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><id>0</id><name>kesha blow me</name></threeml>";
        ByteArrayInputStream bais=new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser=new SimpleParser(bais);
        XmlNode actual3ml=simpleParser.readXML(new KXmlParser());
        XmlNode threeml=midlet.send3mlRequest(testURL, HttpConnection.POST, "id=0&name=kesha+blow+me");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }
}
