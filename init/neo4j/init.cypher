// =====================
// Usuários
// =====================
CREATE (u1:User {id: 1, firstName: 'Daniel', lastName: 'Abella', email: 'danielabella@example.com'});
CREATE (u2:User {id: 2, firstName: 'Maria', lastName: 'Silva', email: 'mariasilva@example.com'});
CREATE (u3:User {id: 3, firstName: 'João', lastName: 'Gomes', email: 'joaogomes@example.com'});
CREATE (u4:User {id: 4, firstName: 'Ana', lastName: 'Costa', email: 'anacosta@example.com'});
CREATE (u5:User {id: 5, firstName: 'Lucas', lastName: 'Pereira', email: 'lucaspereira@example.com'});
CREATE (u6:User {id: 6, firstName: 'Fernanda', lastName: 'Santos', email: 'fernandasantos@example.com'});
CREATE (u7:User {id: 7, firstName: 'Rafael', lastName: 'Oliveira', email: 'rafaeloliveira@example.com'});
CREATE (u8:User {id: 8, firstName: 'Camila', lastName: 'Almeida', email: 'camilaalmeida@example.com'});
CREATE (u9:User {id: 9, firstName: 'Gustavo', lastName: 'Martins', email: 'gustavomartins@example.com'});
CREATE (u10:User {id: 10, firstName: 'Patrícia', lastName: 'Rodrigues', email: 'patriciarodrigues@example.com'});

// =====================
// Relacionamentos - Conexões
// =====================

// Conexões mútuas (amizades/conhecidos)
MATCH (u1:User {id: 1}), (u2:User {id: 2})
CREATE (u1)-[:CONNECTED_TO]->(u2),
       (u2)-[:CONNECTED_TO]->(u1);

MATCH (u1:User {id: 1}), (u3:User {id: 3})
CREATE (u1)-[:CONNECTED_TO]->(u3),
       (u3)-[:CONNECTED_TO]->(u1);

MATCH (u4:User {id: 4}), (u5:User {id: 5})
CREATE (u4)-[:CONNECTED_TO]->(u5),
       (u5)-[:CONNECTED_TO]->(u4);

MATCH (u6:User {id: 6}), (u7:User {id: 7})
CREATE (u6)-[:CONNECTED_TO]->(u7),
       (u7)-[:CONNECTED_TO]->(u6);

MATCH (u8:User {id: 8}), (u9:User {id: 9})
CREATE (u8)-[:CONNECTED_TO]->(u9),
       (u9)-[:CONNECTED_TO]->(u8);

MATCH (u2:User {id: 2}), (u5:User {id: 5})
CREATE (u2)-[:CONNECTED_TO]->(u5);

MATCH (u5:User {id: 5}), (u6:User {id: 6})
CREATE (u5)-[:CONNECTED_TO]->(u6);

// =====================
// Relacionamentos - Seguidores
// =====================

// Alguns seguem os outros (direcional, não necessariamente mútuo)
MATCH (u2:User {id: 2}), (u1:User {id: 1})
CREATE (u2)-[:FOLLOWS]->(u1);

MATCH (u3:User {id: 3}), (u1:User {id: 1})
CREATE (u3)-[:FOLLOWS]->(u1);

MATCH (u5:User {id: 5}), (u2:User {id: 2})
CREATE (u5)-[:FOLLOWS]->(u2);

MATCH (u6:User {id: 6}), (u3:User {id: 3})
CREATE (u6)-[:FOLLOWS]->(u3);

MATCH (u7:User {id: 7}), (u4:User {id: 4})
CREATE (u7)-[:FOLLOWS]->(u4);

MATCH (u9:User {id: 9}), (u8:User {id: 8})
CREATE (u9)-[:FOLLOWS]->(u8);

MATCH (u10:User {id: 10}), (u1:User {id: 1})
CREATE (u10)-[:FOLLOWS]->(u1);