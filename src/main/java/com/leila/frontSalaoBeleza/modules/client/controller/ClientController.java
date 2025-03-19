package com.leila.frontSalaoBeleza.modules.client.controller;

import com.leila.frontSalaoBeleza.modules.client.dto.Token;
import com.leila.frontSalaoBeleza.modules.client.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProfileClientService profileClientService;

    @Autowired
    private FindAppointmentsByPeriodService findAppointmentsByPeriodService;

    @Autowired
    private NewAppointment newAppointment;

    @RequestMapping("/login")
    public String login(){
        return "client/login";
    }

    @PostMapping("/signIn")
    public String signIn(RedirectAttributes redirectAttributes, HttpSession session, String email, String password){
        try{
            var token = this.clientService.login(email, password);
            var grants = token.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())).toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAccessToken());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", token);

            return "redirect:/client/profile";
        }catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error_message", "Email/Senha incorretos");
            return "redirect:/client/login";
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CLIENT')")
    public String profile(Model model) {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = this.profileClientService.execute(authentication.getDetails().toString());

            model.addAttribute("user", user);

            return "client/profile";
        }catch (HttpClientErrorException.Unauthorized e) {
            return "redirect:/client/login";
        }
    }


    @GetMapping("/services")
    @PreAuthorize("hasRole('CLIENT')")
    public String services(Model model, HttpSession session) {
        try {
            var token = (Token) session.getAttribute("token");
            if (token == null) {
                return "redirect:/client/login";
            }
            var servicesList = clientService.getServices(getToken());
            model.addAttribute("services", servicesList);
            return "client/services";
        } catch (Exception e) {
            model.addAttribute("error_message", "Erro ao obter os serviços: " + e.getMessage());
            return "client/login";
        }
    }

    @GetMapping("/appointments")
    @PreAuthorize("hasRole('CLIENT')")
    public String appointments(String startDate, String endDate, Model model) {

      try{
          if(startDate != null && endDate != null){
              var appointments = this.findAppointmentsByPeriodService.execute( getToken(), startDate, endDate);
              model.addAttribute("appointments", appointments);
          }
      }catch (HttpClientErrorException.Unauthorized e) {
            return "redirect:/client/login";
      }
      return "client/appointments";
    }
    @PostMapping("/appointments/new")
    @PreAuthorize("hasRole('CLIENT')")
    public String newAppointment(@RequestParam("serviceId") UUID serviceId,
                                 @RequestParam("date") String date) {
        date = date.trim().replace(",", "");
        System.out.println(date + " " + serviceId);
        this.newAppointment.execute(getToken(), date, serviceId);
        System.out.println(serviceId);
        System.out.println(date);


        return "redirect:/client/appointments";
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }




}
