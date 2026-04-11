TRUNCATE TABLE tb_parking_spot CASCADE;
TRUNCATE TABLE tb_car CASCADE;

-- 1. Inserir Carros Primeiro (por que devido à chave estrangeira)
INSERT INTO tb_car (id, plate_car, model_car, color_car) VALUES
                                                             (gen_random_uuid(), 'ZGG-4366', 'Civic', 'Cinza'),
                                                             (gen_random_uuid(), 'KCF-1679', 'Corolla', 'Preto'),
                                                             (gen_random_uuid(), 'TQD-7097', 'HB20', 'Branco'),
                                                             (gen_random_uuid(), 'JES-9096', 'Onix', 'Prata'),
                                                             (gen_random_uuid(), 'HZT-7130', 'Golf', 'Vermelho'),
                                                             (gen_random_uuid(), 'YMM-6194', 'Compass', 'Azul'),
                                                             (gen_random_uuid(), 'OGT-2281', 'Argo', 'Branco'),
                                                             (gen_random_uuid(), 'VHK-4197', 'Renegade', 'Verde'),
                                                             (gen_random_uuid(), 'RQN-3902', 'Sandero', 'Preto'),
                                                             (gen_random_uuid(), 'NDG-8451', 'Hilux', 'Prata');

-- 2. Inserir as Vagas de Estacionamento
INSERT INTO tb_parking_spot (id, parking_spot_number, responsible_name, apartment, date_register, status, car_id) VALUES
                                                                                                                      (1, '201-A', 'Carlos Souza', 'Ap 101', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'ZGG-4366')),
                                                                                                                      (2, '202-A', 'Ana Pereira', 'Ap 102', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'KCF-1679')),
                                                                                                                      (3, '203-B', 'Marcos Oliveira', 'Ap 201', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'TQD-7097')),
                                                                                                                      (4, '204-B', 'Fernanda Lima', 'Ap 202', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'JES-9096')),
                                                                                                                      (5, '205-C', 'Rafael Costa', 'Ap 301', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'HZT-7130')),
                                                                                                                      (6, '206-C', 'Juliana Mendes', 'Ap 302', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'YMM-6194')),
                                                                                                                      (7, '207-D', 'Bruno Alves', 'Ap 401', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'OGT-2281')),
                                                                                                                      (8, '208-D', 'Patricia Rocha', 'Ap 402', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'VHK-4197')),
                                                                                                                      (9, '209-E', 'Leonardo Santos', 'Ap 501', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'RQN-3902')),
                                                                                                                      (10, '210-E', 'Camila Ferreira', 'Ap 502', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'NDG-8451'));