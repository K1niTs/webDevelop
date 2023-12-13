package com.example.webdevelop.webDevelop.Services.ServicesIMPL;

import com.example.webdevelop.webDevelop.Controllers.views.ModelViewModel;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.Models.Model;
import com.example.webdevelop.webDevelop.Repositories.ModelRepository;
import com.example.webdevelop.webDevelop.Services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@EnableCaching
@Service
public class ModelServiceIMPL implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceIMPL(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelDTO createModel(ModelDTO modelDTO) {
        Model model = modelMapper.map(modelDTO, Model.class);
        Model savedModel = modelRepository.save(model);
        return modelMapper.map(savedModel, ModelDTO.class);

    }

    @Override
    public ModelDTO getModelById(UUID id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            return modelMapper.map(modelOptional.get(), ModelDTO.class);
        }
        return null;
    }

    @Override
    public List<ModelDTO> getAllModels() {
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(model -> modelMapper.map(model, ModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ModelDTO updateModel(UUID id, ModelDTO modelDTO) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            Model model = modelOptional.get();
            model.setName(modelDTO.getName());
            Model updateModels = modelRepository.save(model);
            return modelMapper.map(updateModels, ModelDTO.class);
        }
        return null;
    }

    @Override
    public void deleteModel(UUID id) {
        modelRepository.deleteById(id);
    }

    public List<Object[]> findModelAndBrandByImageURL() {
        return modelRepository.findModelAndBrandByImageURL();
    }

    @Override
    public List<ModelViewModel> ModelsByImageURL() {
        List<Model> models = modelRepository.findAll();
        List<ModelViewModel> modelViewModels = new ArrayList<>();
        for (Model modelDTO : models) {
            ModelViewModel viewModel = new ModelViewModel(modelDTO.getName(), modelDTO.getCategory(), modelDTO.getImageURL(), modelDTO.getStartYear(), modelDTO.getEndYear());
            modelViewModels.add(viewModel);
        }
        return modelViewModels;
    }

    @Override
    public List<ModelDTO> getAllModelsOrderByViewCountDesc() {
        List<Model> models = modelRepository.findAllOrderByViewCountDesc();
        return models.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void incrementViewCount(UUID modelId) {
        Model model = modelRepository.findById(modelId).orElse(null);
        if (model != null) {
            model.setViewCount(model.getViewCount() + 1);
            modelRepository.save(model);
        }
    }

    private ModelDTO convertToDTO(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(model, ModelDTO.class);
    }
}

