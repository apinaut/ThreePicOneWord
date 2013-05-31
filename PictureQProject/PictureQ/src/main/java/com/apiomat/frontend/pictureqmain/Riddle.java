/*
 * Copyright (c) 2011-2013, Apinauten GmbH
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this 
 *    list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THIS FILE IS GENERATED AUTOMATICALLY. DON'T MODIFY IT.
 */
package com.apiomat.frontend.pictureqmain;

import java.util.*;

import com.apiomat.frontend.*;
import com.apiomat.frontend.basics.*;
import com.apiomat.frontend.callbacks.*;
import com.apiomat.frontend.helper.*;

import rpc.json.me.*;


/**
* Generated class for your Riddle data model 
*/
public class Riddle extends AbstractClientDataModel
{
    /**
    * Default constructor. Needed for internal processing.
    */
    public Riddle ( )
    {
        super( );
    }

    /**
    * Returns the simple name of this class 
    */
    public String getSimpleName( )
    {
        return "Riddle";
    }

    /**
    * Returns the name of the module where this class belongs to
    */
    public String getModuleName( )
    {
        return "PictureQMain";
    }
    
    /**
    * Returns the system to connect to
    */
    public String getSystem( )
    {
        return MemberModel.system;
    }

    /**
    * Returns a list of objects of this class filtered by the given query from server
    * @query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
    */
    public static final List<Riddle> getRiddles( String query ) throws Exception
    {
        Riddle o = new Riddle();
        return Datastore.getInstance( ).loadFromServer( Riddle.class, o.getModuleName( ),
            o.getSimpleName( ), query );
    }
    
    /**
     * Get a list of objects of this class filtered by the given query from server
     * This method works in the background and call the callback function when finished
     *
     * @param query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
     * @param listAOMCallback The callback method which will called when request is finished
     */
    public static void getRiddlesAsync(final String query, final AOMCallback<List<Riddle>> listAOMCallback) 
    {
       getRiddlesAsync(query, false, listAOMCallback);
    }
    
    /**
    * Returns a list of objects of this class filtered by the given query from server
    *
    * @query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
    * @param withReferencedHrefs set to true to get also all HREFs of referenced models
    */
    public static final List<Riddle> getRiddles( String query, boolean withReferencedHrefs ) throws Exception
    {
        Riddle o = new Riddle();
        return Datastore.getInstance( ).loadFromServer( Riddle.class, o.getModuleName( ),
            o.getSimpleName( ), withReferencedHrefs, query);
    }
    
    /**
     * Get a list of objects of this class filtered by the given query from server
     * This method works in the background and call the callback function when finished
     *
     * @param query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
     * @param withReferencedHrefs set true to get also all HREFs of referenced models
     * @param listAOMCallback The callback method which will called when request is finished
     */
    public static void getRiddlesAsync(final String query, final boolean withReferencedHrefs, final AOMCallback<List<Riddle>> listAOMCallback) 
    {
         Riddle o = new  Riddle();
        Datastore.getInstance().loadFromServerAsync(Riddle.class,o.getModuleName(), o.getSimpleName(), withReferencedHrefs, query, listAOMCallback);
    }

    public String getSolution()
    {
         return this.data.optString( "solution" );
    }

    public void setSolution( String arg )
    {
        String solution = arg;
        this.data.put( "solution", solution );
    }
    public Long getComplexity()
    {
         return this.data.optLong( "complexity" );
    }

    public void setComplexity( Long arg )
    {
        Long complexity = arg;
        this.data.put( "complexity", complexity );
    }
    /**
    * Returns the URL of the image.
    * @return the URL of the image
    */
    public String getPic1URL()
    {
        return this.data.optString( "pic1URL" ) + ".img?apiKey=" 
            + MemberModel.apiKey + "&system=" + this.getSystem();
    }

    /**
    * Returns an URL of the image. <br/>
    * You can provide several parameters to manipulate the image:
    * @param width the width of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param height the height of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param backgroundColorAsHex the background color of the image, null or empty uses the original background color. Caution: Don't send the '#' symbol!
    *        Example: <i>ff0000</i>
    * @param alpha the alpha value of the image, null to take the original value.
    * @param format the file format of the image to return, e.g. <i>jpg</i> or <i>png</i>
    * @return the URL of the image
    */
    public String getPic1URL(int width, int height, String backgroundColorAsHex, 
        Double alpha, String format)
    {
        String parameters =  ".img?apiKey=" + MemberModel.apiKey + "&system=" + this.getSystem();
        parameters += "&width=" + width + "&height=" + height + "&bgcolor=" + backgroundColorAsHex;
        if(alpha != null)
            parameters += "&alpha=" + alpha;
        if(format != null)
            parameters += "&format=" + format;
        return this.data.optString( "pic1URL" ) + parameters;
    }

    public String postPic1( byte[] data ) throws Exception
    {
        String href = Datastore.getInstance( ).postStaticDataOnServer( data, true);
        this.data.put( "pic1URL", href );
        this.save();
        return href;
    }
    
    public void deletePic1() throws Exception
    {
        Datastore.getInstance( ).deleteOnServer(this.data.optString( "pic1URL" ));
    }

    /**
    * Returns the URL of the image.
    * @return the URL of the image
    */
    public String getPic2URL()
    {
        return this.data.optString( "pic2URL" ) + ".img?apiKey=" 
            + MemberModel.apiKey + "&system=" + this.getSystem();
    }

    /**
    * Returns an URL of the image. <br/>
    * You can provide several parameters to manipulate the image:
    * @param width the width of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param height the height of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param backgroundColorAsHex the background color of the image, null or empty uses the original background color. Caution: Don't send the '#' symbol!
    *        Example: <i>ff0000</i>
    * @param alpha the alpha value of the image, null to take the original value.
    * @param format the file format of the image to return, e.g. <i>jpg</i> or <i>png</i>
    * @return the URL of the image
    */
    public String getPic2URL(int width, int height, String backgroundColorAsHex, 
        Double alpha, String format)
    {
        String parameters =  ".img?apiKey=" + MemberModel.apiKey + "&system=" + this.getSystem();
        parameters += "&width=" + width + "&height=" + height + "&bgcolor=" + backgroundColorAsHex;
        if(alpha != null)
            parameters += "&alpha=" + alpha;
        if(format != null)
            parameters += "&format=" + format;
        return this.data.optString( "pic2URL" ) + parameters;
    }

    public String postPic2( byte[] data ) throws Exception
    {
        String href = Datastore.getInstance( ).postStaticDataOnServer( data, true);
        this.data.put( "pic2URL", href );
        this.save();
        return href;
    }
    
    public void deletePic2() throws Exception
    {
        Datastore.getInstance( ).deleteOnServer(this.data.optString( "pic2URL" ));
    }

    /**
    * Returns the URL of the image.
    * @return the URL of the image
    */
    public String getPic3URL()
    {
        return this.data.optString( "pic3URL" ) + ".img?apiKey=" 
            + MemberModel.apiKey + "&system=" + this.getSystem();
    }

    /**
    * Returns an URL of the image. <br/>
    * You can provide several parameters to manipulate the image:
    * @param width the width of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param height the height of the image, 0 to use the original size. If only width or height are provided, 
    *        the other value is computed.
    * @param backgroundColorAsHex the background color of the image, null or empty uses the original background color. Caution: Don't send the '#' symbol!
    *        Example: <i>ff0000</i>
    * @param alpha the alpha value of the image, null to take the original value.
    * @param format the file format of the image to return, e.g. <i>jpg</i> or <i>png</i>
    * @return the URL of the image
    */
    public String getPic3URL(int width, int height, String backgroundColorAsHex, 
        Double alpha, String format)
    {
        String parameters =  ".img?apiKey=" + MemberModel.apiKey + "&system=" + this.getSystem();
        parameters += "&width=" + width + "&height=" + height + "&bgcolor=" + backgroundColorAsHex;
        if(alpha != null)
            parameters += "&alpha=" + alpha;
        if(format != null)
            parameters += "&format=" + format;
        return this.data.optString( "pic3URL" ) + parameters;
    }

    public String postPic3( byte[] data ) throws Exception
    {
        String href = Datastore.getInstance( ).postStaticDataOnServer( data, true);
        this.data.put( "pic3URL", href );
        this.save();
        return href;
    }
    
    public void deletePic3() throws Exception
    {
        Datastore.getInstance( ).deleteOnServer(this.data.optString( "pic3URL" ));
    }

}
