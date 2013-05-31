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
* Generated class for your User data model 
*/
public class User extends MemberModel
{
    private List<Riddle> solved = new ArrayList<Riddle>();
    /**
    * Default constructor. Needed for internal processing.
    */
    public User ( )
    {
        super( );
    }

    /**
    * Returns the simple name of this class 
    */
    public String getSimpleName( )
    {
        return "User";
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
    public static final List<User> getUsers( String query ) throws Exception
    {
        User o = new User();
        return Datastore.getInstance( ).loadFromServer( User.class, o.getModuleName( ),
            o.getSimpleName( ), query );
    }
    
    /**
     * Get a list of objects of this class filtered by the given query from server
     * This method works in the background and call the callback function when finished
     *
     * @param query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
     * @param listAOMCallback The callback method which will called when request is finished
     */
    public static void getUsersAsync(final String query, final AOMCallback<List<User>> listAOMCallback) 
    {
       getUsersAsync(query, false, listAOMCallback);
    }
    
    /**
    * Returns a list of objects of this class filtered by the given query from server
    *
    * @query a query filtering the results in SQL style (@see <a href="http://doc.apiomat.com">documentation</a>)
    * @param withReferencedHrefs set to true to get also all HREFs of referenced models
    */
    public static final List<User> getUsers( String query, boolean withReferencedHrefs ) throws Exception
    {
        User o = new User();
        return Datastore.getInstance( ).loadFromServer( User.class, o.getModuleName( ),
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
    public static void getUsersAsync(final String query, final boolean withReferencedHrefs, final AOMCallback<List<User>> listAOMCallback) 
    {
         User o = new  User();
        Datastore.getInstance().loadFromServerAsync(User.class,o.getModuleName(), o.getSimpleName(), withReferencedHrefs, query, listAOMCallback);
    }

    public Long getScore()
    {
         return this.data.optLong( "score" );
    }

    public void setScore( Long arg )
    {
        Long score = arg;
        this.data.put( "score", score );
    }
    public List<Riddle> loadSolved( String query ) throws Exception
    {
        final String refUrl = this.data.optString( "solvedHref" );
        if( refUrl==null || refUrl.length()==0 )
        {
            return solved;
        } 
        solved = Datastore.getInstance( ).loadFromServer( Riddle.class, refUrl, query );
        return solved;
    }
    
    /**
    * Getter for local linked variable
    */
    public List<Riddle> getSolved() 
    {
        return solved;
    }

    public String postSolved( Riddle refData ) throws ApiomatRequestException
    {
        String href = refData.getHref();
        if(href == null || href.length() < 1) 
        {
            throw new ApiomatRequestException(Status.SAVE_REFERENECE_BEFORE_REFERENCING);
        }
        String refHref = Datastore.getInstance( ).postOnServer(refData, this.data.optString("solvedHref"));
        
        if(refHref!=null && refHref.length()>0)
        {
            //check if local list contains refData with same href
            if(ModelHelper.containsHref(solved, refHref)==false)
            {
                solved.add(refData);
            }
        }
        return href;
    }
    
    public void removeSolved( Riddle refData ) throws Exception
    {
        String id = refData.getHref( ).substring( refData.getHref( ).lastIndexOf( "/" ) + 1 );
        Datastore.getInstance( ).deleteOnServer( this.data.optString("solvedHref") + "/" + id);
        solved.remove(refData);
    }

}
