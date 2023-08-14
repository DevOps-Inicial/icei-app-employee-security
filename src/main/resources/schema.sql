/* Table Employees */
CREATE TABLE icei.employees(
    employee_id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL,
    address text NOT NULL,
    cell_phone text NOT NULL,
    enabled boolean NOT NULL,
    created_by_user varchar(20) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by_user varchar(20) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    number_of_modifications BIGINT NOT NULL
);
/*Tabla GUsers */
CREATE TABLE icei.users(
    user_id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    enabled boolean NOT NULL,
    created_by_user varchar(20) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_by_user varchar(20) NOT NULL,
    last_modified_date timestamp NOT NULL,
    number_of_modifications bigint NOT NULL
);

/* Tabla GRoles */
CREATE TABLE icei.roles(
    role_id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    name text NOT NULL,
    enabled boolean NOT NULL DEFAULT TRUE,
    created_by_user varchar(20) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_by_user varchar(20) NOT NULL,
    last_modified_date timestamp NOT NULL,
    number_of_modifications bigint NOT NULL
);

/* Tabla G_Users_Roles */
CREATE TABLE icei.user_roles(
    user_role_id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_id BIGINT references icei.users(user_id),
    role_id BIGINT references icei.roles(role_id)
);
