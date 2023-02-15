package cucumber.models.web;

import lombok.Data;

@Data
public class SearchVendor {

    String fullName;
    String email;
    String vendorCompany;
    String brand;
    String region;
    String addressCity;
    String addressState;
    String tags;
    String approved;
    String shopify;
}
