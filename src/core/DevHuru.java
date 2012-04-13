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
package core;

import com.sun.lwuit.Button;
import com.sun.lwuit.CheckBox;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.RadioButton;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.FlowLayout;
import xml.XmlNode;
import xml.SimpleParser;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;
import test.DevHuruTest;

/**
 * @author user mwenja07@gmail.com
 * Created : Apr 9, 2012 9:58:00 PM
 * File : DevHuru.java
 *
 * Description:
 *
 */
public class DevHuru extends MIDlet {
    
    public Form frmResult;

    public DevHuru() {
        Display.init(this);
        frmResult=new Form("Result");
        DevHuruTest devHuruTestor = new DevHuruTest(this);
    }

    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    /**
     * Sends a HTTP request to a web server that returns a 3ml document
     * 
     * @param url The URL of the web server and document to be fetched
     * @param type The HTTP request type
     * @param params The URL-encoded parameters that are to be sent with the request
     * 
     * @return 3ml as an XmlNode
     */
    public XmlNode send3mlRequest(String url, String type, String params) {
        XmlNode result = null;
        //create a connection to the web server
        HttpConnection httpConnection = null;
        try {
            //check if request type is for retrieving only or sending and retrieving
            if (type.equalsIgnoreCase(HttpConnection.GET)) {
                //check if there are any parameters to send with the request
                if (params.length() > 0) {
                    url = url + "?" + params;
                }

                //open connection to URL
                httpConnection = (HttpConnection) Connector.open(url, Connector.READ, true);

                //set the User-Agent header field
                httpConnection.setRequestProperty("User-Agent", "Profile/MIDP-1.2 Configuration/CLDC-1.1");

            } else if (type.equalsIgnoreCase(HttpConnection.POST)) {
                httpConnection = (HttpConnection) Connector.open(url, Connector.READ_WRITE, true);

                //set the request method to POST
                httpConnection.setRequestMethod(HttpConnection.POST);

                //set the User-Agent header field
                httpConnection.setRequestProperty("User-Agent", "Profile/MIDP-1.2 Configuration/CLDC-1.1");

                //check if there are any parameters to send with the request
                if (params.length() > 0) {
                    //set the Content-Type header field
                    httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    //open an outgoing connection
                    DataOutputStream dos = httpConnection.openDataOutputStream();
                    dos.write(params.toString().getBytes());
                    dos.close();
                }

            }

            //read response
            SimpleParser simpleParser = new SimpleParser(httpConnection.openInputStream());
            result = simpleParser.readXML(new KXmlParser());
        } catch (IOException ioException) {
            handleException(ioException);
        } catch (XmlPullParserException xppe) {
            handleException(xppe);
        } finally {
            if (httpConnection != null) {
                try {
                    httpConnection.close();
                } catch (IOException ex) {
                    handleException(ex);
                }
            }
        }
        return result;
    }

    public void handleException(Exception e) {
        e.printStackTrace();
    }
    
    public void showUI(Component c){
        frmResult.setLayout(new BorderLayout());
        frmResult.getContentPane().addComponent(BorderLayout.CENTER,c);
        frmResult.show();
    }

    
    public Component processBody(XmlNode bodyML){
        Component c=null;
        if (bodyML.nodeName.equalsIgnoreCase("label")) {
            Label l = new Label();

            //TODO: set the label's name
            //TODO: set the label's icon

            //set the label's text
            if (bodyML.attributes.containsKey("text")) {
                String text = bodyML.getAttr("text");
                l.setText(text);
            }
            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("textfield")) {
            TextField l = new TextField();

            //TODO: set the textfield's name
            //TODO: set the textfield's input constraints

            //set the textfield's text
            if (bodyML.attributes.containsKey("text")) {
                l.setText(bodyML.getAttr("text"));
            }
            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("textarea")) {
            TextArea l = new TextArea();

            //TODO: set the textarea's name
            //TODO: set the textarea's input constraints

            //set the textarea's text
            StringBuffer text = new StringBuffer();
            text.append(bodyML.nodeValue);
            int childCount = bodyML.children.size();
            for (int i = 0; i < childCount; i++) {
                text.append((String) bodyML.children.elementAt(i).toString());
            }
            l.setText(text.toString());

            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("button")) {
            Button l = new Button();

            //TODO: set the button's name
            //TODO: set the button's icon

            //set the button's text
            if (bodyML.attributes.containsKey("text")) {
                l.setText(bodyML.getAttr("text"));
            }

            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("checkbox")) {
            CheckBox l = new CheckBox();

            //TODO: set the checkbox's name
            //TODO: set the checkbox's icon

            //set the checkbox's text
            if (bodyML.attributes.containsKey("text")) {
                l.setText(bodyML.getAttr("text"));
            }

            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("combobox")) {
            ComboBox l = new ComboBox();

            //TODO: set the combobox's name

            //add items to the combobox
            int childCount = bodyML.children.size();
            for (int i = 0; i < childCount; i++) {
                XmlNode item = (XmlNode) bodyML.children.elementAt(i);
                if (item.nodeName.equalsIgnoreCase("item")) {
                    l.addItem((String) item.nodeValue);
                }
            }

            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("radiobutton")) {
            RadioButton l = new RadioButton();

            //TODO: set the radiobutton's name
            //TODO: set the radiobutton's icon

            //set the radiobutton's text
            if (bodyML.attributes.containsKey("text")) {
                l.setText(bodyML.getAttr("text"));
            }

            c = l;
        }
        if (bodyML.nodeName.equalsIgnoreCase("borderlayout")) {
            Container borderContainer = new Container();
            BorderLayout b = new BorderLayout();

            borderContainer.setLayout(b);

            //TODO: set the borderlayout's center behaviour

            int childCount = bodyML.children.size();
            for (int i = 0; i < childCount; i++) {
                XmlNode child = (XmlNode) bodyML.children.elementAt(i);
                if (child.nodeName.equalsIgnoreCase("north")) {
                    int count = child.children.size();
                    for (int j = 0; j < count; j++) {
                        Component childCo = processBody((XmlNode) child.children.elementAt(j));
                        borderContainer.addComponent(BorderLayout.NORTH, childCo);
                    }
                }
                if (child.nodeName.equalsIgnoreCase("east")) {
                    int count = child.children.size();
                    for (int j = 0; j < count; j++) {
                        Component childCo = processBody((XmlNode) child.children.elementAt(j));
                        borderContainer.addComponent(BorderLayout.EAST, childCo);
                    }
                }
                if (child.nodeName.equalsIgnoreCase("west")) {
                    int count = child.children.size();
                    for (int j = 0; j < count; j++) {
                        Component childCo = processBody((XmlNode) child.children.elementAt(j));
                        borderContainer.addComponent(BorderLayout.WEST, childCo);
                    }
                }
                if (child.nodeName.equalsIgnoreCase("south")) {
                    int count = child.children.size();
                    for (int j = 0; j < count; j++) {
                        Component childCo = processBody((XmlNode) child.children.elementAt(j));
                        borderContainer.addComponent(BorderLayout.SOUTH, childCo);
                    }
                }
                if (child.nodeName.equalsIgnoreCase("center")) {
                    int count = child.children.size();
                    for (int j = 0; j < count; j++) {
                        Component childCo = processBody((XmlNode) child.children.elementAt(j));
                        borderContainer.addComponent(BorderLayout.CENTER, childCo);
                    }
                }
            }

            c = borderContainer;
        }
        if (bodyML.nodeName.equalsIgnoreCase("boxlayout")) {
            Container boxContainer = new Container();
            BoxLayout b = new BoxLayout(BoxLayout.Y_AXIS);

            boxContainer.setLayout(b);

            //set the boxlayout's orientation
            if(bodyML.attributes.containsKey("align")){
                String orient=bodyML.getAttr("align");
                if(orient.equalsIgnoreCase("x-axis")){
                    boxContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
                }
            }

            int childCount = bodyML.children.size();
            for (int i = 0; i < childCount; i++) {
                XmlNode child = (XmlNode) bodyML.children.elementAt(i);
                boxContainer.addComponent(processBody((XmlNode) child));
            }

            c = boxContainer;
        }
        if (bodyML.nodeName.equalsIgnoreCase("flowlayout")) {
            Container flowContainer = new Container();
            FlowLayout f = new FlowLayout();

            flowContainer.setLayout(f);

            //TODO: set the flowlayout's orientation

            int childCount = bodyML.children.size();
            for (int i = 0; i < childCount; i++) {
                XmlNode child = (XmlNode) bodyML.children.elementAt(i);
                flowContainer.addComponent(processBody((XmlNode) child));
            }

            c = flowContainer;
        }
        return c;
    }
    
    public Component interpret3ml(XmlNode threeml) {
        Component c = null;
        if (threeml.nodeName.equalsIgnoreCase("threeml")) {
            //by default the whole UI is in a parent Container
            Container root = new Container(new BorderLayout());
            //iterate through the children and add their UI to the Container
            int childCount = threeml.children.size();
            for (int i = 0; i < childCount; i++) {
                XmlNode child=(XmlNode) threeml.children.elementAt(i);
                if(child.nodeName.equalsIgnoreCase("body")){
                    int grandChildrenCount=child.children.size();
                    for(int j=0;j<grandChildrenCount;j++){
                       XmlNode grandChild=(XmlNode)child.children.elementAt(j);
                       root.addComponent(BorderLayout.CENTER,processBody(grandChild));
                    }
                }
                if(child.nodeName.equalsIgnoreCase("head")){
                    processHead(child);
                }
                else{
                    //if no head or body, assume body code
                    int grandChildrenCount=child.children.size();
                    for(int j=0;j<grandChildrenCount;j++){
                       XmlNode grandChild=(XmlNode)child.children.elementAt(j);
                       root.addComponent(BorderLayout.CENTER,processBody(grandChild));
                    }
                }
                
            }
            c = root;
        }
        return c;
    }

    public void processHead(XmlNode child) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
