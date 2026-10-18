# Checklist de entrega e roteiro do vídeo (não precisa ir pro GitHub)

## Parte 1 (Teams, chat direto com o professor, até 18/10 23h59)
- [ ] Projeto rodando e deploy feito no Render (README seção 8)
- [ ] Prints em docs/img/ com os nomes usados no README (01-index.png ... 14-dashboard.png)
- [ ] Print do Spring Initializr (.png/.jpg) com as dependências da seção 2
- [ ] integrantes.txt preenchido (nomes, RMs, turma, link do GitHub, link do deploy, "Render", IDE IntelliJ)
- [ ] Enviar: .txt + print do Initializr

## Parte 2 (Teams, chat direto)
- [ ] .zip da pasta inteira do projeto (sem as pastas target/ e .idea/)
- [ ] .txt (mesmo integrantes.txt, com link do vídeo)
- [ ] Vídeo de 5 a 7 min (YouTube não listado ou .mp4)

## Roteiro do vídeo (~6 min)
1. (0:00–0:40) Apresentação: grupo, arquitetura (diagrama do README), dois projetos + Docker
2. (0:40–1:30) `docker compose up -d`, Dashboard do RabbitMQ mostrando as 3 exchanges, 3 queues e bindings
3. (1:30–2:30) Código: RabbitConfig, CompraService/PagamentoProducer, PagamentoRequestListener (worker), PagamentoResponseListener, EmailService
4. (2:30–3:40) App: login como cliente → carrinho → finalizar → pedido APROVADO → e-mail chegando na caixa de entrada
5. (3:40–4:30) Postman: POST /api/compras (202) → GET /api/compras/{id} (APROVADO) → compra acima de R$ 500 (RECUSADO)
6. (4:30–5:20) RabbitMQ Monitor: parar o worker, fazer compra, mostrar mensagem "Ready" na fila, subir o worker e ver o consumo; Publish message manual
7. (5:20–6:30) Login ADMIN: dashboard com gráficos, todos os pedidos, tela TB_EMAIL; mostrar tb_email no banco (DBeaver/pgAdmin/IntelliJ Database)
