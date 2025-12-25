package servlets;
import jakarta.servlet.http.HttpServletRequest;

public class URLutils {
    public URLutils() {
    }

    public static String getFullUrlForRedirect(HttpServletRequest request, String baseUrl) {
        String var10000 = request.getContextPath();
        return var10000 + baseUrl;
    }
}
