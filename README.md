### Agradecimentos

Agradeço imensamente pela oportunidade de participar do teste. Infelizmente, não consegui completá-lo no prazo de três dias, pois realizá-lo junto da faculdade e trabalho impossibilita a realização dele durante os dias úteis. Peço desculpas por qualquer inconveniente e espero poder demonstrar meu potencial em futuras oportunidades, apesar do projeto falho.

# SamsungFakeStore

## Visão Geral
SamsungFakeStore é uma aplicação Java que simula uma loja virtual da Samsung. Utiliza PrimeFaces no frontend e Spring Boot no backend.

## Tecnologias
- **Frontend**: PrimeFaces (porta 4200, executa no Wildfly)
- **Backend**: Spring Boot (porta 3000)

## Configuração e Execução

### Frontend
- Implantado no Wildfly.
- Execute `make` para compilar e iniciar automaticamente todo o front na porta 4200.

### Backend
- Execute os mocks SQL localizados em `resources/` para configurar os dados iniciais.
- Inicie a aplicação Spring Boot na porta 3000.

## Uso
- Frontend: `http://localhost:4200`
- APIs do Backend: `http://localhost:3000`
