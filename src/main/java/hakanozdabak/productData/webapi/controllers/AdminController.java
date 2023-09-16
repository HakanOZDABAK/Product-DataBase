package hakanozdabak.productData.webapi.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

     @GetMapping
     @PreAuthorize("hasAuthority('admin:read')")
    public String get(){
         return "GET:: admin controller";
     }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post(){
        return "post:: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put(){
        return "put:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete(){
        return "delete:: admin controller";
    }
}
