package com.RecipeManagementApp_Backend.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ElasticsearchClient elasticsearchClient;

    public List<Recipe> recipesByCategory(String categoryName) throws IOException {
        Supplier<Query> supplier = SearchUtil.supplierWithCategory(categoryName);
        SearchResponse<Recipe> searchResponse  = elasticsearchClient
                .search(s->s.index("recipe").query(supplier.get()), Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> searchRecipesInFields(String searchTerm, List<String> fields) throws IOException {
        Supplier<Query> supplier = SearchUtil.supplierFindRecipes(searchTerm, fields);
        SearchResponse<Recipe> searchResponse  = elasticsearchClient
                .search(s->s.index("recipe").query(supplier.get()), Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> recipesFuzzySearch(String searchTerm) throws IOException {
        Supplier<Query> supplier = SearchUtil.createSupplierQuery(searchTerm);
        SearchResponse<Recipe> searchResponse  = elasticsearchClient
                .search(s->s.index("recipe").query(supplier.get()), Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> recipesCreatedAfterDate(String date) throws IOException, ParseException {

        System.out.println(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        long unixTimestamp = parsedDate.getTime();

        System.out.println("Unix Timestamp: " + unixTimestamp);


        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                        .index("recipe")
                        .query(q -> q
                                .range(t -> t
                                        .field("createdAt")
                                        .gte(JsonData.of(unixTimestamp))
                                )
                        ) ,
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> sortRecipesByRating() throws IOException{
        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                .index("recipe")
                .query(q -> q
                        .range(t -> t
                                .field("rating")
                                .gte(JsonData.of(0))
                        )
                )
                .sort( so -> so
                        .field(FieldSort.of(f -> f
                                .field("rating")
                                .order(SortOrder.Desc)))
                ),
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> top6RatedRecipes() throws IOException{
        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                        .index("recipe")
                        .query(q -> q
                                .range(t -> t
                                        .field("rating")
                                        .gte(JsonData.of(0))
                                )
                        ).sort( so -> so
                                .field(FieldSort.of(f -> f
                                        .field("rating")
                                        .order(SortOrder.Desc)))
                        ).size(6),
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> sortRecipesByDate() throws IOException{
        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                        .index("recipe")
                        .sort( so -> so
                                .field(FieldSort.of(f -> f
                                        .field("createdAt")
                                        .order(SortOrder.Desc)))
                        ),
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public Recipe getLatestRecipe() throws IOException {
        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                        .index("recipe")
                        .sort(so -> so
                                .field(FieldSort.of(f -> f
                                        .field("createdAt")
                                        .order(SortOrder.Desc)))
                        ).size(1),
                Recipe.class);

        List<Hit<Recipe>> hits = searchResponse.hits().hits();

        if (!hits.isEmpty()) {
            return hits.get(0).source();
        } else {
            return null;
        }
    }
}
