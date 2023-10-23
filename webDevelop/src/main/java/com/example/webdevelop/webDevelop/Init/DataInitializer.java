package com.example.webdevelop.webDevelop.Init;

import com.example.webdevelop.webDevelop.DTO.*;
import com.example.webdevelop.webDevelop.Enum.Category;
import com.example.webdevelop.webDevelop.Enum.Engine;
import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Enum.Transmission;
import com.example.webdevelop.webDevelop.Models.Brand;
import com.example.webdevelop.webDevelop.Models.Model;
import com.example.webdevelop.webDevelop.Models.UserRole;
import com.example.webdevelop.webDevelop.Repositories.*;
import com.example.webdevelop.webDevelop.Services.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final BrandService brandService;
    private final ModelRepository modelRepository;
    private final ModelService modelService;
    private final OfferRepository offerRepository;
    private final OfferService offerService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleService userRoleService;

    @Autowired
    public DataInitializer(BrandRepository brandRepository, BrandService brandService, ModelRepository modelRepository, ModelService modelService, OfferRepository offerRepository, OfferService offerService, UserRepository userRepository, UserService userService, UserRoleRepository userRoleRepository, UserRoleService userRoleService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.modelRepository = modelRepository;
        this.modelService = modelService;
        this.offerRepository = offerRepository;
        this.offerService = offerService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleRepository.save(new UserRole(Role.ADMIN));
        userRoleRepository.save(new UserRole(Role.USER));
        List<UserRole> userRoles = userRoleRepository.findAll();
        System.out.println(userRoles.toString());
        List<UserRoleDTO> userRoleDTO = userRoleService.getAllUserRoles();
        System.out.println(userRoleDTO.toString());

        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            UserDTO userDTO_ = new UserDTO();
            userDTO_.setUsername(faker.name().username());
            userDTO_.setPassword(faker.internet().password());
            userDTO_.setFirstName(faker.name().firstName());
            userDTO_.setLastName(faker.name().lastName());
            userDTO_.setActive(random.nextBoolean());
            userDTO_.setImageUrl(faker.internet().image());
            userDTO_.setCreated(LocalDateTime.now());
            userDTO_.setModified(LocalDateTime.now());
            userDTO_.setRole(userRoleDTO.get(random.nextInt(2)));
            userService.createUser(userDTO_);
        }
        List<UserDTO> userDTOList = userService.getAllUsers();

        String[] carBrands = {"Lada", "Opel", "BMW", "McLaren", "Renault"};
        for (String brand : carBrands) {
            BrandDTO brandDTO = new BrandDTO(UUID.randomUUID(), brand, LocalDateTime.now(), LocalDateTime.now());
            brandDTO.setId(UUID.randomUUID());
            brandDTO.setName(brand);
            brandDTO.setCreated(LocalDateTime.now());
            brandDTO.setModified(LocalDateTime.now());

            Brand brand_ = new Brand();
            brand_.setName(brandDTO.getName());
            brand_.setCreated(brandDTO.getCreated());
            brand_.setModified(brandDTO.getModified());
            brandRepository.save(brand_);

            if (brand.equals("Lada")) {
                List<ModelDTO> ladaModels = new ArrayList<>();
                ladaModels.add(new ModelDTO(null, "Vesta", Category.CAR, "https://example.com/ladavesta.jpg", 2015, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                ladaModels.add(new ModelDTO(null, "Niva", Category.CAR, "https://example.com/ladaniva.jpg", 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                ladaModels.add(new ModelDTO(null, "Granta", Category.CAR, "https://example.com/ladagranta.jpg", 2018, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                ladaModels.add(new ModelDTO(null, "Calina", Category.CAR, "https://example.com/ladacalina.jpg", 2011, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                ladaModels.add(new ModelDTO(null, "VAZ2107", Category.CAR, "https://example.com/ladavaz2107.jpg", 2010, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));

                for (ModelDTO modelDTO : ladaModels) {
                    Model model = new Model();
                    model.setName(modelDTO.getName());
                    model.setCategory(modelDTO.getCategory());
                    model.setStartYear(modelDTO.getStartYear());
                    model.setEndYear(modelDTO.getEndYear());
                    model.setCreated(modelDTO.getCreated());
                    model.setModified(modelDTO.getModified());
                    model.setImageURL(modelDTO.getImageURL());
                    model.setBrand(brand_);
                    modelRepository.save(model);
                }
            } else if (brand.equals("Opel")) {
                List<ModelDTO> opelModels = new ArrayList<>();
                opelModels.add(new ModelDTO(null, "Astra", Category.CAR, "https://example.com/opelastra.jpg", 2015, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                opelModels.add(new ModelDTO(null, "Corsa", Category.CAR, "https://example.com/opelcorsa.jpg", 2017, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                opelModels.add(new ModelDTO(null, "Insignia", Category.CAR, "https://example.com/opelinsignia.jpg", 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                opelModels.add(new ModelDTO(null, "Invalidka", Category.CAR, "https://example.com/opelinvalidka.jpg", 1988, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                opelModels.add(new ModelDTO(null, "Kashtanka", Category.CAR, "https://example.com/opelkashtanka.jpg", 2018, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));

                for (ModelDTO modelDTO : opelModels) {
                    Model model = new Model();
                    model.setName(modelDTO.getName());
                    model.setCategory(modelDTO.getCategory());
                    model.setStartYear(modelDTO.getStartYear());
                    model.setEndYear(modelDTO.getEndYear());
                    model.setCreated(modelDTO.getCreated());
                    model.setModified(modelDTO.getModified());
                    model.setImageURL(modelDTO.getImageURL());
                    model.setBrand(brand_);
                    modelRepository.save(model);
                }
            } else if (brand.equals("BMW")) {
                List<ModelDTO> bmwModels = new ArrayList<>();
                bmwModels.add(new ModelDTO(null, "Series 3", Category.CAR, "https://example.com/bmwseries3.jpg", 2023, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                bmwModels.add(new ModelDTO(null, "M5", Category.CAR, "https://example.com/bmwm5.jpg", 2019, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                bmwModels.add(new ModelDTO(null, "Series 7", Category.CAR, "https://example.com/bmwseries7.jpg", 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                bmwModels.add(new ModelDTO(null, "X6", Category.CAR, "https://example.com/bmwx6.jpg", 2018, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                bmwModels.add(new ModelDTO(null, "XX", Category.CAR, "https://example.com/bmwxx.jpg", 2022, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));

                for (ModelDTO modelDTO : bmwModels) {
                    Model model = new Model();
                    model.setName(modelDTO.getName());
                    model.setCategory(modelDTO.getCategory());
                    model.setStartYear(modelDTO.getStartYear());
                    model.setEndYear(modelDTO.getEndYear());
                    model.setCreated(modelDTO.getCreated());
                    model.setModified(modelDTO.getModified());
                    model.setImageURL(modelDTO.getImageURL());
                    model.setBrand(brand_);
                    modelRepository.save(model);
                }
            } else if (brand.equals("McLaren")) {
                List<ModelDTO> mclarenModels = new ArrayList<>();
                mclarenModels.add(new ModelDTO(null, "570S", Category.CAR, "https://example.com/mclaren570S.jpg", 2012, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                mclarenModels.add(new ModelDTO(null, "720S", Category.CAR, "https://example.com/mclaren720S.jpg", 2017, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                mclarenModels.add(new ModelDTO(null, "Senna", Category.CAR, "https://example.com/mclarensenna.jpg", 2019, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                mclarenModels.add(new ModelDTO(null, "GT", Category.CAR, "https://example.com/mclarengt.jpg", 2023, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                mclarenModels.add(new ModelDTO(null, "440GTX", Category.CAR, "https://example.com/mclaren440gtx.jpg", 2022, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));

                for (ModelDTO modelDTO : mclarenModels) {
                    Model model = new Model();
                    model.setName(modelDTO.getName());
                    model.setCategory(modelDTO.getCategory());
                    model.setStartYear(modelDTO.getStartYear());
                    model.setEndYear(modelDTO.getEndYear());
                    model.setCreated(modelDTO.getCreated());
                    model.setModified(modelDTO.getModified());
                    model.setImageURL(modelDTO.getImageURL());
                    model.setBrand(brand_);
                    modelRepository.save(model);
                }
            } else if (brand.equals("Renault")) {
                List<ModelDTO> renaultModels = new ArrayList<>();
                renaultModels.add(new ModelDTO(null, "Clio", Category.CAR, "https://example.com/renaultclio.jpg", 2011, 2024, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                renaultModels.add(new ModelDTO(null, "Duster", Category.CAR, "https://example.com/renaultduster.jpg", 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                renaultModels.add(new ModelDTO(null, "Megane", Category.CAR, "https://example.com/renaultmegane.jpg", 2012, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                renaultModels.add(new ModelDTO(null, "Kadjar", Category.CAR, "https://example.com/renaultkadjar.jpg", 2018, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));
                renaultModels.add(new ModelDTO(null, "Laguna", Category.CAR, "https://example.com/renaultlaguna.jpg", 2015, 2023, LocalDateTime.now(), LocalDateTime.now(), brandDTO));

                for (ModelDTO modelDTO : renaultModels) {
                    Model model = new Model();
                    model.setName(modelDTO.getName());
                    model.setCategory(modelDTO.getCategory());
                    model.setStartYear(modelDTO.getStartYear());
                    model.setEndYear(modelDTO.getEndYear());
                    model.setCreated(modelDTO.getCreated());
                    model.setModified(modelDTO.getModified());
                    model.setImageURL(modelDTO.getImageURL());
                    model.setBrand(brand_);
                    modelRepository.save(model);
                }
            }
        }

        List<ModelDTO> modelDTOList = modelService.getAllModels();

        for (int i = 0; i < 100; i++) {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(faker.lorem().sentence());
            offerDTO.setEngine(Engine.values()[faker.random().nextInt(Engine.values().length)]);
            offerDTO.setImageUrl(faker.internet().image());
            offerDTO.setMileage(faker.random().nextInt(1000, 100000));
            offerDTO.setPrice(faker.random().nextInt(900000, 5000000));
            offerDTO.setTransmission(Transmission.values()[faker.random().nextInt(Transmission.values().length)]);
            offerDTO.setYear(faker.random().nextInt(2016, 2020));
            offerDTO.setCreated(LocalDateTime.now());
            offerDTO.setModified(LocalDateTime.now());
            offerDTO.setSeller(userDTOList.get(random.nextInt(userDTOList.size())));
            offerDTO.setModel(modelDTOList.get(random.nextInt(modelDTOList.size())));
            offerService.createOffer(offerDTO);
            //запрос 1 на вывод машин по году больше 15
            List<Object[]> results = brandService.getBrandModelCountWhereStartYearGreaterThan2015();
            for (Object[] result : results) {
                String brandName = (String) result[0];
                Long modelCount = (Long) result[1];
                System.out.println("Brand: " + brandName + ", Model Count: " + modelCount);
            // запрос 2 запрос извлекает имена моделей и брендов из таблиц models и brands, где URL изображения модели начинается с https://example.com/renault.
//            List<Object[]> modelAndBrandList = modelRepository.findModelAndBrandByImageURL();
//            for (Object[] row : modelAndBrandList) {
//                String modelName = (String) row[0];
//                String brandName = (String) row[1];
//                System.out.println("Model Name: " + modelName + ", Brand Name: " + brandName);
//            запрос 3 Выбрать только неактивных пользователей, которые разместили предложения, и включить информацию о модели (Model):
//            List<Object[]> results = userRepository.findInactiveUsersWithModels();
//
//            // Обрабатываем результаты запроса
//            for (Object[] row : results) {
//                String username = (String) row[0];
//                Boolean isActive = (Boolean) row[1];
//                String modelName = (String) row[2];
//
//                // Делаем что-то с данными, например, выводим их на консоль
//                System.out.println("Username: " + username);
//                System.out.println("Is Active: " + isActive);
//                System.out.println("Model Name: " + modelName);
                //запрос 4 вывод с userrole админов и юзеров по ролям
//            List<Object[]> userRoleCounts = userRoleService.countUsersByRole();
//
//            for (Object[] result : userRoleCounts) {
//                String roleName = ((Role) result[0]).name();
//                Long userCount = (Long) result[1];
//                System.out.println("Роль: " + roleName + ", Количество пользователей: " + userCount);
                //запрос 5 Получение списка предложений с информацией о моделях, брендах и фильтрацией по цене >=4000000
//                List<Object[]> offerData = offerRepository.findOffersWithModelAndBrand();
//
//                // Обработка результатов
//                for (Object[] result : offerData) {
//                    String description = (String) result[0];
//                    String modelName = (String) result[1];
//                    String brandName = (String) result[2];
//                    System.out.println("Description: " + description + ", Model Name: " + modelName + ", Brand Name: " + brandName);
//            }
                }
            }
        }
    }








