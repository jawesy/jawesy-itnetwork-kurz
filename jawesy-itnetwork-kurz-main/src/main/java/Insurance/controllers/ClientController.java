package Insurance.controllers;

import Insurance.models.dto.ClientDTO;
import Insurance.models.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Displays a list of all clients (only accessible by administrators).
     * @param model Model for passing data to the template.
     * @return The template name for the client list.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public String listClients(Model model) {
        // Load all clients from the database
        List<ClientDTO> clients = clientService.getAll();
        // Add the list of clients to the model
        model.addAttribute("clients", clients);
        return "pages/clients/index";
    }

    /**
     * Displays the form for editing a client.
     * @param clientId The ID of the client to be edited.
     * @param model Model for passing data to the template.
     * @return The template name for client editing.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/edit/{clientId}")
    public String renderEditClientForm(@PathVariable("clientId") long clientId, Model model) {
        // Load the client by their ID
        ClientDTO clientDTO = clientService.getById(clientId);
        // Add the client to the model
        model.addAttribute("clientDTO", clientDTO);
        return "pages/clients/edit";
    }

    /**
     * Processes the client edit form.
     * @param clientId The ID of the client to be edited.
     * @param clientDTO The DTO containing the client data.
     * @param model Model for passing data to the template.
     * @param redirectAttributes Attributes for redirecting.
     * @return Redirects to the client list or returns to the form in case of an error.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/edit/{clientId}")
    public String editClient(
            @PathVariable("clientId") long clientId,
            ClientDTO clientDTO,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            // Update the client
            clientService.edit(clientDTO);
            // Add success message
            redirectAttributes.addFlashAttribute("success", "Údaje klienta byly úspěšně aktualizovány.");
            return "redirect:/clients/profile";
        } catch (Exception e) {
            // Handle error
            model.addAttribute("error", "Chyba: Aktualizace klienta se nezdařila.");
            return "pages/clients/edit";
        }
    }

    /**
     * Handles the request to delete a client.
     * @param clientId The ID of the client to be deleted.
     * @param redirectAttributes Attributes for redirecting.
     * @return Redirects to the client list.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable("clientId") long clientId, RedirectAttributes redirectAttributes) {
        try {
            // Delete the client by their ID
            clientService.remove(clientId);
            // Add success message
            redirectAttributes.addFlashAttribute("success", "Klient byl úspěšně smazán.");
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Chyba: Smazání klienta se nezdařilo.");
        }
        return "redirect:/clients";
    }

    /**
     * Displays the details of a specific client.
     * @param clientId The ID of the client.
     * @param model Model for passing data to the template.
     * @return The template name for displaying client details.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{clientId}")
    public String renderDetail(@PathVariable("clientId") long clientId, Model model) {
        // Load the client by their ID
        ClientDTO clientDTO = clientService.getById(clientId);
        // Add the client to the model
        model.addAttribute("client", clientDTO);
        return "pages/clients/profile";
    }

    /**
     * Displays the profile of the logged-in user.
     * If the client does not exist, redirects to the page for creating a new client.
     * @param model Model for passing data to the template.
     * @param redirectAttributes Attributes for redirecting.
     * @return The template name for the profile or redirects to the form for creating a new client.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/profile")
    public String renderProfile(Model model, RedirectAttributes redirectAttributes) {
        ClientDTO clientDTO = clientService.getProfile();

        // If the client does not exist, redirect the user to create a new client
        if (clientDTO == null) {
            return "redirect:/clients/newclient";
        }

        // Check for insurance details
        boolean hasMandatoryInsurance = clientDTO.getMandatoryInsurance() != null;
        boolean hasCrashInsurance = clientDTO.getCrashInsurance() != null;
        boolean hasPropertyInsurance = clientDTO.getPropertyInsurance() != null;

        // Add data to the model
        model.addAttribute("client", clientDTO);
        model.addAttribute("hasMandatoryInsurance", hasMandatoryInsurance);
        model.addAttribute("hasCrashInsurance", hasCrashInsurance);
        model.addAttribute("hasPropertyInsurance", hasPropertyInsurance);

        return "pages/clients/profile";
    }

    /**
     * Displays the form for creating a new client.
     * @param model Model for passing data to the template.
     * @return The template name for the new client form.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/newclient")
    public String renderNewClientForm(Model model) {
        // Prepare a new client DTO with default values
        ClientDTO clientDTO = clientService.prepareNewClient();
        // Add the client DTO to the model
        model.addAttribute("clientDTO", clientDTO);
        return "pages/clients/newclient";
    }
    /**
     * Processes the new client creation form.
     *
     * @param clientDTO The DTO containing the new client data.
     * @param model Model for passing data to the template.
     * @param redirectAttributes Attributes for redirecting after the operation.
     * @return Redirects to the client list or returns to the form in case of an error.
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/newclient")
    public String createClient(
            ClientDTO clientDTO,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            // Create a new client
            clientService.create(clientDTO);
            // Add success message
            redirectAttributes.addFlashAttribute("success", "Nový klient byl úspěšně vytvořen.");
            return "redirect:/clients/profile";
        } catch (Exception e) {
            // Handle error
            model.addAttribute("error", "Chyba: Vytvoření nového klienta se nezdařilo.");
            return "pages/clients/newclient";
        }
    }

}
