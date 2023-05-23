package beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private int id;
    private String name;
    private String url;
    private String email;
    private String password;
    private List<Coupon> coupons;


}
