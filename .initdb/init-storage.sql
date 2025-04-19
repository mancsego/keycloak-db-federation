CREATE TABLE users (
    userId INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (firstName, lastName)
VALUES
('Istvan', 'Abraham'),
('Jozsef', 'Sebestyen'),
('Indrit', 'Rexhepi'),
('Sina', 'Fischer');