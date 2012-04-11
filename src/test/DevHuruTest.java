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

import com.sun.lwuit.Button;
import com.sun.lwuit.ButtonGroup;
import com.sun.lwuit.CheckBox;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Label;
import com.sun.lwuit.RadioButton;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.layouts.BorderLayout;
import core.DevHuru;
import xml.SimpleParser;
import xml.XmlNode;
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

    public DevHuruTest(DevHuru midlet) {
        this.midlet = midlet;
        testURL = "http://localhost/test.php";
        try {
            //ok("Test Send Simple Get 3ml Request",testSend3mlSimpleGetRequest());
            //ok("Test Send Simple Post 3ml Request",testSend3mlSimplePostRequest());
            //ok("Test Send Simple Post 3ml Request",testSend3mlGetWithParamRequest());
            //ok("Test Send Simple Post 3ml Request",testSend3mlPostWithParamRequest());
            ok("Test Build Threeml", testBuildThreeml());
            ok("Test Build Label", testBuildLabel());
            ok("Test Build TextField", testBuildTextField());
            ok("Test Build TextArea", testBuildTextArea());
            ok("Test Build Button", testBuildButton());
            ok("Test Build CheckBox", testBuildCheckBox());
            ok("Test Build ComboBox", testBuildComboBox());
            ok("Test Build RadioButton", testBuildRadioButton());
            ok("Test Build BorderLayout", testBuildBorderLayout());
        } catch (Exception e) {
            midlet.handleException(e);
        }
    }

    private void ok(String testDescription, boolean testResult) {
        System.out.println(testDescription + ": " + (testResult ? "Success" : "Failure"));
    }

    private boolean testSend3mlSimpleGetRequest() throws XmlPullParserException, IOException {
        boolean result = false;
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><label>Summer dont know me no more</label><button action=\"http://localhost/test2.xml\">Fire Coming Out Of Monkey's Head</button></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        XmlNode threeml = midlet.send3mlRequest(testURL, HttpConnection.GET, "");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }

    private boolean testSend3mlSimplePostRequest() throws XmlPullParserException, IOException {
        boolean result = false;
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><label>Summer dont know me no more</label><button action=\"http://localhost/test2.xml\">Fire Coming Out Of Monkey's Head</button></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        XmlNode threeml = midlet.send3mlRequest(testURL, HttpConnection.POST, "");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }

    private boolean testSend3mlGetWithParamRequest() throws XmlPullParserException, IOException {
        boolean result = false;
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><id>0</id><name>kesha blow me</name></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        XmlNode threeml = midlet.send3mlRequest(testURL, HttpConnection.GET, "id=0&name=kesha+blow+me");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }

    private boolean testSend3mlPostWithParamRequest() throws XmlPullParserException, IOException {
        boolean result = false;
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><id>0</id><name>kesha blow me</name></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        XmlNode threeml = midlet.send3mlRequest(testURL, HttpConnection.POST, "id=0&name=kesha+blow+me");
        //System.out.println(threeml.toString());
        //System.out.println(actual3ml.toString());
        return threeml.toString().equals(actual3ml.toString());
    }

    private boolean testBuildThreeml() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return testContainer.toString().equals(actualContainer.toString());
    }

    private boolean testBuildLabel() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><label text=\"some stuff\"/></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        Label l = new Label();
        l.setText("some stuff");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
//        System.out.println(getContainerString(actualContainer));
//        System.out.println(getContainerString(testContainer));
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildTextField() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><textfield text=\"some stuff\"/></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        TextField l = new TextField();
        l.setText("some stuff");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildTextArea() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><textarea>some stuff</textarea></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        TextArea l = new TextArea();
        l.setText("some stuff");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildButton() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><button text=\"some stuff\"/></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        Button l = new Button();
        l.setText("some stuff");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildCheckBox() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><checkbox text=\"some stuff\"/></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        CheckBox l = new CheckBox();
        l.setText("some stuff");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildComboBox() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><combobox><item>One</item><item>Two</item></combobox></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        ComboBox l = new ComboBox();
        l.addItem("One");
        l.addItem("Two");
        actualContainer.addComponent(l);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildRadioButton() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><radiobutton text=\"some stuff\"/></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        RadioButton r = new RadioButton();
        r.setText("some stuff");

        actualContainer.addComponent(r);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;
        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private boolean testBuildBorderLayout() throws XmlPullParserException, IOException {
        String actualthreeml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><threeml><borderlayout><north><label text=\"some stuff\"/></north></borderlayout></threeml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(actualthreeml.getBytes());
        SimpleParser simpleParser = new SimpleParser(bais);
        XmlNode actual3ml = simpleParser.readXML(new KXmlParser());
        Container actualContainer = new Container();
        Container actualBorder=new Container();
        actualBorder.setLayout(new BorderLayout());
        Label l = new Label();
        l.setText("some stuff");

        actualBorder.addComponent(BorderLayout.NORTH, l);
        actualContainer.addComponent(actualBorder);
        Component c = midlet.interpret3ml(actual3ml);
        if (c == null) {
            return false;
        }
        Container testContainer = (Container) c;

        return getContainerString(testContainer).equals(getContainerString(actualContainer));
    }

    private String getContainerString(Container testContainer) {
        StringBuffer result = new StringBuffer();
        result.append(testContainer.toString());
        int count = testContainer.getComponentCount();
        for (int i = 0; i < count; i++) {
            Component c = testContainer.getComponentAt(i);
            if (c instanceof Container) {
                result.append(getContainerString((Container) c));
            } else {
                result.append(c.toString());
            }
        }
        return result.toString();
    }
}
