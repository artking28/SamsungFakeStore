## Agradecimentos

Agradeço imensamente à Samsung pela oportunidade de participar do teste.
Infelizmente, não consegui completá-lo no prazo de três dias, pois conciliá-lo com faculdade e trabalho
tornou inviável a execução durante os dias úteis.
Peço desculpas por qualquer inconveniente e espero poder demonstrar meu potencial em futuras oportunidades,
mesmo com este projeto incompleto.

## Visão Geral
SamsungFakeStore é uma aplicação Java que simula uma loja virtual da Samsung, utilizando PrimeFaces no frontend e Spring Boot no backend.

## Tecnologias
- **Frontend**: PrimeFaces (porta 4200, executa no Wildfly)
- **Backend**: Spring Boot (porta 3000)

## Configuração e Execução

### Frontend
1. Implantado no Wildfly.
2. Entre na pasta `SamsungFakeStoreAPI` e rode `mvn clean install` para instalar o backend localmente.
   Ele será utilizado pelo frontend.
3. Entre na pasta `SamsungFakeStoreFront` e execute `make` para compilar e iniciar automaticamente
   o frontend na porta 4200.

### Backend
1. Execute os scripts SQL localizados em `SamsungFakeStoreAPI/src/main/resources/mocks.sql` para configurar os dados iniciais.
2. Inicie a aplicação Spring Boot na porta 3000.

## Uso
- Frontend: `http://localhost:4200`
- APIs do Backend: `http://localhost:3000`
