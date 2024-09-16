package Insurance.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    /**
     * Adds the current URI to the model.
     * This method is called before each request to include the current URI in the model, which can be useful for navigation.
     *
     * @param model   The model for passing data to the template.
     * @param request The current HTTP request to retrieve the URI.
     */
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("currentURI", request.getRequestURI());
    }

    /**
     * Displays the home page.
     *
     * @return The template name for the home page.
     */
    @GetMapping("/")
    public String renderIndex() {
        return "pages/home/index";
    }

    /**
     * Displays the contacts page.
     *
     * @return The template name for the contacts page.
     */
    @GetMapping("/contacts")
    public String renderContacts() {
        return "pages/home/contacts";
    }

    /**
     * Displays the "About Us" page.
     *
     * @return The template name for the "About Us" page.
     */
    @GetMapping("/aboutus")
    public String renderAboutus() {
        return "pages/home/aboutus";
    }

    /**
     * Displays the profile page.
     *
     * @return The template name for the profile page.
     */
    @GetMapping("/profile")
    public String renderProfile() {
        return "pages/home/profile";
    }
}
