-- Принудительно установим пароль MD5
ALTER USER postgres WITH PASSWORD '0419';

-- Таблица профилей
CREATE TABLE IF NOT EXISTS profile
(
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL
    );

-- Таблица питомцев
CREATE TABLE IF NOT EXISTS pet
(
    id_pet SERIAL PRIMARY KEY,
    pet VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    id_profile BIGINT NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (id_profile)
    REFERENCES profile(id)
    ON DELETE CASCADE
    );

