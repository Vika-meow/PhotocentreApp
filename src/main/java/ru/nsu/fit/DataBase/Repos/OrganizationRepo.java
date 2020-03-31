package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Organization;

public interface OrganizationRepo extends CrudRepository<Organization, String> {
}
