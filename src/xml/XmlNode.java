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

package xml;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author user mwenja07@gmail.com
 * Created : Mar 30, 2012 11:34:11 AM
 * File : XmlNode.java
 *
 * Description:
 *
 */
public class XmlNode {
    public String nodeName=null;
    public String nodeValue=null;
    public Hashtable attributes=new Hashtable();
    public Vector children=new Vector();

    public XmlNode(){   
    }

    /**
     * Returns all the attributes as an array of strings
     *
     * @return array of strings of attributes
     */
    public String[] getAttributeNames()
	{
		String[] names = new String[attributes.size()];

		Enumeration e = attributes.keys();

		int i = 0;

		while(e.hasMoreElements())
		{
			names[i] = (String)e.nextElement();

			i++;
		}
		return names;
	}

    public void setAttr(String key,String value){
        attributes.put(key, value);
    }

    public String getAttr(String key){
        return (String)attributes.get(key);
    }

    public void addChild(XmlNode n){
        children.addElement(n);
    }

    public String toString(){
        StringBuffer xml=new StringBuffer();
        xml.append("<");
        xml.append(nodeName);
        Enumeration e=attributes.keys();
        while(e.hasMoreElements()){
            String nE=(String)e.nextElement();
            xml.append(" "+nE+"=\""+attributes.get(nE)+"\"");
        }
        xml.append(">");
        int childCount=children.size();
        for(int i=0;i<childCount;i++){
            xml.append(((XmlNode)children.elementAt(i)).toString());
        }
        if(nodeValue!=null){
            xml.append(nodeValue);
        }
        xml.append("</");
        xml.append(nodeName);
        xml.append(">");
        return xml.toString();
    }
}
