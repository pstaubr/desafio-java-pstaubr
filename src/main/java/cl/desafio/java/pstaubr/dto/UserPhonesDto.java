package cl.desafio.java.pstaubr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Pablo Staub R
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPhonesDto implements Serializable {

    private static final long serialVersionUID = 3962055822077059433L;
    private String number;

    @NotEmpty
    private String citycode;

    @NotEmpty
    private String contrycode;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserPhonesDto other = (UserPhonesDto) obj;
        return Objects.equals(citycode, other.citycode) && Objects.equals(contrycode, other.contrycode)
                && Objects.equals(number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citycode, contrycode, number);
    }

}
