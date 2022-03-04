* As classes de domínio (por exemplo, CozinhaService) não devem ter contato com protocolo HTTP, não pode ter contato com Web.
* Isso é para outra camada da aplicação.
* Logo, exceções que devem retornar, por exemplo, HttpStatus.NOT_FOUND, é melhor estar no controller. 
* Neste exemplo, é usado exceção do tipo ResponseStatusException no controller através de um try/catch.

### Estendendo ResponseStatusException
* Extendemos a classe ResponseStatusException e crio 1 construtor a partir da superclasse que recebe como parâmetro 'status' e 'mensagem/reason', e assim é possível customizar o status e a reason. 
* Existe uma sobrecarga de construtores, que recebe como padrão HttpStatus.NOT_FOUND, permitindo ter flexibilidade de informar outro também.
* Na classe de 'service' não se muda nada. Mas, no controller não é necessário mais o catch, pois o EntidadeNaoEncontradaException é um ResponseStatusException(pois herda), então não é preciso tratar mais.
* VANTAGEM: é que quem lança a exception consegue customizar o código Http. Se fosse utilizado o @ResponseStatus, seria necessário criar outra classe de Exception. Por exemplo para BadRequest.
* DESVANTAGEM: customização dentro de uma classe de negócio.


### Simplificando o código com o uso de @ResponseStatus em exceptions
* Simplificamos ao substituir onde tem Optional/onde precisamos dar um findById.
* Colocamos em uma classe Service o código repetido de "findBy ou Falha"
>         return cozinhaRepository.findById(id)
>              .orElseThrow(() -> new EntidadeNaoEncontradaException("entidade não encontrada"));
* Na refatoração do método de Update, não é necessário mais verificar se cozinhaAtual é nula ou não com o if/isPresent(), porque agora nunca será nulo. Se não houver uma cozinha, será lançada a exception.
* Foram criadas duas constantes para mensagens na CozinhaService