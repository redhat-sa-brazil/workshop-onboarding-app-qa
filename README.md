# workshop-onboarding-app-qa

## Ver todas as perguntas

Para visualizar as perguntas, basta enviar um `GET` no contexto `http://localhost:8080/rest/question`

A resposta será conforme exemplo abaixo:

```
{
    "_embedded": {
        "question": [
            {
                "id": 1,
                "description": "Qual sua cor favorita?",
                "options": [
                    {
                        "id": 2,
                        "description": "Vermelho"
                    },
                    {
                        "id": 3,
                        "description": "Azul"
                    },
                    {
                        "id": 4,
                        "description": "Amarelo"
                    }
                ],
                "enabled": true,
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/rest/question/1"
                    },
                    "question": {
                        "href": "http://localhost:8080/rest/question/1"
                    }
                }
            },
            {
                "id": 5,
                "description": "Você conhece docker?",
                "options": [
                    {
                        "id": 6,
                        "description": "Sim"
                    },
                    {
                        "id": 7,
                        "description": "Não"
                    }
                ],
                "enabled": false,
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/rest/question/5"
                    },
                    "question": {
                        "href": "http://localhost:8080/rest/question/5"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/rest/question{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/rest/profile/question"
        }
    },
    "page": {
        "size": 20,
        "totalElements": 2,
        "totalPages": 1,
        "number": 0
    }
}
```

## Criar pergunta

Para criar uma nova pergunta, envie um `POST` para o contexto `http://localhost:8080/rest/question` contendo o seguinte conteúdo no body:

```
{
	"description": "Qual a sua idade?",
	"options": [
		{
			"description": "opcao 1"
		},
		{
			"description": "opcao 2"
		}
	]
}
```

## Atualizar pergunta

Para atualizar uma pergunta, basta enviar um `PUT` ou um `PATCH` para o contexto `http://localhost:8080/rest/question/<id da pergunta>` com o body conforme exemplo abaixo:

```
{
	"description": "Qual a sua idade?",
	"options": [
		{
			"description": "opcao 1"
		},
		{
			"description": "opcao 3"
		}
	]
}
```

## Deletar pergunta

Para deletar uma pergunta, basta executar um `DELETE` no contexto `http://localhost:8080/rest/question/<id da pergunta>`

## Responder

Para responder uma questão, envie uma requisição para `http://localhost:8080/rest/answer` com o body da seguinte forma:

```
{
   	"comment": "Nunca ouvi falar",
   	"email": "gluszczy@redhat.com",
    	"option": {
   		"id": 2
    	}
}
```

## Ativar uma questão

Para ativar uma questão, envie uma requisição POST `http://localhost:8080/rest/question/<id>/activate`. Não há necessidade de mandar conteúdo no body.

## Desativar uma questão

Para desativar uma questão, envie uma requisição POST `http://localhost:8080/rest/question/<id>/desactivate`. Não há necessidade de mandar conteúdo no body.

## Instructor WebSocket

Assim que uma pergunta é respondida por um estudante, a mesma é encaminhada para o canal do instrutor.

```
function connect() {
	var socket = new SockJS('http://localhost:8080/socket');
     stompClient = Stomp.over(socket);  
     stompClient.connect({}, function(frame) {
     	stompClient.subscribe('/instructor', function(messageOutput) {
          	console.log("Recebi msg do canal instructor " + messageOutput);
          });
     });
}
```

## Student WebSocket

Assim que uma questão é ativada/desativada, a questao será enviada pelo canal `/student` conforme código abaixo:

```
		function connect() {
                var socket = new SockJS('http://localhost:8080/socket');
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                    stompClient.subscribe('/student', function(messageOutput) {
                        console.log("Recebi msg do canal student " + messageOutput);
                    });
                });
            }
```
