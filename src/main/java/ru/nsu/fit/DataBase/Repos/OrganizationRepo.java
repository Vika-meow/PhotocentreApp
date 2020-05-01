package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Organization;

import java.util.List;

public interface OrganizationRepo extends CrudRepository<Organization, String> {
    public Organization findByAddress(String id);
    public List<Organization> findByBranchOfficeAdressIsNull();
    public List<Organization> findByBranchOfficeAdressIsNotNull();
}
