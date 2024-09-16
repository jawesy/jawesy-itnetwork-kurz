package Insurance.controllers;

import jakarta.servlet.http.HttpServletRequest;
import Insurance.data.repositories.UserRepository;
import Insurance.models.dto.UserDTO;
import Insurance.models.exceptions.DuplicateEmailException;
import Insurance.models.exceptions.PasswordsDoNotEqualException;
import Insurance.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository usersRepository;

    /**
     * Adds the current URI to the model. This method runs before every request and
     * adds the current request URI to the model, useful for navigation and template use.
     *
     * @param model   Model to add the current URI attribute to.
     * @param request The current HTTP request containing the URI.
     */
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("currentURI", request.getRequestURI());
    }

    /**
     * Displays the login form.
     *
     * @return The template name for the login page.
     */
    @GetMapping("login")
    public String renderLogin() {
        return "/pages/account/login.html";
    }

    /**
     * Displays the registration form.
     * Handles GET requests to the registration page and creates an empty UserDTO for the form.
     *
     * @param userDTO UserDTO object passed to the template.
     * @return The template name for the registration page.
     */
    @GetMapping("register")
    public String renderRegister(@ModelAttribute("userDTO") UserDTO userDTO) {
        return "/pages/account/register";
    }

    /**
     * Processes the registration form.
     * If the form is filled out correctly, a new user is created. In case of validation errors,
     * the user is redirected back to the registration page with the appropriate error messages.
     *
     * @param userDTO            UserDTO object containing the form data.
     * @param result             The result of form validation.
     * @param redirectAttributes Attributes for redirection after success or failure.
     * @return Redirects to the login page upon successful registration, or returns to the registration page if there are errors.
     */
    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        // If there are form errors, the user is returned to the registration page.
        if (result.hasErrors())
            return renderRegister(userDTO);

        try {
            // Attempts to create a new user.
            userService.create(userDTO, false);
            redirectAttributes.addFlashAttribute("success", "Uživatel úspěšně zaregistrován.");
        } catch (DuplicateEmailException e) {
            // If the email is already registered, returns an error message.
            result.rejectValue("email", "error", "Email je již používán.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            // If passwords do not match, returns an error message.
            result.rejectValue("password", "error", "Hesla se neshodují.");
            return "/pages/account/register";
        }

        // Redirects to the login page after successful registration.
        return "redirect:/account/login";
    }
}
