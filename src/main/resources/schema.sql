/* Table Employees */
CREATE TABLE icei.employees(
    employee_id uuid PRIMARY KEY default uuid_generate_v4() UNIQUE NOT NULL,
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