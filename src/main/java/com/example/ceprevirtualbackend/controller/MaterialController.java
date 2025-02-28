package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Material;
import com.example.ceprevirtualbackend.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Material> getAll(){
        return materialService.getMaterials();
    }
    @GetMapping("/{materialId}")
    public Optional<Material> getById(@PathVariable("materialId") Long materialId){
        return materialService.getMaterial(materialId);
    }
    @PostMapping
    public Material saveUpdate(@RequestBody Material material){
        materialService.saveOrUpdateMaterial(material);
        return material;
    }
    @DeleteMapping("/{materialId}")
    public void delete(@PathVariable("materialId") Long materialId){
        materialService.deleteMaterial(materialId);
    }
}
