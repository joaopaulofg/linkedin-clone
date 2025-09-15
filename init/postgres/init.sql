-- ======================================================
-- Tabelas principais
-- ======================================================

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    head_line VARCHAR(255)
);

-- Tabela de empresas
CREATE TABLE IF NOT EXISTS companies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    industry VARCHAR(100)
);

-- Tabela de vagas (jobs)
CREATE TABLE IF NOT EXISTS jobs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    company_id BIGINT REFERENCES companies(id)
);

-- ======================================================
-- Inserir dados iniciais
-- ======================================================

-- =====================
-- Usuários
-- =====================

INSERT INTO users (first_name, last_name, email, password, head_line)
VALUES 
('Daniel', 'Abella', 'danielabella@example.com', 'senha123', 'Gerente de Projetos & Professor'),
('Maria', 'Silva', 'mariasilva@example.com', 'senha123', 'Desenvolvedora Backend'),
('João', 'Gomes', 'joaogomes@example.com', 'senha123', 'Analista de Sistemas'),
('Ana', 'Costa', 'anacosta@example.com', 'senha123', 'Engenheira de Software'),
('Lucas', 'Pereira', 'lucaspereira@example.com', 'senha123', 'Arquiteto de Software'),
('Fernanda', 'Santos', 'fernandasantos@example.com', 'senha123', 'Product Manager'),
('Rafael', 'Oliveira', 'rafaeloliveira@example.com', 'senha123', 'Desenvolvedor Frontend'),
('Camila', 'Almeida', 'camilaalmeida@example.com', 'senha123', 'UX Designer'),
('Gustavo', 'Martins', 'gustavomartins@example.com', 'senha123', 'Scrum Master'),
('Patrícia', 'Rodrigues', 'patriciarodrigues@example.com', 'senha123', 'Especialista em QA');

-- =====================
-- Empresas
-- =====================
INSERT INTO companies (name, industry, description)
VALUES
('Tech Solutions', 'Tecnologia', 'Empresa especializada em soluções de software corporativo'),
('InnovateX', 'Consultoria', 'Consultoria em inovação e processos empresariais'),
('DataCorp', 'Dados e Analytics', 'Serviços avançados de análise de dados e BI'),
('WebFactory', 'Desenvolvimento Web', 'Agência focada em desenvolvimento de sites e apps'),
('CloudNine', 'Cloud Computing', 'Especialista em soluções de cloud e infraestrutura');

-- =====================
-- Vagas
-- =====================
INSERT INTO jobs (title, description, company_id)
VALUES
('Desenvolvedor Java', 'Vaga para desenvolvedor Java com Spring', 1),
('Analista de Dados', 'Vaga para analista de dados', 2),
('Engenheiro de DevOps', 'Vaga para engenheiro de DevOps com AWS', 5),
('Designer UX/UI', 'Vaga para designer UX/UI com experiência em Figma', 4),
('Product Owner', 'Vaga para Product Owner ágil', 2),
('Desenvolvedor Frontend', 'Vaga para desenvolvedor React', 4),
('Engenheiro de Dados', 'Vaga para engenheiro de dados com SQL e Python', 3),
('Scrum Master', 'Vaga para Scrum Master certificado', 2),
('Cloud Architect', 'Vaga para arquiteto de soluções em cloud', 5),
('QA Engineer', 'Vaga para especialista em testes automatizados', 1);