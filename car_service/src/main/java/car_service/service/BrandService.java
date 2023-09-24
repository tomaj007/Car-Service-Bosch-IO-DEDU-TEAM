package car_service.service;

import car_service.data.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrand();

    Brand getBrand(long id);

    Brand createBrand(Brand brand);

    Brand updateBrand(Brand brand, long id);

    void deleteBrand(long id);

    //Toma
    List<Brand> findAllByNameContaining(String name);
}