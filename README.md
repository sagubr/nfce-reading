# Consulta NFC-e

Este repositório contém o código-fonte do microserviço Consulta NFC-e. 

O microserviço é responsável por receber uma requisição `POST` via API no endpoint `http://localhost:8083/inspector/process-url` e realizar uma consulta no site da Secretaria da Fazenda de Minas Gerais (https://portalsped.fazenda.mg.gov.br/portalnfce/sistema/qrcode.xhtml?p=). A aplicação recebe a URL originada do QRCode da Nota Fiscal do Consumidor Eletrônica. 

A partir do momento que é enviado a URL, o microserviço faz o acesso ao site e obtém os dados do HTML. Desse modo, ele realiza um parser no HTML utilizando a biblioteca JSoup. O parser foi contruído com base na formatação da site de Minas Gerais e pode não ser reutilizável para sites de outros de estados.

Após finalizado o processo de parser, os dados são persistidos no Banco de Dados de forma ordenada, separando o Fornecedor, o Produto e a Nota Fiscal.

**Cabe ressaltar que não foi encontrado uma API da Secretaria da Fazenda que disponibilizasse o consumo dos dados da NFc-e, por este motivo, foi utilizado a biblioteca JSoup.**


## Stack utilizada

**Back-end:** Java e Micronaut

**Banco de Dados:** Postgres


## Trabalhos Futuros

- Desenvolvimento de um aplicativo mobile responsável pela leitura do QRCode na NFc-e
- Desenvolvimento de um painel analytics para consumir o banco de dados e construir insights sobre o consumo de produtos, a influência da sazonalidade na variação dos preços, entre outros possíveis relatórios que poderão ser extraídos.
- Desenvolvimento da autenticação de usuários, a fim de tornar o app público e deixar os dados individualizados entre os usuários.
