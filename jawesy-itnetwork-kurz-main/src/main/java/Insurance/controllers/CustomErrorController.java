package Insurance.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Handles error pages based on the HTTP status code.
     * If the status code is 404 or 500, specific error templates are shown.
     * For other errors, a default error page is returned.
     *
     * @param request The current HTTP request containing the error status code.
     * @param model The model for passing data to the template.
     * @return The template name for the error page.
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == 404) {
                return "error/error-404";  // Page for 404 errors
            } else if (statusCode == 500) {
                return "error/error-500";  // Page for 500 errors
            }
        }
        // Default error page for other types of errors
        return "error/error";
    }

    /**
     * Adds the current URI and error status code to the model so they can be displayed on the error page.
     *
     * @param model The model to which attributes are added.
     * @param request The current HTTP request.
     */
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("currentURI", request.getRequestURI());  // Adds the current URI
        model.addAttribute("status", request.getAttribute("javax.servlet.error.status_code"));  // Adds the error status code
    }
}

