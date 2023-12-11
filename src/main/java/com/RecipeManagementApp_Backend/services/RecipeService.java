package com.RecipeManagementApp_Backend.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.RecipeManagementApp_Backend.entities.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final ElasticsearchClient elasticsearchClient;

    public String index( Recipe recipe ) throws IOException {
        IndexResponse response= elasticsearchClient.index(i->i
                .index("recipe")
                .id(String.valueOf(recipe.getId()))
                .document(recipe));

        Map<String,String> responseMessages = Map.of(
                "Created","Document has been created",
                "Updated", "Document has been updated"
        );

        return responseMessages.getOrDefault(response.result().name(),"Error has occurred");

    }

    public Recipe getById(Integer id){
        GetResponse<Recipe> response = null;
        try {
            response = elasticsearchClient.get(g -> g
                            .index("recipe")
                            .id(String.valueOf(id)),
                    Recipe.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.found()){
            return response.source();
        }
        else return null;
    }

}
