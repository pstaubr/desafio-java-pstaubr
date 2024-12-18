package cl.desafio.java.pstaubr.entity;


import cl.desafio.java.pstaubr.dto.UserPhonesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * @author Pablo Staub R
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class PhonesEntity extends BaseEntity<UserPhonesDto, PhonesEntity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "phones")
    @JsonIgnore
    private Set<UserEntity> userEntities = new HashSet<>();

    @Override
    public UserPhonesDto normalizeObj(PhonesEntity entity) {

        UserPhonesDto obj = new UserPhonesDto();

        obj.setCitycode(entity.getCityCode());
        obj.setContrycode(entity.getCountryCode());
        obj.setNumber(entity.getNumber());
        return obj;
    }

    @Override
    public List<UserPhonesDto> normalizeObjList(List<PhonesEntity> entityList) {

        List<UserPhonesDto> result = new ArrayList<UserPhonesDto>();

        for (PhonesEntity user : entityList) {

            result.add(normalizeObj(user));

        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhonesEntity other = (PhonesEntity) obj;
        return Objects.equals(cityCode, other.cityCode) && Objects.equals(countryCode, other.countryCode)
                && Objects.equals(id, other.id) && Objects.equals(number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCode, countryCode, id, number);
    }

    public PhonesEntity(String number, String cityCode, String countryCode) {
        super();
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

}
