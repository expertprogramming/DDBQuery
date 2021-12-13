/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ddbquery;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import java.util.Iterator;

/**
 *
 * @author Naveen
 */

public class DDBIndexQueryWithKeyOnly {
    public static void main(String args[]){
        try{
            DynamoDB dynamoDB;
            dynamoDB = new DynamoDB(new AmazonDynamoDBClient(new ProfileCredentialsProvider()));

        Table table = dynamoDB.getTable("CountryInfo");
        Index index = table.getIndex("country_name-index");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("country_name = :country_name")
            .withValueMap(new ValueMap()
                .withString(":country_name","United States"));

        ItemCollection<QueryOutcome> items = index.query(spec);
        Iterator<Item> iter = items.iterator(); 
        while (iter.hasNext()) {
            System.out.println(iter.next().toJSONPretty());
        }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
