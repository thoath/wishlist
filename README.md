# WishList (Lista de Desejos)

### Referência
Esta aplicação tem como objetivo disponibilizar as seguintes funcionalidades:

* Adicionar um produto na Wishlist do cliente.
* Remover um produto da Wishlist do cliente.
* Consultar todos os produtos da Wishlist do cliente.
* Consultar se um determinado produto está na Wishlist do cliente.

####Pré requisitos para executar o serviço
* Maven instalado.
* IDE de sua escolha.
* O userId precisa estar no formato de objectId, segue link para gerar objectIds válidos: https://codepen.io/joshuajameshunt/pen/xWmNEr

####Inciando o seviço
* Clonar repositório em sua máquina.
* Na pasta raiz executar o segunte comando <code>mvn clean install</code>
* Configurar sua IDE para executar o projeto através da application 
ou na pasta target executar o comando <code>java -jar wishlist-0.0.1-SNAPSHOT.jar
</code>

####Caminho para documentação da api
http://localhost:8081/swagger-ui.html#

####Caminho para os testes integrados com api client
pasta_raiz/integration/labs_request.json (ler o arquivo readme)