CREATE TABLE users (
    user_id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE(email)
);

INSERT INTO users (email, first_name, last_name)
VALUES
('test1@test.com', 'Istvan', 'Abraham'),
('test2@test.com', 'Jozsef', 'Sebestyen'),
('test3@test.com', 'Indrit', 'Rexhepi'),
('test4@test.com', 'Sina', 'Fischer');