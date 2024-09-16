package Insurance.controllers;

import Insurance.models.dto.CrashDTO;
import Insurance.models.dto.MandatoryDTO;
import Insurance.models.dto.PropertyDTO;
import Insurance.models.services.CrashService;
import Insurance.models.services.MandatoryService;
import Insurance.models.services.PropertyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AgreementController {

    // Autowired services for different insurance types
    @Autowired
    private MandatoryService mandatoryService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CrashService crashService;

    /**
     * Adds the current URI to the model, which can be useful for templates.
     *
     * @param model Model to which the URI is added.
     * @param request The current HTTP request.
     */
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("currentURI", request.getRequestURI());
    }

    /**
     * Displays crash insurance information.
     *
     * @return The name of the template displaying crash insurance details.
     */
    @GetMapping("/insurances/crashInfo")
    public String renderCrashInfo() {
        return "pages/insurances/crashInfo";
    }

    /**
     * Displays mandatory insurance information.
     *
     * @return The name of the template displaying mandatory insurance details.
     */
    @GetMapping("/insurances/mandatoryInfo")
    public String renderMandatoryInfo() {
        return "pages/insurances/mandatoryInfo";
    }

    /**
     * Displays property insurance information.
     *
     * @return The name of the template displaying property insurance details.
     */
    @GetMapping("/insurances/propertyInfo")
    public String renderPropertyInfo() {
        return "pages/insurances/propertyInfo";
    }

    /**
     * Displays the form for creating mandatory insurance.
     *
     * @param model The model to pass data to the template.
     * @param clientId The ID of the client for whom the insurance is being created.
     * @return The template name for the mandatory insurance form.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/insurances/mandatoryForm")
    public String showMandatoryInsuranceForm(Model model, @RequestParam("clientId") Long clientId) {
        MandatoryDTO mandatoryDTO = new MandatoryDTO();
        mandatoryDTO.setClientId(clientId);
        model.addAttribute("mandatoryDTO", mandatoryDTO);
        return "pages/insurances/mandatoryForm";
    }

    /**
     * Displays the form for creating property insurance.
     *
     * @param model The model to pass data to the template.
     * @param clientId The ID of the client for whom the insurance is being created.
     * @return The template name for the property insurance form.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/insurances/propertyForm")
    public String showPropertyInsuranceForm(Model model, @RequestParam("clientId") Long clientId) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setClientId(clientId);
        model.addAttribute("propertyDTO", propertyDTO);
        return "pages/insurances/propertyForm";
    }

    /**
     * Displays the form for creating crash insurance.
     *
     * @param model The model to pass data to the template.
     * @param clientId The ID of the client for whom the insurance is being created.
     * @return The template name for the crash insurance form.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/insurances/crashForm")
    public String showCrashInsuranceForm(Model model, @RequestParam("clientId") Long clientId) {
        CrashDTO crashDTO = new CrashDTO();
        crashDTO.setClientId(clientId);
        model.addAttribute("crashDTO", crashDTO);
        return "pages/insurances/crashForm";
    }

    /**
     * Processes the form for creating mandatory insurance.
     *
     * @param mandatoryDTO The DTO containing the form data for mandatory insurance.
     * @param result The result of form validation.
     * @param model The model to pass data to the template.
     * @param redirectAttributes Attributes for redirection.
     * @return Redirects to the client profile page upon success.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurances/mandatoryForm")
    public String createMandatoryInsurance(
            @Valid @ModelAttribute("mandatoryDTO") MandatoryDTO mandatoryDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("mandatoryDTO", mandatoryDTO);
            return "pages/insurances/mandatoryForm";
        }

        mandatoryService.create(mandatoryDTO);
        redirectAttributes.addFlashAttribute("success", "Povinné ručení úspěšně sjednáno.");
        return "redirect:/clients/profile";
    }

    /**
     * Processes the form for creating crash insurance.
     *
     * @param crashDTO The DTO containing the form data for crash insurance.
     * @param result The result of form validation.
     * @param model The model to pass data to the template.
     * @param redirectAttributes Attributes for redirection.
     * @return Redirects to the client profile page upon success.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurances/crashForm")
    public String createCrashInsurance(
            @Valid @ModelAttribute("crashDTO") CrashDTO crashDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("crashDTO", crashDTO);
            return "pages/insurances/crashForm";
        }

        crashService.create(crashDTO);
        redirectAttributes.addFlashAttribute("success", "Havarijní pojištění úspěšně sjednáno.");
        return "redirect:/clients/profile";
    }

    /**
     * Processes the form for creating property insurance.
     *
     * @param propertyDTO The DTO containing the form data for property insurance.
     * @param result The result of form validation.
     * @param model The model to pass data to the template.
     * @param redirectAttributes Attributes for redirection.
     * @return Redirects to the client profile page upon success.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurances/propertyForm")
    public String createPropertyInsurance(
            @Valid @ModelAttribute("propertyDTO") PropertyDTO propertyDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("propertyDTO", propertyDTO);
            return "pages/insurances/propertyForm";
        }

        propertyService.create(propertyDTO);
        redirectAttributes.addFlashAttribute("success", "Pojištění nemovitosti úspěšně sjednáno.");
        return "redirect:/clients/profile";
    }
}

