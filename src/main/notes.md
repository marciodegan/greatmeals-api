* As classes de domínio (por exemplo, CozinhaService) não devem ter contato com protocolo HTTP, não pode ter contato com Web.
* Isso é para outra camada da aplicação.
* Logo, exceções que devem retornar, por exemplo, HttpStatus.NOT_FOUND, é melhor estar no controller. 
* Neste exemplo, é usado exceção do tipo ResponseStatusException no controller através de um try/catch.