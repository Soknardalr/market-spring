package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.raspad.marketspring.dto.Student;
import ru.raspad.marketspring.services.ProductService;
import ru.raspad.marketspring.services.StudentService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final StudentService service;
    private final ProductService productService;

    @GetMapping("/products")
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products.html";
    }



    // http://localhost:8189/app


    // http://localhost:8189/app/hello
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>sir, yes, sir</h1>";
    }

    // http://localhost:8189/app/buy
    @GetMapping("/buy")
    @ResponseBody
    public String buy() {
        return "sir, buy, sir";
    }

    // http://localhost:8189/app/calculate?first=5&second=4
    @GetMapping("/calculate")
    @ResponseBody
    public int calculate(@RequestParam(name = "first") int a, @RequestParam(required = false, defaultValue = "0", name = "second") int b) {
        return a + b;
    }

    // http://localhost:8189/app/products/12/info
//    @GetMapping("/products/{id}/info")
//    @ResponseBody
//    public String info(@PathVariable String id) {
//        return "prod with id = " + id;
//    }

    @GetMapping("/page")
    public String page(Model model, @RequestParam Long id) {
        model.addAttribute("student", service.getStudent(id));
        return "index.html";
    }
    @GetMapping("/product")
    public String product(Model model, @RequestParam Long id) {
        model.addAttribute("product", productService.getProduct(id));
        return "product.html";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model){
        model.addAttribute("students", service.getStudents());
        return "students.html";
    }

}
