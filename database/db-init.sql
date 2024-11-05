CREATE TABLE IF NOT EXISTS user_account (
    id SERIAL PRIMARY KEY,  -- Khóa chính với cột tự động tăng
    username VARCHAR(50),  -- Cột username
    email VARCHAR(100) NOT NULL UNIQUE,  -- Cột email, không được null và phải là duy nhất
    password VARCHAR(255) NOT NULL,  -- Cột password, không được null
    access_token VARCHAR(255),  -- Cột access_token để lưu JWT hoặc token truy cập
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Cột thời gian tạo, mặc định là thời gian hiện tại
);