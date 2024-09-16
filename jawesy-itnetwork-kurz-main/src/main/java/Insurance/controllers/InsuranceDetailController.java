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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InsuranceDetailController {

    @Autowired
    private CrashService crashService;

    @Autowired
    private MandatoryService mandatoryService;

    @Autowired
    private PropertyService propertyService;

    /**
     * Displays the details of crash insurance for a specific client.
     *
     * @param clientId The ID of the client whose crash insurance details are being requested.
     * @param model    The model for passing data to the view.
     * @return The view template for crash insurance details.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/crashDetails/{clientId}")
    public String getCrashDetail(@PathVariable Long clientId, Model model) {
        CrashDTO crash = crashService.getById(clientId);
        model.addAttribute("crashInsurance", crash);
        return "pages/details/crashDetails";
    }

    /**
     * Displays the details of mandatory insurance for a specific client.
     *
     * @param clientId The ID of the client whose mandatory insurance details are being requested.
     * @param model    The model for passing data to the view.
     * @return The view template for mandatory insurance details.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/mandatoryDetails/{clientId}")
    public String getMandatoryDetail(@PathVariable Long clientId, Model model) {
        MandatoryDTO mandatory = mandatoryService.getById(clientId);
        model.addAttribute("mandatoryInsurance", mandatory);
        return "pages/details/mandatoryDetails";
    }

    /**
     * Displays the details of property insurance for a specific client.
     *
     * @param clientId The ID of the client whose property insurance details are being requested.
     * @param model    The model for passing data to the view.
     * @return The view template for property insurance details.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/propertyDetails/{clientId}")
    public String getPropertyDetail(@PathVariable Long clientId, Model model) {
        PropertyDTO property = propertyService.getById(clientId);
        model.addAttribute("propertyInsurance", property);
        return "pages/details/propertyDetails";
    }

    /**
     * Handles the deletion of crash insurance for a specific client.
     *
     * @param clientId           The ID of the client whose crash insurance is being deleted.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @return Redirects to the client's profile page.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurance/deleteCrash/{clientId}")
    public String deleteCrashInsurance(@PathVariable Long clientId, RedirectAttributes redirectAttributes) {
        try {
            crashService.remove(clientId);
            redirectAttributes.addFlashAttribute("success", "Havarijní pojištění bylo úspěšně zrušeno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při mazání havarijního pojištění: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Handles the deletion of mandatory insurance for a specific client.
     *
     * @param clientId           The ID of the client whose mandatory insurance is being deleted.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @return Redirects to the client's profile page.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurance/deleteMandatory/{clientId}")
    public String deleteMandatoryInsurance(@PathVariable Long clientId, RedirectAttributes redirectAttributes) {
        try {
            mandatoryService.remove(clientId);
            redirectAttributes.addFlashAttribute("success", "Povinné ručení bylo úspěšně zrušeno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při mazání povinného ručení: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Handles the deletion of property insurance for a specific client.
     *
     * @param clientId           The ID of the client whose property insurance is being deleted.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @return Redirects to the client's profile page.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/insurance/deleteProperty/{clientId}")
    public String deletePropertyInsurance(@PathVariable Long clientId, RedirectAttributes redirectAttributes) {
        try {
            propertyService.remove(clientId);
            redirectAttributes.addFlashAttribute("success", "Pojištění bylo úspěšně zrušeno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při mazání pojištění nemovitosti: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Handles the editing of crash insurance.
     *
     * @param clientId           The ID of the client whose crash insurance is being edited.
     * @param crashDTO           The crash insurance details.
     * @param result             The binding result for validation errors.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @param model              The model for passing data to the view.
     * @return Redirects to the client's profile page or displays the form again in case of errors.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/details/crashFormEdit/{clientId}")
    public String editCrash(@PathVariable Long clientId, @Valid @ModelAttribute("crashDTO") CrashDTO crashDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("crashDTO", crashDTO);
            return "pages/details/crashFormEdit";
        }
        try {
            crashService.edit(crashDTO);
            redirectAttributes.addFlashAttribute("success", "Havarijní pojištění bylo úspěšně upraveno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při úpravě havarijního pojištění: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Handles the editing of mandatory insurance.
     *
     * @param clientId           The ID of the client whose mandatory insurance is being edited.
     * @param mandatoryDTO       The mandatory insurance details.
     * @param result             The binding result for validation errors.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @param model              The model for passing data to the view.
     * @return Redirects to the client's profile page or displays the form again in case of errors.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/details/mandatoryFormEdit/{clientId}")
    public String editMandatory(@PathVariable Long clientId, @Valid @ModelAttribute("mandatoryDTO") MandatoryDTO mandatoryDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mandatoryDTO", mandatoryDTO);
            return "pages/details/mandatoryFormEdit";
        }
        try {
            mandatoryService.edit(mandatoryDTO);
            redirectAttributes.addFlashAttribute("success", "Povinné ručení bylo úspěšně upraveno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při úpravě povinného ručení: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Handles the editing of property insurance.
     *
     * @param clientId           The ID of the client whose property insurance is being edited.
     * @param propertyDTO        The property insurance details.
     * @param result             The binding result for validation errors.
     * @param redirectAttributes Attributes to carry success or error messages.
     * @param model              The model for passing data to the view.
     * @return Redirects to the client's profile page or displays the form again in case of errors.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/details/propertyFormEdit/{clientId}")
    public String editProperty(@PathVariable Long clientId, @Valid @ModelAttribute("propertyDTO") PropertyDTO propertyDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("propertyDTO", propertyDTO);
            return "pages/details/propertyFormEdit";
        }
        try {
            propertyService.edit(propertyDTO);
            redirectAttributes.addFlashAttribute("success", "Pojištění nemovitosti bylo úspěšně upraveno.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Chyba při úpravě pojištění nemovitosti: " + e.getMessage());
        }
        return "redirect:/clients/profile";
    }

    /**
     * Displays the form for editing crash insurance.
     *
     * @param clientId The ID of the client whose crash insurance is being edited.
     * @param model    The model for passing data to the view.
     * @return The view template for editing crash insurance.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/crashFormEdit/{clientId}")
    public String renderCrashEdit(@PathVariable Long clientId, Model model) {
        CrashDTO crashDTO = crashService.getById(clientId);
        model.addAttribute("crashDTO", crashDTO);
        return "pages/details/crashFormEdit";
    }

    /**
     * Displays the form for editing mandatory insurance.
     *
     * @param clientId The ID of the client whose mandatory insurance is being edited.
     * @param model    The model for passing data to the view.
     * @return The view template for editing mandatory insurance.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/mandatoryFormEdit/{clientId}")
    public String renderMandatoryEdit(@PathVariable Long clientId, Model model) {
        MandatoryDTO mandatoryDTO = mandatoryService.getById(clientId);
        model.addAttribute("mandatoryDTO", mandatoryDTO);
        return "pages/details/mandatoryFormEdit";
    }

    /**
     * Displays the form for editing property insurance.
     *
     * @param clientId The ID of the client whose property insurance is being edited.
     * @param model    The model for passing data to the view.
     * @return The view template for editing property insurance.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/propertyFormEdit/{clientId}")
    public String renderPropertyEdit(@PathVariable Long clientId, Model model) {
        PropertyDTO propertyDTO = propertyService.getById(clientId);
        model.addAttribute("propertyDTO", propertyDTO);
        return "pages/details/propertyFormEdit";
    }
}
