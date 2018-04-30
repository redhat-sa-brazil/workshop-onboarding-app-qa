# workshop-onboarding-app-qa

## Ver todas as perguntas

Para visualizar as perguntas, basta enviar um `GET` no contexto `http://localhost:8080/rest/question`

A resposta será conforme exemplo abaixo:

```
{
    "_embedded": {
        "question": [
            {
                "description": "Qual sua cor favorita?",
                "options": [
                    {
                        "option": "Vermelho"
                    },
                    {
                        "option": "Azul"
                    },
                    {
                        "option": "Amarelo"
                    }
                ],
                "active": true,
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
                "description": "Você conhece docker?",
                "options": [
                    {
                        "option": "Sim"
                    },
                    {
                        "option": "Não"
                    }
                ],
                "active": false,
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
			"option": "opcao 1"
		},
		{
			"option": "opcao 2"
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
			"option": "opcao 1"
		},
		{
			"option": "opcao 3"
		}
	]
}
```

## Deletar pergunta

Para deletar uma pergunta, basta executar um `DELETE` no contexto `http://localhost:8080/rest/question/<id da pergunta>`