package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.ModelViewModel;
import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Services.ModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }


    @GetMapping("/details/{model-id}")
    public String modelDetails(@PathVariable("model-id") UUID modelId, Model model) {


        ModelDTO modelDTO = modelService.getModelById(modelId);
        modelService.incrementViewCount(modelId);

        model.addAttribute("modelDetails", modelDTO);
        return "models-details";
    }


    @GetMapping("/all")
    public String getAllModels(Model model) {
        List<ModelDTO> modelDTOS = modelService.getAllModels();
        model.addAttribute("models", modelDTOS);
        return "models-all";
    }

    @GetMapping("/model-and-brand-by-image")
    public List<Object[]> findModelAndBrandByImageURL() {
        return modelService.findModelAndBrandByImageURL();
    }

    @PutMapping("/{id}")
    public ModelDTO updateModel(@PathVariable UUID id,@Valid @RequestBody ModelDTO modelDTO) {
        return modelService.updateModel(id, modelDTO);
    }

    @PostMapping("/delete/models/{id}")
    public String deleteModel(@PathVariable UUID id) {
        modelService.deleteModel(id);
        return "redirect:/models/all";
    }

    @GetMapping("/by-image-url")
    public List<ModelViewModel> ModelsByImageURL() {
        return modelService.ModelsByImageURL();
    }

    @GetMapping("/add")
    public String createModel(){
        return "models-add";
    }
    @ModelAttribute("modelModel")
    public ModelDTO initModel(){
        return new ModelDTO();
    }
    @PostMapping("/add")
    public String createModel(@ModelAttribute("modelDTO") ModelDTO modelDTO) {
        modelService.createModel(modelDTO);
        return "redirect:/models/add";
    }

}

