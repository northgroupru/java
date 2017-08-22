package ru.proitr.example.domain.auth;

import ru.proitr.example.domain.IdGeneratedEntity;
import ru.proitr.example.domain.MainConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ilnar on 22.08.17.
 */
@Entity
@Table(schema = MainConstants.AUTH_SCHEMA_NAME)
public class Users extends IdGeneratedEntity
{

}
