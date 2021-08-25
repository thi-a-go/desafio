# Índice
1. [Documentação de negócio](#documentao-de-negcio)
2. [Tecnologias](#tecnologias)
3. [Executar na IDE](#executar-na-ide)
4. [Executar no browser](#executar-no-browser)
5. [Executar no Postman](#executar-no-postman)
6. [Executar no Docker](#executar-no-docker)


## Documentação de negócio 

#1. Introdução
Este teste visa avaliar o nível que o programador possui no desenvolvimento de aplicações backend Rest. 
O tempo para executar pode variar, porém esperamos que o mesmo seja concluído em no máximo uma (01) semana. 
Procure atender os requisitos na sua totalidade.

#2. Requisitos

a. Dado um post com o seguinte body para o seu endpoint:
header Content-Type: text/html; charset=utf-8
body 44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN

b. Desmembrar a entrada, armazenar em uma entidade e retornar um json com a seguinte estrutura:
{"logic": 44332211,"serial": "123","model": "PWWIN","sam": 0,"ptid": "F04A2E4088B","plat": 4,"version": "8.00b3","mxr": 0,"mxf": 16777216,"VERFM":”PWWIN"}

c. Para que a entrada seja considerada válida, a mesma deve seguir o seguinte schema:
```
{
	"title": "Terminal",
	"type": "object",
	"properties": {
		"logic": {
			"type": "integer"
		},
		"serial": {
			"type": "string"
		},
		"model": {
			"type": "string"
		},
		"sam": {
			"type": "integer",
			"minimum": 0
		},
		"ptid": {
			"type": "string"
		},
		"plat": {
			"type": "integer"
		},
		"version": {
			"type": "string"
		},
		"mxr": {
			"type": "integer"
		},
		"mxf": {
			"type": "integer"
		},
		"VERFM": {
			"type": "string"
		}
	},
	"required": ["logic", "serial", "model", "version"]
}
```

d. O Endpoint criado deve aceitar consultas, porém não deve aceitar o verbo DELETE.

e. O Endpoint não deve aceitar o verbo POST em JSON.

f. O Endpoint deve aceitar consultas de um registro seguindo o seguinte padrão,
	[versão da aplicação]/[nome da entidade]/[atributo 'logic']

g. As alterações aos objetos armazenados devem ser feitas via JSON usando o padrão de
	url [versão da aplicação]/[nome da entidade]/[atributo 'logic']


## Tecnologias
* Java
   * JavaEE: 11
   * Runtime: TOMCAT
   
* Container: TOMCAT

* Maven

* Spring boot 2

* H2 (Base de dados)


## Executar na IDE
	
	*OBS.: O Projeto foi estruturado sobre a ide: eclipse! 
	
	Não deveriamos ter qualquer inflexibilidade sobre a questão de sua utilização, em outros ambientes da preferencias do programador.
	Aqui o maven, desde de que não tenha qualquer proxy para internet. Deveria buscar as dependencias sem sofrimento.
	
	
	** Atenção, caso tenha alguma questão de preferencia sobre o local de onde fica o .M2 na maquina local, não esquecer antes de importar o projeto: 
	1. apontar local do .m2;
	2. selecionar arquivo: setting.xml, "caso use proxy e outras configurações".

Partindo do pressuposto, que estará utilizando o eclipse, seguir:

1. Download para pasta de trabalho.

2. acessar menu File > Import... > Maven > Existing Maven Project > Next > Browser > <escolher pasta com projeto > Finish 

3. Executar: clicar com o botão direito sobre o projeto e pressionar a opção Run As > Java Application

Pronto!


## Executar no browser

	OBS: Se atentar a versão do projeto que está descrita no pom.xml do projeto, sem essa versão não é possivel acessar os end-points

Aqui o objetivo é verificar se o projeto está no ar. Digitar no browser: 

*Listar todos: http://127.0.0.1:8080/0.0.1-SNAPSHOT/products

*Buscar item: http://127.0.0.1:8080/0.0.1-SNAPSHOT/products/">codigo-logic<"

## Executar no Postman
	
	OBS.: Para teste total das funcionalidade, sem o uso promp de commando com o curl, criei end-points no postman, basta importar.
	
- Acessar pasta do projeto: \crud\src\main\resources\postman e importar para a ferramenta: postman a coleção: conductor-terminal.postman_collection.json.

- link de auxilio, caso queira usar o curl: https://www.alura.com.br/artigos/curl-como-usar

## Executar no Docker

Para o caso de deesejar executar no docker local, seguir os passos:

1. Criar imagem, obs: nome da imagem sugerida: >conductor/crud<
	-comando: 
	
	docker build -t conductor/crud .
	
2. Verificar lista de imagens criadas:
	-comando: 
	
	docker image list

3. Executar imagem com configurações de variaveis de ambiente
	-comando: 

	 docker run -p 8080:8080 -e SPRING_PROFILE_ACTIVE='prod' -e CONDUCTOR_DATA_DRIVE='org.h2.Driver' -e CONDUCTOR_DATA_URL='jdbc:h2:mem:conductor' -e CONDUCTOR_DATA_USERNAME='sa' -e CONDUCTOR_DATA_PASSWORD='12345' conductor/crud .