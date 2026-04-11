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
INSERT INTO tb_parking_spot (id, block, parking_spot_number, responsible_name, date_register, status, car_id) VALUES
                                                                                                                    (1, 'A', '01', 'Carlos Souza', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'ZGG-4366')),
                                                                                                                    (2, 'A', '02', 'Ana Pereira', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'KCF-1679')),
                                                                                                                    (3, 'A', '03', 'Marcos Oliveira', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'TQD-7097')),
                                                                                                                    (4, 'A', '04', 'Fernanda Lima', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'JES-9096')),
                                                                                                                    (5, 'A', '05', 'Rafael Costa', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'HZT-7130')),
                                                                                                                    (6, 'A', '06', 'Juliana Mendes', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'YMM-6194')),
                                                                                                                    (7, 'A', '07', 'Bruno Alves', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'OGT-2281')),
                                                                                                                    (8, 'A', '08', 'Patricia Rocha', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'VHK-4197')),
                                                                                                                    (9, 'A', '09', 'Leonardo Santos', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'RQN-3902')),
                                                                                                                    (10, 'A', '10', 'Camila Ferreira', NOW(), 'RESERVED', (SELECT id FROM tb_car WHERE plate_car = 'NDG-8451'));
