package car_service.service;

import car_service.data.entity.TypeOfService;

import java.util.List;

public interface TypeOfServiceService {
    List<TypeOfService> getTypeOfService();

    TypeOfService getTypeOfService(long id);

    TypeOfService createTypeOfService(TypeOfService typeOfService);

    TypeOfService updateTypeOfService(TypeOfService typeOfService, long id);

    void deleteTypeOfService(long id);

    //All Kiril
    List<TypeOfService> findByPriceBetween(double smallPrice, double bigPrice);

    List<TypeOfService> findByDescriptionContaining(String description);

    List<TypeOfService> findByDescription(String description);

    List<TypeOfService> findByIdLessThanOrderByType(long idTypeOfService);
}