package com.arya.cassandra.controller;


import com.arya.cassandra.model.SuperHero;
import com.arya.cassandra.service.SuperHeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/super-hero")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;



    @Operation(summary = "Get a Super heroes list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Super heroes list",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<SuperHero>> findAll() {
        List<SuperHero> list = superHeroService.findAll();
        return ResponseEntity.ok().body(list);
    }




    @Operation(summary = "Get a Super hero by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Super hero",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SuperHero.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Super hero not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuperHero> findById(@PathVariable Long id) {
        SuperHero superHero = superHeroService.findById(id);
        return ResponseEntity.ok().body(superHero);
    }




    @Operation(summary = "Save Super hero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save Super hero",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SuperHero.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SuperHero> save(@RequestBody SuperHero superHero) {
        SuperHero savedSuperHero = superHeroService.save(superHero);
        return ResponseEntity.ok().body(savedSuperHero);
    }



    @Operation(summary = "Update Super hero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Super hero",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SuperHero.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping
    public ResponseEntity<SuperHero> update(@RequestBody SuperHero superHero) {
        SuperHero updatedSuperHero = superHeroService.update(superHero);
        return ResponseEntity.ok().body(updatedSuperHero);
    }




    @Operation(summary = "Delete a Super hero by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the Super hero",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        superHeroService.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}