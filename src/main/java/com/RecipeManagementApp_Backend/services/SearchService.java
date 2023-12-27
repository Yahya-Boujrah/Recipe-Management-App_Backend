package com.RecipeManagementApp_Backend.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<Recipe> recipesCreatedAfterDate(LocalDateTime localDateTime) throws IOException {
        String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
        System.out.println(localDateTime);

        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                        .index("recipe")
                        .query(q -> q
                                .range(t -> t
                                        .field("CreatedDate")
                                        .gt(JsonData.of(formattedDate))
                                )
                        ) ,
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    public List<Recipe> recipesWithHigherRating(float rating) throws IOException{
        SearchResponse<Recipe> searchResponse = elasticsearchClient.search(s -> s
                .index("recipe")
                .query(q -> q
                        .range(t -> t
                                .field("rating")
                                .gte(JsonData.of(rating))
                        )
                ),
                Recipe.class);

        return searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

}
