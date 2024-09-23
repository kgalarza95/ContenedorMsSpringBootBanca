
CREATE USER 'APP_BANCO_GESTION'@'localhost' IDENTIFIED BY 'APP_BANCO_GESTION';

CREATE SCHEMA `spring_boot_banco_gestion` ;

GRANT ALL PRIVILEGES ON spring_boot_banco_gestion.* TO 'APP_BANCO_GESTION'@'localhost';
