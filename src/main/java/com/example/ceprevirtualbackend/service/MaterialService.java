package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Material;
import com.example.ceprevirtualbackend.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    MaterialRepository materialRepository;
    public List<Material> getMaterials(){
        return materialRepository.findAll();
    }
    public Optional<Material> getMaterial(Long id){
        return materialRepository.findById(id);
    }
    public void saveOrUpdateMaterial(Material material){
        materialRepository.save(material);
    }
    public void deleteMaterial(Long id){
        materialRepository.deleteById(id);
    }
}
