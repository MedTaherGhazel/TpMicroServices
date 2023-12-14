package ma.app.productsapp.web;

import ma.app.productsapp.entities.Product;
import ma.app.productsapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@EnableMethodSecurity
@Controller
public class ProductController{

    @Autowired
    OAuth2AuthorizedClientService oauth2ClientService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    //@PreAuthorize("hasRole('client-user')")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    //@PreAuthorize("hasRole('client-admin')")
    public String products(Model model,@AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);


        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model,@AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);


        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);
        String url = "http://localhost:8081/all";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Supplier>>()
                {});

        List<Supplier> pageSuppliers = responseEntity.getBody();
        model.addAttribute("suppliers",pageSuppliers);
        return "suppliers";
    }
    @GetMapping("/inventory")
    public String inventory(Model model,@AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);


        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);
        String url = "http://localhost:8082/all";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Product>> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Product>>()
                {});

        List<Product> pageProducts = responseEntity.getBody();
        model.addAttribute("products",pageProducts);
        return "products";
    }
    @GetMapping("/customers")
    public String customers(Model model,@AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);


        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);
        String url = "http://localhost:8086/all";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Customer>> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Customer>>()
                {});

        List<Customer> pageCustomers = responseEntity.getBody();
        model.addAttribute("customers",pageCustomers);
        return "customers";
    }
    @GetMapping("/billing")
    public String billing(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);

        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8083/bills/full/{id}"; // Removed extra slash
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers); // Changed the entity type to Void
        ResponseEntity<List<Bill>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Bill>>() {});

        List<Bill> bills = responseEntity.getBody();
        model.addAttribute("bills", bills); // Changed the attribute name to "bills"
        return "billing"; // Assuming "billing" is the correct template name
    }

    @GetMapping("/all")
    public String allsuppliers(Model model,@AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);


        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);



        String url = "http://localhost:8081/suppliers";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
        ResponseEntity<PagedModel<Supplier>>response = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<PagedModel<Supplier>>()
                {});

        model.addAttribute("supplier",response.getBody().toString());
        return "supphateoas";
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("errorMessage","problème d'autorisation");
        return "errors";
    }


    /*
     * @GetMapping("/jwt")
     *
     * @ResponseBody public Map<String,String> map(HttpServletRequest request){
     * KeycloakAuthenticationToken token =(KeycloakAuthenticationToken)
     * request.getUserPrincipal(); KeycloakPrincipal
     * principal=(KeycloakPrincipal)token.getPrincipal(); KeycloakSecurityContext
     * keycloakSecurityContext=principal.getKeycloakSecurityContext();
     * Map<String,String> map = new HashMap<>(); map.put("access_token",
     * keycloakSecurityContext.getTokenString());
     * return map;
     * }
     */
}

class Bill {
    private Long id;
    private Date billDate;
    private Long customerId;
    private List<ProductItem> productItems;
    private Customer customer;
    public Bill() {
        super();
    }
    public Bill(Long id, Date billDate, Long customerId, List<ProductItem> productItems, Customer customer) {
        this.id = id;
        this.billDate = billDate;
        this.customerId = customerId;
        this.productItems = productItems;
        this.customer = customer;
    }
    public Long getId() {
        return id;
    }
    public Date getBillDate() {
        return billDate;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public List<ProductItem> getProductItems() {
        return productItems;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billDate=" + billDate +
                ", customerId=" + customerId +
                ", productItems=" + productItems +
                ", customer=" + customer +
                '}';
    }
}
class  ProductItem{
    private Long id;
    private Long productID;
    private double price;
    private double quantity;
    private Bill bill;
    private Product product;
    public ProductItem() {
        super();
    }
    public ProductItem(Long id, Long productID, double price, double quantity, Bill bill, Product product) {
        this.id = id;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.bill = bill;
        this.product = product;
    }
    public Long getId() {
        return id;
    }
    public Long getProductID() {
        return productID;
    }
    public double getPrice() {
        return price;
    }
    public double getQuantity() {
        return quantity;
    }
    public Bill getBill() {
        return bill;
    }
    public Product getProduct() {
        return product;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setProductID(Long productID) {
        this.productID = productID;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", productID=" + productID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", bill=" + bill +
                ", product=" + product +
                '}';
    }
}
class Customer{
    private Long id;
    private String name;
    private String email;
      public Customer() {
        super();}
    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


class Supplier{
    private Long id;
    private String name;
    private String email;

    public Supplier() {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
public  class Product{
    private Long id;
    private String name;
    private double price;

    public Long getId() {
        return id;
    }

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
public Product(){
        super();
}
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

}
