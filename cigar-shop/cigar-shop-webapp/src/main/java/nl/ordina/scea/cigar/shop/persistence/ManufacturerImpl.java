package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.Manufacturer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Manufacturer")
@Table(name = "MANUFACTURER")
public class ManufacturerImpl implements Manufacturer {
    @Id
    private Integer id;
    private String name;

    public ManufacturerImpl(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    protected ManufacturerImpl() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManufacturerImpl that = (ManufacturerImpl) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
