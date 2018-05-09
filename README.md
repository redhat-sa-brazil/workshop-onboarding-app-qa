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

Para responder uma questão, envie via WebSocket uma requisição da seguinte forma:

```
			var response = {
                	"comment": "Nunca ouvi falar",
                	"email": "gluszczy@redhat.com",
                	"option": {
                		"id": 2
                	}
                };
                
                stompClient.send("/app/student", {}, 
                  JSON.stringify(response)
                );
```

Para escutar por mensagens, basta subscrever nos canais conforme abaixo:

```
		function connect() {
                var socket = new SockJS('http://localhost:8080/socket');
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                    stompClient.subscribe('/answer/message', function(messageOutput) {
                        console.log("Recebi msg do canal student " + messageOutput);
                    });
                    
                    stompClient.subscribe('/question/message', function(messageOutput) {
                        console.log("Recebi msg do canal instructor " + messageOutput);
                    });
                });
            }
```