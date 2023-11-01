package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.ModelViewModel;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.Services.ModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/create")
    public ModelDTO createModel(@Valid @RequestBody ModelDTO modelDTO) {
        return modelService.createModel(modelDTO);
    }

    @GetMapping("/{id}")
    public ModelDTO getModelById(@PathVariable UUID id) {
        return modelService.getModelById(id);
    }

    @GetMapping("/all")
    public List<ModelDTO> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/model-and-brand-by-image")
    public List<Object[]> findModelAndBrandByImageURL() {
        return modelService.findModelAndBrandByImageURL();
    }

    @PutMapping("/{id}")
    public ModelDTO updateModel(@PathVariable UUID id,@Valid @RequestBody ModelDTO modelDTO) {
        return modelService.updateModel(id, modelDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable UUID id) {
        modelService.deleteModel(id);
    }

    @GetMapping("/by-image-url")
    public List<ModelViewModel> ModelsByImageURL() {
        return modelService.ModelsByImageURL();
    }
}

