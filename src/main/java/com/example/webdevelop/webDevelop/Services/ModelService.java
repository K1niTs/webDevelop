package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.Controllers.views.ModelViewModel;
import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.DTO.UserDTO;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);
    ModelDTO getModelById(UUID id);
    List<ModelDTO> getAllModels();
    ModelDTO updateModel(UUID id, ModelDTO modelDTO);
    void deleteModel(UUID id);

    List<Object[]> findModelAndBrandByImageURL();
    List<ModelViewModel> ModelsByImageURL();

}
