package car_service.controllers.view;

import car_service.data.entity.Car;

import car_service.data.entity.User;
import car_service.service.BrandService;
import car_service.service.CarService;
import car_service.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/carView")
public class CarViewController {
    private final CarService carService;
    private final EmployeeService employeeService;
    private final BrandService brandService;

    public CarViewController(CarService carService, EmployeeService employeeService, BrandService brandService) {
        this.carService = carService;
        this.employeeService = employeeService;
        this.brandService = brandService;
    }

    @GetMapping
    public String getCar(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Car> cars = new ArrayList<>();
        Set<Car> cars1 = new LinkedHashSet<>();

        if (user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            cars = carService.getCarsByCustomer(user.getId());

        } else if (user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
            long autoServiceId = employeeService.getEmployee(user.getId()).getAutoService().getId();
            cars1 = carService.getCarsByAutoService(autoServiceId);
            cars = (new ArrayList<>(cars1));

        } else {
            cars = carService.getCars();
        }

        cars = carService.getCars();
        model.addAttribute("cars", cars);
        return "/car/car";
    }

    @GetMapping("/edit/{id}")
    public String showEditCar(Model model, @PathVariable Long id) {
        model.addAttribute("car", carService.getCars(id));
        model.addAttribute("brands", brandService.getBrand());
        return "/car/edit-car";
    }

    @PostMapping("/update/{id}")
    public String updateCar(Model model, @PathVariable Long id, Car car) {
        carService.updateCar(car, id);
        return "redirect:/carView";
    }

    @GetMapping("/create-car")
    public String showCreateCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("brands", brandService.getBrand());
        return "/car/create-car";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:/carView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        carService.deleteCar(id);
        return "redirect:/carView";
    }

    @GetMapping("/customer")
    public String getCarsByCustomer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Car> cars = new ArrayList<>();
        if (user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            cars = carService.getCarsByCustomer(user.getId());
        } else {
            cars = carService.getCars();
        }

        model.addAttribute("cars", cars);

        return "/car/car";
    }
}