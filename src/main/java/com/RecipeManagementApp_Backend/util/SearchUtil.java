package com.RecipeManagementApp_Backend.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;

import java.util.List;
import java.util.function.Supplier;

public class SearchUtil {

    //multimatch value in all specified fields
    public static MultiMatchQuery findRecipes(String searchTerm, List<String> fields) {
        val multimatch = new MultiMatchQuery.Builder();
        return multimatch.query(searchTerm).fields(fields).analyzer("standard").build();

    }

    //search with approximate terms
    public static FuzzyQuery createFuzzyQuery(String approximateRecipeName){
        val fuzzyQuery  = new FuzzyQuery.Builder();
        return  fuzzyQuery.field("title").value(approximateRecipeName).build();
    }
    public static Supplier<Query> createSupplierQuery(String approximateRecipeName){
        return ()->Query.of(q->q.fuzzy(createFuzzyQuery(approximateRecipeName)));
    }

    //search with category
    public static MatchQuery matchQueryWithCategory(String categoryName) {
        val matchQuery = new MatchQuery.Builder();
        return matchQuery.field("category.name").query(categoryName).analyzer("standard").build();
    }
    public static Supplier<Query> supplierWithCategory(String categoryName){
        return ()-> Query.of(q-> q.match(matchQueryWithCategory(categoryName)));
    }


    //match all
    public static Supplier<Query> matchAllsupplier(){
        return ()->Query.of(q->q.matchAll(matchAllQuery()));
    }
    public static MatchAllQuery matchAllQuery(){
        val  matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }
}
