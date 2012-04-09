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

import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author user mwenja07@gmail.com
 * Created : Mar 30, 2012 11:36:50 AM
 * File : SimpleParser.java
 *
 * Description:
 *
 */
public class SimpleParser {
    InputStream is;

    public SimpleParser(InputStream is) {
        this.is = is;
    }

    public XmlNode readXML(XmlPullParser xparser) throws XmlPullParserException, IOException {
        XmlNode root = null;
        xparser.setInput(is, null);
        int event = xparser.nextToken();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                root = parse(xparser, event);
            }
            event = xparser.nextToken();
        }
        is.close();
        xparser=null;
        return root;
    }

    private XmlNode parse(XmlPullParser xparser, int startEvent) throws XmlPullParserException, IOException {
        //System.out.println("Parsing");
        //Start parsing if the previous event was a START TAG
        if (startEvent == XmlPullParser.START_TAG) {
            //Create a new node
            XmlNode node = new XmlNode();
            node.nodeName = xparser.getName();
            //System.out.println("Name: " + p.getName());
            int count = xparser.getAttributeCount();
            // System.out.println(count);

            for (int i = 0; i < count; i++) {

                String attrName = xparser.getAttributeName(i);
                String attrVal = xparser.getAttributeValue(i);
                //  System.out.println(attrName + ": " + attrVal);
                node.setAttr(attrName, attrVal);
            }

            //Get the next Event
            int event = xparser.nextToken();
            //Loop until an END_TAG event is found
            while (event != XmlPullParser.END_TAG) {
                //If event is START_TAG get the node and add to it's children vector
                if (event == XmlPullParser.START_TAG) {
                    node.addChild(parse(xparser, event));
                }
                //if event is a TEXT_TAG set the node's value to the element's text
                if (event == XmlPullParser.TEXT) {
                    node.nodeValue = xparser.getText();
                    //System.out.println("Text: " + p.getText());
                }
                //Get the next Event
                event = xparser.nextToken();
            }
            //System.out.println("Finished parsing: "+node.nodeName);
            return node;
        }
        return null;
    }
}
